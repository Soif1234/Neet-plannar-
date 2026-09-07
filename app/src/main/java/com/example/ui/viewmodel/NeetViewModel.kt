package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AppDatabase
import com.example.data.model.MistakeEntryEntity
import com.example.data.model.PastPaperTopicEntity
import com.example.data.model.StudyBlockEntity
import com.example.data.repository.NeetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class PerformanceMetrics(
    val totalBlocks: Int = 0,
    val completedBlocks: Int = 0,
    val completionPercentage: Int = 0,
    val totalMcqsSolved: Int = 0,
    val targetMcqsTotal: Int = 0,
    val totalMistakes: Int = 0,
    val sillyErrors: Int = 0,
    val readingErrors: Int = 0,
    val memoryErrors: Int = 0,
    val conceptualErrors: Int = 0,
    val biologyCompleted: Int = 0,
    val biologyTotal: Int = 0,
    val chemistryCompleted: Int = 0,
    val chemistryTotal: Int = 0,
    val physicsCompleted: Int = 0,
    val physicsTotal: Int = 0,
    val auditCompleted: Int = 0,
    val auditTotal: Int = 0
)

class NeetViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NeetRepository

    init {
        val db = AppDatabase.getDatabase(application, viewModelScope)
        repository = NeetRepository(
            studyBlockDao = db.studyBlockDao(),
            mistakeEntryDao = db.mistakeEntryDao(),
            pastPaperDao = db.pastPaperDao()
        )

        // Ensure database is populated if empty
        viewModelScope.launch(Dispatchers.IO) {
            repository.ensureDataSeeded()
        }
    }

    // UI Filter States
    private val _selectedWeek = MutableStateFlow(1) // 1 to 6, 0 for all
    val selectedWeek: StateFlow<Int> = _selectedWeek.asStateFlow()

    private val _selectedDayFilter = MutableStateFlow<Int?>(null) // null = all days in week
    val selectedDayFilter: StateFlow<Int?> = _selectedDayFilter.asStateFlow()

    private val _mistakeSubjectFilter = MutableStateFlow("All")
    val mistakeSubjectFilter: StateFlow<String> = _mistakeSubjectFilter.asStateFlow()

    private val _mistakeCategoryFilter = MutableStateFlow("All")
    val mistakeCategoryFilter: StateFlow<String> = _mistakeCategoryFilter.asStateFlow()

    private val _currentPhase = MutableStateFlow("Recovery") // "Recovery" or "Mastery"
    val currentPhase: StateFlow<String> = _currentPhase.asStateFlow()

    // Database Flows
    val allStudyBlocks: StateFlow<List<StudyBlockEntity>> = repository.allBlocks
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allMistakes: StateFlow<List<MistakeEntryEntity>> = repository.allMistakes
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val pastPaperTopics: StateFlow<List<PastPaperTopicEntity>> = repository.allPastPaperTopics
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val weakFocusAreas: StateFlow<List<PastPaperTopicEntity>> = repository.weakFocusAreas
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Filtered Study Blocks
    val filteredStudyBlocks: StateFlow<List<StudyBlockEntity>> = combine(
        allStudyBlocks,
        _selectedWeek,
        _selectedDayFilter
    ) { blocks, week, dayFilter ->
        blocks.filter { block ->
            val matchWeek = if (week == 0) true else block.weekNumber == week
            val matchDay = if (dayFilter == null) true else block.dayNumber == dayFilter
            matchWeek && matchDay
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Filtered Mistakes
    val filteredMistakes: StateFlow<List<MistakeEntryEntity>> = combine(
        allMistakes,
        _mistakeSubjectFilter,
        _mistakeCategoryFilter
    ) { mistakes, subject, category ->
        mistakes.filter { entry ->
            val matchSubject = if (subject == "All") true else entry.subject.contains(subject, ignoreCase = true)
            val matchCategory = if (category == "All") true else entry.errorCategory.equals(category, ignoreCase = true)
            matchSubject && matchCategory
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Performance Metrics
    val performanceMetrics: StateFlow<PerformanceMetrics> = combine(
        allStudyBlocks,
        allMistakes
    ) { blocks, mistakes ->
        val total = blocks.size
        val completed = blocks.count { it.isCompleted }
        val pct = if (total > 0) (completed * 100) / total else 0

        val totalMcqs = blocks.sumOf { it.completedMcqs }
        val targetMcqs = blocks.sumOf { it.targetMcqs }

        val silly = mistakes.count { it.errorCategory.contains("Silly", ignoreCase = true) || it.errorCategory.contains("Calc", ignoreCase = true) }
        val reading = mistakes.count { it.errorCategory.contains("Reading", ignoreCase = true) }
        val memory = mistakes.count { it.errorCategory.contains("Memory", ignoreCase = true) }
        val conceptual = mistakes.count { it.errorCategory.contains("Conceptual", ignoreCase = true) }

        val bioBlocks = blocks.filter { it.subject.equals("Biology", ignoreCase = true) }
        val chemBlocks = blocks.filter { it.subject.equals("Chemistry", ignoreCase = true) }
        val physBlocks = blocks.filter { it.subject.equals("Physics", ignoreCase = true) }
        val auditBlocks = blocks.filter { it.subject.contains("Audit", ignoreCase = true) }

        PerformanceMetrics(
            totalBlocks = total,
            completedBlocks = completed,
            completionPercentage = pct,
            totalMcqsSolved = totalMcqs,
            targetMcqsTotal = targetMcqs,
            totalMistakes = mistakes.size,
            sillyErrors = silly,
            readingErrors = reading,
            memoryErrors = memory,
            conceptualErrors = conceptual,
            biologyCompleted = bioBlocks.count { it.isCompleted },
            biologyTotal = bioBlocks.size,
            chemistryCompleted = chemBlocks.count { it.isCompleted },
            chemistryTotal = chemBlocks.size,
            physicsCompleted = physBlocks.count { it.isCompleted },
            physicsTotal = physBlocks.size,
            auditCompleted = auditBlocks.count { it.isCompleted },
            auditTotal = auditBlocks.size
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PerformanceMetrics())

    // Intent Actions
    fun setWeek(week: Int) {
        _selectedWeek.value = week
        _selectedDayFilter.value = null
    }

    fun setDayFilter(day: Int?) {
        _selectedDayFilter.value = day
    }

    fun setMistakeSubjectFilter(subject: String) {
        _mistakeSubjectFilter.value = subject
    }

    fun setMistakeCategoryFilter(category: String) {
        _mistakeCategoryFilter.value = category
    }

    fun setPhase(phase: String) {
        _currentPhase.value = phase
    }

    fun toggleBlockCompletion(block: StudyBlockEntity) {
        viewModelScope.launch {
            val newCompleted = !block.isCompleted
            val completedMcqs = if (newCompleted && block.completedMcqs == 0) block.targetMcqs else if (!newCompleted) 0 else block.completedMcqs
            repository.setBlockCompletion(block.id, newCompleted, completedMcqs)
        }
    }

    fun markDayComplete(dayNumber: Int, completed: Boolean) {
        viewModelScope.launch {
            repository.setDayCompletion(dayNumber, completed)
        }
    }

    fun updateBlockNotesAndMcqs(block: StudyBlockEntity, mcqs: Int, notes: String) {
        viewModelScope.launch {
            repository.updateBlock(
                block.copy(
                    completedMcqs = mcqs,
                    studentNotes = notes,
                    isCompleted = mcqs >= block.targetMcqs && block.targetMcqs > 0
                )
            )
        }
    }

    fun addMistake(
        subject: String,
        chapter: String,
        questionSummary: String,
        errorCategory: String,
        whyReason: String,
        correctTakeaway: String
    ) {
        viewModelScope.launch {
            val mistake = MistakeEntryEntity(
                subject = subject,
                chapter = chapter,
                questionSummary = questionSummary,
                errorCategory = errorCategory,
                whyReason = whyReason,
                correctTakeaway = correctTakeaway
            )
            repository.insertMistake(mistake)
        }
    }

    fun toggleMistakeReviewed(mistake: MistakeEntryEntity) {
        viewModelScope.launch {
            repository.setMistakeReviewed(mistake.id, !mistake.isReviewed)
        }
    }

    fun deleteMistake(mistake: MistakeEntryEntity) {
        viewModelScope.launch {
            repository.deleteMistake(mistake)
        }
    }

    fun updateTopicAccuracy(topicId: Int, accuracy: Int, mistakesCount: Int) {
        viewModelScope.launch {
            repository.updateTopicPerformance(topicId, accuracy, mistakesCount)
        }
    }

    fun resetSchedule() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.resetToInitialSchedule()
        }
    }
}

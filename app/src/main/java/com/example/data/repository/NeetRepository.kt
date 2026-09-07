package com.example.data.repository

import com.example.data.InitialData
import com.example.data.dao.MistakeEntryDao
import com.example.data.dao.PastPaperDao
import com.example.data.dao.StudyBlockDao
import com.example.data.model.MistakeEntryEntity
import com.example.data.model.PastPaperTopicEntity
import com.example.data.model.StudyBlockEntity
import kotlinx.coroutines.flow.Flow

class NeetRepository(
    private val studyBlockDao: StudyBlockDao,
    private val mistakeEntryDao: MistakeEntryDao,
    private val pastPaperDao: PastPaperDao
) {
    // Study Blocks
    val allBlocks: Flow<List<StudyBlockEntity>> = studyBlockDao.getAllBlocks()
    val completedBlocksCount: Flow<Int> = studyBlockDao.getCompletedBlocksCount()
    val totalBlocksCount: Flow<Int> = studyBlockDao.getTotalBlocksCount()
    val totalCompletedMcqs: Flow<Int?> = studyBlockDao.getTotalCompletedMcqs()

    fun getBlocksByWeek(week: Int): Flow<List<StudyBlockEntity>> =
        studyBlockDao.getBlocksByWeek(week)

    fun getBlocksByDay(day: Int): Flow<List<StudyBlockEntity>> =
        studyBlockDao.getBlocksByDay(day)

    suspend fun setBlockCompletion(id: Int, completed: Boolean, mcqs: Int) {
        studyBlockDao.setBlockCompletion(id, completed, mcqs)
    }

    suspend fun setDayCompletion(dayNumber: Int, completed: Boolean) {
        studyBlockDao.setDayCompletion(dayNumber, completed)
    }

    suspend fun updateBlock(block: StudyBlockEntity) {
        studyBlockDao.updateBlock(block)
    }

    // Mistake Entries
    val allMistakes: Flow<List<MistakeEntryEntity>> = mistakeEntryDao.getAllMistakes()
    val totalMistakesCount: Flow<Int> = mistakeEntryDao.getTotalMistakesCount()

    fun getMistakesBySubject(subject: String): Flow<List<MistakeEntryEntity>> =
        mistakeEntryDao.getMistakesBySubject(subject)

    fun getMistakesByCategory(category: String): Flow<List<MistakeEntryEntity>> =
        mistakeEntryDao.getMistakesByCategory(category)

    suspend fun insertMistake(mistake: MistakeEntryEntity) {
        mistakeEntryDao.insertMistake(mistake)
    }

    suspend fun setMistakeReviewed(id: Int, reviewed: Boolean) {
        mistakeEntryDao.setReviewed(id, reviewed)
    }

    suspend fun updateMistake(mistake: MistakeEntryEntity) {
        mistakeEntryDao.updateMistake(mistake)
    }

    suspend fun deleteMistake(mistake: MistakeEntryEntity) {
        mistakeEntryDao.deleteMistake(mistake)
    }

    // Past Exam Papers
    val allPastPaperTopics: Flow<List<PastPaperTopicEntity>> = pastPaperDao.getAllTopics()
    val weakFocusAreas: Flow<List<PastPaperTopicEntity>> = pastPaperDao.getWeakFocusAreas()

    fun getPastPapersBySubject(subject: String): Flow<List<PastPaperTopicEntity>> =
        pastPaperDao.getTopicsBySubject(subject)

    suspend fun updateTopicPerformance(id: Int, accuracy: Int, mistakes: Int) {
        pastPaperDao.updatePerformance(id, accuracy, mistakes)
    }

    suspend fun ensureDataSeeded() {
        val blockCount = studyBlockDao.getBlocksCountDirect()
        if (blockCount == 0) {
            studyBlockDao.insertAll(InitialData.getInitialStudyBlocks())
        }
        val pastPaperCount = pastPaperDao.getTopicsCountDirect()
        if (pastPaperCount == 0) {
            pastPaperDao.insertAll(InitialData.getInitialPastPaperTopics())
        }
        val mistakeCount = mistakeEntryDao.getMistakesCountDirect()
        if (mistakeCount == 0) {
            InitialData.getInitialMistakes().forEach {
                mistakeEntryDao.insertMistake(it)
            }
        }
    }

    suspend fun resetToInitialSchedule() {
        studyBlockDao.insertAll(InitialData.getInitialStudyBlocks())
        if (pastPaperDao.getTopicsCountDirect() == 0) {
            pastPaperDao.insertAll(InitialData.getInitialPastPaperTopics())
        }
    }
}

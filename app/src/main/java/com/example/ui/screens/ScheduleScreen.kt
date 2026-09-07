package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LockClock
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.StudyBlockEntity
import com.example.ui.components.AddMistakeDialog
import com.example.ui.components.EditBlockProgressDialog
import com.example.ui.components.SubjectBadge
import com.example.ui.theme.BiologyGreen
import com.example.ui.theme.ChemistryAmber
import com.example.ui.theme.PhysicsIndigo
import com.example.ui.viewmodel.NeetViewModel

@Composable
fun ScheduleScreen(
    viewModel: NeetViewModel,
    modifier: Modifier = Modifier
) {
    val blocks by viewModel.filteredStudyBlocks.collectAsState()
    val selectedWeek by viewModel.selectedWeek.collectAsState()
    val selectedDay by viewModel.selectedDayFilter.collectAsState()

    var editingBlock by remember { mutableStateOf<StudyBlockEntity?>(null) }
    var loggingMistakeForBlock by remember { mutableStateOf<StudyBlockEntity?>(null) }

    // Group blocks by dayNumber
    val groupedDays = remember(blocks) {
        blocks.groupBy { it.dayNumber }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Recovery Strategy Header Banner
        Surface(
            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = "Schedule",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Recovery Phase: 7h Daily Study Routine",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Reverse Engineering Method: 8:00-9:30 AM (Memory/NCERT) • 5:00-9:00 PM (Heavy-Lifting & MCQs) • 9:30-11:00 PM (Error Audit)",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 11.5.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                )
            }
        }

        // Week Selector Tabs (Weeks 1 to 6, All)
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                FilterChip(
                    selected = selectedWeek == 0,
                    onClick = { viewModel.setWeek(0) },
                    label = { Text("All 6 Weeks (42 Days)") },
                    modifier = Modifier.testTag("week_all_chip")
                )
            }
            val weekRanges = listOf(
                1 to "W1: Sept 12-18",
                2 to "W2: Sept 19-25",
                3 to "W3: Sept 26-Oct 2",
                4 to "W4: Oct 3-9",
                5 to "W5: Oct 10-16",
                6 to "W6: Oct 17-23"
            )
            items(weekRanges) { (wk, label) ->
                FilterChip(
                    selected = selectedWeek == wk,
                    onClick = { viewModel.setWeek(wk) },
                    label = { Text(label) },
                    modifier = Modifier.testTag("week_${wk}_chip")
                )
            }
        }

        // Day Filter Chips if a specific week is chosen
        if (selectedWeek != 0) {
            val startDay = (selectedWeek - 1) * 7 + 1
            val endDay = selectedWeek * 7
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 2.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                item {
                    FilterChip(
                        selected = selectedDay == null,
                        onClick = { viewModel.setDayFilter(null) },
                        label = { Text("All Days in W$selectedWeek", fontSize = 11.sp) }
                    )
                }
                for (d in startDay..endDay) {
                    item {
                        val isBuffer = (d % 7 == 0)
                        FilterChip(
                            selected = selectedDay == d,
                            onClick = { viewModel.setDayFilter(if (selectedDay == d) null else d) },
                            label = {
                                Text(if (isBuffer) "Day $d (Buffer)" else "Day $d", fontSize = 11.sp)
                            }
                        )
                    }
                }
            }
        }

        // List of Days
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (groupedDays.isEmpty()) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Default.Schedule,
                                contentDescription = "Schedule",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(48.dp)
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "No Study Blocks Found",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = if (selectedWeek != 0 || selectedDay != null)
                                    "No blocks match the current filter. Reset filters or reload the 42-day recovery curriculum."
                                else
                                    "Your 42-day recovery curriculum has not yet been loaded.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                if (selectedWeek != 0 || selectedDay != null) {
                                    OutlinedButton(
                                        onClick = {
                                            viewModel.setWeek(1)
                                            viewModel.setDayFilter(null)
                                        }
                                    ) {
                                        Text("Reset Filters")
                                    }
                                }
                                Button(
                                    onClick = {
                                        viewModel.resetSchedule()
                                        viewModel.setWeek(1)
                                        viewModel.setDayFilter(null)
                                    },
                                    modifier = Modifier.testTag("load_schedule_btn")
                                ) {
                                    Text("Load 42-Day Schedule")
                                }
                            }
                        }
                    }
                }
            }

            groupedDays.forEach { (dayNumber, dayBlocks) ->
                val firstBlock = dayBlocks.first()
                val isBuffer = firstBlock.isBufferDay
                val allDayCompleted = dayBlocks.all { it.isCompleted }
                val completedCount = dayBlocks.count { it.isCompleted }
                val totalDayMcqs = dayBlocks.sumOf { it.targetMcqs }
                val solvedDayMcqs = dayBlocks.sumOf { it.completedMcqs }

                item(key = dayNumber) {
                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("day_card_$dayNumber"),
                        colors = CardDefaults.elevatedCardColors(
                            containerColor = if (isBuffer)
                                MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.25f)
                            else
                                MaterialTheme.colorScheme.surface
                        ),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            // Day Header
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = firstBlock.dateDisplay,
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = if (allDayCompleted) BiologyGreen else MaterialTheme.colorScheme.onSurface
                                        )
                                        if (isBuffer) {
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Surface(
                                                color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.15f),
                                                shape = RoundedCornerShape(6.dp)
                                            ) {
                                                Text(
                                                    text = "Consolidation",
                                                    color = MaterialTheme.colorScheme.tertiary,
                                                    style = MaterialTheme.typography.labelSmall,
                                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                                )
                                            }
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = "$completedCount of ${dayBlocks.size} blocks completed • $solvedDayMcqs/$totalDayMcqs MCQs",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }

                                // Quick Mark Day Completed
                                OutlinedButton(
                                    onClick = {
                                        viewModel.markDayComplete(dayNumber, !allDayCompleted)
                                    },
                                    contentPadding = ButtonDefaults.ContentPadding,
                                    modifier = Modifier.testTag("day_complete_button_$dayNumber")
                                ) {
                                    Icon(
                                        imageVector = if (allDayCompleted) Icons.Default.CheckCircle else Icons.Outlined.Circle,
                                        contentDescription = "Mark Day Complete",
                                        tint = if (allDayCompleted) BiologyGreen else MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = if (allDayCompleted) "All Done" else "Mark Day",
                                        fontSize = 12.sp
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))
                            LinearProgressIndicator(
                                progress = { if (dayBlocks.isNotEmpty()) completedCount.toFloat() / dayBlocks.size else 0f },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(4.dp)
                                    .clip(RoundedCornerShape(2.dp)),
                                color = BiologyGreen,
                                trackColor = MaterialTheme.colorScheme.surfaceVariant
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            // Blocks inside this day
                            dayBlocks.forEachIndexed { index, block ->
                                StudyBlockItem(
                                    block = block,
                                    onToggleCheck = { viewModel.toggleBlockCompletion(block) },
                                    onEditProgress = { editingBlock = block },
                                    onLogMistake = { loggingMistakeForBlock = block }
                                )
                                if (index < dayBlocks.size - 1) {
                                    HorizontalDivider(
                                        modifier = Modifier.padding(vertical = 8.dp),
                                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }

    // Edit block progress dialog
    editingBlock?.let { block ->
        EditBlockProgressDialog(
            chapter = block.chapter,
            targetMcqs = block.targetMcqs,
            initialCompletedMcqs = block.completedMcqs,
            initialNotes = block.studentNotes,
            onDismiss = { editingBlock = null },
            onSave = { mcqs, notes ->
                viewModel.updateBlockNotesAndMcqs(block, mcqs, notes)
                editingBlock = null
            }
        )
    }

    // Quick Mistake Logging dialog for a block
    loggingMistakeForBlock?.let { block ->
        AddMistakeDialog(
            initialSubject = block.subject,
            initialChapter = block.chapter,
            onDismiss = { loggingMistakeForBlock = null },
            onConfirm = { subject, chapter, question, category, why, takeaway ->
                viewModel.addMistake(
                    subject = subject,
                    chapter = chapter,
                    questionSummary = question,
                    errorCategory = category,
                    whyReason = why,
                    correctTakeaway = takeaway
                )
                loggingMistakeForBlock = null
            }
        )
    }
}

@Composable
fun StudyBlockItem(
    block: StudyBlockEntity,
    onToggleCheck: () -> Unit,
    onEditProgress: () -> Unit,
    onLogMistake: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onToggleCheck() }
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.Top
    ) {
        // Large Accessible Checkbox / Check Icon
        IconButton(
            onClick = onToggleCheck,
            modifier = Modifier
                .padding(top = 2.dp)
                .size(44.dp)
                .testTag("block_check_${block.id}")
        ) {
            Icon(
                imageVector = if (block.isCompleted) Icons.Default.CheckCircle else Icons.Outlined.Circle,
                contentDescription = if (block.isCompleted) "Completed" else "Incomplete",
                tint = if (block.isCompleted) BiologyGreen else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        // Content
        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SubjectBadge(subject = block.subject)
                Text(
                    text = block.timeSlot,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = block.chapter,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                textDecoration = if (block.isCompleted) TextDecoration.LineThrough else TextDecoration.None,
                color = if (block.isCompleted) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f) else MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = block.topicsDescription,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 16.sp
            )

            if (block.studentNotes.isNotBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = "Notes: ${block.studentNotes}",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }

            // Target vs Solved MCQs and quick actions
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (block.targetMcqs > 0) {
                    Text(
                        text = "Target: ${block.targetMcqs} MCQs ${if (block.completedMcqs > 0) "(${block.completedMcqs} solved)" else ""}",
                        style = MaterialTheme.typography.labelSmall,
                        color = if (block.completedMcqs >= block.targetMcqs) BiologyGreen else MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                } else {
                    Text(
                        text = "Night Audit Session",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Medium
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    TextButton(
                        onClick = onEditProgress,
                        modifier = Modifier.height(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Log Progress",
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text("Log MCQs", fontSize = 11.sp)
                    }

                    if (block.blockCategory == "NIGHT_AUDIT" || block.blockCategory.contains("EVENING")) {
                        TextButton(
                            onClick = onLogMistake,
                            modifier = Modifier.height(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Log Error",
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text("+ Mistake", fontSize = 11.sp)
                        }
                    }
                }
            }
        }
    }
}

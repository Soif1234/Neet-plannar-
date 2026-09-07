package com.example.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Biotech
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AuditPurple
import com.example.ui.theme.BiologyGreen
import com.example.ui.theme.ChemistryAmber
import com.example.ui.theme.ConceptualErrorColor
import com.example.ui.theme.MemoryErrorColor
import com.example.ui.theme.PhysicsIndigo
import com.example.ui.theme.ReadingErrorColor
import com.example.ui.theme.SillyErrorColor

@Composable
fun SubjectBadge(subject: String, modifier: Modifier = Modifier) {
    val (bgColor, textColor, icon) = when {
        subject.contains("Bio", ignoreCase = true) -> Triple(BiologyGreen.copy(alpha = 0.15f), BiologyGreen, Icons.Default.Biotech)
        subject.contains("Chem", ignoreCase = true) -> Triple(ChemistryAmber.copy(alpha = 0.15f), ChemistryAmber, Icons.Default.Science)
        subject.contains("Phys", ignoreCase = true) -> Triple(PhysicsIndigo.copy(alpha = 0.15f), PhysicsIndigo, Icons.Default.Speed)
        else -> Triple(AuditPurple.copy(alpha = 0.15f), AuditPurple, Icons.Default.MenuBook)
    }

    Surface(
        color = bgColor,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = subject,
                tint = textColor,
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = subject,
                color = textColor,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ErrorCategoryBadge(category: String, modifier: Modifier = Modifier) {
    val (bgColor, textColor) = when {
        category.contains("Silly", ignoreCase = true) || category.contains("Calc", ignoreCase = true) ->
            Pair(SillyErrorColor.copy(alpha = 0.15f), SillyErrorColor)
        category.contains("Read", ignoreCase = true) ->
            Pair(ReadingErrorColor.copy(alpha = 0.15f), ReadingErrorColor)
        category.contains("Memory", ignoreCase = true) ->
            Pair(MemoryErrorColor.copy(alpha = 0.15f), MemoryErrorColor)
        else ->
            Pair(ConceptualErrorColor.copy(alpha = 0.15f), ConceptualErrorColor)
    }

    Surface(
        color = bgColor,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        Text(
            text = category,
            color = textColor,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
        )
    }
}

@Composable
fun StatMetricCard(
    title: String,
    value: String,
    subtitle: String,
    accentColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
                color = accentColor
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMistakeDialog(
    initialSubject: String = "Physics",
    initialChapter: String = "",
    onDismiss: () -> Unit,
    onConfirm: (
        subject: String,
        chapter: String,
        question: String,
        category: String,
        why: String,
        takeaway: String
    ) -> Unit
) {
    val errorCategories = listOf(
        "Silly/Calculation Error",
        "Reading Error",
        "Memory Error",
        "Conceptual Error"
    )
    val subjects = listOf(
        "Physics",
        "Chemistry - Physical",
        "Chemistry - Organic",
        "Chemistry - Inorganic",
        "Biology"
    )

    var selectedSubject by remember { mutableStateOf(initialSubject) }
    var chapterText by remember { mutableStateOf(initialChapter) }
    var questionText by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(errorCategories.first()) }
    var whyText by remember { mutableStateOf("") }
    var takeawayText by remember { mutableStateOf("") }

    var subjectExpanded by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Log Mistake to Notebook", style = MaterialTheme.typography.titleLarge)
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Subject Selector
                ExposedDropdownMenuBox(
                    expanded = subjectExpanded,
                    onExpandedChange = { subjectExpanded = it }
                ) {
                    OutlinedTextField(
                        value = selectedSubject,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Subject") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = subjectExpanded) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = subjectExpanded,
                        onDismissRequest = { subjectExpanded = false }
                    ) {
                        subjects.forEach { s ->
                            DropdownMenuItem(
                                text = { Text(s) },
                                onClick = {
                                    selectedSubject = s
                                    subjectExpanded = false
                                }
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = chapterText,
                    onValueChange = { chapterText = it },
                    label = { Text("Chapter") },
                    placeholder = { Text("e.g. Thermodynamics, Optics") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                // Error Category Selector
                Text(
                    text = "Error Category:",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    errorCategories.forEach { cat ->
                        FilterChip(
                            selected = selectedCategory == cat,
                            onClick = { selectedCategory = cat },
                            label = { Text(cat.replace(" Error", ""), fontSize = 11.sp) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                OutlinedTextField(
                    value = questionText,
                    onValueChange = { questionText = it },
                    label = { Text("Question Summary / Snippet") },
                    placeholder = { Text("e.g. Dimension formula for Magnetic Flux") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = whyText,
                    onValueChange = { whyText = it },
                    label = { Text("Why was this mistake made?") },
                    placeholder = { Text("e.g. Minor calculation slip; missed the negative sign") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = takeawayText,
                    onValueChange = { takeawayText = it },
                    label = { Text("Correct Takeaway / Formula") },
                    placeholder = { Text("e.g. Flux = B*A = [M L² T⁻² A⁻¹]") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (chapterText.isNotBlank() && whyText.isNotBlank()) {
                        onConfirm(
                            selectedSubject,
                            chapterText,
                            questionText.ifBlank { "Practice Question" },
                            selectedCategory,
                            whyText,
                            takeawayText.ifBlank { "Review notes." }
                        )
                    }
                },
                modifier = Modifier.testTag("save_mistake_button")
            ) {
                Text("Save to Notebook")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun EditBlockProgressDialog(
    chapter: String,
    targetMcqs: Int,
    initialCompletedMcqs: Int,
    initialNotes: String,
    onDismiss: () -> Unit,
    onSave: (completedMcqs: Int, notes: String) -> Unit
) {
    var mcqsText by remember { mutableStateOf(initialCompletedMcqs.toString()) }
    var notesText by remember { mutableStateOf(initialNotes) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Update Study Session", style = MaterialTheme.typography.titleLarge)
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = chapter,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                OutlinedTextField(
                    value = mcqsText,
                    onValueChange = { mcqsText = it.filter { ch -> ch.isDigit() } },
                    label = { Text("MCQs Completed (Target: $targetMcqs)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = notesText,
                    onValueChange = { notesText = it },
                    label = { Text("Session Notes & Observations") },
                    placeholder = { Text("e.g. Solved at 1.5x speed, struggled with numerical #14") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 3
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val mcqs = mcqsText.toIntOrNull() ?: initialCompletedMcqs
                    onSave(mcqs, notesText)
                },
                modifier = Modifier.testTag("save_session_progress_button")
            ) {
                Text("Update Progress")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.ChemistryAmber
import com.example.ui.viewmodel.NeetViewModel

@Composable
fun MasteryPlannerScreen(
    viewModel: NeetViewModel,
    modifier: Modifier = Modifier
) {
    var selectedSection by remember { mutableStateOf(0) } // 0: Mastery Phase Schedule, 1: Strategy Rules

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TabRow(
            selectedTabIndex = selectedSection,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary
        ) {
            Tab(
                selected = selectedSection == 0,
                onClick = { selectedSection = 0 },
                text = { Text("Mastery Blueprint", fontSize = 13.sp, fontWeight = FontWeight.Bold) }
            )
            Tab(
                selected = selectedSection == 1,
                onClick = { selectedSection = 1 },
                text = { Text("Core Strategies", fontSize = 13.sp, fontWeight = FontWeight.Bold) }
            )
        }

        when (selectedSection) {
            0 -> MasteryBlueprintContent()
            1 -> CoreStrategyContent()
        }
    }
}

@Composable
fun MasteryBlueprintContent() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "Next 4 Months: Mastery Phase (11.5h Daily)",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Shift your focus from reviewing to intense exam conditioning, rigorous mock tests, and performance trend analysis.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
        }

        val masteryBlocks = listOf(
            Triple(
                "Morning Block (7:00 AM - 12:00 PM)",
                "5-Hour High-Focus Window",
                "Dedicate to high-focus problem-solving and mock tests. Mimic actual NEET exam environment. Spend 3 hours taking a test and 2 hours rigorously analyzing your mistakes."
            ),
            Triple(
                "Afternoon Block (1:00 PM - 5:00 PM)",
                "4-Hour Weak Concept Review",
                "Review weak concepts identified in your morning mock tests. Re-read NCERT theory and clarify doubts for questions answered incorrectly."
            ),
            Triple(
                "Evening Block (4:00 PM - 9:00 PM)",
                "5-Hour Weakness Targeting Session",
                "Revisit recorded video lectures strictly for topics you got wrong in morning tests. Practice heavy numericals for Physics and Physical Chemistry."
            ),
            Triple(
                "Night Block (9:30 PM - 11:00 PM)",
                "1.5-Hour Formula Revision & Memorization",
                "Reserve this block for formula revision and memorization. Review your mistake journal, recite Physics formulas, and go over organic chemistry named reactions."
            )
        )

        items(masteryBlocks) { (title, subtitle, desc) ->
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = desc,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 18.sp
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@Composable
fun CoreStrategyContent() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Text(
                text = "NEET 2027 Core Strategies & Rules",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Preserved directly from your personalized blueprint to guide your day-to-day preparation:",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        val rules = listOf(
            Pair(
                "Reverse Engineering Method",
                "Because you have studied the syllabus for 5 years and have recorded coaching videos, attempt MCQs first! Only watch videos for concepts you have completely forgotten during your 6-month break (at 1.25x or 1.5x speed)."
            ),
            Pair(
                "1.5-Hour Blocks Rule",
                "30 mins quick theory review + 1 hour MCQ practice (approx. 40-50 questions)."
            ),
            Pair(
                "2-Hour Evening Blocks Rule",
                "First 15 mins attempt 15-20 MCQs. If you struggle, spend 45 mins watching recorded videos at 1.5x speed. Spend remaining 1 hour doing rigorous MCQ practice."
            ),
            Pair(
                "Audit Block (9:30 PM - 11:00 PM)",
                "Log errors into Mistake Notebook. Categorize as: Silly/Calculation (2+3=6), Reading (missed 'not/except'), Memory (forgot formula), or Conceptual (applied wrong theory)."
            ),
            Pair(
                "Smart Use of Recorded Lectures",
                "Do NOT binge-watch lectures! Watching videos is passive learning. Attempt 30-50 MCQs on a chapter before studying. If you score well, skip the video and just review notes."
            ),
            Pair(
                "Weekly Recovery Milestones",
                "Weeks 1-2: Focus on strongest subjects to rebuild momentum & confidence.\nWeeks 3-6: Transition to moderately difficult & heavy-weightage units (Mechanics, Plant Phys, Reproduction, GOC).\nWeeks 7-8: Full-length mock tests on Sundays to test stamina before 11.5h Mastery Phase."
            )
        )

        items(rules) { (title, content) ->
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = content,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 18.sp
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

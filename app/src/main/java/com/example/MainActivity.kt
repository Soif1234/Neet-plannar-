package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Insights
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.screens.DashboardScreen
import com.example.ui.screens.MasteryPlannerScreen
import com.example.ui.screens.MistakeNotebookScreen
import com.example.ui.screens.PastPaperAnalyticsScreen
import com.example.ui.screens.ScheduleScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.NeetViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: NeetViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                NeetMainApp(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NeetMainApp(viewModel: NeetViewModel) {
    var currentScreenIndex by rememberSaveable { mutableIntStateOf(0) }

    val navItems = listOf(
        NavigationItem("Schedule", Icons.Default.CalendarMonth, "schedule_tab"),
        NavigationItem("Dashboard", Icons.Default.Insights, "dashboard_tab"),
        NavigationItem("Mistakes", Icons.Default.MenuBook, "mistakes_tab"),
        NavigationItem("Past Papers", Icons.Default.Analytics, "past_papers_tab"),
        NavigationItem("Planner", Icons.Default.AutoAwesome, "planner_tab")
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = when (currentScreenIndex) {
                            0 -> "Daily Study Schedule"
                            1 -> "Performance Dashboard"
                            2 -> "Mistake Notebook"
                            3 -> "Past Exam Papers (PYQ)"
                            else -> "Adaptive Study Planner"
                        },
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = MaterialTheme.colorScheme.surfaceVariant.let { 4.dp }
            ) {
                navItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = currentScreenIndex == index,
                        onClick = { currentScreenIndex = index },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label
                            )
                        },
                        label = { Text(item.label, fontSize = 10.5.sp, maxLines = 1) },
                        modifier = Modifier.testTag(item.testTag)
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentScreenIndex) {
                0 -> ScheduleScreen(viewModel = viewModel)
                1 -> DashboardScreen(
                    viewModel = viewModel,
                    onNavigateToMistakes = { currentScreenIndex = 2 },
                    onNavigateToPastPapers = { currentScreenIndex = 3 },
                    onNavigateToPlanner = { currentScreenIndex = 4 }
                )
                2 -> MistakeNotebookScreen(viewModel = viewModel)
                3 -> PastPaperAnalyticsScreen(
                    viewModel = viewModel,
                    onNavigateToPlanner = { currentScreenIndex = 4 }
                )
                4 -> MasteryPlannerScreen(viewModel = viewModel)
            }
        }
    }
}

data class NavigationItem(
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val testTag: String
)

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Hello $name!", modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme { Greeting("Android") }
}

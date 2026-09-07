package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "past_paper_topics")
data class PastPaperTopicEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val subject: String,              // "Physics", "Chemistry", "Biology"
    val chapter: String,              // e.g. "Genetics and Evolution", "Chemical Bonding", "Mechanics - Rotational Motion"
    val avgQuestionsPerYear: Float,   // e.g. 4.5f
    val weightagePercentage: Float,   // e.g. 8.5%
    val weightageTier: String,        // "Critical High-Yield", "High Yield", "Moderate Yield"
    val userAccuracyPercent: Int = 70,// Estimated/Tracked user accuracy
    val totalMistakesLogged: Int = 0,
    val recommendedActions: String    // Action plan e.g. "Target 50 PYQs + revise MOT"
)

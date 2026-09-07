package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mistake_entries")
data class MistakeEntryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val subject: String,              // "Physics", "Chemistry - Physical", "Chemistry - Organic", "Chemistry - Inorganic", "Biology"
    val chapter: String,              // e.g. "Some Basic Concepts of Chemistry", "Laws of Motion"
    val questionSummary: String,       // Specific question or MCQ topic
    val errorCategory: String,        // "Silly/Calculation Error", "Reading Error", "Memory Error", "Conceptual Error"
    val whyReason: String,            // Document the "Why" (e.g. "Misread 'not correct' in prompt", "Added 2+3=6")
    val correctTakeaway: String,      // Correct formula or concise principle
    val timestamp: Long = System.currentTimeMillis(),
    val isReviewed: Boolean = false
)

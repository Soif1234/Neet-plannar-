package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "study_blocks")
data class StudyBlockEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val dayNumber: Int,               // 1 to 42
    val weekNumber: Int,              // 1 to 6
    val dateDisplay: String,          // e.g. "Day 1: Saturday, 12/9/26"
    val blockTitle: String,           // e.g. "Morning Block (8:00 AM - 9:30 AM)"
    val timeSlot: String,             // "8:00 AM - 9:30 AM", "5:00 PM - 7:00 PM", etc.
    val blockCategory: String,        // "MORNING", "EVENING_1", "EVENING_2", "NIGHT_AUDIT"
    val subject: String,              // "Biology", "Chemistry", "Physics", "Audit"
    val chapter: String,              // "Cell Structure and Function (Part 1)"
    val topicsDescription: String,    // "Read NCERT for Prokaryotic vs. Eukaryotic cells and Cell Membrane. Do 40 MCQs."
    val targetMcqs: Int,              // e.g. 40
    val completedMcqs: Int = 0,
    val isCompleted: Boolean = false,
    val isBufferDay: Boolean = false,
    val studentNotes: String = ""
)

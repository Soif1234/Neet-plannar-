package com.example.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.StudyBlockEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StudyBlockDao {
    @Query("SELECT * FROM study_blocks ORDER BY dayNumber ASC, id ASC")
    fun getAllBlocks(): Flow<List<StudyBlockEntity>>

    @Query("SELECT * FROM study_blocks WHERE weekNumber = :week ORDER BY dayNumber ASC, id ASC")
    fun getBlocksByWeek(week: Int): Flow<List<StudyBlockEntity>>

    @Query("SELECT * FROM study_blocks WHERE dayNumber = :day ORDER BY id ASC")
    fun getBlocksByDay(day: Int): Flow<List<StudyBlockEntity>>

    @Query("SELECT COUNT(*) FROM study_blocks WHERE isCompleted = 1")
    fun getCompletedBlocksCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM study_blocks")
    fun getTotalBlocksCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM study_blocks")
    suspend fun getBlocksCountDirect(): Int

    @Query("SELECT SUM(completedMcqs) FROM study_blocks")
    fun getTotalCompletedMcqs(): Flow<Int?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(blocks: List<StudyBlockEntity>)

    @Update
    suspend fun updateBlock(block: StudyBlockEntity)

    @Query("UPDATE study_blocks SET isCompleted = :completed, completedMcqs = :completedMcqs WHERE id = :id")
    suspend fun setBlockCompletion(id: Int, completed: Boolean, completedMcqs: Int)

    @Query("UPDATE study_blocks SET isCompleted = :completed WHERE dayNumber = :dayNumber")
    suspend fun setDayCompletion(dayNumber: Int, completed: Boolean)
}

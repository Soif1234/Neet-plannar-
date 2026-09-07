package com.example.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.MistakeEntryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MistakeEntryDao {
    @Query("SELECT * FROM mistake_entries ORDER BY timestamp DESC")
    fun getAllMistakes(): Flow<List<MistakeEntryEntity>>

    @Query("SELECT * FROM mistake_entries WHERE subject LIKE :subjectPrefix ORDER BY timestamp DESC")
    fun getMistakesBySubject(subjectPrefix: String): Flow<List<MistakeEntryEntity>>

    @Query("SELECT * FROM mistake_entries WHERE errorCategory = :category ORDER BY timestamp DESC")
    fun getMistakesByCategory(category: String): Flow<List<MistakeEntryEntity>>

    @Query("SELECT COUNT(*) FROM mistake_entries")
    fun getTotalMistakesCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM mistake_entries")
    suspend fun getMistakesCountDirect(): Int

    @Query("SELECT COUNT(*) FROM mistake_entries WHERE errorCategory = :category")
    fun getCountByCategory(category: String): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMistake(mistake: MistakeEntryEntity)

    @Update
    suspend fun updateMistake(mistake: MistakeEntryEntity)

    @Delete
    suspend fun deleteMistake(mistake: MistakeEntryEntity)

    @Query("UPDATE mistake_entries SET isReviewed = :reviewed WHERE id = :id")
    suspend fun setReviewed(id: Int, reviewed: Boolean)
}

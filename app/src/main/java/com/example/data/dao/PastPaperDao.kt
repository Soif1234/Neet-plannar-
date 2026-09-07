package com.example.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.PastPaperTopicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PastPaperDao {
    @Query("SELECT * FROM past_paper_topics ORDER BY avgQuestionsPerYear DESC")
    fun getAllTopics(): Flow<List<PastPaperTopicEntity>>

    @Query("SELECT * FROM past_paper_topics WHERE subject = :subject ORDER BY avgQuestionsPerYear DESC")
    fun getTopicsBySubject(subject: String): Flow<List<PastPaperTopicEntity>>

    @Query("SELECT * FROM past_paper_topics WHERE userAccuracyPercent < 65 OR totalMistakesLogged > 1 ORDER BY avgQuestionsPerYear DESC")
    fun getWeakFocusAreas(): Flow<List<PastPaperTopicEntity>>

    @Query("SELECT COUNT(*) FROM past_paper_topics")
    suspend fun getTopicsCountDirect(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(topics: List<PastPaperTopicEntity>)

    @Update
    suspend fun updateTopic(topic: PastPaperTopicEntity)

    @Query("UPDATE past_paper_topics SET userAccuracyPercent = :accuracy, totalMistakesLogged = :mistakes WHERE id = :id")
    suspend fun updatePerformance(id: Int, accuracy: Int, mistakes: Int)
}

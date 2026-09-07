package com.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.dao.MistakeEntryDao
import com.example.data.dao.PastPaperDao
import com.example.data.dao.StudyBlockDao
import com.example.data.model.MistakeEntryEntity
import com.example.data.model.PastPaperTopicEntity
import com.example.data.model.StudyBlockEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        StudyBlockEntity::class,
        MistakeEntryEntity::class,
        PastPaperTopicEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studyBlockDao(): StudyBlockDao
    abstract fun mistakeEntryDao(): MistakeEntryDao
    abstract fun pastPaperDao(): PastPaperDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "neet_prep_database"
                )
                .fallbackToDestructiveMigration()
                .fallbackToDestructiveMigrationOnDowngrade()
                .addCallback(DatabaseCallback(context.applicationContext, scope))
                .build()
                INSTANCE = instance
                instance
            }
        }

        private class DatabaseCallback(
            private val appContext: Context,
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                scope.launch(Dispatchers.IO) {
                    populate(getDatabase(appContext, scope))
                }
            }

            override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
                super.onDestructiveMigration(db)
                scope.launch(Dispatchers.IO) {
                    populate(getDatabase(appContext, scope))
                }
            }

            private suspend fun populate(database: AppDatabase) {
                if (database.studyBlockDao().getBlocksCountDirect() == 0) {
                    database.studyBlockDao().insertAll(InitialData.getInitialStudyBlocks())
                }
                if (database.mistakeEntryDao().getMistakesCountDirect() == 0) {
                    InitialData.getInitialMistakes().forEach {
                        database.mistakeEntryDao().insertMistake(it)
                    }
                }
                if (database.pastPaperDao().getTopicsCountDirect() == 0) {
                    database.pastPaperDao().insertAll(InitialData.getInitialPastPaperTopics())
                }
            }
        }
    }
}

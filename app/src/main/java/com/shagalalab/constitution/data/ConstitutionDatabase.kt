package com.shagalalab.constitution.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ArticleModel::class, ChapterModel::class, PartModel::class], version = 1, exportSchema = false)
abstract class ConstitutionDatabase : RoomDatabase() {

    abstract fun constitutionDao(): ConstitutionDao

    companion object {
        private var INSTANCE: ConstitutionDatabase? = null
        fun getInstance(context: Context): ConstitutionDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    ConstitutionDatabase::class.java,
                    "constitution.db"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}

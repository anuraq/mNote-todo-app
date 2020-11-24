package com.anuraq.mnote.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.*

@Database(entities = arrayOf(Task::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    //abstract fun taskDao(): TaskDao
    companion object {
        val DATABASE_NAME = "tasks_db"
        private var instance: AppDatabase? = null
        private val prePopulateCallback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Thread(PrePopulateDatabase(instance as AppDatabase)).start()
            }
        }

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, DATABASE_NAME
                )
                    .addCallback(prePopulateCallback)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance as AppDatabase
        }
    }

    abstract fun taskDao(): TaskDao

    private class PrePopulateDatabase : Runnable {
        private var taskDao: TaskDao

        constructor( dataBase: AppDatabase){
            taskDao = dataBase.taskDao()
        }

        override fun run() {
            val r: Random = Random()
            taskDao.insert(Task( r.nextInt(),"Anurag", "xakejdgbfe"))
            taskDao.insert(Task( r.nextInt(),"Aditya", "tyrtyrjgbfe"))
        }

    }

}
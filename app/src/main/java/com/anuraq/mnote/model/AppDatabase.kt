package com.anuraq.mnote.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.*

@Database(entities = arrayOf(Task::class), version = 1)
public abstract class AppDatabase : RoomDatabase() {
    //abstract fun taskDao(): TaskDao
    private lateinit var instance: AppDatabase
    val DATABASE_NAME = "task_db"

    private val prePopulateCallback: Callback = object : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            Thread(PrePopulateDatabase(instance)).start()
        }
    }


    @Synchronized
    open fun getInstance(context: Context): AppDatabase? {
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
        return instance
    }

    abstract fun taskDao(): TaskDao

    private class PrePopulateDatabase internal constructor(dataBase: AppDatabase) : Runnable {
        private val taskDao: TaskDao = TODO()
        override fun run() {
            val r: Random = Random()
            taskDao.insert(Task( r.nextInt(),"Anurag", "xakejdgbfe"))
            taskDao.insert(Task( r.nextInt(),"Aditya", "tyrtyrjgbfe"))
        }

        init {
            taskDao = dataBase.taskDao()
        }
    }

}
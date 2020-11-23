package com.anuraq.mnote.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val tid: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "description") val desc: String
) {
    fun setTaskTitle( title: String) { this.title = title }

}
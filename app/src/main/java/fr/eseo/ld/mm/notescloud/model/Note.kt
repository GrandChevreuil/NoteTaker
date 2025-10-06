package fr.eseo.ld.mm.notescloud.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey val id: String,
    val author: String,
    val title: String,
    val body: String,
    val creationDate: LocalDateTime,
    val modificationDate: LocalDateTime
)

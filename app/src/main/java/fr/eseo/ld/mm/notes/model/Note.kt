package fr.eseo.ld.mm.notes.model

import java.time.LocalDateTime

class Note(val id: String, val author : String
, val title : String,
    val body: String,
    val creationDate: LocalDateTime,
    val modificationDate: LocalDateTime) {

}
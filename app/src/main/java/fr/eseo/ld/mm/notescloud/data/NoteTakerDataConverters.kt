package fr.eseo.ld.mm.notescloud.data

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class NoteTakerDataConverters {
    @TypeConverter
    fun fromTimeStamp(value: Long?): LocalDateTime? {
        return value?.let {
            Instant.ofEpochSecond(it)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()
        }
    }

    @TypeConverter
    fun toTimeStamp(value: LocalDateTime?): Long? {
        return value?.let {
            it.atZone(ZoneId.systemDefault()).toEpochSecond()
        }
    }
}


package fr.eseo.ld.mm.notescloud.data

import androidx.room.TypeConverter
import com.google.firebase.Timestamp
import java.util.Date

class NoteTakerDataConverters {
    @TypeConverter
    fun fromTimestamp(value: Timestamp?): Long? {
        return value?.toDate()?.time
    }

    @TypeConverter
    fun toTimestamp(value: Long?): Timestamp? {
        return value?.let { millis ->
            Timestamp(Date(millis))
        }
    }
}


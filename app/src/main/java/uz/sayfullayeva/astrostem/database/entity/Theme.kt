package uz.sayfullayeva.astrostem.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.sayfullayeva.astrostem.utils.TYPE

@Entity(tableName = "theme")
data class Theme(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val type: TYPE,
)
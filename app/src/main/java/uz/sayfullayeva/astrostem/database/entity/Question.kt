package uz.sayfullayeva.astrostem.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question")
data class Question(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "question_id")
    val questionId: Long = 0,
    val title: String,
    @ColumnInfo(name = "test_id")
    val testId: Int
)
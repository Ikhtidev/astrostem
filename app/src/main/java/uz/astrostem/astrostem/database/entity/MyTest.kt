package uz.astrostem.astrostem.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_test")
data class MyTest(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    @ColumnInfo(name = "test_result")
    var testResult: Int = 0,
    @ColumnInfo(name = "tests_count")
    var testsCount: Int = 0
)

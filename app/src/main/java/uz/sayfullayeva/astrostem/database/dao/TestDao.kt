package uz.sayfullayeva.astrostem.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import uz.sayfullayeva.astrostem.database.entity.MyTest

@Dao
interface TestDao {

    @Query("select * from my_test order by id")
    fun getAllTests(): List<MyTest>

    @Query("SELECT * FROM my_test WHERE tests_count!=:testsCount")
    fun getResultTest(testsCount: Int? = 0): List<MyTest>

    @Insert
    fun addTest(test: MyTest)

    @Update
    fun updateTest(test: MyTest)
}
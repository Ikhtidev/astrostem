package uz.sayfullayeva.astrostem.database.dao

import androidx.room.*
import uz.sayfullayeva.astrostem.database.entity.Question
import uz.sayfullayeva.astrostem.database.entity.QuestionWithVariant

@Dao
interface QuestionDao {

    @Query("select * from question order by question_id")
    fun getAllQuestions():List<Question>

    @Query("select * from question where test_id = :testId")
    fun getAllQuestionsByThemeId(testId:Long):List<Question>

    @Insert
    fun addQuestion(question: Question): Long

    @Transaction
    @Query("SELECT * FROM question WHERE test_id = :testId")
    fun getQuestionsWithVariants(testId: Int): List<QuestionWithVariant>

}
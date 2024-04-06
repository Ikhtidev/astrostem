package uz.sayfullayeva.astrostem.ui

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import uz.sayfullayeva.astrostem.R
import uz.sayfullayeva.astrostem.database.ThemeDatabase
import uz.sayfullayeva.astrostem.database.entity.QuestionWithVariant
import uz.sayfullayeva.astrostem.databinding.ActivityTestBinding
import uz.sayfullayeva.astrostem.utils.Constants.Companion.TEST_ID
import java.util.Collections

class TestActivity : AppCompatActivity() {

    private val binding: ActivityTestBinding by lazy {
        ActivityTestBinding.inflate(layoutInflater)
    }

    private val themeDatabase: ThemeDatabase by lazy {
        ThemeDatabase.getInstance()
    }
    private lateinit var questionsWithVariants: MutableList<QuestionWithVariant>
    private var selectedAnswer = ""
    private var correctAnswer = 0
    private var currentTest: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }
        questionsWithVariants =
            themeDatabase.questionDao().getQuestionsWithVariants(TEST_ID).toMutableList()
        questionsWithVariants.shuffle()
        questionsWithVariants = questionsWithVariants.subList(0, 30)
        setViewsVisibility()
        setTestViews(questionsWithVariants[currentTest])

    }

    private fun setViewsVisibility() {
        binding.radioBtn1.isEnabled = true
        binding.radioBtn2.isEnabled = true
        binding.radioBtn3.isEnabled = true
        binding.radioBtn4.isEnabled = true
        binding.radioGroup.clearCheck()
        binding.btnCheck.visibility = View.INVISIBLE
        binding.layoutTrueAns.visibility = View.INVISIBLE
        binding.layoutFalseAns.visibility = View.INVISIBLE
    }

    private fun setTestViews(questionWithVariant: QuestionWithVariant) {
        binding.tvCurrentTest.text =
            getString(R.string.all_test_count, (currentTest + 1).toString())
        binding.tvQuestion.text = questionWithVariant.question.title
        val variants = questionWithVariant.variants.shuffled()
        val trueAnswer = variants.filter { variant -> variant.isTrue == true }
        binding.radioBtn1.text = variants[0].title
        binding.radioBtn2.text = variants[1].title
        if (variants.size > 2) {
            binding.apply {
                radioBtn3.text = variants[2].title
                radioBtn4.text = variants[3].title
            }
        }

        binding.btnCheck.setOnClickListener {
            binding.btnCheck.visibility = View.GONE
            binding.radioBtn1.isEnabled = false
            binding.radioBtn2.isEnabled = false
            binding.radioBtn3.isEnabled = false
            binding.radioBtn4.isEnabled = false
            if (selectedAnswer.equals(trueAnswer[0].title)) {
                binding.layoutTrueAns.visibility = View.VISIBLE
                correctAnswer += 1
            }
            else binding.layoutFalseAns.visibility = View.VISIBLE
        }
    }

    fun onRadioButtonClicked(view: View) {
        selectedAnswer = (view as RadioButton).text.toString()
        binding.btnCheck.visibility = View.VISIBLE
    }

    fun onNextButtonClicked(view: View) {
        setViewsVisibility()
        currentTest += 1
        if (currentTest < questionsWithVariants.size)
            setTestViews(questionsWithVariants[currentTest])
        else {
            Toast.makeText(this, getString(R.string.result_text, correctAnswer.toString()), Toast.LENGTH_SHORT).show()
            val myTest = themeDatabase.testDao().getAllTests()[TEST_ID]
            if (correctAnswer>myTest.testResult){
                myTest.testResult = correctAnswer
                themeDatabase.testDao().updateTest(myTest)
            }
            finish()
        }
    }
}
package uz.astrostem.astrostem.ui

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import uz.astrostem.astrostem.adapters.TestVariantAdapter
import uz.astrostem.astrostem.database.ThemeDatabase
import uz.astrostem.astrostem.database.entity.QuestionWithVariant
import uz.astrostem.astrostem.database.entity.Variant
import uz.astrostem.astrostem.databinding.ActivityTestBinding
import uz.astrostem.astrostem.utils.Constants.Companion.TEST_ID

class TestActivity : AppCompatActivity() {
    private val binding: ActivityTestBinding by lazy {
        ActivityTestBinding.inflate(layoutInflater)
    }

    private val themeDatabase: ThemeDatabase by lazy {
        ThemeDatabase.getInstance(this)
    }
    private lateinit var questionsWithVariants: List<QuestionWithVariant>
    private var selectedAnswer = ""
    private var currentTest: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }
        questionsWithVariants = themeDatabase.questionDao().getQuestionsWithVariants(TEST_ID)

        setTestViews(questionsWithVariants[currentTest])
    }

    private fun setTestViews(questionWithVariant: QuestionWithVariant) {
        binding.radioGroup.clearCheck()
        binding.btnCheck.visibility = View.GONE
        binding.layoutTrueAns.visibility = View.GONE
        binding.layoutFalseAns.visibility = View.GONE
        binding.tvQuestion.text = questionWithVariant.question.title
        val variants = questionWithVariant.variants.shuffled()
        val trueAnswer = variants.filter { variant -> variant.isTrue == true }
        binding.radioBtn1.text = variants[0].title
        binding.radioBtn2.text = variants[1].title
        if (variants.size > 2){
            binding.apply {
                radioBtn3.text = variants[2].title
                radioBtn4.text = variants[3].title
            }
        }

        binding.btnCheck.setOnClickListener {
            binding.btnCheck.visibility = View.GONE
            if (selectedAnswer.equals(trueAnswer[0].title))
                binding.layoutTrueAns.visibility = View.VISIBLE
            else
                binding.layoutFalseAns.visibility = View.VISIBLE
        }
    }


    fun onRadioButtonClicked(view: View) {
        selectedAnswer = (view as RadioButton).text.toString()
        binding.btnCheck.visibility = View.VISIBLE
    }

    fun onNextButtonClicked(view: View) {
        currentTest+=1
        if (currentTest<questionsWithVariants.size-1)
        setTestViews(questionsWithVariants[currentTest])
        else Toast.makeText(this, "testlar tugadi", Toast.LENGTH_SHORT).show()
    }
}
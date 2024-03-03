package uz.astrostem.astrostem.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import uz.astrostem.astrostem.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {
    private val binding: ActivityTestBinding by lazy {
        ActivityTestBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }
        setTestViews(1)
    }

    private fun setTestViews(testNumber: Int) {
        binding.apply {
            tvQuestion.text = "savol ${testNumber}"
            radioBtn1.text = "javob 1"
            radioBtn2.text = "javob 2"
            radioBtn3.text = "javob 3"
            radioBtn4.text = "javob 4"
        }
        binding.btnCheck.setOnClickListener {
            binding.btnCheck.visibility = View.GONE
            when {
                binding.radioBtn1.isChecked -> {
                    binding.layoutTrueAns.visibility = View.VISIBLE
                }
                binding.radioBtn2.isChecked -> {
                    binding.layoutFalseAns.visibility = View.VISIBLE
                }
                binding.radioBtn3.isChecked -> {
        //                do taskId 3
                }
                binding.radioBtn4.isChecked -> {
        //                do taskId 4
                }
            }
        }
    }

    fun onRadioButtonClicked(view: View) {
        binding.btnCheck.visibility=View.VISIBLE
    }

    fun onNextButtonClicked(view: View) {
        Toast.makeText(this, "next", Toast.LENGTH_SHORT).show()
    }
}
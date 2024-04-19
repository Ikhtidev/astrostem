package uz.sayfullayeva.astrostem.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.sayfullayeva.astrostem.R
import uz.sayfullayeva.astrostem.databinding.ActivityQuestionsBinding
import uz.sayfullayeva.astrostem.utils.Constants

class QuestionsActivity : AppCompatActivity() {

    private val binding:ActivityQuestionsBinding by lazy {
        ActivityQuestionsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val fileName = "${Constants.ONLY_QUESTIONS}.pdf"

        binding.apply {
            toolbarName.text = Constants.ONLY_QUESTIONS
            btnBack.setOnClickListener {
                finish()
            }
            pdfView.fromAsset(fileName)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(false)
                .defaultPage(0)
                .load()
        }
    }
}
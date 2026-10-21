package uz.sayfullayeva.astrostem.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import uz.sayfullayeva.astrostem.adapters.PdfAdapter
import uz.sayfullayeva.astrostem.databinding.ActivityQuestionsBinding
import uz.sayfullayeva.astrostem.utils.Constants

class QuestionsActivity : AppCompatActivity() {

    private val binding: ActivityQuestionsBinding by lazy {
        ActivityQuestionsBinding.inflate(layoutInflater)
    }

    private var pdfAdapter: PdfAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val fileName = "${Constants.ONLY_QUESTIONS}.pdf"

        binding.apply {
            toolbarName.text = Constants.ONLY_QUESTIONS
            btnBack.setOnClickListener {
                finish()
            }
            pdfAdapter = PdfAdapter(this@QuestionsActivity, fileName)
            rvPdf.layoutManager = LinearLayoutManager(this@QuestionsActivity)
            rvPdf.adapter = pdfAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        pdfAdapter?.close()
    }
}

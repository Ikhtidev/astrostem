package uz.sayfullayeva.astrostem.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import uz.sayfullayeva.astrostem.adapters.PdfAdapter
import uz.sayfullayeva.astrostem.databinding.ActivityPdfViewerBinding
import uz.sayfullayeva.astrostem.utils.Constants.Companion.FILE_NUMBER
import uz.sayfullayeva.astrostem.utils.Constants.Companion.FRAGMENT_TYPE

class PdfViewerActivity : AppCompatActivity() {

    private val binding: ActivityPdfViewerBinding by lazy {
        ActivityPdfViewerBinding.inflate(layoutInflater)
    }

    private var pdfAdapter: PdfAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val fileName = "${(FILE_NUMBER + 1)}.${FRAGMENT_TYPE.name.lowercase()}.pdf"

        binding.apply {
            toolbarName.text = FRAGMENT_TYPE.typeName
            btnBack.setOnClickListener {
                finish()
            }
            pdfAdapter = PdfAdapter(this@PdfViewerActivity, fileName)
            rvPdf.layoutManager = LinearLayoutManager(this@PdfViewerActivity)
            rvPdf.adapter = pdfAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        pdfAdapter?.close()
    }
}

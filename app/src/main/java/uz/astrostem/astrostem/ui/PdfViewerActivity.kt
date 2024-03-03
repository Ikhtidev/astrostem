package uz.astrostem.astrostem.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uz.astrostem.astrostem.databinding.ActivityPdfViewerBinding
import uz.astrostem.astrostem.utils.Constants.Companion.FILE_NUMBER
import uz.astrostem.astrostem.utils.Constants.Companion.FRAGMENT_TYPE

class PdfViewerActivity : AppCompatActivity() {

    private val binding: ActivityPdfViewerBinding by lazy {
        ActivityPdfViewerBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val fileName = "${(FILE_NUMBER + 1)}.${FRAGMENT_TYPE.name.lowercase()}.pdf"

        binding.apply {
            toolbarName.text = FRAGMENT_TYPE.typeName
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
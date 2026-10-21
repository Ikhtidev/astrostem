package uz.sayfullayeva.astrostem.adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.sayfullayeva.astrostem.databinding.ItemPdfPageBinding
import java.io.File

class PdfAdapter(
    private val context: Context,
    private val assetFileName: String
) : RecyclerView.Adapter<PdfAdapter.PdfPageViewHolder>() {

    private var parcelFileDescriptor: ParcelFileDescriptor? = null
    private var pdfRenderer: PdfRenderer? = null
    private var pageCount: Int = 0

    init {
        setupRenderer()
    }

    private fun setupRenderer() {
        try {
            val file = File(context.cacheDir, assetFileName)
            if (!file.exists()) {
                context.assets.open(assetFileName).use { input ->
                    file.outputStream().use { output ->
                        input.copyTo(output)
                    }
                }
            }
            parcelFileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
            parcelFileDescriptor?.let { descriptor ->
                pdfRenderer = PdfRenderer(descriptor)
                pageCount = pdfRenderer?.pageCount ?: 0
            }
        } catch (e: Exception) {
            e.printStackTrace()
            pageCount = 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PdfPageViewHolder {
        val binding = ItemPdfPageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PdfPageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PdfPageViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = pageCount

    fun close() {
        try {
            pdfRenderer?.close()
            parcelFileDescriptor?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    inner class PdfPageViewHolder(private val binding: ItemPdfPageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pageIndex: Int) {
            val renderer = pdfRenderer ?: return
            if (pageIndex < 0 || pageIndex >= renderer.pageCount) return

            var page: PdfRenderer.Page? = null
            try {
                page = renderer.openPage(pageIndex)
                
                // Scale rendering up to 2x for high crispness on HD displays
                val displayMetrics = context.resources.displayMetrics
                val targetWidth = displayMetrics.widthPixels
                val scale = targetWidth.toFloat() / page.width.toFloat()
                val targetHeight = (page.height * scale).toInt()

                val bitmap = Bitmap.createBitmap(
                    targetWidth.coerceAtLeast(1),
                    targetHeight.coerceAtLeast(1),
                    Bitmap.Config.ARGB_8888
                )

                page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
                binding.imgPdfPage.setImageBitmap(bitmap)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                try {
                    page?.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}

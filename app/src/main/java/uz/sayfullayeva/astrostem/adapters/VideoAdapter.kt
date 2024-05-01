package uz.sayfullayeva.astrostem.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.sayfullayeva.astrostem.database.entity.Video
import uz.sayfullayeva.astrostem.databinding.ItemVideoBinding

class VideoAdapter(
    private val videos: List<Video>,
    private val onVideoClicked: (Video) -> Unit
) : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(video: Video) {
            binding.apply {
                videoName.text = video.name
                itemView.setOnClickListener {
                    onVideoClicked(video)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int = videos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.onBind(videos[position])
}
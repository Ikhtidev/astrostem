package uz.sayfullayeva.astrostem.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import uz.sayfullayeva.astrostem.R
import uz.sayfullayeva.astrostem.adapters.VideoAdapter
import uz.sayfullayeva.astrostem.database.entity.Video
import uz.sayfullayeva.astrostem.databinding.FragmentVideosBinding
import uz.sayfullayeva.astrostem.ui.VideoActivity

class VideosFragment : Fragment(R.layout.fragment_videos){

    private var _binding: FragmentVideosBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentVideosBinding.bind(view)

        val videoAdapter = VideoAdapter(createVideos(), onVideoClicked = { video: Video ->
            val intent = Intent(requireActivity(), VideoActivity::class.java)
            intent.putExtra(getString(R.string.videos), video.videoUrl)
            startActivity(intent)
        })
        binding.rvVideo.adapter = videoAdapter
    }

    private fun createVideos(): List<Video> {
        val videos = ArrayList<Video>()

        videos.add(Video("Osmon koordinatalari(rus)", "http://astrostem.uz/wp-content/uploads/2023/09/osmon-koordinatalari-rus.mp4"))
        videos.add(Video("Osmon koordinatalari(o’zbek)", "http://astrostem.uz/wp-content/uploads/2023/09/osmon-koordinatalari-ozbek-1.mp4"))
        videos.add(Video("Osmon sferasi(rus auditoriya)", "http://astrostem.uz/wp-content/uploads/2023/09/osmon-sferasi-rus-auditoriya.mov"))
        videos.add(Video("Oy harakati(o’zbek)", "http://astrostem.uz/wp-content/uploads/2023/09/oy-harakati-ozbek.mp4"))
        videos.add(Video("Vaqtni o’lchash sistemasi(rus)", "http://astrostem.uz/wp-content/uploads/2023/09/vaqtni-olchash-sistemasi-rus.mp4"))
        videos.add(Video("Osmon sferasi(rus)", "http://astrostem.uz/wp-content/uploads/2023/09/osmon-sferasi-rus.mp4"))
        videos.add(Video("Surilma xarita(rus)", "http://astrostem.uz/wp-content/uploads/2023/09/surilma-xarita-rus.mp4"))
        videos.add(Video("Surilma xarita(o’zbek)", "http://astrostem.uz/wp-content/uploads/2023/09/surilma-xarita-ozbek.mov"))
        videos.add(Video("Vaqtni o’lchash sistemasi(o’zbek)", "http://astrostem.uz/wp-content/uploads/2023/09/vaqtni-olchash-sistemasi-ozbek.mp4"))
        videos.add(Video("Yulduzlar atlasi(rus)", "http://astrostem.uz/wp-content/uploads/2023/09/yulduzlar-atlasi-rus.mp4"))
        videos.add(Video("Osmon sferasi(o’zbek)", "http://astrostem.uz/wp-content/uploads/2023/09/osmon-sferasi-ozbek.mp4"))
        videos.add(Video("Osmon koordinatalari(o’zbek)", "http://astrostem.uz/wp-content/uploads/2023/09/osmon-koordinatalari-ozbek.mp4"))

        return videos
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
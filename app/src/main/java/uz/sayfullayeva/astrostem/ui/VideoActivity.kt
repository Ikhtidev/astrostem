package uz.sayfullayeva.astrostem.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import uz.sayfullayeva.astrostem.R
import uz.sayfullayeva.astrostem.databinding.ActivityVideoBinding

class VideoActivity : AppCompatActivity() {

    private val binding: ActivityVideoBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityVideoBinding.inflate(layoutInflater)
    }
    private var player: ExoPlayer? = null
    private var playWhenReady = true
    private var playbackPosition = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }

    override fun onStart() {
        super.onStart()
        if (Build.VERSION.SDK_INT > 23) {
            initializePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        hideSystemUi()
        if ((Build.VERSION.SDK_INT <= 23 || player == null)) {
            initializePlayer()
        }
player!!.addListener(object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                super.onPlayerError(error)
                Toast.makeText(
                    applicationContext,
                    "xatolik: ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()
                player!!.play()
            }
        })
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT <= 23) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Build.VERSION.SDK_INT > 23) {
            releasePlayer()
        }
    }

    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, binding.videoView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    private fun initializePlayer() {
        val videoUrl = intent.getStringExtra(getString(R.string.videos))?:""
        player = ExoPlayer.Builder(this)
            .build()
            .also { exoPlayer ->
                binding.videoView.player = exoPlayer
                val mediaItem = MediaItem.fromUri(videoUrl)
                exoPlayer.setMediaItem(mediaItem,playbackPosition)
                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.prepare()
                exoPlayer.play()
            }

    }

    private fun releasePlayer() {
        player?.let { exoPlayer ->
            playbackPosition = exoPlayer.currentPosition
            playWhenReady = exoPlayer.playWhenReady
            exoPlayer.release()
        }
        player = null
    }
}
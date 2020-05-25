package tm.mr.compactplayerview

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import tm.mr.compactplayerview.extensions.toMediaSource
import tm.mr.compactplayerview.extensions.toMediaSourceOrNull

class PlayerView : PlayerView {

    var exoPlayer: SimpleExoPlayer
    lateinit var mediaSource: MediaSource
    private val userAgent = Util.getUserAgent(context, "CompactPlayerView")
    private val dataSourceFactory = DefaultDataSourceFactory(context, userAgent)
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {

        val trackSelector = DefaultTrackSelector()
        val loadControl = DefaultLoadControl()
        val renderersFactory = DefaultRenderersFactory(context)

        exoPlayer = ExoPlayerFactory.newSimpleInstance(
            context, renderersFactory, trackSelector, loadControl
        )
        player = exoPlayer
    }

    fun play() {
        exoPlayer.volume = 0f
        exoPlayer.prepare(mediaSource)
        exoPlayer.playWhenReady = true
    }

    @Throws(Exception::class)
    fun play(src: Any) {
        when (src) {
            is List<*> -> {
                mediaSource = (src as List<Any>).toMediaSource(context, dataSourceFactory)
                play()
            }
            is Int -> {
                src.toMediaSourceOrNull(context, dataSourceFactory)
                    ?.let {
                        mediaSource = it
                    }
                play()
            }
            is Uri -> {
                mediaSource = src.toMediaSource(dataSourceFactory)
                play()
            }
            is String -> {
                mediaSource = Uri.parse(src).toMediaSource(dataSourceFactory)
                play()
            }
            else -> throw Exception("Unknown source: $src")
        }
    }

    fun release() {
        exoPlayer.stop()
        exoPlayer.release()
    }

}
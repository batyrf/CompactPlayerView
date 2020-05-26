package tm.mr.compactplayerview

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import tm.mr.compactplayerview.extensions.getRawIdOrNull
import tm.mr.compactplayerview.extensions.toMediaSource
import tm.mr.compactplayerview.extensions.toMediaSourceOrNull

class PlayerView : PlayerView {

    var exoPlayer: SimpleExoPlayer
    private var mediaSource: MediaSource? = null

    var onVolumeChangeListener: ((Float) -> Unit)? = null
    var volume: Float = 0f
        get() = exoPlayer.volume
        set(value) {
            field = value
            exoPlayer.volume = field
            onVolumeChangeListener?.invoke(exoPlayer.volume)
        }

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
        initAttrs(attrs)
    }

    private fun initAttrs(attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.CompactPlayerView, 0, 0)
        try {
            volume = ta.getFloat(R.styleable.CompactPlayerView_volume, 0f)
            ta.getString(R.styleable.CompactPlayerView_src)
                ?.split(",")
                ?.map {
                    it.getRawIdOrNull(context) ?: it.trim()
                }?.let {
                    set(it)
                }
            if (ta.getBoolean(R.styleable.CompactPlayerView_autoplay, false))
                play()
        } finally {
            ta.recycle()
        }
    }

    @Throws(Exception::class)
    fun set(src: Any) {
        when (src) {
            is List<*> -> mediaSource = (src as List<Any>).toMediaSource(context, dataSourceFactory)
            is Int -> src.toMediaSourceOrNull(context, dataSourceFactory)?.let { mediaSource = it }
            is Uri -> mediaSource = src.toMediaSource(dataSourceFactory)
            is String -> mediaSource = Uri.parse(src).toMediaSource(dataSourceFactory)
            else -> throw Exception("Unknown source: $src")
        }
        exoPlayer.prepare(mediaSource)
    }

    fun play() {
        exoPlayer.playWhenReady = true
    }

    @Throws(Exception::class)
    fun play(src: Any) {
        set(src)
        play()
    }

    fun release() {
        exoPlayer.stop()
        exoPlayer.release()
    }

}
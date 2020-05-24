package tm.mr.compactplayerview

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class PlayerView : PlayerView {

    var exoPlayer: SimpleExoPlayer
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

    fun play(url: String) {
        val userAgent = Util.getUserAgent(context, "CompactPlayerView")
//        val mediaSource = ExtractorMediaSource
//            .Factory(DefaultDataSourceFactory(context, userAgent))
//            .setExtractorsFactory(DefaultExtractorsFactory())
//            .createMediaSource(Uri.parse(url))

        val mediaSource = HlsMediaSource.Factory(DefaultDataSourceFactory(context, userAgent))
            .createMediaSource(Uri.parse(url))

        exoPlayer.volume = 0f
        exoPlayer.prepare(mediaSource)
        exoPlayer.playWhenReady = true
    }

    fun release() {
        exoPlayer.stop()
        exoPlayer.release()
    }

}
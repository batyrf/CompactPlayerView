package tm.mr.compactplayerview.extensions

import android.content.Context
import android.net.Uri
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.upstream.DataSource

fun List<*>.toMediaSource(context: Context, dataSourceFactory: DataSource.Factory): MediaSource {
    return ConcatenatingMediaSource()
        .apply {
            this@toMediaSource
                .mapNotNull {
                    when (it) {
                        is Int -> it.toMediaSourceOrNull(context, dataSourceFactory)
                        is String -> Uri.parse(it).toMediaSource(dataSourceFactory)
                        is Uri -> it.toMediaSource(dataSourceFactory)
                        else -> null
                    }
                }
                .map {
                    addMediaSource(it)
                }
        }
}
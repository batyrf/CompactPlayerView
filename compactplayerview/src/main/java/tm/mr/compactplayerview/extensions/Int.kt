package tm.mr.compactplayerview.extensions

import android.content.Context
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DataSpec
import com.google.android.exoplayer2.upstream.RawResourceDataSource

fun Int.toMediaSourceOrNull(context: Context, dataSourceFactory: DataSource.Factory): MediaSource? {
    val rawDataSource = RawResourceDataSource(context)
    rawDataSource.open(DataSpec(RawResourceDataSource.buildRawResourceUri(this)))
    return rawDataSource.uri?.toMediaSource(dataSourceFactory)
}
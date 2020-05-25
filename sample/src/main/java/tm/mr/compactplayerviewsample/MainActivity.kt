package tm.mr.compactplayerviewsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv.setItems(
            listOf(
                listOf(
                    "http://gormless.dx.am/videos/coverr-lighting-candle--1581349807482.mp4",
                    R.raw.islands,
                    "/storage/emulated/0/download/test.mp4"),
                "http://gormless.dx.am/videos/coverr-lighting-candle--1581349807482.mp4",
                R.raw.islands,
                "/storage/emulated/0/download/test.mp4",
                "http://217.174.225.146/legacyhls/ch003.m3u8"
            )
        )
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }
}

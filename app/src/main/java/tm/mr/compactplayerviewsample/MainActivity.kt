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
                "http://217.174.225.146/legacyhls/ch000.m3u8",
                "http://217.174.225.146/legacyhls/ch001.m3u8",
                "http://217.174.225.146/legacyhls/ch002.m3u8",
                "http://217.174.225.146/legacyhls/ch003.m3u8",
                "http://217.174.225.146/legacyhls/ch004.m3u8",
                "http://217.174.225.146/legacyhls/ch005.m3u8",
                "http://217.174.225.146/legacyhls/ch006.m3u8"
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

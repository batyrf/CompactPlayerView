package tm.mr.compactplayerviewsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        player.play("http://gormless.dx.am/videos/mama.mp4")

        player.onVolumeChangeListener = {
            tvVolume.text = it.toString()
        }

        volDown.setOnClickListener {
            player.volume -= 0.1f
        }

        volUp.setOnClickListener {
            player.volume += 0.1f
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }
}

import android.content.Context
import android.media.AudioManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_okhttp.*

/**
 * Author: LuoXia
 * Date: 2020/9/22 18:08
 * Description: 学习音频的通路。这里以设置音量大小为例，看他下面是怎么走的
 */
class AudioCodes : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_okhttp)
    }

    /**
     * audio的音频通路
     * 这里以控制
     */
    private fun setAudioVolum() {
        //实例化
        val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        //设置声音
        audioManager.adjustStreamVolume(
            AudioManager.STREAM_MUSIC,
            AudioManager.ADJUST_RAISE,
            AudioManager.FLAG_SHOW_UI
        )

    }

}
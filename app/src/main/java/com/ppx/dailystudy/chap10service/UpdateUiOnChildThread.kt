import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_update_ui_on_child_thread.*

/**
 * Author: LuoXia
 * Date: 2020/9/23 23:28
 * Description: 在子线程中更新ui
 */
class UpdateUiOnChildThread : AppCompatActivity() {

    companion object {
        const val UPDATE_TEXT = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_ui_on_child_thread)

        bt_click_to_change.setOnClickListener {
            //直接这样在子线程里面更新ui肯定是不行的啦
//            Thread(Runnable { tv_content.text = "this moon festival is coming" }).start()

            //于是我们可以通过handler来发送
            val message = Message()
            message.what = UPDATE_TEXT
            handler.sendMessage(message)
        }
    }

    //使用handler做一个简单的异步消息处理，handler的handleMessage这里面的东西都是在主线程操作了
    private val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                UPDATE_TEXT -> {
                    tv_content.text = "the moon festival is coming"
                }
            }
        }
    }
}
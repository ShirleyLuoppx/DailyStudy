import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_okhttp.*
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.xml.sax.InputSource
import org.xml.sax.XMLReader
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream
import java.io.StringReader
import java.lang.Exception
import javax.xml.parsers.SAXParserFactory

/**
 * Author: LuoXia
 * Date: 2020/9/21 20:38
 * Description: 一个okhttp的简单测试类
 */
class OkHttpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_okhttp)

        bt_send_okhttp_request.setOnClickListener {
            sendOkHttpRequest()
        }
    }

    /**
     * 使用okhttp3发送一个网络请求连接到百度首页
     */
    private fun sendOkHttpRequest() {
        Thread(Runnable {
            val okHttpClient = OkHttpClient()

            val getRequest = Request.Builder().url("http://www.baidu.com").build()
            val getResponse = okHttpClient.newCall(getRequest).execute()
            val getStringData = getResponse.body?.string()

            val requestBody = FormBody.Builder()
                .add("name", "ppx")
                .add("pwd", "123123")
                .build()
            val postRequest =
                Request.Builder().url("http://www.baidu.com").post(requestBody).build()
            val postResponse = okHttpClient.newCall(postRequest).execute()

            tv_content.post {
                tv_content.text = postResponse.body?.string()
            }

            postResponse.body?.string()?.let {
                parseXmlWithPull(it)
//                saxParseXmlData(it)
            }

        }).start()
    }

    /**
     * pull方式解析xml数据(需要本地自己搭建一个简单的服务器，从服务器上拉取数据)
     */
    private fun parseXmlWithPull(xmlData: String) {
        try {
            //实例化一个xml的pull方式的解析工厂
            val xmlPullParserFactory = XmlPullParserFactory.newInstance()
            //通过工厂实例化一个xmlpull方式的解析器
            val xmlPullParser = xmlPullParserFactory.newPullParser()
            xmlPullParser.setInput(StringReader(xmlData))

            //获取他的事件类型
            var eventType = xmlPullParser.eventType

            var id = ""
            var name = ""
            var version = ""

            //不是最后一个节点就继续解析
            while (eventType != XmlPullParser.END_DOCUMENT) {

                val nodeName = xmlPullParser.name

                when (eventType) {
                    //开始解析某个节点
                    XmlPullParser.START_TAG -> {
                        when (nodeName) {
                            "id" -> {
                                id = xmlPullParser.nextText()
                            }
                            "name" -> {
                                name = xmlPullParser.nextText()
                            }
                            "version" -> {
                                version = xmlPullParser.nextText()
                            }
                        }
                    }
                    //完成解析某个节点
                    XmlPullParser.END_TAG -> {
                        if (nodeName == "app") {
                            Log.d("ippx", "parseXmlWithPull: id=$id,name=$name,version=$version")
                        }
                    }
                }
                eventType = xmlPullParser.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * sax方式解析xml数据，还有dom解析，这些都是java的基础知识了，我还依稀记得以前上课的时候老师讲过的，只不过上班以后没用过就忘记了
     */
    private fun saxParseXmlData(xmlData: String) {
        val saxParserFactory = SAXParserFactory.newInstance()
        val xmlReader = saxParserFactory.newSAXParser().xmlReader

        xmlReader.contentHandler = SaxParseDefaultHandler()
        xmlReader.parse(InputSource(StringReader(xmlData)))
    }

    /**
     * jsonObject的简单用法
     */
    private fun parseJsonDataByJsonObject(jsonString: String) {
        try {
            val jsonArray = JSONArray(jsonString)
            for (i in 0..jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val id = jsonObject.getString("id")
                val name = jsonObject.getString("name")
                val version = jsonObject.getString("version")
                Log.d("ippx", "parseJsonDataByJsonObject: id=$id,name=$name,version=$version")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
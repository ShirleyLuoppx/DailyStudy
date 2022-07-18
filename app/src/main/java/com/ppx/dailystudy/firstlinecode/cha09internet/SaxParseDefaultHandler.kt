import android.util.Log
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import java.lang.StringBuilder

/**
 * Author: LuoXia
 * Date: 2020/9/21 23:34
 * Description:  此类主要是用于sax方式解析xml数据 的
 * 继承 DefaultHandler
 * 重写 5个方法
 */
class SaxParseDefaultHandler : DefaultHandler() {

    private var nodeName: String? = ""
    private var id: StringBuilder? = null
    private var name: StringBuilder? = null
    private var version: StringBuilder? = null

    override fun startDocument() {
        super.startDocument()

        id = StringBuilder()
        name = StringBuilder()
        version = StringBuilder()
    }

    override fun startElement(
        uri: String?,
        localName: String?,
        qName: String?,
        attributes: Attributes?
    ) {
        super.startElement(uri, localName, qName, attributes)

        nodeName = localName
    }

    override fun characters(ch: CharArray?, start: Int, length: Int) {
        super.characters(ch, start, length)

        when (nodeName) {
            "id" -> {
                id?.append(ch, start, length)
            }
            "name" -> {
                name?.append(ch, start, length)
            }
            "version" -> {
                version?.append(ch, start, length)
            }
        }
    }

    override fun endElement(uri: String?, localName: String?, qName: String?) {
        super.endElement(uri, localName, qName)
        if ("app" == nodeName) {
            Log.d("ippx", "endElement: id=$id,name=$name,version=$version")

            //将长度清空
            id?.setLength(0)
            name?.setLength(0)
            version?.setLength(0)
        }
    }

    override fun endDocument() {
        super.endDocument()
    }

}
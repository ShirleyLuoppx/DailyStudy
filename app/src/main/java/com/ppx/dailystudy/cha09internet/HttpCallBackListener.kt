import java.lang.Exception

/**
 * Author: LuoXia
 * Date: 2020/9/23 23:12
 * Description: 网络请求回调监听
 */
interface HttpCallBackListener {
    fun onSuccess(dataString: String)

    fun onError(e: Exception)
}
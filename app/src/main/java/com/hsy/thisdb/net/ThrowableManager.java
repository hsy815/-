package com.hsy.thisdb.net;

import android.content.Context;
import android.net.ParseException;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.util.net
 * @创始人: hsy
 * @创建时间: 2018/11/22 13:26
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/11/22 13:26
 * @修改描述:
 */
public class ThrowableManager {

    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static void ErrorException(Context context, Throwable e) {
        Log.i("tag", "e.toString = " + e.toString());

        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                    mToast(context, "哎呀～走丢啦 |･ω･｀)");//404找不到服务器
                    break;
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                    mToast(context, "不好～服务器睡着啦 Ծ‸Ծ");//服务器错误
                    break;
                default:
                    mToast(context, "咦～网络开小差了");//网络错误
                    break;
            }

        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            mToast(context, "老大，小的解析不了啊");//解析错误
        } else if (e instanceof ConnectException) {
            mToast(context, "老大，人家不给访问...");//连接失败
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            mToast(context, "老大，您的证书失效了...");//证书验证失败
        } else if (e instanceof java.net.SocketTimeoutException) {
            mToast(context, "老大，等不到了...");//请求超时
        } else {
            mToast(context, "老大，请求失败了...");
        }
    }

    private static void mToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}

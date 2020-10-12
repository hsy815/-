package com.hsy.thisdb.net;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

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
public class ReceivedCookiesInterceptor implements Interceptor {

    private Context mContext;
    SharedPreferences sharedPreferences;

    public ReceivedCookiesInterceptor(Context context) {
        this.mContext = context;
        sharedPreferences = context.getSharedPreferences("cookie", Context.MODE_PRIVATE);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (chain == null)
            Log.d("http", "Receivedchain == null");
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("set-cookie").isEmpty()) {
            final StringBuffer cookieBuffer = new StringBuffer();
            Observable.from(originalResponse.headers("set-cookie"))
                    .map(new Func1<String, String>() {
                        @Override
                        public String call(String s) {
                            String[] cookieArray = s.split(";");
                            return cookieArray[0];
                        }
                    })
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String cookie) {
                            cookieBuffer.append(cookie).append(";");
                        }
                    });
            Log.e("tag", cookieBuffer.toString());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("cookie", cookieBuffer.toString());
            editor.commit();
        }
        return originalResponse;
    }
}

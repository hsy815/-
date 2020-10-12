package com.hsy.thisdb.net;

import com.hsy.thisdb.MyApplication;

import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
public class NetRetrofit {
    private static final int DEFAULT_TIMEOUT = 120;
        private static String URL = "http://192.168.1.10:1000/";
//    public static String URL = "http://10.5.35.220:80/";

    static ApiService reRetrofit(String mUrl) {
        OkHttpClient client = new OkHttpClient.Builder()
                .proxy(Proxy.NO_PROXY)//不使用代理 以防被抓包（测试时需要注意）
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))//打印log
                .addInterceptor(new ReceivedCookiesInterceptor(MyApplication.getInstent().getApplicationContext()))
                .addInterceptor(new AddCookiesInterceptor(MyApplication.getInstent().getApplicationContext(), ""))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }
}

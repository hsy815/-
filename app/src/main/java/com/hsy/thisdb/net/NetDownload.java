package com.hsy.thisdb.net;

import android.content.Context;

import com.hsy.thisdb.util.DownloadUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.net
 * @创始人: hsy
 * @创建时间: 2018/12/13 10:30
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/12/13 10:30
 * @修改描述:
 */
public class NetDownload {

    //    public static String URL = "http://192.168.1.10:80/";
    public static String URL = "http://10.5.35.220:80/";
    private static final int DEFAULT_TIMEOUT = 120;
    private ApiService apiService;

    private ApiService getDownloadService(String mUrl, DownloadProgressListener listener, File file) {
        DownloadProgressInterceptor interceptor = new DownloadProgressInterceptor(listener, file);
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mUrl)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        return apiService;
    }

    //下载
    public void downloadFile(final Context context, Subscription subscription, DownloadProgressListener listener, String url, String path1, String path2, final File file) {
        Observable observable = getDownloadService(url, listener, file).
                downloadFile(path1, path2)
                .unsubscribeOn(Schedulers.io())
                .map(new Func1<ResponseBody, InputStream>() {
                    @Override
                    public InputStream call(ResponseBody responseBody) {
                        return responseBody.byteStream();
                    }
                })
                .observeOn(Schedulers.computation())
                .doOnNext(new Action1<InputStream>() {
                    @Override
                    public void call(InputStream inputStream) {
                        try {
                            DownloadUtil.writeFile(inputStream, file);
                        } catch (IOException e) {
                            e.printStackTrace();
                            ThrowableManager.ErrorException(context, e);
                        }
                    }
                });
        toSubscribe(observable, (Subscriber) subscription);
    }

    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                // 被观察者的实现线程
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                // 观察者的实现线程
                .subscribe(s);
    }

}

package com.hsy.thisdb.net;

import com.hsy.thisdb.util.DownloadUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.net
 * @创始人: hsy
 * @创建时间: 2018/12/13 10:32
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/12/13 10:32
 * @修改描述:
 */
public class DownloadProgressInterceptor implements Interceptor {
    private long startpos;
    private DownloadProgressListener listener;

    public DownloadProgressInterceptor(DownloadProgressListener listener, File file) {
        this.listener = listener;
        startpos = DownloadUtil.readFile(file);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        //断点续传
        Request request = chain.request().newBuilder()
                .addHeader("Range", "bytes=" + startpos + "-")
                .build();
        Response originalResponse = chain.proceed(request);
        return originalResponse.newBuilder()
                .body(new DownloadResponseBody(originalResponse.body(), listener))
                .build();
    }
}

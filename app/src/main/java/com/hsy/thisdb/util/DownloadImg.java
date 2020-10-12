package com.hsy.thisdb.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.hsy.thisdb.net.DownloadProgressListener;
import com.hsy.thisdb.net.NetDownload;
import com.hsy.thisdb.net.ThrowableManager;

import java.io.File;

import rx.Subscriber;
import rx.Subscription;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.util
 * @创始人: hsy
 * @创建时间: 2018/12/13 11:08
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/12/13 11:08
 * @修改描述:
 */
public class DownloadImg {

    private String down_file = Environment.getExternalStorageDirectory() + "/ThisDb/img.zip";//下载的压缩包文件
    private String dirName = Environment.getExternalStorageDirectory() + "/ThisDb/img";//解压后文件地址

    private NetDownload netDownload;
    private Context context;
    private File outputFile;
    private zipListener zipListener;

    public DownloadImg(Context context, DownloadImg.zipListener zipListener) {
        netDownload = new NetDownload();
        this.context = context;
        this.zipListener = zipListener;
    }

    public void downFile(String baseUrl, String path1, String path2, DownloadProgressListener listener) {
        outputFile = new File(down_file);
        FileUtil.deleteDir(outputFile);
        Subscription subscription = new Subscriber() {
            @Override
            public void onCompleted() {
                zip();
            }

            @Override
            public void onError(Throwable e) {
                ThrowableManager.ErrorException(context, e);
                zipListener.error();
            }

            @Override
            public void onNext(Object o) {

            }
        };
        netDownload.downloadFile(context, subscription, listener, baseUrl, path1, path2, outputFile);
    }

    private void zip() {
        File f = new File(dirName);
        //不存在创建
        if (!f.exists()) {
            f.mkdir();
        }
        boolean isZip = ZipUtil.getInstance().unZip(f.getAbsolutePath(), outputFile.getAbsolutePath());
        if (isZip) {
            Log.e("tag", "解压完成");
            zipListener.down();
        } else {
            zipListener.error();
            Log.e("tag", "解压失败");
        }
    }

    public interface zipListener {
        void down();

        void error();
    }
}

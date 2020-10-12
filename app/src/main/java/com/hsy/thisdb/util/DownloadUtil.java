package com.hsy.thisdb.util;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.util
 * @创始人: hsy
 * @创建时间: 2018/12/13 10:17
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/12/13 10:17
 * @修改描述:
 */
public class DownloadUtil {

    /**
     * 写入文件
     * 断点续传
     *
     * @param in
     * @param file
     */
    public static void writeFile(InputStream in, File file) throws IOException {
        RandomAccessFile savedFile = null;
        long ltest = 0;
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        if (file != null && file.exists()) {
            ltest = file.length();
        }
        if (in != null) {
            savedFile = new RandomAccessFile(file, "rw");
            savedFile.seek(ltest);
            byte[] buffer = new byte[1024 * 128];
            int len = -1;
            while ((len = in.read(buffer)) != -1) {
                savedFile.write(buffer, 0, len);
            }
            in.close();
        } else {
            Log.e("tag", "下载失败");
        }
    }

    /**
     * 读取文件长度
     */
    public static long readFile(File file) {
        if (file != null && file.exists()) {
            return file.length();
        } else {
            return 0;
        }
    }

}

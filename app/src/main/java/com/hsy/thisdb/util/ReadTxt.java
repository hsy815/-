package com.hsy.thisdb.util;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hsy.thisdb.eitity.ApiClass;
import com.hsy.thisdb.eitity.ApiData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.util
 * @创始人: hsy
 * @创建时间: 2018/12/7 16:45
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/12/7 16:45
 * @修改描述:
 */
public class ReadTxt {

    /**
     * 生成文件
     *
     * @param filePath 文件夹
     * @param fileName 文件名
     * @param json     初始数据
     * @return
     */
    public static File makeFilePath(String filePath, String fileName, String json) {
        File file = null;
        makeRootDirectory(filePath);//生成文件夹
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();

                //创建文件后写入初始链接数据
                FileOutputStream outStream = new FileOutputStream(file);
                outStream.write(json.getBytes());
                outStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 生成文件夹
     *
     * @param filePath
     */
    private static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e + "");
        }
    }

    /**
     * 读取txt文件数据
     *
     * @param context
     * @param fname
     * @return
     */
    private static String loadFromSDFile(Context context, String fname) {
        fname = "/ThisDb/" + fname;
        String result = null;
        try {
            File f = new File(Environment.getExternalStorageDirectory() + fname);
            int length = (int) f.length();
            byte[] buff = new byte[length];
            FileInputStream fin = new FileInputStream(f);
            fin.read(buff);
            fin.close();
            result = new String(buff, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 获取请求链接
     *
     * @return map 获取到的txt文件所有数据
     */
    public static Map<String, String> getURL(Context context) {
        String url = loadFromSDFile(context, "thisDb.txt");
        if (TextUtils.isEmpty(url))
            return null;
//
//        url = url.replaceAll("\r|\n", "");
//        if (url.length() < 7)
//            return "";
//
//        int a = url.indexOf(":", 7);
//        if (a <= 0)
//            return "";
//
//        String url_ip = url.substring(7, a);
//
//        String ip = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
//                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
//                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
//                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";//限定输入格式
//        Pattern p = Pattern.compile(ip);
//        Matcher m = p.matcher(url_ip);
//        if (!m.matches())
//            return "";
//
//        return url;

        Map<String, String> map = new HashMap<>();
        try {
            Gson gson = new Gson();
            ApiData apiData = gson.fromJson(url, ApiData.class);
            for (ApiClass apiClass : apiData.getDataList()) {
                map.put(apiClass.getKey(), apiClass.getValue());
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
        return map;
    }

}

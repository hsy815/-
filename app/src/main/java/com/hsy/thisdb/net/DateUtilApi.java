package com.hsy.thisdb.net;

import okhttp3.ResponseBody;
import rx.Observable;

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
public class DateUtilApi {

    public static Observable<ResponseBody> reGetSql(String url, String path1, String path2, String userName) {
        return NetRetrofit.reRetrofit(url).reGetSql(path1, path2, userName);
    }

    public static Observable<ResponseBody> reIsDowned(String url, String path1, String path2) {
        return NetRetrofit.reRetrofit(url).reIsDowned(path1, path2);
    }

    public static Observable<ResponseBody> downloadFile(String url, String path1, String path2) {
        return NetRetrofit.reRetrofit(url).downloadFile(path1, path2);
    }

    public static Observable<ResponseBody> upLoadDaily(String url, String path1, String path2, String dataList, String userName) {
        return NetRetrofit.reRetrofit(url).upLoadDaily(path1, path2, dataList, userName);
    }

    public static Observable<ResponseBody> login(String url,String path1, String path2, String userName, String psw) {
        return NetRetrofit.reRetrofit(url).login(path1, path2,userName,psw);
    }
}

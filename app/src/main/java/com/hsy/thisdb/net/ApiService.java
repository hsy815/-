package com.hsy.thisdb.net;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
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
public interface ApiService {

    /**
     * 更新数据
     * AjaxSql/Get.cspx
     *
     * @return
     */
    @GET("{path1}/{path2}")
    Observable<ResponseBody> reGetSql(
            @Path("path1") String path1,
            @Path("path2") String path2,
            @Query("userName") String userName);

    /**
     * 更新完反馈
     * AjaxPicture/IsDowned.cspx
     *
     * @return
     */
    @GET("{path1}/{path2}")
    Observable<ResponseBody> reIsDowned(
            @Path("path1") String path1,
            @Path("path2") String path2
    );

    /**
     * 下载照片
     * AjaxPicture/Down.cspx
     *
     * @return
     */
    @Streaming
    @GET("{path1}/{path2}")
    Observable<ResponseBody> downloadFile(
            @Path("path1") String path1,
            @Path("path2") String path2
    );

    /**
     * 上传考察信息
     * AjaxDailyInspection/Add.cspx
     *
     * @return
     */
    @FormUrlEncoded
    @HTTP(method = "POST", path = "{path1}/{path2}", hasBody = true)
    Observable<ResponseBody> upLoadDaily(
            @Path("path1") String path1,
            @Path("path2") String path2,
            @Field("dataList") String dataList,
            @Field("userName") String userName);

    @Streaming
    @GET("{path1}/{path2}")
    Observable<ResponseBody> login(
            @Path("path1") String path1,
            @Path("path2") String path2,
            @Query("userName") String userName,
            @Query("password") String passWord
    );
}

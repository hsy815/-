package com.hsy.thisdb.net;

import com.google.gson.Gson;
import com.hsy.thisdb.eitity.ApiClass;
import com.hsy.thisdb.eitity.ApiData;

import java.util.ArrayList;
import java.util.List;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.net
 * @创始人: hsy
 * @创建时间: 2019/6/21 11:30
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2019/6/21 11:30
 * @修改描述:
 */
public class ApiUtil {

    public static final String SQL = "Sql";
    public static final String IS_DOWNED = "IsDowned";
    public static final String DOWN = "Down";
    public static final String DAILY_INSPECTION = "DailyInspection";
    public static final String HTTP = "Http";
    public static final String LOGIN = "Login";

    /**
     * 初始链接或者链接模板转为json
     *
     * @return
     */
    public static String getApiString() {
        String json = "";
        Gson gson = new Gson();
        json = gson.toJson(getApiData(), ApiData.class);
        return json;
    }

    /**
     * 初始链接或者说是链接模板
     *
     * @return
     */
    private static ApiData getApiData() {
        List<ApiClass> list = new ArrayList<>();
    //    list.add(new ApiClass(HTTP, "http://192.168.1.10:1000"));
        list.add(new ApiClass(HTTP, "http://10.5.35.157:80"));
        list.add(new ApiClass(SQL, "Export~GetSql"));
        list.add(new ApiClass(IS_DOWNED, "AjaxPicture~IsDowned.cspx"));
        list.add(new ApiClass(DOWN, "Export~DownPicture"));
        list.add(new ApiClass(DAILY_INSPECTION, "Common~SaveDailyInspection"));
        list.add(new ApiClass(LOGIN, "user~login"));
        return new ApiData(list);
    }
}

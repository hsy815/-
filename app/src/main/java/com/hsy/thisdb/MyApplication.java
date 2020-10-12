package com.hsy.thisdb;

import android.app.Application;

import com.hsy.thisdb.activity.MainActivity;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb
 * @创始人: hsy
 * @创建时间: 2018/11/20 13:41
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/11/20 13:41
 * @修改描述:
 */
public class MyApplication extends Application {

    private static MyApplication application;
    private MainActivity mainActivity;

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public static MyApplication getInstent() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public void finishMain() {
        if (this.mainActivity != null)
            this.mainActivity.finish();
    }
}

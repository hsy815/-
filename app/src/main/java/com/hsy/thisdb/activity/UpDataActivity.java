package com.hsy.thisdb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.hsy.thisdb.MyApplication;
import com.hsy.thisdb.R;
import com.hsy.thisdb.db_control.DbControl;
import com.hsy.thisdb.eitity.DailyInspectionData;
import com.hsy.thisdb.eitity.DailyInspectionDataList;
import com.hsy.thisdb.net.ApiUtil;
import com.hsy.thisdb.net.DateUtilApi;
import com.hsy.thisdb.net.DownloadProgressListener;
import com.hsy.thisdb.net.ThrowableManager;
import com.hsy.thisdb.util.DownloadImg;
import com.hsy.thisdb.util.ReadTxt;
import com.hsy.thisdb.util.SPutils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class UpDataActivity extends AppCompatActivity {

    @BindView(R.id.loading)
    ImageView loading;
    private List<CompositeSubscription> subscriptionList = new ArrayList<>();
    private String mBaseEntity;
    private DbControl dbControl;
    private MyApplication myApplication;
    private Map<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_up_data);
        ButterKnife.bind(this);
        myApplication = MyApplication.getInstent();
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(this).load(R.mipmap.timg_gif2).apply(options).into(loading);
        initData();
    }

    private void initData() {
        String sign = getIntent().getStringExtra("sign");
        dbControl = DbControl.getInstent(this);
        dbControl.addDailyInspectionTable();//新加表
        newTxt();
        if ("upData".equals(sign)) {
            ReGetSql();//获取更新
        } else {
            upLoad();//上传录入数据
        }
    }

    /**
     * 新建文件 存储链接
     */
    private void newTxt() {
        String path = Environment.getExternalStorageDirectory() + "/ThisDb/";
        ReadTxt.makeFilePath(path, "thisDb.txt", ApiUtil.getApiString());
        //本项目所有网络请求的链接都来自thisDb.txt文件，次map就是读取文件中的所有数据
        map = ReadTxt.getURL(this);
    }

    /**
     * 获取更新数据
     */
    private void ReGetSql() {
        if (map == null) {
            Toast.makeText(this, "请检查thisDb.txt文件中是否存在链接", Toast.LENGTH_LONG).show();
            return;
        }
        String path = map.get(ApiUtil.SQL);
        if (TextUtils.isEmpty(path)) {
            Toast.makeText(this, "请检查thisDb.txt文件中是否存在" + ApiUtil.SQL + "的链接", Toast.LENGTH_LONG).show();
            return;
        }
        String[] strings = path.split("~");
//        Log.e("Tag_3", "ip=" + strings[0] + "/" + strings[1] + "/" + strings[2]);

        String spUserName = SPutils.getData(this, "UserName", "admin").toString();

        CompositeSubscription subscription = new CompositeSubscription();
        subscription.add(DateUtilApi.reGetSql(map.get(ApiUtil.HTTP), strings[0], strings[1], spUserName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {
                        if (mBaseEntity != null && subBaseEntity(mBaseEntity)) {
                            deleteTable();
                            setDate();
                        } else {
                            Toast.makeText(UpDataActivity.this, "下载数据不完整，请重试", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ThrowableManager.ErrorException(UpDataActivity.this, e);
                        handler.sendEmptyMessageDelayed(4, 3000);
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            mBaseEntity = responseBody.string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }));
        subscriptionList.add(subscription);
    }

    /**
     * 检测是否获取到所有SQL数据
     *
     * @param mBaseStr sql数据
     * @return
     */
    private boolean subBaseEntity(String mBaseStr) {
        String strEnd = mBaseStr.substring((mBaseStr.length() - 3));
        return ("end".equals(strEnd));
    }

    /**
     * 把SQL数据写入数据库
     */
    private void setDate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mBaseEntity = mBaseEntity.substring(0, (mBaseEntity.length() - 4));
                String[] strings = mBaseEntity.split("~");
                for (int i = 0; i < strings.length; i++) {
                    if (!TextUtils.isEmpty(strings[i]) && !"\r\n".equals(strings[i]) && !"\r".equals(strings[i]) && !"\n".equals(strings[i])) {
                        Log.e("tag", "strings" + i + "=" + strings[i]);
                        dbControl.addAll(strings[i]);
                    }
                }
                handler.obtainMessage(1).sendToTarget();
            }
        }).start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1://下载更新图片
                    downLoad();
                    break;
                case 2://上传成功
                    Toast.makeText(UpDataActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case 4://上传或更新失败
                    finish();
                    break;
                default://更新成功
//                    UpDataSeccess();//暂时不用了
                    myApplication.finishMain();
                    Toast.makeText(UpDataActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UpDataActivity.this, MainActivity.class));
                    finish();
                    break;
            }
        }
    };

    /**
     * 更新成功后给服务器反馈
     */
    private void UpDataSeccess() {
        if (map == null) {
            Toast.makeText(this, "请检查thisDb.txt文件中是否存在链接", Toast.LENGTH_LONG).show();
            return;
        }
        String path = map.get(ApiUtil.IS_DOWNED);
        if (TextUtils.isEmpty(path)) {
            Toast.makeText(this, "请检查thisDb.txt文件中是否存在" + ApiUtil.IS_DOWNED + "的链接", Toast.LENGTH_LONG).show();
            return;
        }
        String[] strings = path.split("~");
//        Log.e("Tag_3", "ip=" + strings[0] + "/" + strings[1] + "/" + strings[2]);

        CompositeSubscription subscription = new CompositeSubscription();
        subscriptionList.add(subscription);
        subscription.add(DateUtilApi.reIsDowned(map.get(ApiUtil.HTTP), strings[0], strings[1])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                    }
                })
        );
    }

    /**
     * 下载更新图片
     */
    private void downLoad() {
        if (map == null) {
            Toast.makeText(this, "请检查thisDb.txt文件中是否存在链接", Toast.LENGTH_LONG).show();
            return;
        }
        String path = map.get(ApiUtil.DOWN);
        if (TextUtils.isEmpty(path)) {
            Toast.makeText(this, "请检查thisDb.txt文件中是否存在" + ApiUtil.DOWN + "的链接", Toast.LENGTH_LONG).show();
            return;
        }
        String[] strings = path.split("~");
//        Log.e("Tag_3", "ip=" + strings[0] + "/" + strings[1] + "/" + strings[2]);

        new DownloadImg(UpDataActivity.this, new DownloadImg.zipListener() {
            @Override
            public void down() {
                handler.sendEmptyMessageDelayed(0, 300);
            }

            @Override
            public void error() {
                Toast.makeText(UpDataActivity.this, "图片解压失败", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).downFile(map.get(ApiUtil.HTTP), strings[0], strings[1], new DownloadProgressListener() {
            @Override
            public void onStartDownload(long length) {

            }

            @Override
            public void onProgress(int progress) {

            }

            @Override
            public void onFail(String errorInfo) {

            }
        });
    }

    /**
     * 删除所有数据库中的数据
     */
    private void deleteTable() {
        dbControl.DaleAll("DELETE FROM AgeList");
        dbControl.DaleAll("DELETE FROM AgeStructure");
        dbControl.DaleAll("DELETE FROM AnnualAppraisal");
        dbControl.DaleAll("DELETE FROM DivisionWork");
        dbControl.DaleAll("DELETE FROM EduExperience");
        dbControl.DaleAll("DELETE FROM FamilyMember");
        dbControl.DaleAll("DELETE FROM GenderStructure");
        dbControl.DaleAll("DELETE FROM Inspection");
        dbControl.DaleAll("DELETE FROM Institutions");
        dbControl.DaleAll("DELETE FROM investigate0");
        dbControl.DaleAll("DELETE FROM KeyProjectAndWork");
        dbControl.DaleAll("DELETE FROM MajorDegree");
        dbControl.DaleAll("DELETE FROM Personal");
        dbControl.DaleAll("DELETE FROM RewardPunish");
        dbControl.DaleAll("DELETE FROM SortTable");
        dbControl.DaleAll("DELETE FROM SysDepartment");
        dbControl.DaleAll("DELETE FROM WorkExperience");
        dbControl.DaleAll("DELETE FROM SysUser");
        dbControl.DaleAll("DELETE FROM " + DbControl.TABLE_NAME);
    }

    /**
     * 上传数据
     */
/*
    private void upLoad() {
        if (dbControl.getAllTableName().contains(DbControl.TABLE_NAME)) {
            String sql = "select * from " + DbControl.TABLE_NAME;
            List<DailyInspectionData> dataList = dbControl.getDailyInspection(sql);
            if (dataList != null) {
                Gson gson = new Gson();
                String json = gson.toJson(new DailyInspectionDataList(dataList));
                submitDailyInspection(json);
            }
        } else {
            handler.sendEmptyMessageDelayed(4, 300);
        }
    }
*/

    /**
     * 上传数据
     */
    private void upLoad() {
        String sql = "select * from investigate0 where padSign = 1";
        List<DailyInspectionData> dataList = dbControl.getDailyInspectionData(sql);
        if (dataList != null) {
            Gson gson = new Gson();
            String json = gson.toJson(new DailyInspectionDataList(dataList));
            submitDailyInspection(json);
        } else {
            handler.sendEmptyMessageDelayed(4, 300);
        }
    }

    /**
     * 提交上传数据
     *
     * @param json
     */
    private void submitDailyInspection(String json) {
        if (map == null) {
            Toast.makeText(this, "请检查thisDb.txt文件中是否存在链接", Toast.LENGTH_LONG).show();
            return;
        }
        String path = map.get(ApiUtil.DAILY_INSPECTION);
        if (TextUtils.isEmpty(path)) {
            Toast.makeText(this, "请检查thisDb.txt文件中是否存在" + ApiUtil.DAILY_INSPECTION + "的链接", Toast.LENGTH_LONG).show();
            return;
        }
        String[] strings = path.split("~");
//        Log.e("Tag_3", "ip=" + strings[0] + "/" + strings[1] + "/" + strings[2]);

        String spUserName = SPutils.getData(this, "UserName", "").toString();

        CompositeSubscription subscription = new CompositeSubscription();//ReadTxt.getURL(this)
        subscription.add(DateUtilApi.upLoadDaily(map.get(ApiUtil.HTTP), strings[0], strings[1], json, spUserName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {
                        handler.sendEmptyMessageDelayed(2, 300);
                    }

                    @Override
                    public void onError(Throwable e) {
                        ThrowableManager.ErrorException(UpDataActivity.this, e);
                        handler.sendEmptyMessageDelayed(4, 3000);
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        handler.sendEmptyMessageDelayed(2, 300);
                    }
                }));
        subscriptionList.add(subscription);
    }

    @Override
    protected void onStop() {
        super.onStop();
        for (CompositeSubscription subscription :
                subscriptionList) {
            if (subscription != null && subscription.hasSubscriptions()) {
                subscription.unsubscribe();
            }
        }
    }
}

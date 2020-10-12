package com.hsy.thisdb.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.hsy.thisdb.R;
import com.hsy.thisdb.db_control.DbControl;
import com.hsy.thisdb.net.ApiUtil;
import com.hsy.thisdb.net.DateUtilApi;
import com.hsy.thisdb.net.ThrowableManager;
import com.hsy.thisdb.util.ReadTxt;
import com.hsy.thisdb.util.SPutils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class LoginActivity extends AppCompatActivity {

    private final int SDK_PERMISSION_REQUEST = 127;

    private DbControl dbControl;//数据库控制类

    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.cancel)
    TextView cancel;
    @BindView(R.id.ok)
    TextView ok;

    private boolean isPermission = false;

    private String[] permission = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };

    private Map<String, String> map;
    private CompositeSubscription subscription;
    private String sUserName = "";
    private String sPassWord = "";
    private String mBaseEntity = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        checkDangerousPermissions(permission);
        dbControl = DbControl.getInstent(this);
        newTxt();
        sUserName = SPutils.getData(this, "UserName", "").toString();
        sPassWord = SPutils.getData(this, "PassWord", "").toString();
        userName.setText(sUserName);
        password.setText(sPassWord);
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

    @OnClick({R.id.cancel, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                finish();
                break;
            case R.id.ok:
                login();
                break;
        }
    }

    private void login() {
        if (!isPermission) {
            Toast.makeText(this, "请先到设置打开相关权限", Toast.LENGTH_SHORT).show();
            return;
        }

        String mUsername = userName.getText().toString();
        String mPassword = password.getText().toString();
        if (TextUtils.isEmpty(mUsername)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(mPassword)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        loginRe(mUsername, mPassword);

//        以下代码为本地登录验证
//        String Sql = "SELECT * FROM SysUser WHERE UserName = '" + mUsername + "'";
//        List<UserData> userDataList = dbControl.getUserData(Sql);
//        if (userDataList != null && userDataList.size() > 0) {
//            String psw = userDataList.get(0).getPassword();
//            if (mPassword.equals(psw)) {
//                loginSuccess(mUsername, mPassword);
//            } else {
//                Toast.makeText(this, "用户名密码不匹配", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            Toast.makeText(this, "该用户不存在", Toast.LENGTH_SHORT).show();
//        }
    }

    /**
     * 网络请求后台认证登陆
     */
    private void loginRe(String userName, String Psw) {
        if (map == null) {
            Toast.makeText(this, "请检查thisDb.txt文件中是否存在链接", Toast.LENGTH_LONG).show();
            return;
        }
        String path = map.get(ApiUtil.LOGIN);
        if (TextUtils.isEmpty(path)) {
            Toast.makeText(this, "请检查thisDb.txt文件中是否存在" + ApiUtil.LOGIN + "的链接", Toast.LENGTH_LONG).show();
            return;
        }
        String[] strings = path.split("~");
        subscription = new CompositeSubscription();
        subscription.add(DateUtilApi.login(map.get(ApiUtil.HTTP), strings[0], strings[1], userName, Psw)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {
                        loginResult();
                    }

                    @Override
                    public void onError(Throwable e) {
                        ThrowableManager.ErrorException(LoginActivity.this, e);
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

    }

    private void loginResult() {
        if ("true".equals(mBaseEntity)) {
            Intent intent = new Intent(LoginActivity.this, UpDataActivity.class);
            intent.putExtra("sign", "upData");
            startActivity(intent);
        } else {
            Toast.makeText(this, "用户不存在或用户名密码不匹配", Toast.LENGTH_LONG).show();
        }
        finish();
    }

    private void loginSuccess(String userName, String passWord) {
//        String spUserName = SPutils.getData(this, "UserName", "").toString();
//        if (userName.equals(spUserName)) {
//            startActivity(new Intent(this, MainActivity.class));
//        } else {
        SPutils.saveData(this, "UserName", userName);
        SPutils.saveData(this, "PassWord", passWord);
        Intent intent = new Intent(LoginActivity.this, UpDataActivity.class);
        intent.putExtra("sign", "upData");
        startActivity(intent);
//        }
        finish();
    }

    /**
     * 检查权限是否授权
     *
     * @param permissions 需要授权的权限
     */
    private void checkDangerousPermissions(String[] permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> noPermission = new ArrayList<String>();
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                    noPermission.add(permission);
                }
            }
            if (noPermission.size() > 0) {
                requestPermissions(noPermission.toArray(new String[noPermission.size()]), SDK_PERMISSION_REQUEST);
            } else {
                isPermission = true;
            }
        }
    }

    /**
     * 权限授权回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        ArrayList<Integer> noGrantResults = new ArrayList();
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                noGrantResults.add(result);
            }
        }
        isPermission = noGrantResults.size() == 0;
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (subscription != null && subscription.hasSubscriptions()) {
            subscription.unsubscribe();
        }
    }
}

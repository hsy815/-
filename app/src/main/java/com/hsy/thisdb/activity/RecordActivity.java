package com.hsy.thisdb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hsy.thisdb.R;
import com.hsy.thisdb.db_control.DbControl;
import com.hsy.thisdb.eitity.DailyInspectionData;
import com.hsy.thisdb.eitity.Personal;
import com.hsy.thisdb.util.DateUtil;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecordActivity extends AppCompatActivity {

    @BindView(R.id.record_back)
    TextView recordBack;
    @BindView(R.id.record_save)
    TextView recordSave;
    @BindView(R.id.record_name)
    TextView recordName;
    @BindView(R.id.record_daily_inspection)
    EditText recordDailyInspection;
    @BindView(R.id.record_record_name)
    EditText recordRecordName;
    @BindView(R.id.record_date)
    TextView recordDate;
    @BindView(R.id.record_work)
    TextView recordWork;
    @BindView(R.id.record_daily_evaluate)
    EditText recordDailyEvaluate;

    private DbControl dbControl;//数据库控制类
    private Personal personal;
    private DailyInspectionData mInspectionData;//修改数据
    private boolean isUpDate;//是否是修改

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_record);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
        dbControl = DbControl.getInstent(this);
        personal = (Personal) getIntent().getSerializableExtra("personal");
        mInspectionData = (DailyInspectionData) getIntent().getSerializableExtra("mInspectionData");
        if (personal != null) {
            isUpDate = false;
            recordName.setText(personal.getFullname());
            recordWork.setText(personal.getWorkUnit());
        }
        if (mInspectionData != null) {
            isUpDate = true;
            recordName.setText(mInspectionData.getFullname());
            recordWork.setText(mInspectionData.getWorkUnit());
            recordDailyEvaluate.setText(mInspectionData.getRecordEvaluate());
            recordDailyInspection.setText(mInspectionData.getDailyInspection());
        }
        recordName.setFocusableInTouchMode(false);
        recordDate.setText(DateUtil.getDate2_(new Date(System.currentTimeMillis())));
        dbControl.addDailyInspectionTable();//新建数据表
    }

    @OnClick({R.id.record_back, R.id.record_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.record_back:
                finish();
                break;
            case R.id.record_save:
                saveData();
                break;
        }
    }

    private void saveData() {
        DailyInspectionData inspectionData = getDailyInspection();
        if (inspectionData != null) {
//            dbControl.addDailyInspectionData(inspectionData);
            if (isUpDate) {
                dbControl.upDateDailyInspection0(inspectionData);
                Toast.makeText(RecordActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RecordActivity.this, MainActivity.class);
                setResult(2, intent);
            } else {
                dbControl.addInvestigate0Data(inspectionData);
                Toast.makeText(RecordActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    }

    private DailyInspectionData getDailyInspection() {
        String recordDaily = recordDailyInspection.getText().toString();
//        String recordName = recordRecordName.getText().toString();
        String recordEvaluate = recordDailyEvaluate.getText().toString();
        String recordDates = recordDate.getText().toString();

        if (TextUtils.isEmpty(recordDaily)) {
            Toast.makeText(RecordActivity.this, "请输入现实表现", Toast.LENGTH_SHORT).show();
            return null;
        }

        if (TextUtils.isEmpty(recordEvaluate)) {
            recordEvaluate = "";
        }

//        if (TextUtils.isEmpty(recordName)) {
//            Toast.makeText(RecordActivity.this, "请输入记录人", Toast.LENGTH_SHORT).show();
//            return null;
//        }

        if (isUpDate)
            return new DailyInspectionData(
                    mInspectionData.getFullname(),
                    mInspectionData.getPersonalIdCard(),
                    getdata(recordDates),
                    recordDaily,
                    recordEvaluate,
                    mInspectionData.getWorkUnit());
        else
            return new DailyInspectionData(
                    personal.getFullname(),
                    personal.getPersonalIdCard(),
                    getdata(recordDates),
                    recordDaily,
                    recordEvaluate,
                    personal.getWorkUnit());
    }

    private String getdata(String str) {
        str = str.substring(0, str.length() - 3);
        str = str.replace("-", ".");
        return str;
    }
}

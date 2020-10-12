package com.hsy.thisdb.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hsy.thisdb.R;
import com.hsy.thisdb.adapter.DetailsFamilyAdapter;
import com.hsy.thisdb.adapter.DetailsWorkAdapter;
import com.hsy.thisdb.adapter.PopWindowAdapter;
import com.hsy.thisdb.db_control.DbControl;
import com.hsy.thisdb.eitity.AnnualAppraisal;
import com.hsy.thisdb.eitity.DetailsWorkExperience;
import com.hsy.thisdb.eitity.FamilyMember;
import com.hsy.thisdb.eitity.Personal;
import com.hsy.thisdb.eitity.RewardPunish;
import com.hsy.thisdb.eitity.WorkExperience;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Deprecated
public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.details_xingming)
    TextView detailsXingming;
    @BindView(R.id.details_xingbie)
    TextView detailsXingbie;
    @BindView(R.id.details_chushengnianyue)
    TextView detailsChushengnianyue;
    @BindView(R.id.details_minzu)
    TextView detailsMinzu;
    @BindView(R.id.details_jiguan)
    TextView detailsJiguan;
    @BindView(R.id.details_chushengdi)
    TextView detailsChushengdi;
    @BindView(R.id.details_rudangshijian)
    TextView detailsRudangshijian;
    @BindView(R.id.details_gongzuoshijian)
    TextView detailsGongzuoshijian;
    @BindView(R.id.details_jiankang)
    TextView detailsJiankang;
    @BindView(R.id.details_zhuanye)
    TextView detailsZhuanye;
    @BindView(R.id.details_zhuanchang)
    TextView detailsZhuanchang;
    @BindView(R.id.details_quanrizhi)
    TextView detailsQuanrizhi;
    @BindView(R.id.details_zaizhi)
    TextView detailsZaizhi;
    @BindView(R.id.details_quanrizhi_yuanxiao)
    TextView detailsQuanrizhiYuanxiao;
    @BindView(R.id.details_zaizhi_yuanxiao)
    TextView detailsZaizhiYuanxiao;
    @BindView(R.id.details_zhiwu_1)
    TextView detailsZhiwu1;
    @BindView(R.id.details_zhiwu_2)
    TextView detailsZhiwu2;
    @BindView(R.id.details_zhiwu_3)
    TextView detailsZhiwu3;

    @BindView(R.id.details_jiangcheng)
    TextView detailsJiangcheng;
    @BindView(R.id.details_kaohe)
    TextView detailsKaohe;
    @BindView(R.id.details_ren_mian)
    TextView detailsRenMian;
    @BindView(R.id.details_cyc)
    RecyclerView detailsCyc;
    @BindView(R.id.details_chengbao)
    TextView detailsChengbao;
    @BindView(R.id.details_chengbao_shi)
    TextView detailsChengbaoShi;
    @BindView(R.id.details_back)
    TextView detailsBack;
    @BindView(R.id.details_title)
    TextView detailsTitle;
    @BindView(R.id.details_jianli)
    RecyclerView detailsJianli;
    @BindView(R.id.details_img)
    ImageView detailsImg;
    @BindView(R.id.details_more)
    TextView detailsMore;
    @BindView(R.id.details_rel)
    RelativeLayout detailsRel;

    private Personal personal;
    private DbControl dbControl;
    private DetailsFamilyAdapter familyAdapter;
    private DetailsWorkAdapter detailsWorkAdapter;
    private RecyclerView popwin_cyc;
    private PopWindowAdapter popWindowAdapter;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
        popupWindow = new PopupWindow(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        detailsCyc.setLayoutManager(layoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.custom_divider));
        detailsCyc.addItemDecoration(decoration);
        familyAdapter = new DetailsFamilyAdapter(this);
        detailsCyc.setAdapter(familyAdapter);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        detailsJianli.setLayoutManager(layoutManager2);
        detailsWorkAdapter = new DetailsWorkAdapter(this);
        detailsJianli.setAdapter(detailsWorkAdapter);

        initData();
    }

    private void initData() {
        dbControl = DbControl.getInstent(this);
        personal = (Personal) getIntent().getSerializableExtra("personal");

        setDataToUI();
    }

    private void setDataToUI() {

        if (personal == null) return;
        detailsXingming.setText(personal.getFullname());
        detailsXingbie.setText(personal.getGender());
        detailsChushengnianyue.setText(personal.getBirthday());
        detailsMinzu.setText(personal.getEthnicity());
        detailsJiguan.setText(personal.getNativeOrigin());
        detailsChushengdi.setText(personal.getNativePlace());
        detailsRudangshijian.setText(personal.getJoinDate());
        detailsGongzuoshijian.setText(personal.getWorkDate());
        detailsJiankang.setText(personal.getHealthyStatus());
        detailsZhuanye.setText(personal.getMajorDegree());
        detailsZhuanchang.setText(personal.getSpeciality());
        detailsQuanrizhi.setText((personal.getFullBackgroundDegree().replace("<br/>", "\n")));
        detailsQuanrizhiYuanxiao.setText(personal.getFullSchoolMajor());
        detailsZaizhi.setText((personal.getGoOnBackgroundDegree().replace("<br/>", "\n")));
        detailsZaizhiYuanxiao.setText(personal.getGoOnSchoolMajor());
        detailsZhiwu1.setText(personal.getWorkUnit() + " " + personal.getSectionName() + " " + personal.getDuty());

        String img_url = Environment.getExternalStorageDirectory()
                + "/ThisDb/img/"
                + (TextUtils.isEmpty(personal.getPersonalIdCard()) ? "0" : personal.getPersonalIdCard())
                + ".jpg";
        Glide.with(DetailsActivity.this).load(img_url)
                .into(detailsImg);

        setWorkExperience();
        setRewardPunish();
        setAnnualAppraisal();
        setFamilyMember();
    }

    /**
     * 简历
     */
    private void setWorkExperience() {
        List<DetailsWorkExperience> detailsWorkExperienceList = new ArrayList<>();
        List<WorkExperience> list = dbControl.WorkExperienceSelect("select * from WorkExperience where PersonalIdCard like '" + personal.getPersonalIdCard() + "'");
        for (WorkExperience workExperience : list) {
            detailsWorkExperienceList.add(new DetailsWorkExperience(workExperience.getStartDate()
                    + " - " + workExperience.getEndDate(), workExperience.getWorkUnit()
                    + workExperience.getSectionName()
                    + workExperience.getDuty()
                    + (TextUtils.isEmpty(workExperience.getMemo()) ? "" : "(" + workExperience.getMemo() + ")")));
        }
        detailsWorkAdapter.setDetailsWorkExperiences(detailsWorkExperienceList);
    }

    /**
     * 奖惩情况
     */
    private void setRewardPunish() {
        String str = "";
        List<RewardPunish> list = dbControl.RewardPunishSelect("select * from RewardPunish where PersonalIdCard like '" + personal.getPersonalIdCard() + "'");
        for (RewardPunish rewardPunish : list) {
            str += rewardPunish.getMemo() + "\n";
        }
        detailsJiangcheng.setText(str);
    }

    /**
     * 年度考核结果
     */
    private void setAnnualAppraisal() {
        String str = "";
        List<AnnualAppraisal> list = dbControl.AnnualAppraisalSelect("select * from AnnualAppraisal where PersonalIdCard like '" + personal.getPersonalIdCard() + "'");
        for (AnnualAppraisal appraisal : list) {
            str += appraisal.getDate() + "年度" + appraisal.getDegree() + "\n";
        }
        detailsKaohe.setText(str);
    }

    /**
     * 家庭主要成员及重要社会关系
     */
    private void setFamilyMember() {
        List<FamilyMember> list = dbControl.FamilyMemberSelect("select * from FamilyMember where PersonalIdCard like '" + personal.getPersonalIdCard() + "'");
        familyAdapter.setFamilyList(list);
    }

    @OnClick({R.id.details_back, R.id.details_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.details_back:
                finish();
                break;
            case R.id.details_more:
                Intent intent = new Intent(DetailsActivity.this, MoreActivity.class);
                intent.putExtra("more_sql", personal.getPersonalIdCard());
                startActivity(intent);
//                showPop(detailsMore);
                break;
        }
    }

    private void showPop(View btnTools) {
        View mainView = LayoutInflater.from(this).inflate(R.layout.popwin, null);
        popwin_cyc = mainView.findViewById(R.id.popwin_cyc);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        popwin_cyc.setLayoutManager(layoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.custom_divider));
        popwin_cyc.addItemDecoration(decoration);
        popWindowAdapter = new PopWindowAdapter(getPopWinData(), this);
        popwin_cyc.setAdapter(popWindowAdapter);
        popWindowAdapter.setOnPopwinListener(new PopWindowAdapter.OnPopwinListener() {
            @Override
            public void popwin(String text) {
                Intent intent = new Intent(DetailsActivity.this, MoreActivity.class);
                intent.putExtra("more_sql", personal.getPersonalIdCard());
                startActivity(intent);
                popupWindow.dismiss();
            }
        });
        // 为了避免部分机型不显示，我们需要重新设置一下宽高
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setContentView(mainView);
        //设置显示隐藏动画
        popupWindow.setAnimationStyle(R.style.AnimTools);
        //设置背景透明
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x0000));
        // 设置pop可点击，为false点击事件无效，默认为true
        popupWindow.setTouchable(true);
        // 设置点击pop外侧消失，默认为false；在focusable为true时点击外侧始终消失
        popupWindow.setOutsideTouchable(true);
        //设置默认获取焦点
        popupWindow.setFocusable(true);
        //以某个控件的x和y的偏移量位置开始显示窗口
        popupWindow.showAsDropDown(btnTools, 0, 0);
    }

    private List<String> getPopWinData() {
        List<String> stringList = new ArrayList<>();
        stringList.add("平时考察");
//        stringList.add("专项考察");
        return stringList;
    }
}

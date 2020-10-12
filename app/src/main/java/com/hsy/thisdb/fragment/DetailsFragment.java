package com.hsy.thisdb.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hsy.thisdb.R;
import com.hsy.thisdb.activity.MainActivity;
import com.hsy.thisdb.adapter.DetailsFamilyAdapter;
import com.hsy.thisdb.adapter.DetailsWorkAdapter;
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
import butterknife.Unbinder;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.fragment
 * @创始人: hsy
 * @创建时间: 2018/12/20 18:39
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/12/20 18:39
 * @修改描述:
 */
public class DetailsFragment extends Fragment {

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
    @BindView(R.id.details_img)
    ImageView detailsImg;
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
    @BindView(R.id.details_jianli)
    RecyclerView detailsJianli;
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
    Unbinder unbinder;
    @BindView(R.id.details_is_cyc)
    RecyclerView detailsIsCyc;

    private Personal personal;
    private List<Personal> personalList;
    private DbControl dbControl;
    private String PersonalIdCard;
    private DetailsFamilyAdapter familyAdapter;
    private DetailsFamilyAdapter familyAdapterIs;
    private DetailsWorkAdapter detailsWorkAdapter;
    private MainActivity mainActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
        personal = mainActivity.getPersonals();
        if (personal == null) {
            PersonalIdCard = mainActivity.getmPersonalIdCard();
        } else {
            PersonalIdCard = personal.getPersonalIdCard();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        unbinder = ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    private void initUI() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mainActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        detailsCyc.setLayoutManager(layoutManager);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(mainActivity);
        layoutManager3.setOrientation(LinearLayoutManager.VERTICAL);
        detailsIsCyc.setLayoutManager(layoutManager3);
        DividerItemDecoration decoration = new DividerItemDecoration(mainActivity, DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.custom_divider));
        detailsCyc.addItemDecoration(decoration);
        detailsIsCyc.addItemDecoration(decoration);
        familyAdapter = new DetailsFamilyAdapter(mainActivity);
        familyAdapterIs = new DetailsFamilyAdapter(mainActivity);
        detailsCyc.setAdapter(familyAdapter);
        detailsIsCyc.setAdapter(familyAdapterIs);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(mainActivity);
        layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        detailsJianli.setLayoutManager(layoutManager2);
        detailsWorkAdapter = new DetailsWorkAdapter(mainActivity);
        detailsJianli.setAdapter(detailsWorkAdapter);

        initData();
    }

    private void initData() {
        dbControl = DbControl.getInstent(mainActivity);
        if (!TextUtils.isEmpty(PersonalIdCard)) {
            personalList = dbControl.select("select * from Personal where PersonalIdCard = '" + PersonalIdCard + "'");
            personal = personalList.get(0);
            setDataToUI();
        }
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
        Glide.with(mainActivity).load(img_url)
                .into(detailsImg);

        setWorkExperience();
        setRewardPunish();
        setAnnualAppraisal();
        setFamilyMember();
        setIsFamilyMember();
    }

    /**
     * 简历
     */
    private void setWorkExperience() {
        List<DetailsWorkExperience> detailsWorkExperienceList = new ArrayList<>();
        List<WorkExperience> list = dbControl.WorkExperienceSelect("select * from WorkExperience where PersonalIdCard like '" + personal.getPersonalIdCard() + "' order by StartDate asc");
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
        List<FamilyMember> list = dbControl.FamilyMemberSelect("select * from FamilyMember where PersonalIdCard like '" + personal.getPersonalIdCard() + "' and IsFamily = '是' ");
        familyAdapter.setFamilyList(list);
    }

    /**
     * 家庭主要成员及重要社会关系
     */
    private void setIsFamilyMember() {
        List<FamilyMember> list = dbControl.FamilyMemberSelect("select * from FamilyMember where PersonalIdCard like '" + personal.getPersonalIdCard() + "' and IsFamily = '否' ");
        familyAdapterIs.setFamilyList(list);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

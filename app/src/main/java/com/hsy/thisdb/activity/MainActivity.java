package com.hsy.thisdb.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hsy.thisdb.MyApplication;
import com.hsy.thisdb.R;
import com.hsy.thisdb.adapter.DailyInspectionAdapter;
import com.hsy.thisdb.adapter.HomeLeftAdapter;
import com.hsy.thisdb.adapter.HomeTitleAdapter;
import com.hsy.thisdb.adapter.InspectionAdapter;
import com.hsy.thisdb.adapter.MoreAdapter;
import com.hsy.thisdb.adapter.NewAgeAndSexAdapter;
import com.hsy.thisdb.adapter.NewInstitutionsAdapter;
import com.hsy.thisdb.adapter.NewPersonalAdapter;
import com.hsy.thisdb.adapter.PopWindowAdapter;
import com.hsy.thisdb.adapter.SysDepartmentAdapter;
import com.hsy.thisdb.db_control.DbControl;
import com.hsy.thisdb.eitity.AgeAndGender;
import com.hsy.thisdb.eitity.DailyInspectionData;
import com.hsy.thisdb.eitity.DataBack;
import com.hsy.thisdb.eitity.Inspection;
import com.hsy.thisdb.eitity.Institutions;
import com.hsy.thisdb.eitity.NewSysDepartment;
import com.hsy.thisdb.eitity.Peacetime;
import com.hsy.thisdb.eitity.Personal;
import com.hsy.thisdb.eitity.SysDepartment;
import com.hsy.thisdb.fragment.ComprehensiveFragment;
import com.hsy.thisdb.fragment.DetailsFragment;
import com.hsy.thisdb.net.ApiUtil;
import com.hsy.thisdb.util.AnimationUtils;
import com.hsy.thisdb.util.HomeHelpDbUtil;
import com.hsy.thisdb.util.HomeHelpUtil;
import com.hsy.thisdb.util.ReadTxt;
import com.hsy.thisdb.util.TitleUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ComprehensiveFragment.ComprehensiveSelectSql, HomeHelpUtil.HomeHelpUtilClick {

    /**
     * <input type="text" name="WorkUnit" class="input-text f-l" placeholder="单位名称" />
     * <input type="text" name="Fullname" class="input-text f-l " placeholder="姓名" />
     * <input type="text" name="StartDate" class="input-text w-140 f-l " placeholder="起始时间" />
     * <input type="text" name="EndDate" class="input-text w-140 f-l " placeholder="终止时间" />
     * <input type="checkbox" value="中共党员" name="Politics" />	政治面貌
     * <input type="checkbox" value="民主党派,群众" name="Politics" />
     * <input type="radio" name="Gender" value="男" />	性别
     * <input type="radio" name="Gender" value="女" />
     * <input type="radio" name="Gender" value="" />（全部）
     * <input type="checkbox" value="硕士及以上" name="EduDegree" />	学历
     * <input type="checkbox" value="本科" name="EduDegree" />
     * <input type="checkbox" value="大专" name="EduDegree" />
     * <input type="checkbox" value="大专以下" name="EduDegree" />
     * <input type="text" name="Major" class="f-l input-text" /></td>--%>
     * <input value="公务员" type="checkbox" name="Identity" />	身份类别
     * <input value="参公" type="checkbox" name="Identity" />
     * <input value="事业" type="checkbox" name="Identity" />
     * <input value="正科" type="checkbox" name="DutyDegree" />	职级
     * <input value="副科" type="checkbox" name="DutyDegree" />
     * <input value="中层正职" type="checkbox" name="DutyDegree" />
     * <input value="中层副职" type="checkbox" name="DutyDegree" />
     * <input type="text" name="Major" class="f-l input-text " style="width :284px" placeholder="请输入查询内容" />	查询内容
     * <input value="major" type="checkbox" class="ml-5" name="MajorTable" />
     * <input value="speciality" type="checkbox" name="MajorTable" />
     * <input value="work" type="checkbox" name="MajorTable" />
     */

    private static final int SDK_PERMISSION_REQUEST = 100;
    @BindView(R.id.new_home_icon)
    ImageView newHomeIcon;
    @BindView(R.id.new_home_cyc)
    RecyclerView newHomeCyc;
    @BindView(R.id.new_main_left_cyc)
    RecyclerView newMainLeftCyc;
    @BindView(R.id.new_main_right_cyc)
    RecyclerView newMainRightCyc;
    @BindView(R.id.new_main_frame)
    FrameLayout newMainFrame;
    @BindView(R.id.main_rel)
    RelativeLayout mainRel;
    @BindView(R.id.new_main_left_tv1)
    TextView newMainLeftTv1;
    @BindView(R.id.new_main_left_tv2)
    TextView newMainLeftTv2;
    @BindView(R.id.new_main_left_cyc2)
    RecyclerView newMainLeftCyc2;
    @BindView(R.id.new_main_left_img1)
    ImageView newMainLeftImg1;
    @BindView(R.id.new_main_left_img2)
    ImageView newMainLeftImg2;
    @BindView(R.id.new_main_left_img0)
    ImageView newMainLeftImg0;
    @BindView(R.id.new_main_left_tv0)
    TextView newMainLeftTv0;
    @BindView(R.id.new_home_back)
    TextView newHomeBack;
    @BindView(R.id.new_main_left_lin)
    NestedScrollView newMainLeftLin;
    @BindView(R.id.new_home_leader)
    TextView newHomeLeader;

    private MyApplication application;
    private Stack<DataBack> backStack;
    private FragmentTransaction transaction;
    private ComprehensiveFragment comprehensiveFragment;
    private DetailsFragment detailsFragment;
    private PopWindowAdapter popWindowAdapter;

    private DbControl dbControl;//数据库控制类
    private HomeHelpUtil homeHelpUtil;//辅助类
    private HomeHelpDbUtil homeHelpDbUtil;//数据库操作辅助类
    private Personal personals;
    private String TitleSelection = "所有单位";//记录选中的title
    private String SysDepartmentID = "";//单位id = deptId or DeptID
    private String strWorkUnit = "";//单位名称
    private String mPersonalIdCard = "";//身份证
    private String mPersonalIdCardTime = "";//记录从详情页点到平时考察的标记  "0"没有身份证id直接显示  "1"有身份证id显示
    private String mComprehensive = "";//记录综合查询 ""不需要做任何处理  "0"代表进入列表  "1"进入详情
    private String mComprehensiveSql = "";//记录综合查询 sql
    private String ParentId = "";//分类  "" 所有   10 机关部门和单位    50  乡镇街道
    private int PersonalIdPosition;//记录基本信息页被选中的位置
    private HomeTitleAdapter homeTitleAdapter;//头部选项控制器
    private HomeLeftAdapter homeLeftAdapter1;//左边列表1控制器
    private HomeLeftAdapter homeLeftAdapter2;//左边列表2控制器
    private Map<Object, Boolean> mMapSignLeft;//左边列表标记
    private DividerItemDecoration decoration;//绿色分割线
    private DividerItemDecoration decoration1;//白色分割线
    private String rankSign = "领导班子";//领导班子\中层干部

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        application = MyApplication.getInstent();
        application.setMainActivity(this);
        initUI();
    }

    private void initUI() {
        getPersimmions();
        backStack = new Stack<>();
        dbControl = DbControl.getInstent(this);
        homeHelpUtil = HomeHelpUtil.getInstent(this);
        homeHelpDbUtil = new HomeHelpDbUtil();
        homeHelpUtil.setHomeHelpUtilClick(this);
        comprehensiveFragment = new ComprehensiveFragment();
        detailsFragment = new DetailsFragment();
        newTxt();
        setFragment();
        setTitleUI();
        setLeftUI();
        setRightUI();
    }

    private void setTitleUI() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        newHomeCyc.setLayoutManager(layoutManager);
        homeTitleAdapter = new HomeTitleAdapter(TitleUtil.getHomeTitle(), this);
        newHomeCyc.setAdapter(homeTitleAdapter);
        homeTitleAdapter.setOnHomeTitleListener(new HomeTitleAdapter.OnHomeTitleListener() {
            @Override
            public void title_text(int position, View view, String text) {
                homeTitleAdapter.changeImageVisable(position);
                homeHelpUtil.HomeTitleClick(view, text);
            }
        });
        homeTitleAdapter.changeImageVisable(0);
    }

    /**
     * 新建文件 存储链接
     */
    private void newTxt() {
        String path = Environment.getExternalStorageDirectory() + "/ThisDb/";
        ReadTxt.makeFilePath(path, "thisDb.txt", ApiUtil.getApiString());
    }

    private void setLeftUI() {
        mMapSignLeft = new HashMap<>();
        mMapSignLeft.put("all", false);
        mMapSignLeft.put("office", false);
        List<SysDepartment> sysDepartments1 = homeHelpDbUtil.getSysDepartment(dbControl, "10");
        for (SysDepartment sysDepartment : sysDepartments1) {
            mMapSignLeft.put(sysDepartment.getId(), false);
        }
        mMapSignLeft.put("Township", false);
        List<SysDepartment> sysDepartments2 = homeHelpDbUtil.getSysDepartment(dbControl, "50");
        for (SysDepartment sysDepartment : sysDepartments2) {
            mMapSignLeft.put(sysDepartment.getId(), false);
        }

        newMainLeftCyc.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                //解决ScrollView里存在多个RecyclerView时滑动卡顿的问题
                //如果你的RecyclerView是水平滑动的话可以重写canScrollHorizontally方法
                return false;
            }
        });
        DividerItemDecoration decoration1 = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        decoration1.setDrawable(ContextCompat.getDrawable(this, R.drawable.custom_divider));
        newMainLeftCyc.addItemDecoration(decoration1);
        homeLeftAdapter1 = new HomeLeftAdapter(this, sysDepartments1);
        newMainLeftCyc.setAdapter(homeLeftAdapter1);
        homeLeftAdapter1.setOnHomeLeftListener(new HomeLeftAdapter.OnHomeLeftListener() {
            @Override
            public void left_text(SysDepartment sysDepartment) {
                SysDepartmentID = sysDepartment.getId() + "";
                strWorkUnit = sysDepartment.getName();
                homeLeftAdapter1.setmMapSignLeft(TitleUtil.setSign(mMapSignLeft, SysDepartmentID));
                homeLeftAdapter2.setmMapSignLeft(TitleUtil.setSign(mMapSignLeft, SysDepartmentID));
                setUpLeftBtn(3);
                ParentId = "";
                requestLeftSelect();
            }
        });

        newMainLeftCyc2.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        DividerItemDecoration decoration2 = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        decoration2.setDrawable(ContextCompat.getDrawable(this, R.drawable.custom_divider));
        newMainLeftCyc2.addItemDecoration(decoration2);
        homeLeftAdapter2 = new HomeLeftAdapter(this, sysDepartments2);
        newMainLeftCyc2.setAdapter(homeLeftAdapter2);
        homeLeftAdapter2.setOnHomeLeftListener(new HomeLeftAdapter.OnHomeLeftListener() {
            @Override
            public void left_text(SysDepartment sysDepartment) {
                SysDepartmentID = sysDepartment.getId() + "";
                strWorkUnit = sysDepartment.getName();
                homeLeftAdapter1.setmMapSignLeft(TitleUtil.setSign(mMapSignLeft, SysDepartmentID));
                homeLeftAdapter2.setmMapSignLeft(TitleUtil.setSign(mMapSignLeft, SysDepartmentID));
                setUpLeftBtn(3);
                ParentId = "";
                requestLeftSelect();
            }
        });
    }

    private void setRightUI() {
        decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.custom_divider));
        decoration1 = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        decoration1.setDrawable(ContextCompat.getDrawable(this, R.drawable.white_bg));
        setSysDepartment();
    }

    private void setNewMainRightCycLAndD() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        newMainRightCyc.setLayoutManager(layoutManager);
        newMainRightCyc.removeItemDecoration(decoration);
        newMainRightCyc.removeItemDecoration(decoration1);
        newMainRightCyc.addItemDecoration(decoration);
        newMainRightCyc.setPadding(0, 0, 0, 0);
    }

    /**
     * 基本信息查询
     *
     * @param deptId
     * @param ParentId
     */
    private void setPersonalAdapter(String deptId, String ParentId, String mRankSign) {
        List<Personal> list = new ArrayList<>();
        list.add(new Personal(strWorkUnit, "标题"));
        list.add(new Personal("姓名", "性别", "出生年月", "工作",
                "单位", "及职务", "全日制教育\n学历",
                "全日制教育\n专业", "在职教育\n专业",
                "在职教育学历", "任现职时长",
                "在本单位时长", "参加工作\n时间", "政治面貌"));
        list.addAll(homeHelpDbUtil.getPersonal(dbControl, deptId, ParentId, mRankSign));
        NewPersonalAdapter personalAdapter = new NewPersonalAdapter(this, list);
        setNewMainRightCycLAndD();
        newMainRightCyc.setAdapter(personalAdapter);
        personalAdapter.setOnItemClickListener(new NewPersonalAdapter.OnItemClickListener() {
            @Override
            public void Item(int i, Personal personal) {
                if (i != 1) {
                    PersonalIdPosition = i;
                    addDetailFragment(personal, "");
                }
            }
        });
        personalAdapter.setOnItemLongClickListener(new NewPersonalAdapter.OnItemLongClickListener() {
            @Override
            public void Item(int i, Personal personal) {
                Intent intent = new Intent(MainActivity.this, RecordActivity.class);
                intent.putExtra("personal", personal);
                startActivity(intent);
            }
        });
//        newMainRightCyc.scrollToPosition(PersonalIdPosition);//之前被选中的置顶
    }

    /**
     * 平时考察
     *
     * @param personalIdCard
     * @param DeptID
     */
    private void setMoreAdapter(String personalIdCard, String DeptID, String mRankSign) {
        MoreAdapter moreAdapter = new MoreAdapter(this);
        List<Peacetime> peacetimeList = new ArrayList<>();
        peacetimeList.add(new Peacetime("身份证", strWorkUnit, "单位及职务", "出生年月", "性别", "考察时间", "综合评价", "现实表现", "考察类型"));
        peacetimeList.add(new Peacetime("身份证", "姓名", "单位及职务", "出生年月", "性别", "考察时间", "综合评价", "现实表现", "考察类型"));
        peacetimeList.addAll(homeHelpDbUtil.getinvestigate0(dbControl, personalIdCard, DeptID, mRankSign));
        moreAdapter.setPeacetimeList(peacetimeList);
        moreAdapter.setOnMoreItemClick(new MoreAdapter.OnMoreItemClick() {
            @Override
            public void moreItem(Peacetime peacetime) {
                if ("1".equals(mPersonalIdCardTime)) {
                    mPersonalIdCardTime = "";
                } else {
                    mPersonalIdCardTime = "0";
                }
                addDetailFragment(null, peacetime.getPersonalIdCard());
            }
        });
        setNewMainRightCycLAndD();
        newMainRightCyc.setAdapter(moreAdapter);
    }

    /**
     * 年龄性别结构
     *
     * @param deptId
     * @param ParentId
     */
    private void setAgeAndSex(String deptId, String ParentId) {
        NewAgeAndSexAdapter andSexAdapter = new NewAgeAndSexAdapter(this);
        List<AgeAndGender> list = new ArrayList<>();
        list.add(new AgeAndGender("", 0, 0, 0, 0, 0, 0, 0));
        list.addAll(homeHelpDbUtil.getAgeAndGender(dbControl, deptId, ParentId));
        andSexAdapter.setGenderList(list);
        setNewMainRightCycLAndD();
        newMainRightCyc.setAdapter(andSexAdapter);
    }

    /**
     * 日常考察数据表
     *
     * @param deptId
     * @param ParentId
     */
    private void setDailyInspection(final String deptId, final String ParentId, final String mRankSign) {
        final DailyInspectionAdapter inspectionAdapter = new DailyInspectionAdapter(this);
        List<DailyInspectionData> list = new ArrayList<>();
        list.add(new DailyInspectionData("姓名", "", "日期", "日常考察", "综合评价", ""));
        list.add(new DailyInspectionData("姓名", "", "日期", "日常考察", "综合评价", ""));
        if (dbControl.getAllTableName().contains(DbControl.TABLE_NAME)) {
            list.addAll(homeHelpDbUtil.getInvestigate0(dbControl, deptId, ParentId, "", "", "", mRankSign));
        }
        inspectionAdapter.setDataList(list);
        setNewMainRightCycLAndD();
        newMainRightCyc.setAdapter(inspectionAdapter);
        inspectionAdapter.setOnClickSubmit(new DailyInspectionAdapter.OnClickSubmit() {
            @Override
            public void data(String name, String start, String end) {
                List<DailyInspectionData> listSelect = new ArrayList<>();
                listSelect.add(new DailyInspectionData("姓名", "", "日期", "日常考察", "综合评价", ""));
                listSelect.add(new DailyInspectionData("姓名", "", "日期", "日常考察", "综合评价", ""));
                if (dbControl.getAllTableName().contains(DbControl.TABLE_NAME)) {
                    listSelect.addAll(homeHelpDbUtil.getInvestigate0(dbControl, deptId, ParentId, name, start, end, mRankSign));
                }
                inspectionAdapter.setDataList(listSelect);
            }
        });
        inspectionAdapter.setOnLongClick(new DailyInspectionAdapter.OnLongClick() {
            @Override
            public void data(DailyInspectionData inspectionData) {
                selectDialog(dbControl, inspectionData);
            }
        });
    }

    /**
     * 干部编制情况
     *
     * @param deptId
     * @param ParentId
     */
    private void setInstitutions(String deptId, String ParentId) {
        NewInstitutionsAdapter institutionsAdapter = new NewInstitutionsAdapter(this);
        List<Institutions> list = new ArrayList<>();
        list.add(new Institutions(0, "", "", "", "", "", "", "", "", "", 0, 0, 0, 0, 0, 0));
        list.addAll(homeHelpDbUtil.getInstitutions(dbControl, deptId, ParentId));
        institutionsAdapter.setInstitutionsList(list);
        setNewMainRightCycLAndD();
        newMainRightCyc.setAdapter(institutionsAdapter);
    }

    /**
     * 督查信息
     *
     * @param personalIdCard
     * @param deptId
     */
    private void setInspection(String personalIdCard, String deptId, String mRankSign) {
        InspectionAdapter inspectionAdapter = new InspectionAdapter(this);
        List<Inspection> list = new ArrayList<>();
        list.add(new Inspection(strWorkUnit, "", "", "", "", "", "", "", 0, "", "", ""));
        list.add(new Inspection("姓名", "", "工作单位及职务", "填报时间", "房产信息", "股票信息", "办企业情况", "督查信息", 0, "", "", "信访信息"));
        list.addAll(homeHelpDbUtil.getInspection(dbControl, personalIdCard, deptId, mRankSign));
        inspectionAdapter.setInspectionList(list);
//        inspectionAdapter.setOnInspectionItemClick(new InspectionAdapter.OnInspectionItemClick() {
//            @Override
//            public void InspectionItem(Inspection inspection) {
//                if ("1".equals(mPersonalIdCardTime)) {
//                    mPersonalIdCardTime = "";
//                } else {
//                    mPersonalIdCardTime = "0";
//                }
//                addDetailFragment(null, inspection.getPersonalIdCard());
//            }
//        });
        setNewMainRightCycLAndD();
        newMainRightCyc.setAdapter(inspectionAdapter);
    }

    /**
     * 所有单位
     */
    private void setSysDepartment() {
        final SysDepartmentAdapter sysDepartmentAdapter = new SysDepartmentAdapter(this);
        List<NewSysDepartment> sysDepartmentList = homeHelpDbUtil.getNewSysDepartment(dbControl);
        for (NewSysDepartment sysDepartment :
                sysDepartmentList) {
            if ((sysDepartment.getId() + "").equals(SysDepartmentID)) {
                sysDepartment.setSelect(true);
            }
        }
        sysDepartmentAdapter.setList(sysDepartmentList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
        gridLayoutManager.setSpanSizeLookup(
                new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        int type = newMainRightCyc.getAdapter().getItemViewType(position);
                        if (type == SysDepartmentAdapter.TYPE_ONE)
                            return 5;
                        else
                            return 1;
                    }
                });
        sysDepartmentAdapter.setOnItemClickListener(new SysDepartmentAdapter.OnItemClickListener() {
            @Override
            public void Item(int i, NewSysDepartment newSysDepartment) {
                sysDepartmentAdapter.changeImageVisable(i);
                SysDepartmentID = newSysDepartment.getId() + "";
                strWorkUnit = newSysDepartment.getName();
                TitleSelection = "基本情况";
                homeTitleAdapter.changeImageVisable(1);
                setPersonalAdapter(SysDepartmentID, "", rankSign);
            }
        });
        newMainRightCyc.setLayoutManager(gridLayoutManager);
        newMainRightCyc.removeItemDecoration(decoration1);
        newMainRightCyc.removeItemDecoration(decoration);
        newMainRightCyc.addItemDecoration(decoration1);
        newMainRightCyc.setPadding(50, 0, 0, 0);
        newMainRightCyc.setAdapter(sysDepartmentAdapter);
    }

    /**
     * Title选项返回sql
     *
     * @param str
     */
    @Override
    public void home_sql(String str) {
//        Log.e("tag", "home_sql=" + str);
        TitleSelection = str;
        newHomeBack.setVisibility(View.GONE);
        if ("所有单位".equals(str)) {
            setSysDepartment();
            removeFragment();
        }
        if ("基本情况".equals(str)) {
            setPersonalAdapter(SysDepartmentID, "", rankSign);
            removeFragment();
        }
        if ("督查信息".equals(str)) {
            setInspection(mPersonalIdCard, SysDepartmentID, rankSign);
            removeFragment();
            if (!TextUtils.isEmpty(mPersonalIdCard)) {
                newHomeBack.setVisibility(View.VISIBLE);
                mPersonalIdCardTime = "1";
            } else {
                mPersonalIdCardTime = "0";
            }
        }
        if ("平时考察".equals(str)) {
            setMoreAdapter(mPersonalIdCard, SysDepartmentID, rankSign);
            removeFragment();
            if (!TextUtils.isEmpty(mPersonalIdCard)) {
                newHomeBack.setVisibility(View.VISIBLE);
                mPersonalIdCardTime = "1";
            } else {
                mPersonalIdCardTime = "0";
            }
        }
        if ("编制情况".equals(str)) {
            setInstitutions(SysDepartmentID, "");
            removeFragment();
        }
        if ("年龄性别结构".equals(str)) {
            setAgeAndSex(SysDepartmentID, "");
            removeFragment();
        }
        if ("日常考察".equals(str)) {
            setDailyInspection(SysDepartmentID, "", rankSign);
            removeFragment();
        }
        if ("综合查询".equals(str)) {
            addComprehensiveFragment();
        }
        if ("更新".equals(str)) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("温馨提示")
                    .setMessage("有丢失数据风险哦，是否要更新？")
                    .setPositiveButton("确定", (dialog, which) -> {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        intent.putExtra("sign", "upData");
                        startActivity(intent);
                        dialog.dismiss();
                    })
                    .setNegativeButton("取消", (dialog, which) -> dialog.dismiss()).create().show();
        }
        if ("上传".equals(str)) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("温馨提示")
                    .setMessage("是否要上传？")
                    .setPositiveButton("确定", (dialog, which) -> {
                        Intent intent = new Intent(MainActivity.this, UpDataActivity.class);
                        intent.putExtra("sign", "upLoad");
                        startActivity(intent);
                    })
                    .setNegativeButton("取消", (dialog, which) -> dialog.dismiss()).create().show();
        }
    }

    /**
     * 职级 领导班子、中层干部筛选
     */
    private void rankQuery() {
//        String stopTitle = homeTitleAdapter.getStopTitle();//也可以获取到当前选中的title
        if ("基本情况".equals(TitleSelection)) {
            setPersonalAdapter(SysDepartmentID, ParentId, rankSign);
        }
        if ("平时考察".equals(TitleSelection)) {
            setMoreAdapter("", SysDepartmentID, rankSign);
        }
        if ("督查信息".equals(TitleSelection)) {
            setInspection("", SysDepartmentID, rankSign);
        }
        if ("日常考察".equals(TitleSelection)) {
            setDailyInspection(SysDepartmentID, ParentId, rankSign);
        }
    }

    /**
     * 综合查询sql
     *
     * @param sql
     */
    @Override
    public void selectSql(String sql) {
        mComprehensiveSql = sql;
        List<Personal> list = new ArrayList<>();
        list.add(new Personal("标题", "标题"));
        list.add(new Personal("姓名", "性别", "出生年月", "工作",
                "单位", "及职务", "全日制教育\n学历",
                "全日制教育\n专业", "在职教育\n专业", "在职教育\n学历",
                "任现职时长", "在本单位时长", "参加工作\n时间", "政治面貌"));
        list.addAll(homeHelpDbUtil.getPersonalAll(dbControl, sql));
        NewPersonalAdapter personalAdapter = new NewPersonalAdapter(this, list);
        setNewMainRightCycLAndD();
        newMainRightCyc.setAdapter(personalAdapter);
        personalAdapter.setOnItemClickListener((i, personal) -> {
            if (i != 1) {
                PersonalIdPosition = i;
                mComprehensive = "1";
                addDetailFragment(personal, "");
            }
        });
        personalAdapter.setOnItemLongClickListener((i, personal) -> {
            Intent intent = new Intent(MainActivity.this, RecordActivity.class);
            intent.putExtra("personal", personal);
            startActivity(intent);
        });
        removeFragment();
        mComprehensive = "0";
        newHomeBack.setVisibility(View.VISIBLE);
        homeTitleAdapter.changeImageVisable(1);
    }

    private void requestLeftSelect() {
        removePersonalIdCard();
        removeFragment();
        if ("基本情况".equals(TitleSelection) || "综合查询".equals(TitleSelection)) {
            setPersonalAdapter(SysDepartmentID, ParentId, rankSign);
        }
        if ("平时考察".equals(TitleSelection)) {
            setMoreAdapter("", SysDepartmentID, rankSign);
        }
        if ("督查信息".equals(TitleSelection)) {
            setInspection("", SysDepartmentID, rankSign);
        }
        if ("编制情况".equals(TitleSelection)) {
            setInstitutions(SysDepartmentID, ParentId);
        }
        if ("年龄性别结构".equals(TitleSelection)) {
            setAgeAndSex(SysDepartmentID, ParentId);
        }
        if ("日常考察".equals(TitleSelection)) {
            setDailyInspection(SysDepartmentID, ParentId, rankSign);
        }

//--------------------------2 此处为当查询页面显示时  点击左侧列表 为查询页面更改数据
//        if (comprehensiveFragment.isAdded()) {
//            comprehensiveFragment.setStrWorkUnit(strWorkUnit);
//            comprehensiveFragment.setStrWorkUnitId(SysDepartmentID);
//        } else {
//            if (detailsFragment.isAdded())
//                transaction.remove(detailsFragment);
//            newMainFrame.setVisibility(View.GONE);
//
//            if ("基本情况".equals(TitleSelection)) {
//                setPersonalAdapter(SysDepartmentID, ParentId);
//            }
//            if ("平时考察".equals(TitleSelection)) {
//                setMoreAdapter("", SysDepartmentID);
//            }
//        }
    }

    private void setFragment() {
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    }

    private void addComprehensiveFragment() {
        transaction = getSupportFragmentManager().beginTransaction();
        if (comprehensiveFragment.isAdded())
            transaction.remove(comprehensiveFragment);
        if (detailsFragment.isAdded())
            transaction.remove(detailsFragment);
        transaction.add(R.id.new_main_frame, comprehensiveFragment);
        transaction.show(comprehensiveFragment);
        transaction.commit();
        AnimationUtils.showAndHiddenAnimation(newMainFrame, AnimationUtils.AnimationState.STATE_SHOW, 300);
    }

    private void addDetailFragment(Personal mPersonal, String PersonalIdCard) {
        if (mPersonal != null) {
            personals = mPersonal;
            mPersonalIdCard = mPersonal.getPersonalIdCard();
        }
        if (!TextUtils.isEmpty(PersonalIdCard)) {
            personals = null;
            mPersonalIdCard = PersonalIdCard;
        }
//        removeWorkUnit();
        if (!detailsFragment.isAdded()) {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.new_main_frame, detailsFragment);
            transaction.show(detailsFragment);
            if (comprehensiveFragment.isAdded()) {
                transaction.remove(comprehensiveFragment);
            }
            transaction.commit();
            AnimationUtils.showAndHiddenAnimation(newMainFrame, AnimationUtils.AnimationState.STATE_SHOW, 30);
//            newMainFrame.setVisibility(View.VISIBLE);
            newHomeBack.setVisibility(View.VISIBLE);
        }
    }

    private void removeFragment() {
        if (newMainFrame.getVisibility() == View.VISIBLE) {
//            newMainFrame.setVisibility(View.GONE);
            AnimationUtils.showAndHiddenAnimation(newMainFrame, AnimationUtils.AnimationState.STATE_HIDDEN, 300);
            newHomeBack.setVisibility(View.GONE);
            transaction = getSupportFragmentManager().beginTransaction();
            if (comprehensiveFragment.isAdded())
                transaction.remove(comprehensiveFragment);
            if (detailsFragment.isAdded())
                transaction.remove(detailsFragment);
            transaction.commit();
        }
    }

    @TargetApi(23)
    private void getPersimmions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();
            if (this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
            }
        }
    }


    public String getSysDepartmentID() {
        return SysDepartmentID;
    }

    public String getStrWorkUnit() {
        return strWorkUnit;
    }

    public Personal getPersonals() {
        return personals;
    }

    public String getmPersonalIdCard() {
        return mPersonalIdCard;
    }

    private void removePersonalIdCard() {
        mPersonalIdCard = "";
    }

    private void removeWorkUnit() {
        SysDepartmentID = "";
        strWorkUnit = "";
        ParentId = "";
        homeLeftAdapter1.setmMapSignLeft(TitleUtil.setSignRemove(mMapSignLeft));
        homeLeftAdapter2.setmMapSignLeft(TitleUtil.setSignRemove(mMapSignLeft));
        setUpLeftBtn(3);
    }

    private void removeAll() {
        removePersonalIdCard();
        removeWorkUnit();
    }

    /**
     * 左边列表展开title控制
     * 0 第一个被选中
     * 1 第二个被选中
     * 2 第三个被选中
     * 其他值 所有都不被选中
     *
     * @param sign
     */
    private void setUpLeftBtn(int sign) {
        switch (sign) {
            case 0:
                newMainLeftTv0.setTextColor(Color.RED);
                newMainLeftTv1.setTextColor(Color.WHITE);
                newMainLeftTv2.setTextColor(Color.WHITE);
                break;
            case 1:
                newMainLeftTv0.setTextColor(Color.WHITE);
                newMainLeftTv1.setTextColor(Color.RED);
                newMainLeftTv2.setTextColor(Color.WHITE);
                break;
            case 2:
                newMainLeftTv0.setTextColor(Color.WHITE);
                newMainLeftTv1.setTextColor(Color.WHITE);
                newMainLeftTv2.setTextColor(Color.RED);
                break;
            default:
                newMainLeftTv0.setTextColor(Color.WHITE);
                newMainLeftTv1.setTextColor(Color.WHITE);
                newMainLeftTv2.setTextColor(Color.WHITE);
                break;
        }
    }

    @OnClick({R.id.new_home_icon, R.id.new_home_leader, R.id.new_home_back, R.id.new_main_left_img0, R.id.new_main_left_tv0, R.id.new_main_left_tv1,
            R.id.new_main_left_tv2, R.id.new_main_left_img1, R.id.new_main_left_img2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.new_home_icon:
                popWindowAdapter = homeHelpUtil.showPop(view, TitleUtil.getUpTitle());
                break;
            case R.id.new_home_leader:
                String leader = newHomeLeader.getText().toString();
                if ("领导班子".equals(leader)) {
                    rankSign = "中层干部";
                }
                if ("中层干部".equals(leader)) {
                    rankSign = "领导班子";
                }
                newHomeLeader.setText(rankSign);
                rankQuery();
                break;
            case R.id.new_home_back:
                if (!TextUtils.isEmpty(mComprehensive)) {
                    if ("1".equals(mPersonalIdCardTime)) {
                        mPersonalIdCardTime = "";
                        addDetailFragment(null, mPersonalIdCard);
                    } else if ("1".equals(mComprehensive)) {
                        if (!TextUtils.isEmpty(mComprehensiveSql))
                            selectSql(mComprehensiveSql);
                        removeFragment();
                    } else if ("0".equals(mComprehensive)) {
                        mComprehensive = mComprehensiveSql = "";
                        addComprehensiveFragment();
                        homeTitleAdapter.changeImageVisable(4);
                        newHomeBack.setVisibility(View.GONE);
                        removePersonalIdCard();
                    }
                } else if (!TextUtils.isEmpty(mPersonalIdCardTime)) {
                    if ("1".equals(mPersonalIdCardTime)) {
                        mPersonalIdCardTime = "";
                        addDetailFragment(null, mPersonalIdCard);
                    } else if ("0".equals(mPersonalIdCardTime)) {
                        mPersonalIdCardTime = "";
                        homeTitleAdapter.changeImageVisable(3);
//                    setMoreAdapter("", "");
                        removeFragment();
                        removePersonalIdCard();
                    }
                } else {
                    homeTitleAdapter.changeImageVisable(1);
                    if (!TextUtils.isEmpty(mPersonalIdCard)) {
                        setPersonalAdapter(SysDepartmentID, ParentId, rankSign);
                    }
                    removeFragment();
                    removePersonalIdCard();
                }
                break;
            case R.id.new_main_left_tv0:
            case R.id.new_main_left_img0:
                if (!mMapSignLeft.get("all")) {
                    removeAll();
                    Map<Object, Boolean> mMapSign = TitleUtil.setSign(mMapSignLeft, "all");
                    homeLeftAdapter1.setmMapSignLeft(mMapSign);
                    homeLeftAdapter2.setmMapSignLeft(mMapSign);
                    setUpLeftBtn(0);
                    ParentId = "";
                    requestLeftSelect();
                }
                break;
            case R.id.new_main_left_tv1:
            case R.id.new_main_left_img1:
                if (newMainLeftCyc.getVisibility() == View.GONE) {
                    newMainLeftCyc.setVisibility(View.VISIBLE);
                    newMainLeftImg1.animate().rotation(90);
                } else {
                    newMainLeftCyc.setVisibility(View.GONE);
                    newMainLeftImg1.animate().rotation(0);
                }
                if (!mMapSignLeft.get("office")) {
                    Map<Object, Boolean> mMapSign = TitleUtil.setSign(mMapSignLeft, "office");
                    homeLeftAdapter1.setmMapSignLeft(mMapSign);
                    homeLeftAdapter2.setmMapSignLeft(mMapSign);
                    setUpLeftBtn(1);
                    ParentId = "10";
                    SysDepartmentID = "";
                    requestLeftSelect();
                }
                break;
            case R.id.new_main_left_tv2:
            case R.id.new_main_left_img2:
                if (newMainLeftCyc2.getVisibility() == View.GONE) {
                    newMainLeftImg2.animate().rotation(90);
                    newMainLeftCyc2.setVisibility(View.VISIBLE);
                } else {
                    newMainLeftCyc2.setVisibility(View.GONE);
                    newMainLeftImg2.animate().rotation(0);
                }
                if (!mMapSignLeft.get("Township")) {
                    Map<Object, Boolean> mMapSign = TitleUtil.setSign(mMapSignLeft, "Township");
                    homeLeftAdapter1.setmMapSignLeft(mMapSign);
                    homeLeftAdapter2.setmMapSignLeft(mMapSign);
                    setUpLeftBtn(2);
                    ParentId = "50";
                    SysDepartmentID = "";
                    requestLeftSelect();
                }
                break;
        }
    }

    /**
     * 本地日常考察修改删除弹出框
     *
     * @param dbControl
     * @param inspectionData
     */
    private void selectDialog(final DbControl dbControl, final DailyInspectionData inspectionData) {
        new AlertDialog.Builder(this)
                .setTitle("选择操作")
                .setPositiveButton("修改", (dialog, which) -> {
                    Intent intent = new Intent(MainActivity.this, RecordActivity.class);
                    intent.putExtra("mInspectionData", inspectionData);
                    startActivityForResult(intent, 1);
                    dialog.dismiss();
                })
                .setNegativeButton("删除", (dialog, which) -> {
                    dbControl.deleteDailyInspection0(inspectionData);
                    rankQuery();
                    dialog.dismiss();
                })
                .create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        rankQuery();
    }

}

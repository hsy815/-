package com.hsy.thisdb.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.BasePickerView;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.android.material.navigation.NavigationView;
import com.hsy.thisdb.R;
import com.hsy.thisdb.adapter.AgeStructureAdapter;
import com.hsy.thisdb.adapter.InstitutionsAdapter;
import com.hsy.thisdb.adapter.SexStructureAdapter;
import com.hsy.thisdb.db_control.DbControl;
import com.hsy.thisdb.eitity.Personal;
import com.hsy.thisdb.eitity.SysDepartment;
import com.hsy.thisdb.net.ApiUtil;
import com.hsy.thisdb.util.DateUtil;
import com.hsy.thisdb.util.ReadTxt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Deprecated
public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final int SDK_PERMISSION_REQUEST = 100;

    @BindView(R.id.main_select)
    TextView mainSelect;
    @BindView(R.id.head_back)
    TextView headBack;
    @BindView(R.id.head_title)
    TextView headTitle;
    @BindView(R.id.head_right)
    TextView headRight;
    @BindView(R.id.main_work_unit)
    TextView mainWorkUnit;
    @BindView(R.id.main_full_name)
    EditText mainFullName;
    @BindView(R.id.main_sex_nan)
    RadioButton mainSexNan;
    @BindView(R.id.main_sex_nv)
    RadioButton mainSexNv;
    @BindView(R.id.main_sex_all)
    RadioButton mainSexAll;
    @BindView(R.id.main_sex)
    RadioGroup mainSex;
    @BindView(R.id.main_start_date)
    TextView mainStartDate;
    @BindView(R.id.main_end_date)
    TextView mainEndDate;
    @BindView(R.id.main_politics_in)
    CheckBox mainPoliticsIn;
    @BindView(R.id.main_politics_out)
    CheckBox mainPoliticsOut;
    @BindView(R.id.main_edudegree_ss)
    CheckBox mainEdudegreeSs;
    @BindView(R.id.main_edudegree_b)
    CheckBox mainEdudegreeB;
    @BindView(R.id.main_edudegree_d)
    CheckBox mainEdudegreeD;
    @BindView(R.id.main_edudegree_dx)
    CheckBox mainEdudegreeDx;
    @BindView(R.id.main_ldentity_g)
    CheckBox mainLdentityG;
    @BindView(R.id.main_ldentity_c)
    CheckBox mainLdentityC;
    @BindView(R.id.main_ldentity_s)
    CheckBox mainLdentityS;
    @BindView(R.id.main_dutydegree_z)
    CheckBox mainDutydegreeZ;
    @BindView(R.id.main_dutydegree_f)
    CheckBox mainDutydegreeF;
    @BindView(R.id.main_dutydegree_zz)
    CheckBox mainDutydegreeZz;
    @BindView(R.id.main_dutydegree_zf)
    CheckBox mainDutydegreeZf;
    @BindView(R.id.main_major)
    EditText mainMajor;
    @BindView(R.id.main_major_table_zy)
    CheckBox mainMajorTableZy;
    @BindView(R.id.main_major_table_tc)
    CheckBox mainMajorTableTc;
    @BindView(R.id.main_major_table_fg)
    CheckBox mainMajorTableFg;
    @BindView(R.id.main_rel)
    RelativeLayout mainRel;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.main_scroll)
    ScrollView mainScroll;
    @BindView(R.id.other_age_lin)
    LinearLayout otherAgeLin;
    @BindView(R.id.other_sex_lin)
    LinearLayout otherSexLin;
    @BindView(R.id.other_bianzhi_lin)
    LinearLayout otherBianzhiLin;
    @BindView(R.id.main_age_cyc)
    RecyclerView mainAgeCyc;
    @BindView(R.id.main_sex_cyc)
    RecyclerView mainSexCyc;
    @BindView(R.id.main_bianzhi_cyc)
    RecyclerView mainBianzhiCyc;
    @BindView(R.id.home_left)
    ImageView homeLeft;
    @BindView(R.id.head_reset)
    TextView headReset;


    private DbControl dbControl;
    private List<Personal> personals;
    private List<SysDepartment> sysDepartmentList;
    private int SysDepartmentID = 0;
    private String Gender = "";//性别
    private String StartDate = "";//起始时间
    private String EndDate = "";//结束时间
    private String[] Politics = new String[2];//政治面貌
    private String[] EduDegree = new String[4];//学历
    private String[] Ldentity = new String[3];//身份类别
    private String[] DutyDegree = new String[4];//职级
    private String[] MajorTable = new String[3];//查询内容
    private Date mStartDate;
    private Date mEndDate;

    private AgeStructureAdapter ageStructureAdapter;
    private SexStructureAdapter sexStructureAdapter;
    private InstitutionsAdapter institutionsAdapter;
    private OptionsPickerView<String> optionsPickerView;
    private List<String> departmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
        headTitle.setText("搜索");
        navView.setNavigationItemSelectedListener(this);

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        mainAgeCyc.setLayoutManager(layoutManager1);
        mainAgeCyc.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        ageStructureAdapter = new AgeStructureAdapter(this);
        mainAgeCyc.setAdapter(ageStructureAdapter);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        mainSexCyc.setLayoutManager(layoutManager2);
        mainSexCyc.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        sexStructureAdapter = new SexStructureAdapter(this);
        mainSexCyc.setAdapter(sexStructureAdapter);

        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this);
        layoutManager3.setOrientation(LinearLayoutManager.VERTICAL);
        mainBianzhiCyc.setLayoutManager(layoutManager3);
        mainBianzhiCyc.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        institutionsAdapter = new InstitutionsAdapter(this);
        mainBianzhiCyc.setAdapter(institutionsAdapter);

        CheckBtnListener();
        getPersimmions();
        newTxt();
        dbControl = DbControl.getInstent(this);
    }

    /**
     * 设置条件选择监听
     */
    private void CheckBtnListener() {
        mainSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_sex_nan:
                        Gender = "'男'";
                        break;
                    case R.id.main_sex_nv:
                        Gender = "'女'";
                        break;
                    case R.id.main_sex_all:
                        Gender = "";
                        break;
                }
            }
        });
        mainPoliticsIn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Politics[0] = "'中共党员'";
                } else {
                    Politics[0] = "";
                }
            }
        });
        mainPoliticsOut.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Politics[1] = "'民主党派,群众'";
                } else {
                    Politics[1] = "";
                }
            }
        });
        mainEdudegreeSs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    EduDegree[0] = "硕士,博士,研究生";
                } else {
                    EduDegree[0] = "";
                }
            }
        });
        mainEdudegreeB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    EduDegree[1] = "本科,大学";
                } else {
                    EduDegree[1] = "";
                }
            }
        });
        mainEdudegreeD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    EduDegree[2] = "大专";
                } else {
                    EduDegree[2] = "";
                }
            }
        });
        mainEdudegreeDx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    EduDegree[3] = "高中,中专,高职,职高,小学,初中";
                } else {
                    EduDegree[3] = "";
                }
            }
        });
        mainLdentityG.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Ldentity[0] = "'公务员'";
                } else {
                    Ldentity[0] = "";
                }
            }
        });
        mainLdentityC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Ldentity[1] = "'参公'";
                } else {
                    Ldentity[1] = "";
                }
            }
        });
        mainLdentityS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Ldentity[2] = "'事业'";
                } else {
                    Ldentity[2] = "";
                }
            }
        });
        mainDutydegreeZ.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    DutyDegree[0] = "'正科'";
                } else {
                    DutyDegree[0] = "";
                }
            }
        });
        mainDutydegreeF.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    DutyDegree[1] = "'副科'";
                } else {
                    DutyDegree[1] = "";
                }
            }
        });
        mainDutydegreeZz.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    DutyDegree[2] = "'中层正职'";
                } else {
                    DutyDegree[2] = "";
                }
            }
        });
        mainDutydegreeZf.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    DutyDegree[3] = "'中层副职'";
                } else {
                    DutyDegree[3] = "";
                }
            }
        });
        mainMajorTableZy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MajorTable[0] = "'major'";
                } else {
                    MajorTable[0] = "";
                }
            }
        });
        mainMajorTableTc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MajorTable[1] = "'speciality'";
                } else {
                    MajorTable[1] = "";
                }
            }
        });
        mainMajorTableFg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MajorTable[2] = "'work'";
                } else {
                    MajorTable[2] = "";
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_all) {
            headTitle.setText("搜索");
            otherBianzhiLin.setVisibility(View.GONE);
            otherSexLin.setVisibility(View.GONE);
            otherAgeLin.setVisibility(View.GONE);
        } else if (id == R.id.nav_edit) {
            headTitle.setText("干部编制情况");
            institutionsAdapter.setInstitutionsList(dbControl.institutionsSelect("select * from Institutions"));
            otherSexLin.setVisibility(View.GONE);
            otherAgeLin.setVisibility(View.GONE);
            otherBianzhiLin.setVisibility(View.VISIBLE);
        } else if (id == R.id.nav_age) {
            headTitle.setText("年龄结构");
            ageStructureAdapter.setAgeStructureList(dbControl.ageSelect("select * from AgeStructure"));
            otherBianzhiLin.setVisibility(View.GONE);
            otherSexLin.setVisibility(View.GONE);
            otherAgeLin.setVisibility(View.VISIBLE);
        } else if (id == R.id.nav_sex) {
            headTitle.setText("性别结构");
            sexStructureAdapter.setGenderStructures(dbControl.sexSelect("select * from GenderStructure"));
            otherBianzhiLin.setVisibility(View.GONE);
            otherAgeLin.setVisibility(View.GONE);
            otherSexLin.setVisibility(View.VISIBLE);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick({R.id.home_left, R.id.main_select, R.id.head_right, R.id.main_start_date, R.id.main_end_date,
            R.id.main_work_unit, R.id.head_reset})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_left:
                drawer.openDrawer(GravityCompat.START);
                break;
            case R.id.head_right:
                startActivity(new Intent(HomeActivity.this, UpDataActivity.class));
                break;
            case R.id.main_work_unit:
                ShowSysDepartment();
                break;
            case R.id.main_start_date:
                getStartDate();
                break;
            case R.id.main_end_date:
                getEndDate();
                break;
            case R.id.main_select:
                BtnSelect();
                break;
            case R.id.head_reset:
                Reset();
                break;
        }
    }

    /**
     * 新建文件 存储访问ip
     */
    private void newTxt() {
        String path = Environment.getExternalStorageDirectory() + "/ThisDb/";
        ReadTxt.makeFilePath(path, "thisDb.txt", ApiUtil.getApiString());
    }

    /**
     * 执行条件查询
     */
    private void BtnSelect() {
        String mSql = getSelectSql();
        String sql;
        if (TextUtils.isEmpty(mSql)) {
            sql = "select * from Personal";
        } else {
            if ("and ".equals(mSql.substring(mSql.length() - 4, mSql.length()))) {
                mSql = mSql.substring(0, mSql.length() - 4);
            }
            sql = "select * from Personal p where (" + mSql + ")";
        }

        Log.e("tag", "sql=" + sql);

//        personals = dbControl.select(sql);
        Intent intent = new Intent(HomeActivity.this, ViewActivity.class);
        intent.putExtra("sql", sql);
        startActivity(intent);
    }

    /**
     * 整理SQL
     *
     * @return
     */
    private String getSelectSql() {
        String str = "";

        if (SysDepartmentID == -1) SysDepartmentID = 0;
        if (SysDepartmentID != 0)
            str += "p.DeptID like '," + SysDepartmentID + ",' and ";

        String Fullname = mainFullName.getText().toString();
        if (!TextUtils.isEmpty(Fullname))
            str += "p.Fullname like '%" + Fullname + "%' and ";

        if (!TextUtils.isEmpty(Gender))
            str += "p.Gender = " + Gender + "and ";

        StartDate = (mStartDate == null) ? "" : DateUtil.getDate2_(mStartDate);
        EndDate = (mEndDate == null) ? "" : DateUtil.getDate2_(mEndDate);
        if (TextUtils.isEmpty(StartDate) && !TextUtils.isEmpty(EndDate)) {
            StartDate = "1900-01-01";
            str += "p.Birthday between '" + StartDate + "' and '" + EndDate + "' and ";
        } else if (!TextUtils.isEmpty(StartDate) && TextUtils.isEmpty(EndDate)) {
            EndDate = "2100-01-01";
            str += "p.Birthday between '" + StartDate + "' and '" + EndDate + "' and ";
        } else if (!TextUtils.isEmpty(StartDate) && !TextUtils.isEmpty(EndDate)) {
            str += "p.Birthday between '" + StartDate + "' and '" + EndDate + "' and ";
        }

        if (!TextUtils.isEmpty(getArray(Politics)))
            str += "p.PoliticsStatus in (" + getArray(Politics) + ")and ";

        if (!TextUtils.isEmpty(getArray(Ldentity)))
            str += "p.Identity in (" + getArray(Ldentity) + ")and ";

        if (!TextUtils.isEmpty(getArray(EduDegree)))
            str += "p.PersonalIdCard in (select PersonalIdCard from EduExperience where (" + getEduDegreeSql() + "))and ";

        String mMajor = mainMajor.getText().toString();
        if (!TextUtils.isEmpty(mMajor) &&
                (!TextUtils.isEmpty(MajorTable[0]) ||
                        !TextUtils.isEmpty(MajorTable[1]) ||
                        !TextUtils.isEmpty(MajorTable[2]))) {
            str += "p.PersonalIdCard in (";
            if ("'major'".equals(MajorTable[0])) {
                str += "select PersonalIdCard from EduExperience where Major like '%" + mMajor + "%'";
                if (!TextUtils.isEmpty(MajorTable[1]))
                    str += " union ";
            }
            if ("'speciality'".equals(MajorTable[1])) {
                str += "select PersonalIdCard from MajorDegree where Speciality like '%" + mMajor + "%'";
                if (!TextUtils.isEmpty(MajorTable[2]))
                    str += " union ";
            }
            if ("'work'".equals(MajorTable[2])) {
                str += "select PersonalIdCard from DivisionWork where DividedContent like '%" + mMajor + "%' union ";
                str += "select PersonalIdCard from KeyProjectAndWork where Name like '%" + mMajor + "%'";
            }
            str += ")and ";
        }

        if (!TextUtils.isEmpty(getArray(DutyDegree)))
            str += "p.PersonalIdCard in (select PersonalIdCard from Personal where WorkDegree in(" + getArray(DutyDegree) + "))";
        return str;
    }

    /**
     * 学历整合
     *
     * @return
     */
    private String getEduDegreeSql() {
        String s = "";
        String sql = "";
        for (int i = 0; i < EduDegree.length; i++) {
            if (!TextUtils.isEmpty(EduDegree[i]))
                s += EduDegree[i];
        }
        String[] strs = s.split(",");
        for (String str : strs) {
            sql += "EduBackground like '%" + str + "%' or ";
        }
        return TextUtils.isEmpty(sql) ? "1=1" : sql.substring(0, sql.length() - 3);
    }

    /**
     * 字段拼接
     *
     * @param strings
     * @return
     */
    private String getArray(String[] strings) {
        String str = "";
        for (int i = 0; i < strings.length; i++) {
            if (TextUtils.isEmpty(strings[i])) continue;
            str += strings[i] + ",";
        }
        return TextUtils.isEmpty(str) ? "" : str.substring(0, str.length() - 1);
    }

    /**
     * 查询单位
     *
     * @param content
     * @return
     */
    private List<String> getSysDepartment(String content) {
        if (TextUtils.isEmpty(content))
            sysDepartmentList = dbControl.SysDepartmentSelect("select * from SysDepartment");
        else
            sysDepartmentList = dbControl.SysDepartmentSelect("select * from SysDepartment where name like '%" + content + "%'");
        List<String> departmentList = new ArrayList<>();
        for (SysDepartment sysDepartment :
                sysDepartmentList) {
            departmentList.add(sysDepartment.getName());
        }
        return departmentList;
    }

    /**
     * 单位选择器
     */
    private void ShowSysDepartment() {
        departmentList = getSysDepartment("");
        optionsPickerView = new OptionsPickerBuilder(HomeActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mainWorkUnit.setText(departmentList.get(options1));
                SysDepartmentID = sysDepartmentList.get(options1).getId();
            }
        }).setLayoutRes(R.layout.department_view, new CustomListener() {
            @Override
            public void customLayout(View v) {
                EditText tv_edit = v.findViewById(R.id.tv_edit);
                TextView tv_ok = v.findViewById(R.id.tv_ok);
                LinearLayout tv_lin = v.findViewById(R.id.tv_lin);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                //根据当前屏幕横竖屏来处理弹出框底部或右部与导航重叠问题
                Configuration mConfiguration = getResources().getConfiguration(); //获取设置的配置信息
                int ori = mConfiguration.orientation; //获取屏幕方向
                if (ori == mConfiguration.ORIENTATION_LANDSCAPE) {
                    layoutParams.rightMargin = getDaoHangHeight();
                } else if (ori == mConfiguration.ORIENTATION_PORTRAIT) {
                    layoutParams.bottomMargin = getDaoHangHeight();
                }
                tv_lin.setLayoutParams(layoutParams);
                tv_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        optionsPickerView.dismiss();
                        optionsPickerView.returnData();
                    }
                });
                tv_edit.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        departmentList = getSysDepartment(s.toString());
                        optionsPickerView.setPicker(departmentList);
                    }
                });
            }
        }).build();
        optionsPickerView.setPicker(departmentList);
        optionsPickerView.setSelectOptions(0);
        optionsPickerView.show();
    }

    /**
     * 控制开始时间
     */
    private void getStartDate() {
        TimePickerView pvTime = new TimePickerBuilder(HomeActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (mEndDate != null) {
                    if (date.getTime() > mEndDate.getTime()) {
                        Toast.makeText(HomeActivity.this, "开始时间不能晚于结束时间", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                mStartDate = date;
                mainStartDate.setText(DateUtil.getDate2Word(date));
            }
        }).isDialog(true).build();
        setDialogBottom(pvTime);
        pvTime.show();
    }

    /**
     * 控制结束时间
     */
    private void getEndDate() {
        TimePickerView pvTime = new TimePickerBuilder(HomeActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (mStartDate != null) {
                    if (mStartDate.getTime() > date.getTime()) {
                        Toast.makeText(HomeActivity.this, "结束时间不能早于开始时间", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                mEndDate = date;
                mainEndDate.setText(DateUtil.getDate2Word(date));
            }
        }).isDialog(true).build();
        setDialogBottom(pvTime);
        pvTime.show();
    }

    /**
     * 设置底部弹出日期选择器
     *
     * @param view
     */
    private void setDialogBottom(BasePickerView view) {
        Dialog mDialog = view.getDialog();
        if (mDialog != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);
            params.leftMargin = 0;
            params.rightMargin = 0;
            view.getDialogContainerLayout().setLayoutParams(params);
            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }
    }

    /**
     * 清空所有条件
     */
    private void Reset() {
        mainWorkUnit.setText("");
        SysDepartmentID = 0;
        mainFullName.setText("");
        Gender = "";
        mainSexAll.setChecked(false);
        mainSexNv.setChecked(false);
        mainSexNan.setChecked(false);
        mStartDate = null;
        mEndDate = null;
        mainStartDate.setText("");
        mainEndDate.setText("");
        mainPoliticsIn.setChecked(false);
        mainPoliticsOut.setChecked(false);
        array2null(Politics);
        mainEdudegreeSs.setChecked(false);
        mainEdudegreeB.setChecked(false);
        mainEdudegreeD.setChecked(false);
        mainEdudegreeDx.setChecked(false);
        array2null(EduDegree);
        mainLdentityG.setChecked(false);
        mainLdentityC.setChecked(false);
        mainLdentityS.setChecked(false);
        array2null(Ldentity);
        mainDutydegreeZ.setChecked(false);
        mainDutydegreeZz.setChecked(false);
        mainDutydegreeF.setChecked(false);
        mainDutydegreeZf.setChecked(false);
        array2null(DutyDegree);
        mainMajorTableZy.setChecked(false);
        mainMajorTableTc.setChecked(false);
        mainMajorTableFg.setChecked(false);
        array2null(MajorTable);
        mainMajor.setText("");
    }

    /**
     * 所有数组置空
     *
     * @param strings
     */
    private void array2null(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            strings[i] = "";
        }
    }

    /**
     * 判断底部导航是否显示
     *
     * @param context
     * @param window
     * @return
     */
    public static boolean checkNavigationBarShow(@NonNull Context context, @NonNull Window window) {
        boolean show;
        Display display = window.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getRealSize(point);

        View decorView = window.getDecorView();
        Configuration conf = context.getResources().getConfiguration();
        if (Configuration.ORIENTATION_LANDSCAPE == conf.orientation) {
            View contentView = decorView.findViewById(android.R.id.content);
            show = (point.x != contentView.getWidth());
        } else {
            Rect rect = new Rect();
            decorView.getWindowVisibleDisplayFrame(rect);
            show = (rect.bottom != point.y);
        }
        return show;
    }

    /**
     * 获取导航栏高度
     *
     * @return
     */
    public int getDaoHangHeight() {
        int resourceId = 0;

        if (!checkNavigationBarShow(this, getWindow())) return resourceId;

        int rid = getResources().getIdentifier("config_showNavigationBar", "bool", "android");
        if (rid != 0) {
            resourceId = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            return getResources().getDimensionPixelSize(resourceId);
        } else
            return 0;
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
}

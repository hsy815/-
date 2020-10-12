package com.hsy.thisdb.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.BasePickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.hsy.thisdb.R;
import com.hsy.thisdb.activity.MainActivity;
import com.hsy.thisdb.util.DateUtil;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.fragment
 * @创始人: hsy
 * @创建时间: 2018/12/20 9:10
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/12/20 9:10
 * @修改描述:
 */
public class ComprehensiveFragment extends Fragment {


    ComprehensiveSelectSql comprehensiveSelectSql;//返回查询条件sql
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
    EditText mainStartDate;
    @BindView(R.id.main_end_date)
    EditText mainEndDate;
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
    @BindView(R.id.main_select)
    TextView mainSelect;
    @BindView(R.id.main_scroll)
    ScrollView mainScroll;
    @BindView(R.id.main_rel)
    RelativeLayout mainRel;
    @BindView(R.id.main_clean)
    TextView mainClean;
    @BindView(R.id.main_fullmajor_ss)
    CheckBox mainFullmajorSs;
    @BindView(R.id.main_fullmajor_b)
    CheckBox mainFullmajorB;
    @BindView(R.id.main_fullmajor_d)
    CheckBox mainFullmajorD;
    @BindView(R.id.main_fullmajor_dx)
    CheckBox mainFullmajorDx;
    @BindView(R.id.main_schoolmajor_ss)
    CheckBox mainSchoolmajorSs;
    @BindView(R.id.main_schoolmajor_b)
    CheckBox mainSchoolmajorB;
    @BindView(R.id.main_schoolmajor_d)
    CheckBox mainSchoolmajorD;
    @BindView(R.id.main_schoolmajor_dx)
    CheckBox mainSchoolmajorDx;
    @BindView(R.id.main_old_work_unit)
    EditText mainOldWorkUnit;
    @BindView(R.id.main_dutydegree_zj)
    CheckBox mainDutydegreeZj;
    @BindView(R.id.main_dutydegree_fj)
    CheckBox mainDutydegreeFj;
    @BindView(R.id.main_ldentity_z)
    CheckBox mainLdentityZ;
    @BindView(R.id.main_region_organs)
    RadioButton mainRegionOrgans;
    @BindView(R.id.main_region_towns)
    RadioButton mainRegionTowns;
    @BindView(R.id.main_region_all)
    RadioButton mainRegionAll;
    @BindView(R.id.main_region)
    RadioGroup mainRegion;
    @BindView(R.id.main_start_dept_date)
    EditText mainStartDeptDate;
    @BindView(R.id.main_end_dept_date)
    EditText mainEndDeptDate;
    @BindView(R.id.main_start_duty_date)
    EditText mainStartDutyDate;
    @BindView(R.id.main_end_duty_date)
    EditText mainEndDutyDate;
    private Unbinder unbinder;

    private String strWorkUnit;//主页选中的单位名称
    private String strWorkUnitId;//主页选中的单位id

    private MainActivity mainActivity;
    private String Gender = "";//性别
    private String Region = "";//机关乡镇
    private String StartDate = "";//起始时间
    private String EndDate = "";//结束时间
    private String[] Politics = new String[2];//政治面貌
    private String[] EduDegree = new String[4];//学历
    private String[] Fullmajor = new String[4];//全日制教育
    private String[] Schoolmajor = new String[4];//在职教育
    private String[] Ldentity = new String[4];//身份类别
    private String[] DutyDegree = new String[6];//职级
    private String[] MajorTable = new String[3];//查询内容
    private Date mStartDate;//任现职时长
    private Date mEndDate;//现单位时长

    //--------------------------1  此注释位置原本为 时间选择 单位通过左侧列表选择  将TextView 改为EditText

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        comprehensiveSelectSql = (ComprehensiveSelectSql) getActivity();
        mainActivity = (MainActivity) getActivity();
        strWorkUnit = mainActivity.getStrWorkUnit();
        strWorkUnitId = mainActivity.getSysDepartmentID();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comprehensive, container, false);
        unbinder = ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    private void initUI() {
        CheckBtnListener();
//--------------------------1
//        mainWorkUnit.setText((TextUtils.isEmpty(strWorkUnit) ? "所有人" : strWorkUnit));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
        mainRegion.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_region_organs:
                        Region = "deptId like '%,10%'";
                        break;
                    case R.id.main_region_towns:
                        Region = "deptId like '%,50%'";
                        break;
                    case R.id.main_region_all:
                        Region = "";
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
                    Politics[1] = "'民主党派'";
                } else {
                    Politics[1] = "";
                }
            }
        });
        //学历
        mainEdudegreeSs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    EduDegree[0] = "硕士,博士,研究生,";
                } else {
                    EduDegree[0] = "";
                }
            }
        });
        mainEdudegreeB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    EduDegree[1] = "本科,大学,";
                } else {
                    EduDegree[1] = "";
                }
            }
        });
        mainEdudegreeD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    EduDegree[2] = "大专,";
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
        //全日制教育
        mainFullmajorSs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Fullmajor[0] = "硕士,博士,研究生,";
                } else {
                    Fullmajor[0] = "";
                }
            }
        });
        mainFullmajorB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Fullmajor[1] = "本科,大学,";
                } else {
                    Fullmajor[1] = "";
                }
            }
        });
        mainFullmajorD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Fullmajor[2] = "大专,";
                } else {
                    Fullmajor[2] = "";
                }
            }
        });
        mainFullmajorDx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Fullmajor[3] = "高中,中专,高职,职高,小学,初中";
                } else {
                    Fullmajor[3] = "";
                }
            }
        });
        //在职教育
        mainSchoolmajorSs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Schoolmajor[0] = "硕士,博士,研究生,";
                } else {
                    Schoolmajor[0] = "";
                }
            }
        });
        mainSchoolmajorB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Schoolmajor[1] = "本科,大学,";
                } else {
                    Schoolmajor[1] = "";
                }
            }
        });
        mainSchoolmajorD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Schoolmajor[2] = "大专,";
                } else {
                    Schoolmajor[2] = "";
                }
            }
        });
        mainSchoolmajorDx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Schoolmajor[3] = "高中,中专,高职,职高,小学,初中";
                } else {
                    Schoolmajor[3] = "";
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
        mainLdentityS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Ldentity[3] = "'职工'";
                } else {
                    Ldentity[3] = "";
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
        mainDutydegreeZj.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    DutyDegree[4] = "'正科级'";
                } else {
                    DutyDegree[4] = "";
                }
            }
        });
        mainDutydegreeFj.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    DutyDegree[5] = "'副科级'";
                } else {
                    DutyDegree[5] = "";
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

    @OnClick({R.id.main_select, R.id.main_start_date, R.id.main_end_date, R.id.main_clean})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_select:
                BtnSelect();
                break;
//--------------------------1
//            case R.id.main_start_date:
//                getStartDate();
//                break;
//            case R.id.main_end_date:
//                getEndDate();
//                break;
            case R.id.main_clean:
                Reset();
                break;
        }
    }

    /**
     * 执行条件查询
     */
    private void BtnSelect() {
        if (!inspect()) {
            Toast.makeText(mainActivity, "请在查询内容下方勾选条件", Toast.LENGTH_SHORT).show();
            return;
        }
        String mSql = getSelectSql();
        String sql;
        if (TextUtils.isEmpty(mSql)) {
            sql = "select " + sqlFrom() + " from Personal p";
        } else {
            if ("and ".equals(mSql.substring(mSql.length() - 4))) {
                mSql = mSql.substring(0, mSql.length() - 4);
            }
            sql = "select " + sqlFrom() + " from Personal p where (" + mSql + ")";
        }

        Log.e("tag", "sql=" + sql);

        comprehensiveSelectSql.selectSql(sql);
    }

    private String sqlFrom() {
        return "p.Fullname, p.PersonalIdCard, p.Gender, p.Birthday, p.Age, p.Ethnicity, " +
                "p.WorkDate, p.PoliticsStatus, p.JoinDate, p.NativePlace, p.NativeOrigin, p.politicsId, " +
                "p.IsUsing, p.addrId, p.Status, p.DeptID, p.ParentDeptID, p.WorkUnit, p.Identity, " +
                "p.SectionName, p.Duty, p.WorkDegree, p.MajorDegree, p.Speciality, p.EduDegree, " +
                "p.School, p.Department, p.EduMajor, p.StartDate, p.EndDate, p.EduStatus, " +
                "p.EduBackground, p.IsHighest, p.FullSchoolMajor, p.FullBackgroundDegree, " +
                "p.FullMajor, p.IsFullTime, p.GoOnSchoolMajor, p.GoOnBackgroundDegree, " +
                "p.HealthyStatus, p.SortNo," +
                " (CASE WHEN ROUND(replace(substr(date('now'), 1,7), '-', '.') - CurrentDeptStartTime - 0.5, 0) < 1 THEN ''" +
                " ELSE replace(ROUND(replace(substr(date('now'), 1,7), '-', '.') - CurrentDeptStartTime - 0.5, 0), '.0', '') || '年'" +
                " END) || (case when SUBSTR( CurrentDeptStartTime, 6, 2 ) <= SUBSTR(replace(substr(date('now'), 1,7), '-', '.'), 6, 2) then " +
                " SUBSTR(replace(substr(date('now'), 1,7), '-', '.'), 6, 2) - SUBSTR( CurrentDeptStartTime, 6, 2 ) " +
                " ELSE SUBSTR(replace(substr(date('now'), 1,7), '-', '.'), 6, 2) + 12 - SUBSTR( CurrentDeptStartTime, 6, 2 ) " +
                " end ) || '月' as currentDeptDuration, (" +
                " CASE WHEN ROUND(replace(substr(date('now'), 1,7), '-', '.') - CurrentDutyStartTime - 0.5, 0) < 1 THEN ''" +
                " ELSE replace(ROUND(replace(substr(date('now'), 1,7), '-', '.') - CurrentDutyStartTime - 0.5, 0), '.0', '') || '年' " +
                " END ) || (case when SUBSTR( CurrentDutyStartTime, 6, 2 ) <= SUBSTR(replace(substr(date('now'), 1,7), '-', '.'), 6, 2) then " +
                " SUBSTR(replace(substr(date('now'), 1,7), '-', '.'), 6, 2) - SUBSTR( CurrentDutyStartTime, 6, 2 ) " +
                " ELSE SUBSTR(replace(substr(date('now'), 1,7), '-', '.'), 6, 2) + 12 - SUBSTR( CurrentDutyStartTime, 6, 2 ) " +
                " end) || '月'" +
                " as currentDutyDuration ";
    }

    /**
     * 判断输入了查询内容后，是否选择下面的查询条件，如果没有，需要先选择
     *
     * @return 是否选择查询条件
     */
    private boolean inspect() {
        String mMajor = mainMajor.getText().toString();
        if (TextUtils.isEmpty(mMajor)) {
            return true;
        } else {
            return (!TextUtils.isEmpty(MajorTable[0]) ||
                    !TextUtils.isEmpty(MajorTable[1]) ||
                    !TextUtils.isEmpty(MajorTable[2]));
        }
    }

    /**
     * 整理SQL
     *
     * @return
     */
    private String getSelectSql() {
        String str = "";

        String work = mainWorkUnit.getText().toString();
        if (!TextUtils.isEmpty(work)) {
            str += "p.WorkUnit like '%" + work + "%' and ";
        }

        String oldWork = mainOldWorkUnit.getText().toString();
        if (!TextUtils.isEmpty(oldWork)) {
            str += "p.PersonalIdCard in (select PersonalIdCard from WorkExperience where (WorkUnit like '%" + oldWork + "%' and EndDate<>'' )) and ";
        }
//--------------------------1
//        if (!TextUtils.isEmpty(strWorkUnitId)) {
//            if (!"-1".equals(strWorkUnitId) && !"0".equals(strWorkUnitId))
//                str += "p.DeptID like '," + strWorkUnitId + ",' and ";
//        }

        String Fullname = mainFullName.getText().toString();
        if (!TextUtils.isEmpty(Fullname))
            str += "p.Fullname like '%" + Fullname + "%' and ";

        if (!TextUtils.isEmpty(Gender))
            str += "p.Gender = " + Gender + "and ";

        if (!TextUtils.isEmpty(Region))
            str += Region + "and ";

//--------------------------1
//        StartDate = (mStartDate == null) ? "" : DateUtil.getDate2_(mStartDate);
        StartDate = mainStartDate.getText().toString();
//--------------------------1
//        EndDate = (mEndDate == null) ? "" : DateUtil.getDate2_(mEndDate);
        EndDate = mainEndDate.getText().toString();
        if (TextUtils.isEmpty(StartDate) && !TextUtils.isEmpty(EndDate)) {
            StartDate = "1900.01";
            str += "p.Birthday between '" + StartDate + "' and '" + EndDate + "' and ";
        } else if (!TextUtils.isEmpty(StartDate) && TextUtils.isEmpty(EndDate)) {
            EndDate = "9999.12";
            str += "p.Birthday between '" + StartDate + "' and '" + EndDate + "' and ";
        } else if (!TextUtils.isEmpty(StartDate) && !TextUtils.isEmpty(EndDate)) {
            str += "p.Birthday between '" + StartDate + "' and '" + EndDate + "' and ";
        }

        String dept_s = mainStartDeptDate.getText().toString();
        String dept_e = mainEndDeptDate.getText().toString();
        String duty_s = mainStartDutyDate.getText().toString();
        String duty_e = mainEndDutyDate.getText().toString();
        if (!TextUtils.isEmpty(dept_s) || !TextUtils.isEmpty(dept_e)) {
            str += "CurrentDeptStartTime BETWEEN replace(substr(date('now'), 1,7), '-', '.') - "
                    + null20(dept_e) + " and replace(substr(date('now'), 1,7), '-', '.') - " + null20(dept_s) + " and ";
        }

        if (!TextUtils.isEmpty(duty_s) || !TextUtils.isEmpty(duty_e)) {
            str += "CurrentDeptStartTime BETWEEN replace(substr(date('now'), 1,7), '-', '.') - "
                    + null20(duty_e) + " and replace(substr(date('now'), 1,7), '-', '.') - " + null20(duty_s) + " and ";
        }

        if (!TextUtils.isEmpty(getArray(Politics))) {
            boolean isPart = "'中共党员'".equals(Politics[0]);
            boolean isDemocraticPart = "'民主党派'".equals(Politics[1]);
            if (!isPart || !isDemocraticPart) {
                if (isPart && !isDemocraticPart) {
                    str += " p.PoliticsStatus = '中共党员'  and ";
                } else if (!isPart && isDemocraticPart) {
                    str += " p.PoliticsStatus <> '中共党员' and ";
                }
            }
        }

        if (!TextUtils.isEmpty(getArray(Ldentity)))
            str += "p.Identity in (" + getArray(Ldentity) + ")and ";

        if (!TextUtils.isEmpty(getArray(EduDegree)))
            str += "p.PersonalIdCard in (select PersonalIdCard from EduExperience where (" + getEduDegreeSql() + "))and ";

        str += getBackgroundDegreeSql();

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

    private String getBackgroundDegreeSql() {
        String sql_fullmajor = "";
        String sql_Schoolmajor = "";
        if (!TextUtils.isEmpty(getArray(Fullmajor))) {
            sql_fullmajor = "(";
            String fullmajor = "";
            for (int i = 0; i < Fullmajor.length; i++) {
                if (!TextUtils.isEmpty(Fullmajor[i]))
                    fullmajor += Fullmajor[i];
            }
            String[] fullmajors = fullmajor.split(",");
            for (String str : fullmajors) {
                sql_fullmajor += "p.FullBackgroundDegree like '%" + str + "%' or ";
            }
        }

        if (!TextUtils.isEmpty(getArray(Schoolmajor))) {
            sql_Schoolmajor = "(";
            String schoolmajor = "";
            for (int i = 0; i < Schoolmajor.length; i++) {
                if (!TextUtils.isEmpty(Schoolmajor[i]))
                    schoolmajor += Schoolmajor[i];
            }
            String[] schoolmajors = schoolmajor.split(",");
            for (String str : schoolmajors) {
                sql_Schoolmajor += "p.GoOnBackgroundDegree like '%" + str + "%' or ";
            }
        }

        sql_fullmajor = TextUtils.isEmpty(sql_fullmajor) ? " 1=1 " : sql_fullmajor.substring(0, sql_fullmajor.length() - 3) + ")";
        sql_Schoolmajor = TextUtils.isEmpty(sql_Schoolmajor) ? " 1=1 " : sql_Schoolmajor.substring(0, sql_Schoolmajor.length() - 3) + ")";

        return sql_fullmajor + " and " + sql_Schoolmajor + " and ";
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
     * 控制开始时间
     */
    private void getStartDate() {
        TimePickerView pvTime = new TimePickerBuilder(mainActivity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (mEndDate != null) {
                    if (date.getTime() > mEndDate.getTime()) {
                        Toast.makeText(mainActivity, "开始时间不能晚于结束时间", Toast.LENGTH_SHORT).show();
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
        TimePickerView pvTime = new TimePickerBuilder(mainActivity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (mStartDate != null) {
                    if (mStartDate.getTime() > date.getTime()) {
                        Toast.makeText(mainActivity, "结束时间不能早于开始时间", Toast.LENGTH_SHORT).show();
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

    public void setStrWorkUnit(String strWorkUnit) {
        this.strWorkUnit = strWorkUnit;
        mainWorkUnit.setText((TextUtils.isEmpty(strWorkUnit) ? "所有人" : strWorkUnit));
    }

    public void setStrWorkUnitId(String strWorkUnitId) {
        this.strWorkUnitId = strWorkUnitId;
    }

    /**
     * 清空所有条件
     */
    private void Reset() {
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
        mainStartDeptDate.setText("");
        mainEndDeptDate.setText("");
        mainStartDutyDate.setText("");
        mainEndDutyDate.setText("");
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

    private String null20(String str) {
        if (TextUtils.isEmpty(str)) {
            return "0";
        }
        return str;
    }

    public interface ComprehensiveSelectSql {
        void selectSql(String sql);
    }
}

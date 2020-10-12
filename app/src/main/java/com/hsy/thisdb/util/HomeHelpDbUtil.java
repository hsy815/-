package com.hsy.thisdb.util;

import android.text.TextUtils;

import com.hsy.thisdb.db_control.DbControl;
import com.hsy.thisdb.eitity.AgeAndGender;
import com.hsy.thisdb.eitity.DailyInspectionData;
import com.hsy.thisdb.eitity.Inspection;
import com.hsy.thisdb.eitity.Institutions;
import com.hsy.thisdb.eitity.NewSysDepartment;
import com.hsy.thisdb.eitity.Peacetime;
import com.hsy.thisdb.eitity.Personal;
import com.hsy.thisdb.eitity.SysDepartment;

import java.util.ArrayList;
import java.util.List;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.util
 * @创始人: hsy
 * @创建时间: 2018/12/20 12:53
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/12/20 12:53
 * @修改描述:
 */
public class HomeHelpDbUtil {

    /**
     * 获取基本信息列表
     *
     * @param dbControl
     * @param deptId
     * @param ParentId  父关系
     * @return
     */
    public List<Personal> getPersonal(DbControl dbControl, String deptId, String ParentId, String mRankSign) {
        String sql = "select p.Fullname, p.PersonalIdCard, p.Gender, p.Birthday, p.Age, p.Ethnicity, " +
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
                " as currentDutyDuration from Personal p";
        String where = "";

        String orderStr = "p.SortNo";
        if (!TextUtils.isEmpty(deptId) && !"0".equals(deptId)) {
            sql += " left join (select personalIdCard, SortNo from SortTable where DeptId = " + deptId + ") s on p.personalIdCard = s.personalIdCard";
            where = " p.DeptID like '%," + deptId + ",%'";
            orderStr = " s.SortNo";
        } else if (!TextUtils.isEmpty(ParentId) && !"0".equals(ParentId)) {
            where = "p.ParentDeptID like '%," + ParentId + ",%'";
        }

        if (TextUtils.isEmpty(where)) {
            sql = sql + " where exists ( SELECT * FROM WorkExperience w  WHERE w.EndDate = '' and " + "w.IsUsing = 1 and w.IsLeader = " + getArray(mRankSign) + " and p.PersonalIdCard = w.PersonalIdCard )";
        } else {
            sql = sql + " where exists ( SELECT * FROM WorkExperience w  WHERE w.EndDate = '' and " + "w.IsUsing = 1 and w.IsLeader = " + getArray(mRankSign) + " and p.PersonalIdCard = w.PersonalIdCard ) and " + where;
        }
        sql = sql + " order by " + orderStr;
        return dbControl.select(sql);
    }

    /**
     * 获取单位列表
     *
     * @param dbControl
     * @param key
     * @return
     */
    public List<SysDepartment> getSysDepartment(DbControl dbControl, String key) {
        if (TextUtils.isEmpty(key))
            return dbControl.SysDepartmentSelect("select * from SysDepartment order by sortNo asc");
        else
            return dbControl.SysDepartmentSelect("select * from SysDepartment where pId = '" + key + "' order by sortNo asc");
    }

    /**
     * 平时考察
     * 0
     *
     * @param dbControl
     * @param personalIdCard
     * @param DeptID
     * @return
     */
    public List<Peacetime> getinvestigate0(DbControl dbControl, String personalIdCard, String DeptID, String mRankSign) {
        String sql = "select  p.PersonalIdCard, p.FullName, p.WorkUnit, p.BirthDay, p.Gender, Date, Evaluation, Effect, Type from investigate0 i ";
        String where = "";
        if (!TextUtils.isEmpty(personalIdCard)) {
            where = "left join personal p on i.personalIdCard = p.personalIdCard " +
                    "where exists ( SELECT * FROM WorkExperience w  WHERE w.EndDate = '' and " +
                    "w.IsUsing = 1 and w.IsLeader = " + getArray(mRankSign) + " and p.PersonalIdCard = w.PersonalIdCard ) and " +
                    "p.personalIdCard = '" + personalIdCard + "'order by i.Date";

        } else if (!TextUtils.isEmpty(DeptID) && Integer.valueOf(DeptID) > 1000) {
            where = "inner join personal p on i.personalIdCard = p.personalIdCard " +
                    "inner join (select personalIdCard, SortNo, DeptId from SortTable where DeptId = " + DeptID + ")  s on s.personalIdCard = p.personalIdCard " +
                    "where exists ( SELECT * FROM WorkExperience w  WHERE w.EndDate = '' and " +
                    "w.IsUsing = 1 and w.IsLeader = " + getArray(mRankSign) + " and p.PersonalIdCard = w.PersonalIdCard ) " +
                    "order by s.SortNo";
        } else {
            where = "left join personal p on i.personalIdCard = p.personalIdCard " +
                    "where exists ( SELECT * FROM WorkExperience w  WHERE w.EndDate = '' and " +
                    "w.IsUsing = 1 and w.IsLeader = " + getArray(mRankSign) + " and p.PersonalIdCard = w.PersonalIdCard ) " +
                    "order by p.SortNo";
        }
        sql = sql + where;
        return dbControl.PeacetimeSelect(sql);
    }

    public List<Personal> getPersonalAll(DbControl dbControl, String sql) {
        return dbControl.select(sql);
    }

    /**
     * 年龄性别结构
     *
     * @param dbControl
     * @param deptId
     * @param ParentId
     * @return
     */
    public List<AgeAndGender> getAgeAndGender(DbControl dbControl, String deptId, String ParentId) {
        String sql = "select a.WorkUnit, a.Under35, a.Between36_45, a.Between46_55, a.Up56, a.AgeAvgValue, g.man, g.women " +
                "from GenderStructure g inner join AgeStructure a on g.DeptId = a.DeptId where ";
        String where = "1=1";
        if (TextUtils.isEmpty(deptId) && TextUtils.isEmpty(ParentId)) where = "1=1";
        else
            where = TextUtils.isEmpty(deptId) ? " a.deptId like '" + ParentId + "%' and a.deptId > 1000" : "a.deptId = " + deptId;
        sql = sql + where + " order by a.deptId";
        List<AgeAndGender> tempList = dbControl.getAgeAndGender(sql);
        if (TextUtils.isEmpty(deptId)) {
            sql = "SELECT * FROM (select " +
                    "'汇总'" +
                    ", sum(case when age<=35 then 1 else 0 end) as Under35" +
                    ", sum(case when age>=36 and age<=45 then 1 else 0 end) as Between36_45" +
                    ", sum(case when age>=46 and age<=55 then 1 else 0 end) as Between46_55" +
                    ", sum(case when age>=56 then 1 else 0 end) as Up56" +
                    ", avg(age) as AgeAvgValue " +
                    "from AgeList g " +
                    "where DeptID LIKE '" + ParentId + "%')" +
                    "cross join " +
                    "(select " +
                    "sum(man) as Male " +
                    ", sum(women) as FeMale " +
                    "from GenderStructure " +
                    "WHERE DeptID LIKE '" + ParentId + "%')";
//            if (!TextUtils.isEmpty(ParentId)) {
//                sql += " where a.deptId like '" + ParentId + "%'";
//            }
            tempList.addAll(dbControl.getAgeAndGender(sql));
        }
        return tempList;
    }

    /**
     * 日常考察数据表
     *
     * @param dbControl
     * @param deptId
     * @param ParentId
     * @param name
     * @param start
     * @param end
     * @return
     */
    public List<DailyInspectionData> getDailyInspection(DbControl dbControl, String deptId, String ParentId, String name, String start, String end, String mRankSign) {
        String sql = "select * from " + DbControl.TABLE_NAME + " where ( ";
        String where = "";
        where = where + (TextUtils.isEmpty(name) ? "" : " fullname like '%" + name + "%' and ");
        if (TextUtils.isEmpty(start) && !TextUtils.isEmpty(end)) {
            start = "1900.01";
            where += "date between '" + start + "' and '" + end + "' and ";
        } else if (!TextUtils.isEmpty(start) && TextUtils.isEmpty(end)) {
            end = "9999.12";
            where += "date between '" + start + "' and '" + end + "' and ";
        } else if (!TextUtils.isEmpty(start) && !TextUtils.isEmpty(end)) {
            where += "date between '" + start + "' and '" + end + "' and ";
        }

        if (TextUtils.isEmpty(deptId) && TextUtils.isEmpty(ParentId)) {
            where += "exists (select * from WorkExperience w where w.EndDate = '' and w.IsUsing = 1 and " +
                    " w.IsLeader = " + getArray(mRankSign) + " and DailyInspection.PersonalIdCard = w.PersonalIdCard) and ";
        }

        if (!TextUtils.isEmpty(deptId)) {
            where += "exists (select * from WorkExperience w where w.EndDate = '' and w.IsUsing = 1 and " +
                    "w.DeptId like '," + deptId + ",' and w.IsLeader = " + getArray(mRankSign) + " and DailyInspection.PersonalIdCard = w.PersonalIdCard) and ";
        }

        if (!TextUtils.isEmpty(ParentId)) {
            where += "exists (select * from WorkExperience w where w.EndDate = '' and w.IsUsing = 1 and " +
                    "w.DeptId like '," + ParentId + "%' and w.IsLeader = " + getArray(mRankSign) + " and DailyInspection.PersonalIdCard = w.PersonalIdCard) and ";
        }

        if (TextUtils.isEmpty(where)) {
            where = "1=1";
        } else {
            String whereEnd = where.substring(where.length() - 4);
            if ("and ".equals(whereEnd)) {
                where = where.substring(0, where.length() - 4);
            }
        }
        sql = sql + where + " )";
        return dbControl.getDailyInspection(sql);
    }

    /**
     * 日常考察数据表
     *
     * @param dbControl
     * @param deptId
     * @param ParentId
     * @param name
     * @param start
     * @param end
     * @return
     */
    public List<DailyInspectionData> getInvestigate0(DbControl dbControl, String deptId, String ParentId, String name, String start, String end, String mRankSign) {
        String sql = "select * from investigate0 where ( ";
        String where = "";
        where = where + (TextUtils.isEmpty(name) ? "" : " fullname like '%" + name + "%' and ");
        if (TextUtils.isEmpty(start) && !TextUtils.isEmpty(end)) {
            start = "1900.01";
            where += "date between '" + start + "' and '" + end + "' and ";
        } else if (!TextUtils.isEmpty(start) && TextUtils.isEmpty(end)) {
            end = "9999.12";
            where += "date between '" + start + "' and '" + end + "' and ";
        } else if (!TextUtils.isEmpty(start) && !TextUtils.isEmpty(end)) {
            where += "date between '" + start + "' and '" + end + "' and ";
        }

        if (TextUtils.isEmpty(deptId) && TextUtils.isEmpty(ParentId)) {
            where += "exists (select * from WorkExperience w where w.EndDate = '' and w.IsUsing = 1 and " +
                    " w.IsLeader = " + getArray(mRankSign) + " and investigate0.PersonalIdCard = w.PersonalIdCard) and ";
        }

        if (!TextUtils.isEmpty(deptId)) {
            where += "exists (select * from WorkExperience w where w.EndDate = '' and w.IsUsing = 1 and " +
                    "w.DeptId like '," + deptId + ",' and w.IsLeader = " + getArray(mRankSign) + " and investigate0.PersonalIdCard = w.PersonalIdCard) and ";
        }

        if (!TextUtils.isEmpty(ParentId)) {
            where += "exists (select * from WorkExperience w where w.EndDate = '' and w.IsUsing = 1 and " +
                    "w.DeptId like '," + ParentId + "%' and w.IsLeader = " + getArray(mRankSign) + " and investigate0.PersonalIdCard = w.PersonalIdCard) and ";
        }
        where += "padSign = 1 and ";
        String whereEnd = where.substring(where.length() - 4);
        if ("and ".equals(whereEnd)) {
            where = where.substring(0, where.length() - 4);
        }
        sql = sql + where + " )";
        return dbControl.getDailyInspectionData(sql);
    }

    /**
     * 干部编制情况
     *
     * @param dbControl
     * @param deptId
     * @param ParentId
     * @return
     */
    public List<Institutions> getInstitutions(DbControl dbControl, String deptId, String ParentId) {
        String sql = "select * from Institutions where ";
        String where = "1=1";
        if (TextUtils.isEmpty(deptId) && TextUtils.isEmpty(ParentId)) where = "1=1";
        else
            where = TextUtils.isEmpty(deptId) ? " deptId like '" + ParentId + "%' and deptId > 1000" : "deptId = " + deptId;
        sql = sql + where + " order by deptId";
        return dbControl.institutionsSelect(sql);
    }

    /**
     * 督查信息查询
     *
     * @param dbControl
     * @param deptId
     * @return
     */
    public List<Inspection> getInspection(DbControl dbControl, String personalIdCard, String deptId, String mRankSign) {
        String sql = "select * from Inspection p where exists ( SELECT * FROM WorkExperience w  " +
                "WHERE w.EndDate = '' and w.IsUsing = 1 and w.IsLeader = " + getArray(mRankSign) + " and p.PersonalIdCard = w.PersonalIdCard ) and ";
        String where = "1=1";
        if (!TextUtils.isEmpty(personalIdCard))
            where = "personalIdCard = '" + personalIdCard + "'";
        else if (!TextUtils.isEmpty(deptId))
            where = "deptId like '%," + deptId + ",%'";
        sql = sql + where + " order by SortNo";
        return dbControl.inspectionSelect(sql);
    }

    /**
     * 所有单位查询
     *
     * @param dbControl
     * @return
     */
    public List<NewSysDepartment> getNewSysDepartment(DbControl dbControl) {
        List<NewSysDepartment> sysDepartmentListAll = new ArrayList<>();
        String sql = "select substr(replace(name,'东阳市',case when instr(name,'街道')>0 or instr(name,'镇')>0 or instr(name,'乡') then '' else '市' end),0,10) || case when length(name)>10 then '...' else '' end as name," +
                " id, pId, IsUsing, GroupNo, sortNo from SysDepartment where groupNo > 0 and id < 1000";
        List<NewSysDepartment> fristList = dbControl.newSysDepartmentSelect(sql);
        for (int i = 0; i < fristList.size(); i++) {
            String sql2 = "select substr(replace(name,'东阳市',case when instr(name,'街道')>0 or instr(name,'镇')>0 or instr(name,'乡') then '' else '市' end),0,10) || case when length(name)>10 then '...' else '' end as name," +
                    " id, pId, IsUsing, GroupNo, sortNo  from SysDepartment where GroupNo=" + fristList.get(i).getGroupNo() + " order by SortNo";
            sysDepartmentListAll.addAll(dbControl.newSysDepartmentSelect(sql2));
        }
        return sysDepartmentListAll;
    }

    /**
     * 字段拼接
     *
     * @param mRankSign
     * @return
     */
    private int getArray(String mRankSign) {
        if ("中层干部".equals(mRankSign)) {
            return 0;
        } else {
            return 1;
        }
    }
}

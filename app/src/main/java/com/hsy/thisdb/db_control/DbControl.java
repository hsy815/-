package com.hsy.thisdb.db_control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hsy.thisdb.eitity.AgeAndGender;
import com.hsy.thisdb.eitity.AgeStructure;
import com.hsy.thisdb.eitity.AnnualAppraisal;
import com.hsy.thisdb.eitity.DailyInspectionData;
import com.hsy.thisdb.eitity.FamilyMember;
import com.hsy.thisdb.eitity.GenderStructure;
import com.hsy.thisdb.eitity.Inspection;
import com.hsy.thisdb.eitity.Institutions;
import com.hsy.thisdb.eitity.NewSysDepartment;
import com.hsy.thisdb.eitity.Peacetime;
import com.hsy.thisdb.eitity.Personal;
import com.hsy.thisdb.eitity.RewardPunish;
import com.hsy.thisdb.eitity.SysDepartment;
import com.hsy.thisdb.eitity.UserData;
import com.hsy.thisdb.eitity.WorkExperience;
import com.hsy.thisdb.eitity.investigate0;
import com.hsy.thisdb.util.CopyDb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb
 * @创始人: hsy
 * @创建时间: 2018/11/20 9:48
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/11/20 9:48
 * @修改描述:
 */
public class DbControl {

    private static DbControl dbControl;
    private final SQLiteDatabase database;
    private Context context;
    private String DbName = "bd.db";
    private MySqliteHelper mySqliteHelper;

    public static DbControl getInstent(Context mContext) {
        if (dbControl == null)
            dbControl = new DbControl(mContext);
        return dbControl;
    }

    private DbControl(Context context) {
        this.context = context;
        try {
            CopyDb.CopySqliteFileFromRawToDatabases(DbName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mySqliteHelper = new MySqliteHelper(context, DbName, null, 1);
        database = mySqliteHelper.getWritableDatabase();
    }

    public List<Personal> select(String sql) {
        List<Personal> personals = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);
        int id = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            id++;
            personals.add(new Personal(id + "", cursor.getString(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getInt(4),
                    cursor.getString(5), cursor.getString(6), cursor.getString(7),
                    cursor.getString(8), cursor.getString(9), cursor.getString(10),
                    cursor.getInt(11), cursor.getInt(12), cursor.getString(13),
                    cursor.getString(14), cursor.getString(15), cursor.getString(16),
                    cursor.getString(17), cursor.getString(18), cursor.getString(19),
                    cursor.getString(20), cursor.getString(21), cursor.getString(22),
                    cursor.getString(23), cursor.getString(24), cursor.getString(25),
                    cursor.getString(26), cursor.getString(27), cursor.getString(28),
                    cursor.getString(29), cursor.getString(30), cursor.getString(31),
                    cursor.getString(32), cursor.getString(33), cursor.getString(34),
                    cursor.getString(35), cursor.getString(36), cursor.getString(37),
                    cursor.getString(38), cursor.getString(39), cursor.getInt(40),
                    cursor.getString(41), cursor.getString(42)));
        }
        cursor.close();
        return personals;
    }

    /**
     * 年龄情况
     *
     * @param sql
     * @return
     */
    public List<AgeStructure> ageSelect(String sql) {
        List<AgeStructure> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);
        int id = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            id++;
            list.add(new AgeStructure(id, cursor.getString(0), cursor.getInt(1),
                    cursor.getInt(2), cursor.getInt(3), cursor.getInt(4),
                    cursor.getInt(5), cursor.getInt(6)));
        }
        cursor.close();
        return list;
    }

    /**
     * 性别情况
     *
     * @param sql
     * @return
     */
    public List<GenderStructure> sexSelect(String sql) {
        List<GenderStructure> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);
        int id = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            id++;
            list.add(new GenderStructure(id, cursor.getString(0), cursor.getString(1),
                    cursor.getInt(2), cursor.getInt(3), cursor.getInt(4)));
        }
        cursor.close();
        return list;
    }

    /**
     * 干部编制情况
     *
     * @param sql
     * @return
     */
    public List<Institutions> institutionsSelect(String sql) {
        List<Institutions> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);
        int id = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            id++;
            list.add(new Institutions(id, cursor.getString(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6), cursor.getString(7),
                    cursor.getString(8), cursor.getInt(9), cursor.getInt(10),
                    cursor.getInt(11), cursor.getInt(12), cursor.getInt(13),
                    cursor.getInt(14)));
        }
        cursor.close();
        return list;
    }

    /**
     * 单位查询
     *
     * @param sql
     * @return
     */
    public List<NewSysDepartment> newSysDepartmentSelect(String sql) {
        List<NewSysDepartment> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);
        int id = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            id++;
            list.add(new NewSysDepartment(id, cursor.getString(0), cursor.getInt(1),
                    cursor.getInt(2), cursor.getInt(3), cursor.getInt(4),
                    cursor.getInt(5), false));
        }
        cursor.close();
        return list;
    }

    /**
     * 督查信息查询
     *
     * @param sql
     * @return
     */
    public List<Inspection> inspectionSelect(String sql) {
        List<Inspection> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);
        int id = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            id++;
            list.add(new Inspection(cursor.getString(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6), cursor.getString(7),
                    cursor.getInt(8), cursor.getString(9), cursor.getString(10),
                    cursor.getString(11)));
        }
        cursor.close();
        return list;
    }

    /**
     * 奖惩情况
     *
     * @param sql
     * @return
     */
    public List<RewardPunish> RewardPunishSelect(String sql) {
        List<RewardPunish> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);
        int id = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            id++;
            list.add(new RewardPunish(id, cursor.getString(0), cursor.getString(1)));
        }
        cursor.close();
        return list;
    }

    /**
     * 年度考核结果
     *
     * @param sql
     * @return
     */
    public List<AnnualAppraisal> AnnualAppraisalSelect(String sql) {
        List<AnnualAppraisal> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);
        int id = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            id++;
            list.add(new AnnualAppraisal(id, cursor.getString(0), cursor.getString(1),
                    cursor.getString(2)));
        }
        cursor.close();
        return list;
    }

    /**
     * 家庭主要成员及重要社会关系
     *
     * @param sql
     * @return
     */
    public List<FamilyMember> FamilyMemberSelect(String sql) {
        List<FamilyMember> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);
        int id = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            id++;
            list.add(new FamilyMember(id, cursor.getString(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6)));
        }
        cursor.close();
        return list;
    }

    /**
     * 简历
     *
     * @param sql
     * @return
     */
    public List<WorkExperience> WorkExperienceSelect(String sql) {
        List<WorkExperience> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);
        int id = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            id++;
            list.add(new WorkExperience(id, cursor.getString(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4),
                    cursor.getInt(5), cursor.getString(6), cursor.getInt(7),
                    cursor.getInt(8), cursor.getString(9), cursor.getString(10),
                    cursor.getInt(11), cursor.getInt(12), cursor.getString(13),
                    cursor.getString(14), cursor.getString(15)));
        }
        cursor.close();
        return list;
    }


    /**
     * 查询工作
     *
     * @param sql
     * @return
     */
    public List<SysDepartment> SysDepartmentSelect(String sql) {
        List<SysDepartment> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);
        int id = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            id++;
            list.add(new SysDepartment(id, cursor.getString(0), cursor.getInt(1),
                    cursor.getInt(2), cursor.getInt(3), cursor.getInt(4)));
        }
        cursor.close();
        return list;
    }

    public List<investigate0> investigate0SelectAll(String sql) {
        List<investigate0> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);
        int id = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            id++;
            list.add(new investigate0(id, cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getInt(4),
                    cursor.getInt(5), cursor.getString(6), cursor.getString(7),
                    cursor.getString(8)));
        }
        cursor.close();
        return list;
    }

    public List<String[]> investigate0Select(String content) {
        List<String[]> list = new ArrayList<>();
        Cursor cursor = database.query("investigate0", new String[]{"Fullname",
                "PersonalIdCard",
                "WorkUnit",
                "Date",
                "Effect",
                "Type"}, "PersonalIdCard=?", new String[]{"content"}, "", "", "");
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            list.add(new String[]{cursor.getString(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3),
                    cursor.getString(4), cursor.getString(5)});
        }
        cursor.close();
        return list;
    }

    public List<Peacetime> PeacetimeSelect(String sql) {
        List<Peacetime> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            list.add(new Peacetime(cursor.getString(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6), cursor.getString(7),
                    cursor.getString(8)));
        }
        cursor.close();
        return list;
    }

    /**
     * 查询上传数据
     *
     * @param sql
     * @return
     */
    public List<DailyInspectionData> getDailyInspectionData(String sql) {
        List<DailyInspectionData> dataList = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            dataList.add(new DailyInspectionData(cursor.getString(7), cursor.getString(4),
                    cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(8)));
        }
        cursor.close();
        return dataList;
    }

    /**
     * 年龄性别结构
     *
     * @param sql
     * @return
     */
    public List<AgeAndGender> getAgeAndGender(String sql) {
        List<AgeAndGender> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            list.add(new AgeAndGender(cursor.getString(0), cursor.getInt(1),
                    cursor.getInt(2), cursor.getInt(3), cursor.getInt(4),
                    cursor.getInt(5), cursor.getInt(6), cursor.getInt(7)));
        }
        cursor.close();
        return list;
    }

    /**
     * 添加平时考察
     *
     * @param inspectionData
     * @return
     */
    public boolean addInvestigate0Data(DailyInspectionData inspectionData) {
        ContentValues values = new ContentValues();
        values.put("FullName", inspectionData.getFullname());
        values.put("PersonalIdCard", inspectionData.getPersonalIdCard());
        values.put("Date", inspectionData.getDate());
        values.put("Effect", inspectionData.getDailyInspection());
        values.put("Evaluation", inspectionData.getRecordEvaluate());
        values.put("IsUsing", 1);
        values.put("padSign", 1);
        //添加数据到数据库
        long index = database.insert("investigate0", null, values);
        //大于0表示添加成功
        return index > 0;
    }

    /*表名*/
    public static final String TABLE_NAME = "DailyInspection";
    /*id字段*/
    private final String FULL_NAME = "fullname";
    private final String PERSONAL_ID_CARD = "personalIdCard";
    private final String DATE = "date";
    private final String DAILY_INSPECTION = "dailyInspection";
    private final String DAILY_RECORD_NAME = "recordName";

    /**
     * 新建日常考察数据表
     */
    public void addDailyInspectionTable() {
        final String CREATE_PERSON = "create table if not exists " + TABLE_NAME + "(" +
                FULL_NAME + " text," +
                PERSONAL_ID_CARD + " text," +
                DATE + " text," +
                DAILY_INSPECTION + " text," +
                DAILY_RECORD_NAME + " text" +
                ")";

        addAll(CREATE_PERSON);
    }

    /**
     * 日常考察数据表 添加数据
     *
     * @param inspectionData
     * @return
     */
    public boolean addDailyInspectionData(DailyInspectionData inspectionData) {
        ContentValues values = new ContentValues();
        values.put(FULL_NAME, inspectionData.getFullname());
        values.put(PERSONAL_ID_CARD, inspectionData.getPersonalIdCard());
        values.put(DATE, inspectionData.getDate());
        values.put(DAILY_INSPECTION, inspectionData.getDailyInspection());
        values.put(DAILY_RECORD_NAME, inspectionData.getRecordRecordName());
        //添加数据到数据库
        long index = database.insert(TABLE_NAME, null, values);
        //大于0表示添加成功
        return index > 0;
    }

    /**
     * 日常考察数据表查询数据
     *
     * @param sql
     * @return
     */
    public List<DailyInspectionData> getDailyInspection(String sql) {
        List<DailyInspectionData> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            list.add(new DailyInspectionData(cursor.getString(1), cursor.getString(2),
                    cursor.getString(4), cursor.getString(5),
                    cursor.getString(6), cursor.getString(6)));//注意  这里因为暂时不用了  所以是随便取值的  如果使用需要在表里面新增workUnit 字段
        }
        cursor.close();
        return list;
    }

    /**
     * 删除整合后的日程考察单条数据
     *
     * @param inspectionData
     */
    public void deleteDailyInspection0(DailyInspectionData inspectionData) {
        database.execSQL("DELETE FROM investigate0 WHERE (personalIdCard = " + inspectionData.getPersonalIdCard() + " and padSign = 1)");
    }

    /**
     * 修改整合后的日程考察数据
     * values.put("FullName", inspectionData.getFullname());
     * *                       values.put("PersonalIdCard", inspectionData.getPersonalIdCard());
     * *                       values.put("Date", inspectionData.getDate());
     * *                       values.put("Effect", inspectionData.getDailyInspection());
     * *                       values.put("Evaluation", inspectionData.getRecordEvaluate());
     * *                       values.put("IsUsing", 1);
     * *                       values.put("padSign", 1);
     *
     * @param inspectionData
     */
    public void upDateDailyInspection0(DailyInspectionData inspectionData) {
        database.execSQL("UPDATE investigate0 SET " +
                "FullName = '" + inspectionData.getFullname() + "'" +
                ",Date =" + inspectionData.getDate() +
                ",Effect='" + inspectionData.getDailyInspection() + "'" +
                ",Evaluation='" + inspectionData.getRecordEvaluate() + "'" +
                " WHERE (personalIdCard = " + inspectionData.getPersonalIdCard() + " and padSign = 1)");
    }

    /**
     * 获取用户表
     *
     * @param sql
     * @return
     */
    public List<UserData> getUserData(String sql) {
        List<UserData> dataList = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            dataList.add(new UserData(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3),
                    cursor.getInt(4), cursor.getString(5)));
        }
        cursor.close();
        return dataList;
    }

    /**
     * 获取所有表名
     *
     * @return
     */
    public List<String> getAllTableName() {
        List<String> list = new ArrayList();
        String sql = "SELECT name FROM SQLITE_MASTER WHERE type='table' ORDER BY name";
        Cursor cursor = database.rawQuery(sql, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            list.add(cursor.getString(0));
        }
        cursor.close();
        return list;
    }

    /**
     * 判断是否有这个表
     *
     * @param tableName  需要检查的表名
     * @param stringList 所有表
     * @return
     */
    public boolean isTable(String tableName, List<String> stringList) {
        for (int i = 0; 1 < stringList.size(); i++) {
            return tableName.equals(stringList.get(i));
        }
        return false;
    }

    /**
     * 增加一条信息
     *
     * @param sql
     */
    public void addAll(String sql) {
        database.execSQL(sql);
    }

    /**
     * 删除一条信息
     *
     * @param sql
     */
    public void DaleAll(String sql) {
        database.execSQL(sql);
    }

    /**
     * 关闭数据库连接
     */
    public void Close() {
        mySqliteHelper.close();
    }
}

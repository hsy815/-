package com.hsy.thisdb.eitity;

import java.io.Serializable;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb
 * @创始人: hsy
 * @创建时间: 2018/11/20 10:15
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/11/20 10:15
 * @修改描述:
 */
public class Personal implements Serializable {

    //rowid	Fullname	PersonalIdCard	Gender	Birthday	Age	Ethnicity	WorkDate	PoliticsStatus	JoinDate
    // NativePlace	NativeOrigin	politicsId	IsUsing	addrId	Status	DeptID	ParentDeptID	WorkUnit	Identity
    // SectionName	Duty	WorkDegree	MajorDegree	Speciality	EduDegree	School	Department	EduMajor	StartDate
    // EndDate	EduStatus	EduBackground	IsHighest	FullSchoolMajor	FullBackgroundDegree	FullMajor	IsFullTime
    // GoOnSchoolMajor	GoOnBackgroundDegree	HealthyStatus  CurrentDeptStartTime   CurrentDutyStartTime
    public Personal(String fullname, String personalIdCard) {
        Fullname = fullname;
        PersonalIdCard = personalIdCard;
    }

    public Personal(String fullname, String gender, String birthday, String workUnit, String sectionName,
                    String duty, String fullBackgroundDegree, String fullMajor, String goOnSchoolMajor,
                    String goOnBackgroundDegree, String currentDeptStartTime, String currentDutyStartTime,
                    String workDate, String politicsStatus) {
        Fullname = fullname;
        Gender = gender;
        Birthday = birthday;
        WorkUnit = workUnit;
        SectionName = sectionName;
        Duty = duty;
        FullMajor = fullMajor;
        FullBackgroundDegree = fullBackgroundDegree;
        GoOnSchoolMajor = goOnSchoolMajor;
        GoOnBackgroundDegree = goOnBackgroundDegree;
        CurrentDeptStartTime = currentDeptStartTime;
        CurrentDutyStartTime = currentDutyStartTime;
        WorkDate = workDate;
        PoliticsStatus = politicsStatus;
    }

    public Personal(String rowid, String fullname, String personalIdCard, String gender, String birthday,
                    int age, String ethnicity, String workDate, String politicsStatus, String joinDate,
                    String nativePlace, String nativeOrigin, int politicsId, int isUsing, String addrId,
                    String status, String deptID, String parentDeptID, String workUnit, String identity,
                    String sectionName, String duty, String workDegree, String majorDegree, String speciality,
                    String eduDegree, String school, String department, String eduMajor, String startDate,
                    String endDate, String eduStatus, String eduBackground, String isHighest,
                    String fullSchoolMajor, String fullBackgroundDegree, String fullMajor,
                    String isFullTime, String goOnSchoolMajor, String goOnBackgroundDegree, String healthyStatus) {
        this.rowid = rowid;
        Fullname = fullname;
        PersonalIdCard = personalIdCard;
        Gender = gender;
        Birthday = birthday;
        Age = age;
        Ethnicity = ethnicity;
        WorkDate = workDate;
        PoliticsStatus = politicsStatus;
        JoinDate = joinDate;
        NativePlace = nativePlace;
        NativeOrigin = nativeOrigin;
        this.politicsId = politicsId;
        IsUsing = isUsing;
        this.addrId = addrId;
        Status = status;
        DeptID = deptID;
        ParentDeptID = parentDeptID;
        WorkUnit = workUnit;
        Identity = identity;
        SectionName = sectionName;
        Duty = duty;
        WorkDegree = workDegree;
        MajorDegree = majorDegree;
        Speciality = speciality;
        EduDegree = eduDegree;
        School = school;
        Department = department;
        EduMajor = eduMajor;
        StartDate = startDate;
        EndDate = endDate;
        EduStatus = eduStatus;
        EduBackground = eduBackground;
        IsHighest = isHighest;
        FullSchoolMajor = fullSchoolMajor;
        FullBackgroundDegree = fullBackgroundDegree;
        FullMajor = fullMajor;
        IsFullTime = isFullTime;
        GoOnSchoolMajor = goOnSchoolMajor;
        GoOnBackgroundDegree = goOnBackgroundDegree;
        HealthyStatus = healthyStatus;
    }

    public Personal(String rowid, String fullname, String personalIdCard, String gender, String birthday,
                    int age, String ethnicity, String workDate, String politicsStatus, String joinDate,
                    String nativePlace, String nativeOrigin, int politicsId, int isUsing, String addrId,
                    String status, String deptID, String parentDeptID, String workUnit, String identity,
                    String sectionName, String duty, String workDegree, String majorDegree, String speciality,
                    String eduDegree, String school, String department, String eduMajor, String startDate,
                    String endDate, String eduStatus, String eduBackground, String isHighest, String fullSchoolMajor,
                    String fullBackgroundDegree, String fullMajor, String isFullTime, String goOnSchoolMajor,
                    String goOnBackgroundDegree, String healthyStatus, int sortNo, String currentDeptStartTime,
                    String currentDutyStartTime) {
        this.rowid = rowid;
        Fullname = fullname;
        PersonalIdCard = personalIdCard;
        Gender = gender;
        Birthday = birthday;
        Age = age;
        Ethnicity = ethnicity;
        WorkDate = workDate;
        PoliticsStatus = politicsStatus;
        JoinDate = joinDate;
        NativePlace = nativePlace;
        NativeOrigin = nativeOrigin;
        this.politicsId = politicsId;
        IsUsing = isUsing;
        this.addrId = addrId;
        Status = status;
        DeptID = deptID;
        ParentDeptID = parentDeptID;
        WorkUnit = workUnit;
        Identity = identity;
        SectionName = sectionName;
        Duty = duty;
        WorkDegree = workDegree;
        MajorDegree = majorDegree;
        Speciality = speciality;
        EduDegree = eduDegree;
        School = school;
        Department = department;
        EduMajor = eduMajor;
        StartDate = startDate;
        EndDate = endDate;
        EduStatus = eduStatus;
        EduBackground = eduBackground;
        IsHighest = isHighest;
        FullSchoolMajor = fullSchoolMajor;
        FullBackgroundDegree = fullBackgroundDegree;
        FullMajor = fullMajor;
        IsFullTime = isFullTime;
        GoOnSchoolMajor = goOnSchoolMajor;
        GoOnBackgroundDegree = goOnBackgroundDegree;
        HealthyStatus = healthyStatus;
        SortNo = sortNo;
        CurrentDeptStartTime = currentDeptStartTime;
        CurrentDutyStartTime = currentDutyStartTime;
    }

    private String rowid;
    private String Fullname;
    private String PersonalIdCard;
    private String Gender;
    private String Birthday;
    private int Age;
    private String Ethnicity;
    private String WorkDate;
    private String PoliticsStatus;
    private String JoinDate;
    private String NativePlace;
    private String NativeOrigin;
    private int politicsId;
    private int IsUsing;
    private String addrId;
    private String Status;
    private String DeptID;
    private String ParentDeptID;
    private String WorkUnit;
    private String Identity;
    private String SectionName;
    private String Duty;
    private String WorkDegree;
    private String MajorDegree;
    private String Speciality;
    private String EduDegree;
    private String School;
    private String Department;
    private String EduMajor;
    private String StartDate;
    private String EndDate;
    private String EduStatus;
    private String EduBackground;
    private String IsHighest;
    private String FullSchoolMajor;
    private String FullBackgroundDegree;
    private String FullMajor;
    private String IsFullTime;
    private String GoOnSchoolMajor;
    private String GoOnBackgroundDegree;
    private String HealthyStatus;
    private int SortNo;
    private String CurrentDeptStartTime;
    private String CurrentDutyStartTime;

    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getPersonalIdCard() {
        return PersonalIdCard;
    }

    public void setPersonalIdCard(String personalIdCard) {
        PersonalIdCard = personalIdCard;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getEthnicity() {
        return Ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        Ethnicity = ethnicity;
    }

    public String getWorkDate() {
        return WorkDate;
    }

    public void setWorkDate(String workDate) {
        WorkDate = workDate;
    }

    public String getPoliticsStatus() {
        return PoliticsStatus;
    }

    public void setPoliticsStatus(String politicsStatus) {
        PoliticsStatus = politicsStatus;
    }

    public String getJoinDate() {
        return JoinDate;
    }

    public void setJoinDate(String joinDate) {
        JoinDate = joinDate;
    }

    public String getNativePlace() {
        return NativePlace;
    }

    public void setNativePlace(String nativePlace) {
        NativePlace = nativePlace;
    }

    public String getNativeOrigin() {
        return NativeOrigin;
    }

    public void setNativeOrigin(String nativeOrigin) {
        NativeOrigin = nativeOrigin;
    }

    public int getPoliticsId() {
        return politicsId;
    }

    public void setPoliticsId(int politicsId) {
        this.politicsId = politicsId;
    }

    public int getIsUsing() {
        return IsUsing;
    }

    public void setIsUsing(int isUsing) {
        IsUsing = isUsing;
    }

    public String getAddrId() {
        return addrId;
    }

    public void setAddrId(String addrId) {
        this.addrId = addrId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDeptID() {
        return DeptID;
    }

    public void setDeptID(String deptID) {
        DeptID = deptID;
    }

    public String getParentDeptID() {
        return ParentDeptID;
    }

    public void setParentDeptID(String parentDeptID) {
        ParentDeptID = parentDeptID;
    }

    public String getWorkUnit() {
        return WorkUnit;
    }

    public void setWorkUnit(String workUnit) {
        WorkUnit = workUnit;
    }

    public String getIdentity() {
        return Identity;
    }

    public void setIdentity(String identity) {
        Identity = identity;
    }

    public String getSectionName() {
        return SectionName;
    }

    public void setSectionName(String sectionName) {
        SectionName = sectionName;
    }

    public String getDuty() {
        return Duty;
    }

    public void setDuty(String duty) {
        Duty = duty;
    }

    public String getWorkDegree() {
        return WorkDegree;
    }

    public void setWorkDegree(String workDegree) {
        WorkDegree = workDegree;
    }

    public String getMajorDegree() {
        return MajorDegree;
    }

    public void setMajorDegree(String majorDegree) {
        MajorDegree = majorDegree;
    }

    public String getSpeciality() {
        return Speciality;
    }

    public void setSpeciality(String speciality) {
        Speciality = speciality;
    }

    public String getEduDegree() {
        return EduDegree;
    }

    public void setEduDegree(String eduDegree) {
        EduDegree = eduDegree;
    }

    public String getSchool() {
        return School;
    }

    public void setSchool(String school) {
        School = school;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getEduMajor() {
        return EduMajor;
    }

    public void setEduMajor(String eduMajor) {
        EduMajor = eduMajor;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getEduStatus() {
        return EduStatus;
    }

    public void setEduStatus(String eduStatus) {
        EduStatus = eduStatus;
    }

    public String getEduBackground() {
        return EduBackground;
    }

    public void setEduBackground(String eduBackground) {
        EduBackground = eduBackground;
    }

    public String getIsHighest() {
        return IsHighest;
    }

    public void setIsHighest(String isHighest) {
        IsHighest = isHighest;
    }

    public String getFullSchoolMajor() {
        return FullSchoolMajor;
    }

    public void setFullSchoolMajor(String fullSchoolMajor) {
        FullSchoolMajor = fullSchoolMajor;
    }

    public String getFullBackgroundDegree() {
        return FullBackgroundDegree;
    }

    public void setFullBackgroundDegree(String fullBackgroundDegree) {
        FullBackgroundDegree = fullBackgroundDegree;
    }

    public String getFullMajor() {
        return FullMajor;
    }

    public void setFullMajor(String fullMajor) {
        FullMajor = fullMajor;
    }

    public String getIsFullTime() {
        return IsFullTime;
    }

    public void setIsFullTime(String isFullTime) {
        IsFullTime = isFullTime;
    }

    public String getGoOnSchoolMajor() {
        return GoOnSchoolMajor;
    }

    public void setGoOnSchoolMajor(String goOnSchoolMajor) {
        GoOnSchoolMajor = goOnSchoolMajor;
    }

    public String getGoOnBackgroundDegree() {
        return GoOnBackgroundDegree;
    }

    public void setGoOnBackgroundDegree(String goOnBackgroundDegree) {
        GoOnBackgroundDegree = goOnBackgroundDegree;
    }

    public String getHealthyStatus() {
        return HealthyStatus;
    }

    public void setHealthyStatus(String healthyStatus) {
        HealthyStatus = healthyStatus;
    }

    public int getSortNo() {
        return SortNo;
    }

    public void setSortNo(int sortNo) {
        SortNo = sortNo;
    }

    public String getCurrentDeptStartTime() {
        return CurrentDeptStartTime;
    }

    public void setCurrentDeptStartTime(String currentDeptStartTime) {
        CurrentDeptStartTime = currentDeptStartTime;
    }

    public String getCurrentDutyStartTime() {
        return CurrentDutyStartTime;
    }

    public void setCurrentDutyStartTime(String currentDutyStartTime) {
        CurrentDutyStartTime = currentDutyStartTime;
    }
}

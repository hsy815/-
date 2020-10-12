package com.hsy.thisdb.eitity;

import java.io.Serializable;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.eitity
 * @创始人: hsy
 * @创建时间: 2018/11/29 10:42
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/11/29 10:42
 * @修改描述:
 */
public class WorkExperience implements Serializable {
    //rowid	WorkUnit	StartDate	EndDate	Duty	Degree	Status	SectionName 	DeptID	SectionID	PersonalIdCard	Identity	IsUsing	IsImport	FullName	WorkUnit1	Memo
    private int rowid;
    private String WorkUnit;
    private String StartDate;
    private String EndDate;
    private String Duty;
    private String Degree;
    private int Status;
    private String SectionName;
    private int DeptID;
    private int SectionID;
    private String PersonalIdCard;
    private String Identity;
    private int IsUsing;
    private int IsImport;
    private String FullName;
    private String WorkUnit1;
    private String Memo;

    public WorkExperience(int rowid, String workUnit, String startDate, String endDate, String duty, String degree, int status, String sectionName, int deptID, int sectionID, String personalIdCard, String identity, int isUsing, int isImport, String fullName, String workUnit1, String memo) {
        this.rowid = rowid;
        WorkUnit = workUnit;
        StartDate = startDate;
        EndDate = endDate;
        Duty = duty;
        Degree = degree;
        Status = status;
        SectionName = sectionName;
        DeptID = deptID;
        SectionID = sectionID;
        PersonalIdCard = personalIdCard;
        Identity = identity;
        IsUsing = isUsing;
        IsImport = isImport;
        FullName = fullName;
        WorkUnit1 = workUnit1;
        Memo = memo;
    }

    public int getRowid() {
        return rowid;
    }

    public void setRowid(int rowid) {
        this.rowid = rowid;
    }

    public String getWorkUnit() {
        return WorkUnit;
    }

    public void setWorkUnit(String workUnit) {
        WorkUnit = workUnit;
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

    public String getDuty() {
        return Duty;
    }

    public void setDuty(String duty) {
        Duty = duty;
    }

    public String getDegree() {
        return Degree;
    }

    public void setDegree(String degree) {
        Degree = degree;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getSectionName() {
        return SectionName;
    }

    public void setSectionName(String sectionName) {
        SectionName = sectionName;
    }

    public int getDeptID() {
        return DeptID;
    }

    public void setDeptID(int deptID) {
        DeptID = deptID;
    }

    public int getSectionID() {
        return SectionID;
    }

    public void setSectionID(int sectionID) {
        SectionID = sectionID;
    }

    public String getPersonalIdCard() {
        return PersonalIdCard;
    }

    public void setPersonalIdCard(String personalIdCard) {
        PersonalIdCard = personalIdCard;
    }

    public String getIdentity() {
        return Identity;
    }

    public void setIdentity(String identity) {
        Identity = identity;
    }

    public int getIsUsing() {
        return IsUsing;
    }

    public void setIsUsing(int isUsing) {
        IsUsing = isUsing;
    }

    public int getIsImport() {
        return IsImport;
    }

    public void setIsImport(int isImport) {
        IsImport = isImport;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getWorkUnit1() {
        return WorkUnit1;
    }

    public void setWorkUnit1(String workUnit1) {
        WorkUnit1 = workUnit1;
    }

    public String getMemo() {
        return Memo;
    }

    public void setMemo(String memo) {
        Memo = memo;
    }
}

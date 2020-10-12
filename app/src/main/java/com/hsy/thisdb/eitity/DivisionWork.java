package com.hsy.thisdb.eitity;

import java.io.Serializable;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.eitity
 * @创始人: hsy
 * @创建时间: 2018/11/27 18:08
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/11/27 18:08
 * @修改描述:
 */
public class DivisionWork implements Serializable {

    //rowid	ID	DividedContent	StartDate	EndDate	Effect	PersonalIdCard	IsUsing	IsImport	FullName	WorkUnit
    private int rowid;
    private int ID;
    private String DividedContent;
    private String StartDate;
    private String EndDate;
    private String Effect;
    private String PersonalIdCard;
    private int IsUsing;
    private int IsImport;
    private String FullName;
    private String WorkUnit;

    public DivisionWork(int rowid, int ID, String dividedContent, String startDate, String endDate, String effect, String personalIdCard, int isUsing, int isImport, String fullName, String workUnit) {
        this.rowid = rowid;
        this.ID = ID;
        DividedContent = dividedContent;
        StartDate = startDate;
        EndDate = endDate;
        Effect = effect;
        PersonalIdCard = personalIdCard;
        IsUsing = isUsing;
        IsImport = isImport;
        FullName = fullName;
        WorkUnit = workUnit;
    }

    public int getRowid() {
        return rowid;
    }

    public void setRowid(int rowid) {
        this.rowid = rowid;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDividedContent() {
        return DividedContent;
    }

    public void setDividedContent(String dividedContent) {
        DividedContent = dividedContent;
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

    public String getEffect() {
        return Effect;
    }

    public void setEffect(String effect) {
        Effect = effect;
    }

    public String getPersonalIdCard() {
        return PersonalIdCard;
    }

    public void setPersonalIdCard(String personalIdCard) {
        PersonalIdCard = personalIdCard;
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

    public String getWorkUnit() {
        return WorkUnit;
    }

    public void setWorkUnit(String workUnit) {
        WorkUnit = workUnit;
    }
}

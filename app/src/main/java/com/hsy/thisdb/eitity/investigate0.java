package com.hsy.thisdb.eitity;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.eitity
 * @创始人: hsy
 * @创建时间: 2018/12/19 19:22
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/12/19 19:22
 * @修改描述:
 */
public class investigate0 {

    //rowid	ID	Date	Effect	PersonalIdCard	IsUsing	IsImport	FullName	WorkUnit	Type
    private int rowid;
    private int ID;
    private String Date;
    private String Effect;
    private String PersonalIdCard;
    private int IsUsing;
    private int IsImport;
    private String FullName;
    private String WorkUnit;
    private String Type;
    private int padSign;

    public investigate0(int rowid, int ID, String date, String effect, String personalIdCard, int isUsing, int isImport, String fullName, String workUnit, String type) {
        this.rowid = rowid;
        this.ID = ID;
        Date = date;
        Effect = effect;
        PersonalIdCard = personalIdCard;
        IsUsing = isUsing;
        IsImport = isImport;
        FullName = fullName;
        WorkUnit = workUnit;
        Type = type;
    }

    public investigate0(int rowid, int ID, String date, String effect, String personalIdCard, int isUsing, int isImport, String fullName, String workUnit, String type, int padSign) {
        this.rowid = rowid;
        this.ID = ID;
        Date = date;
        Effect = effect;
        PersonalIdCard = personalIdCard;
        IsUsing = isUsing;
        IsImport = isImport;
        FullName = fullName;
        WorkUnit = workUnit;
        Type = type;
        this.padSign = padSign;
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

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
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

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getPadSign() {
        return padSign;
    }

    public void setPadSign(int padSign) {
        this.padSign = padSign;
    }
}

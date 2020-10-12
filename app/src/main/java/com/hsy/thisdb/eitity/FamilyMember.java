package com.hsy.thisdb.eitity;

import java.io.Serializable;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.eitity
 * @创始人: hsy
 * @创建时间: 2018/11/27 16:27
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/11/27 16:27
 * @修改描述:
 */
public class FamilyMember implements Serializable {

    //rowid	Fullname	Relation	FMembeName	FmBirthday	PoliticsStatus	WorkAndDuty	PersonalIdCard
    private int rowid;
    private String Fullname;
    private String Relation;
    private String FMembeName;
    private String FmBirthday;
    private String PoliticsStatus;
    private String WorkAndDuty;
    private String PersonalIdCard;

    public FamilyMember(int rowid, String fullname, String relation, String FMembeName, String fmBirthday, String politicsStatus, String workAndDuty, String personalIdCard) {
        this.rowid = rowid;
        Fullname = fullname;
        Relation = relation;
        this.FMembeName = FMembeName;
        FmBirthday = fmBirthday;
        PoliticsStatus = politicsStatus;
        WorkAndDuty = workAndDuty;
        PersonalIdCard = personalIdCard;
    }

    public int getRowid() {
        return rowid;
    }

    public void setRowid(int rowid) {
        this.rowid = rowid;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getRelation() {
        return Relation;
    }

    public void setRelation(String relation) {
        Relation = relation;
    }

    public String getFMembeName() {
        return FMembeName;
    }

    public void setFMembeName(String FMembeName) {
        this.FMembeName = FMembeName;
    }

    public String getFmBirthday() {
        return FmBirthday;
    }

    public void setFmBirthday(String fmBirthday) {
        FmBirthday = fmBirthday;
    }

    public String getPoliticsStatus() {
        return PoliticsStatus;
    }

    public void setPoliticsStatus(String politicsStatus) {
        PoliticsStatus = politicsStatus;
    }

    public String getWorkAndDuty() {
        return WorkAndDuty;
    }

    public void setWorkAndDuty(String workAndDuty) {
        WorkAndDuty = workAndDuty;
    }

    public String getPersonalIdCard() {
        return PersonalIdCard;
    }

    public void setPersonalIdCard(String personalIdCard) {
        PersonalIdCard = personalIdCard;
    }
}

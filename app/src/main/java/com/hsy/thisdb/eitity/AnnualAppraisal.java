package com.hsy.thisdb.eitity;

import java.io.Serializable;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.eitity
 * @创始人: hsy
 * @创建时间: 2018/11/27 18:07
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/11/27 18:07
 * @修改描述:
 */
public class AnnualAppraisal implements Serializable {

    //rowid	Date	Degree	PersonalIdCard

    private int rowid;
    private String Date;
    private String Degree;
    private String PersonalIdCard;

    public AnnualAppraisal(int rowid, String date, String degree, String personalIdCard) {
        this.rowid = rowid;
        Date = date;
        Degree = degree;
        PersonalIdCard = personalIdCard;
    }

    public int getRowid() {
        return rowid;
    }

    public void setRowid(int rowid) {
        this.rowid = rowid;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDegree() {
        return Degree;
    }

    public void setDegree(String degree) {
        Degree = degree;
    }

    public String getPersonalIdCard() {
        return PersonalIdCard;
    }

    public void setPersonalIdCard(String personalIdCard) {
        PersonalIdCard = personalIdCard;
    }
}

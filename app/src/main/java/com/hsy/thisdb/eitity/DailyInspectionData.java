package com.hsy.thisdb.eitity;

import java.io.Serializable;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.eitity
 * @创始人: hsy
 * @创建时间: 2019/6/19 18:24
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2019/6/19 18:24
 * @修改描述:
 */
public class DailyInspectionData implements Serializable {

    private String fullname;
    private String personalIdCard;
    private String date;
    private String dailyInspection;
    private String recordRecordName;
    private String recordEvaluate;
    private String workUnit;
    private String padSign = "1";

    public DailyInspectionData(String fullname, String personalIdCard, String date, String dailyInspection, String recordEvaluate, String workUnit) {
        this.fullname = fullname;
        this.personalIdCard = personalIdCard;
        this.date = date;
        this.dailyInspection = dailyInspection;
        this.recordEvaluate = recordEvaluate;
        this.workUnit = workUnit;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPersonalIdCard() {
        return personalIdCard;
    }

    public void setPersonalIdCard(String personalIdCard) {
        this.personalIdCard = personalIdCard;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDailyInspection() {
        return dailyInspection;
    }

    public void setDailyInspection(String dailyInspection) {
        this.dailyInspection = dailyInspection;
    }

    public String getRecordRecordName() {
        return recordRecordName;
    }

    public void setRecordRecordName(String recordRecordName) {
        this.recordRecordName = recordRecordName;
    }

    public String getRecordEvaluate() {
        return recordEvaluate;
    }

    public void setRecordEvaluate(String recordEvaluate) {
        this.recordEvaluate = recordEvaluate;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public String getPadSign() {
        return padSign;
    }

    public void setPadSign(String padSign) {
        this.padSign = padSign;
    }

    @Override
    public String toString() {
        return "DailyInspectionData{" +
                "fullname='" + fullname + '\'' +
                ", personalIdCard='" + personalIdCard + '\'' +
                ", date='" + date + '\'' +
                ", dailyInspection='" + dailyInspection + '\'' +
                ", recordEvaluate='" + recordEvaluate + '\'' +
                '}';
    }
}

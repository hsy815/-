package com.hsy.thisdb.eitity;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.eitity
 * @创始人: hsy
 * @创建时间: 2018/12/19 21:05
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/12/19 21:05
 * @修改描述:
 */
public class Peacetime {
    //Fullname	WorkUnit	Birthday Gender	Date	Effect	Type

    private String PersonalIdCard;
    private String Fullname;
    private String WorkUnit;
    private String Birthday;
    private String Gender;
    private String Date;
    private String Effect;
    private String Evaluation;
    private String Type;

    public Peacetime(String personalIdCard, String fullname, String workUnit, String birthday, String gender, String date, String evaluation, String effect, String type) {
        PersonalIdCard = personalIdCard;
        Fullname = fullname;
        WorkUnit = workUnit;
        Birthday = birthday;
        Gender = gender;
        Date = date;
        Effect = effect;
        Evaluation = evaluation;
        Type = type;
    }

    public String getPersonalIdCard() {
        return PersonalIdCard;
    }

    public void setPersonalIdCard(String personalIdCard) {
        PersonalIdCard = personalIdCard;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getWorkUnit() {
        return WorkUnit;
    }

    public void setWorkUnit(String workUnit) {
        WorkUnit = workUnit;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
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

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getEvaluation() {
        return Evaluation;
    }

    public void setEvaluation(String evaluation) {
        Evaluation = evaluation;
    }
}

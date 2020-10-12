package com.hsy.thisdb.eitity;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.eitity
 * @创始人: hsy
 * @创建时间: 2019/2/14 9:31
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2019/2/14 9:31
 * @修改描述:
 */
public class Inspection {
    private String FullName;
    private String PersonalIdCard;
    private String WorkUnit;
    private String Date;
    private String House;
    private String Stock;
    private String Company;
    private String Content;
    private int SortNo;
    private String DeptID;
    private String ParentDeptID;
    private String Petition;

    public Inspection(String fullName, String personalIdCard, String workUnit, String date, String house,
                      String stock, String company, String content, int sortNo, String deptID, String parentDeptID, String petition) {
        FullName = fullName;
        PersonalIdCard = personalIdCard;
        WorkUnit = workUnit;
        Date = date;
        House = house;
        Stock = stock;
        Company = company;
        Content = content;
        SortNo = sortNo;
        DeptID = deptID;
        ParentDeptID = parentDeptID;
        Petition = petition;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getPersonalIdCard() {
        return PersonalIdCard;
    }

    public void setPersonalIdCard(String personalIdCard) {
        PersonalIdCard = personalIdCard;
    }

    public String getWorkUnit() {
        return WorkUnit;
    }

    public void setWorkUnit(String workUnit) {
        WorkUnit = workUnit;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getHouse() {
        return House;
    }

    public void setHouse(String house) {
        House = house;
    }

    public String getStock() {
        return Stock;
    }

    public void setStock(String stock) {
        Stock = stock;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getSortNo() {
        return SortNo;
    }

    public void setSortNo(int sortNo) {
        SortNo = sortNo;
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

    public String getPetition() {
        return Petition;
    }

    public void setPetition(String petition) {
        Petition = petition;
    }
}

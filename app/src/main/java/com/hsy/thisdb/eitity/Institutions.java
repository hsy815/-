package com.hsy.thisdb.eitity;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.eitity
 * @创始人: hsy
 * @创建时间: 2018/11/28 13:55
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/11/28 13:55
 * @修改描述:
 */
public class Institutions {
    //rowid	WorkUnit	DeptID	hdLeadership	hdLeaderDeputy	hdMiddleLvel	IsUsing	sjLeadership	sjLeaderDeputy	sjMiddleLvel	ls	IsSign	ld	IdSign	ml	mlSign
    private int rowid;
    private String WorkUnit;
    private String DeptID;
    private String hdLeadership;
    private String hdLeaderDeputy;
    private String hdMiddleLvel;
    private String IsUsing;
    private String sjLeadership;
    private String sjLeaderDeputy;
    private String sjMiddleLvel;
    private int ls;
    private int IsSign;
    private int ld;
    private int IdSign;
    private int ml;
    private int mlSign;

    public Institutions(int rowid, String workUnit, String deptID, String hdLeadership, String hdLeaderDeputy, String hdMiddleLvel, String isUsing, String sjLeadership, String sjLeaderDeputy, String sjMiddleLvel, int ls, int isSign, int ld, int idSign, int ml, int mlSign) {
        this.rowid = rowid;
        WorkUnit = workUnit;
        DeptID = deptID;
        this.hdLeadership = hdLeadership;
        this.hdLeaderDeputy = hdLeaderDeputy;
        this.hdMiddleLvel = hdMiddleLvel;
        IsUsing = isUsing;
        this.sjLeadership = sjLeadership;
        this.sjLeaderDeputy = sjLeaderDeputy;
        this.sjMiddleLvel = sjMiddleLvel;
        this.ls = ls;
        IsSign = isSign;
        this.ld = ld;
        IdSign = idSign;
        this.ml = ml;
        this.mlSign = mlSign;
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

    public String getDeptID() {
        return DeptID;
    }

    public void setDeptID(String deptID) {
        DeptID = deptID;
    }

    public String getHdLeadership() {
        return hdLeadership;
    }

    public void setHdLeadership(String hdLeadership) {
        this.hdLeadership = hdLeadership;
    }

    public String getHdLeaderDeputy() {
        return hdLeaderDeputy;
    }

    public void setHdLeaderDeputy(String hdLeaderDeputy) {
        this.hdLeaderDeputy = hdLeaderDeputy;
    }

    public String getHdMiddleLvel() {
        return hdMiddleLvel;
    }

    public void setHdMiddleLvel(String hdMiddleLvel) {
        this.hdMiddleLvel = hdMiddleLvel;
    }

    public String getIsUsing() {
        return IsUsing;
    }

    public void setIsUsing(String isUsing) {
        IsUsing = isUsing;
    }

    public String getSjLeadership() {
        return sjLeadership;
    }

    public void setSjLeadership(String sjLeadership) {
        this.sjLeadership = sjLeadership;
    }

    public String getSjLeaderDeputy() {
        return sjLeaderDeputy;
    }

    public void setSjLeaderDeputy(String sjLeaderDeputy) {
        this.sjLeaderDeputy = sjLeaderDeputy;
    }

    public String getSjMiddleLvel() {
        return sjMiddleLvel;
    }

    public void setSjMiddleLvel(String sjMiddleLvel) {
        this.sjMiddleLvel = sjMiddleLvel;
    }

    public int getLs() {
        return ls;
    }

    public void setLs(int ls) {
        this.ls = ls;
    }

    public int getIsSign() {
        return IsSign;
    }

    public void setIsSign(int isSign) {
        IsSign = isSign;
    }

    public int getLd() {
        return ld;
    }

    public void setLd(int ld) {
        this.ld = ld;
    }

    public int getIdSign() {
        return IdSign;
    }

    public void setIdSign(int idSign) {
        IdSign = idSign;
    }

    public int getMl() {
        return ml;
    }

    public void setMl(int ml) {
        this.ml = ml;
    }

    public int getMlSign() {
        return mlSign;
    }

    public void setMlSign(int mlSign) {
        this.mlSign = mlSign;
    }
}

package com.hsy.thisdb.eitity;

import java.io.Serializable;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.eitity
 * @创始人: hsy
 * @创建时间: 2018/11/27 18:05
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/11/27 18:05
 * @修改描述:
 */
public class AgeStructure implements Serializable {

    //rowid	WorkUnit	DeptId	Under35	Between36_45	Between46_55	Up56	IsUsing

    private int rowid;
    private String WorkUnit;
    private int DeptId;
    private int Under35;
    private int Between36_45;
    private int Between46_55;
    private int Up56;
    private int IsUsing;

    public AgeStructure(int rowid, String workUnit, int deptId, int under35, int between36_45, int between46_55, int up56, int isUsing) {
        this.rowid = rowid;
        WorkUnit = workUnit;
        DeptId = deptId;
        Under35 = under35;
        Between36_45 = between36_45;
        Between46_55 = between46_55;
        Up56 = up56;
        IsUsing = isUsing;
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

    public int getDeptId() {
        return DeptId;
    }

    public void setDeptId(int deptId) {
        DeptId = deptId;
    }

    public int getUnder35() {
        return Under35;
    }

    public void setUnder35(int under35) {
        Under35 = under35;
    }

    public int getBetween36_45() {
        return Between36_45;
    }

    public void setBetween36_45(int between36_45) {
        Between36_45 = between36_45;
    }

    public int getBetween46_55() {
        return Between46_55;
    }

    public void setBetween46_55(int between46_55) {
        Between46_55 = between46_55;
    }

    public int getUp56() {
        return Up56;
    }

    public void setUp56(int up56) {
        Up56 = up56;
    }

    public int getIsUsing() {
        return IsUsing;
    }

    public void setIsUsing(int isUsing) {
        IsUsing = isUsing;
    }
}

package com.hsy.thisdb.eitity;

import java.io.Serializable;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.eitity
 * @创始人: hsy
 * @创建时间: 2018/11/28 13:47
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/11/28 13:47
 * @修改描述:
 */
public class GenderStructure implements Serializable {
    //rowid	WorkUnit	DeptId	man	women	IsUsing

    private int rowid;
    private String WorkUnit;
    private String DeptId;
    private int man;
    private int women;
    private int IsUsing;

    public GenderStructure(int rowid, String workUnit, String deptId, int man, int women, int isUsing) {
        this.rowid = rowid;
        WorkUnit = workUnit;
        DeptId = deptId;
        this.man = man;
        this.women = women;
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

    public String getDeptId() {
        return DeptId;
    }

    public void setDeptId(String deptId) {
        DeptId = deptId;
    }

    public int getMan() {
        return man;
    }

    public void setMan(int man) {
        this.man = man;
    }

    public int getWomen() {
        return women;
    }

    public void setWomen(int women) {
        this.women = women;
    }

    public int getIsUsing() {
        return IsUsing;
    }

    public void setIsUsing(int isUsing) {
        IsUsing = isUsing;
    }
}

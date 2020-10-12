package com.hsy.thisdb.eitity;

import java.io.Serializable;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.eitity
 * @创始人: hsy
 * @创建时间: 2018/11/30 9:18
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/11/30 9:18
 * @修改描述:
 */
public class SysDepartment implements Serializable {
    //rowid	name	id	pId	IsUsing	GroupNo

    private int rowid;
    private String name;
    private int id;
    private int pId;
    private int IsUsing;
    private int GroupNo;

    public SysDepartment(int rowid, String name, int id, int pId, int isUsing, int groupNo) {
        this.rowid = rowid;
        this.name = name;
        this.id = id;
        this.pId = pId;
        IsUsing = isUsing;
        GroupNo = groupNo;
    }

    public int getRowid() {
        return rowid;
    }

    public void setRowid(int rowid) {
        this.rowid = rowid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getIsUsing() {
        return IsUsing;
    }

    public void setIsUsing(int isUsing) {
        IsUsing = isUsing;
    }

    public int getGroupNo() {
        return GroupNo;
    }

    public void setGroupNo(int groupNo) {
        GroupNo = groupNo;
    }
}

package com.hsy.thisdb.eitity;

import java.io.Serializable;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.eitity
 * @创始人: hsy
 * @创建时间: 2018/11/29 9:51
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/11/29 9:51
 * @修改描述:
 */
public class RewardPunish implements Serializable {

    //rowid	Memo	PersonalIdCard

    private int rowid;
    private String Memo;
    private String PersonalIdCard;

    public RewardPunish(int rowid, String memo, String personalIdCard) {
        this.rowid = rowid;
        Memo = memo;
        PersonalIdCard = personalIdCard;
    }

    public int getRowid() {
        return rowid;
    }

    public void setRowid(int rowid) {
        this.rowid = rowid;
    }

    public String getMemo() {
        return Memo;
    }

    public void setMemo(String memo) {
        Memo = memo;
    }

    public String getPersonalIdCard() {
        return PersonalIdCard;
    }

    public void setPersonalIdCard(String personalIdCard) {
        PersonalIdCard = personalIdCard;
    }
}

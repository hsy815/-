package com.hsy.thisdb.eitity;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.eitity
 * @创始人: hsy
 * @创建时间: 2018/12/24 16:25
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/12/24 16:25
 * @修改描述:
 */
public class DataBack {

    private int view;//显示的view
    private String SysDepartmentID;//单位id
    private String strWorkUnit;//单位名称
    private String mPersonalIdCard;//身份证号

    public DataBack(int view, String sysDepartmentID, String strWorkUnit, String mPersonalIdCard) {
        this.view = view;
        SysDepartmentID = sysDepartmentID;
        this.strWorkUnit = strWorkUnit;
        this.mPersonalIdCard = mPersonalIdCard;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public String getSysDepartmentID() {
        return SysDepartmentID;
    }

    public void setSysDepartmentID(String sysDepartmentID) {
        SysDepartmentID = sysDepartmentID;
    }

    public String getStrWorkUnit() {
        return strWorkUnit;
    }

    public void setStrWorkUnit(String strWorkUnit) {
        this.strWorkUnit = strWorkUnit;
    }

    public String getmPersonalIdCard() {
        return mPersonalIdCard;
    }

    public void setmPersonalIdCard(String mPersonalIdCard) {
        this.mPersonalIdCard = mPersonalIdCard;
    }
}

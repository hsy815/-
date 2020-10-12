package com.hsy.thisdb.eitity;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.eitity
 * @创始人: hsy
 * @创建时间: 2018/12/26 18:03
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/12/26 18:03
 * @修改描述:
 */
public class AgeAndGender {
    //    a.WorkUnit, a.Under35, a.Between36_45, a.Between46_55, a.Up56, g.man, g.women
    private String WorkUnit;
    private int Under35;
    private int Between36_45;
    private int Between46_55;
    private int Up56;
    private int AgeAvgValue;
    private int man;
    private int women;

    public AgeAndGender(String workUnit, int under35, int between36_45, int between46_55, int up56, int ageAvgValue, int man, int women) {
        WorkUnit = workUnit;
        Under35 = under35;
        Between36_45 = between36_45;
        Between46_55 = between46_55;
        Up56 = up56;
        AgeAvgValue = ageAvgValue;
        this.man = man;
        this.women = women;
    }

    public String getWorkUnit() {
        return WorkUnit;
    }

    public void setWorkUnit(String workUnit) {
        WorkUnit = workUnit;
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

    public int getAgeAvgValue() {
        return AgeAvgValue;
    }

    public void setAgeAvgValue(int ageAvgValue) {
        AgeAvgValue = ageAvgValue;
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
}

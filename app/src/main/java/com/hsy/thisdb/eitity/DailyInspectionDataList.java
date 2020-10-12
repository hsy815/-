package com.hsy.thisdb.eitity;

import java.util.List;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.eitity
 * @创始人: hsy
 * @创建时间: 2019/6/20 16:37
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2019/6/20 16:37
 * @修改描述:
 */
public class DailyInspectionDataList {
   private List<DailyInspectionData> dataList;

    public DailyInspectionDataList(List<DailyInspectionData> dataList) {
        this.dataList = dataList;
    }

    public List<DailyInspectionData> getDataList() {
        return dataList;
    }

    public void setDataList(List<DailyInspectionData> dataList) {
        this.dataList = dataList;
    }
}

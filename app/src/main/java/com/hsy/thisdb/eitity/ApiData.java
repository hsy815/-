package com.hsy.thisdb.eitity;

import java.util.List;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.eitity
 * @创始人: hsy
 * @创建时间: 2019/6/21 10:34
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2019/6/21 10:34
 * @修改描述:
 */
public class ApiData {

    private List<ApiClass> dataList;

    public ApiData(List<ApiClass> dataList) {
        this.dataList = dataList;
    }

    public List<ApiClass> getDataList() {
        return dataList;
    }

    public void setDataList(List<ApiClass> dataList) {
        this.dataList = dataList;
    }
}

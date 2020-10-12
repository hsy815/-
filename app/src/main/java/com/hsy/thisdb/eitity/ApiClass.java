package com.hsy.thisdb.eitity;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.eitity
 * @创始人: hsy
 * @创建时间: 2019/6/21 10:26
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2019/6/21 10:26
 * @修改描述:
 */
public class ApiClass {

    private String key;
    private String value;

    public ApiClass(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

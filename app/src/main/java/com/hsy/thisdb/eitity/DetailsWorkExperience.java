package com.hsy.thisdb.eitity;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.eitity
 * @创始人: hsy
 * @创建时间: 2018/12/12 19:08
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/12/12 19:08
 * @修改描述:
 */
public class DetailsWorkExperience {

    private String details_data;
    private String details_content;

    public DetailsWorkExperience(String details_data, String details_content) {
        this.details_data = details_data;
        this.details_content = details_content;
    }

    public String getDetails_data() {
        return details_data;
    }

    public void setDetails_data(String details_data) {
        this.details_data = details_data;
    }

    public String getDetails_content() {
        return details_content;
    }

    public void setDetails_content(String details_content) {
        this.details_content = details_content;
    }
}

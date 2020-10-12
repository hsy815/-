package com.hsy.thisdb.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.util
 * @创始人: hsy
 * @创建时间: 2018/11/22 13:26
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/11/22 13:26
 * @修改描述:
 */
public class DateUtil {

    public static String getDate2Word(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        String curDate = formatter.format(date);
        return curDate;
    }

    public static String getDate2_(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String curDate = formatter.format(date);
        return curDate;
    }
}

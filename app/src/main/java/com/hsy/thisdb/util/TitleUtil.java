package com.hsy.thisdb.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.util
 * @创始人: hsy
 * @创建时间: 2018/12/20 10:42
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/12/20 10:42
 * @修改描述:
 */
public class TitleUtil {

    public static List<String> getHomeTitle() {
        List<String> list = new ArrayList<>();
        list.add("所有单位");
        list.add("基本情况");
        list.add("督查信息");
//        list.add("分管情况");
//        list.add("考察情况");
        list.add("平时考察");
//        list.add("专项督查");
//        list.add("培训信息");
        list.add("综合查询");
        list.add("编制情况");
        list.add("年龄性别结构");
        list.add("日常考察");//逻辑以写完  打开注释就可以用
        return list;
    }

    public static List<String> getInvestiData() {
        List<String> stringList = new ArrayList<>();
        stringList.add("平时考察");
//        stringList.add("专项考察");
        return stringList;
    }

    public static List<String> getUpTitle() {
        List<String> list = new ArrayList<>();
        list.add("更新");
        list.add("上传");
        return list;
    }

    public static List<String> getUpTitle(String title) {
        List<String> list = new ArrayList<>();
        list.add("更新");
        list.add("上传");
        list.add(title);
        return list;
    }

    public static Map<Object, Boolean> setSign(Map<Object, Boolean> map, String key) {
        for (Map.Entry<Object, Boolean> m : map.entrySet()) {
            m.setValue(false);
        }
        map.put(key, true);
        return map;
    }

    public static Map<Object, Boolean> setSignRemove(Map<Object, Boolean> map) {
        for (Map.Entry<Object, Boolean> m : map.entrySet()) {
            m.setValue(false);
        }
        return map;
    }
}

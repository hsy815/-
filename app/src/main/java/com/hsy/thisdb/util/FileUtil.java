package com.hsy.thisdb.util;

import java.io.File;

/**
 * @项目名: ThisDb
 * @类位置: com.hsy.thisdb.util
 * @创始人: hsy
 * @创建时间: 2018/12/13 13:53
 * @类描述:
 * @修改人: hsy
 * @修改时间: 2018/12/13 13:53
 * @修改描述:
 */
public class FileUtil {

    public static void deleteDir(File file) {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                file.delete();
            }

            for (int index = 0; index < childFiles.length; index++) {
                deleteDir(childFiles[index]);
            }
        }
        file.delete();
    }
}

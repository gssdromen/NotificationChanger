package com.notificationchanger.cedric.notificationchanger.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by dromenwu on 14/12/28.
 */
public class CommonUtils {

    /**
     * 获得存储卡路径
     *
     * @return
     */
    public static String getSdcardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * 读取一个路径下的所有文件与文件夹
     *
     * @param path 路径
     * @return File[] or null
     */
    public static File[] getFilesInPath(String path) {
        File pathFile = new File(path);
        if (pathFile != null) {
            File[] files = pathFile.listFiles();
            return files;
        } else {
            return null;
        }
    }
}

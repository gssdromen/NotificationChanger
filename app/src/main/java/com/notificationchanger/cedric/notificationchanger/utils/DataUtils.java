package com.notificationchanger.cedric.notificationchanger.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.notificationchanger.cedric.notificationchanger.App;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Cedric on 15/1/2.
 */
public class DataUtils {
    private static final String INFO = "stored_info";

    private static final String STORED_MUSIC = "stored_music";
    private static final String NOTIFICATION_INDEX = "notification_index";

    // 两个public方法,get与save
    public static ArrayList<File> getStoredMusic() {
        ArrayList<File> files = getFilesFromPaths(PreferenceUtil.getString(App.getInstance(), INFO, STORED_MUSIC));
        if (files != null){
            return files;
        }else {
            return new ArrayList<>();
        }
    }

    public static void saveStoredMusic(ArrayList<File> musicFiles, boolean isNeedOld) {
        if (isNeedOld) {
            ArrayList<File> oldFiles = getStoredMusic();
            for (File file : oldFiles) {
                musicFiles.add(file);
            }
        }
        String result = getStringOfFiles(musicFiles);
        PreferenceUtil.putString(App.getInstance(), INFO, STORED_MUSIC, result);
    }

    public static int getCurNotificationIndex(){
        return PreferenceUtil.getInt(App.getInstance(), INFO, NOTIFICATION_INDEX);
    }

    public static void saveCurNotificationIndex(int index){
        PreferenceUtil.putInt(App.getInstance(), INFO, NOTIFICATION_INDEX, index);
    }

    /**
     * 返回JSON解析出的文件
     *
     * @param data
     * @return
     */
    private static ArrayList<File> getFilesFromPaths(String data) {
        Gson gson = new Gson();
        ArrayList<File> files = gson.fromJson(data, new TypeToken<ArrayList<File>>() {
        }.getType());
        return files;
    }

    /**
     * 把文件的路径合并,JSON格式
     *
     * @param files
     * @return JSON格式数组, 文件的绝对路径
     */
    private static String getStringOfFiles(ArrayList<File> files) {
        if (files.isEmpty()){
            return null;
        }else {
            Gson gson = new Gson();
            return gson.toJson(files);
        }
    }
}

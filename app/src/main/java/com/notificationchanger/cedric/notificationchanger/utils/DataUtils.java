package com.notificationchanger.cedric.notificationchanger.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.notificationchanger.cedric.notificationchanger.Constants;
import com.notificationchanger.cedric.notificationchanger.utils.PreferenceUtil;
import com.notificationchanger.cedric.notificationchanger.App;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Cedric on 15/1/2.
 */
public class DataUtils {
    private static final String INFO = "stored_info";

    private static final String STORED_MUSIC = "stored_music";

    // 两个public方法,get与save
    public static ArrayList<File> getStoredMusic(){
        return getFilesFromPaths(PreferenceUtil.getString(App.getInstance(),INFO,STORED_MUSIC));
    }

    public static void saveStoredMusic(ArrayList<File> musicFiles){
        if(musicFiles.isEmpty()){
            return;
        }
        ArrayList<File> oldFiles = getStoredMusic();
        for(File file:oldFiles){
            oldFiles.add(file);
        }
        String result = getStringOfFiles(oldFiles);
        PreferenceUtil.putString(App.getInstance(),INFO,STORED_MUSIC,result);
    }

    /**
     * 返回JSON解析出的文件
     * @param data
     * @return
     */
    private static ArrayList<File> getFilesFromPaths(String data){
        Gson gson=new Gson();
        ArrayList<File> files = gson.fromJson(data, new TypeToken<ArrayList<File>>(){}.getType());
        return files;
    }

    /**
     * 把文件的路径合并,JSON格式
     * @param files
     * @return JSON格式数组,文件的绝对路径
     */
    private static String getStringOfFiles(ArrayList<File> files){
        Gson gson=new Gson();
        return gson.toJson(files);
    }
}

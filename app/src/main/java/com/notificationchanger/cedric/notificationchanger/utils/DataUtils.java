package com.notificationchanger.cedric.notificationchanger.utils;

import com.notificationchanger.cedric.notificationchanger.Constants;
import com.notificationchanger.cedric.notificationchanger.utils.PreferenceUtil;
import com.notificationchanger.cedric.notificationchanger.App;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Cedric on 15/1/2.
 */
public class DataUtils {
    private static final String STORED_MUSIC = "stored_music";

    public ArrayList<File> getStoredMusic(){

        return null;
    }

    public void  saveStoredMusic(ArrayList<File> musicFiles){
        if(musicFiles.isEmpty()){
            return;
        }
        ArrayList<File> oldFiles = getStoredMusic();
        for(File file:oldFiles){
            oldFiles.add(file);
        }

    }

    private ArrayList<File> getFilesFromPaths(String path){
        ArrayList<File> files = new ArrayList<>();
        String[] paths = path.split(Constants.SPLIT_MARK);
        for (String s : paths){
            if (!s.isEmpty()){
                File file = new File(s);
                if (file.isFile()){

                }
            }
        }
        return null;
    }

    /**
     * 把文件的路径合并,用符号隔开
     * @param files
     * @return 一个总路径
     */
    private String getStringOfFiles(ArrayList<File> files){
        if (files.isEmpty()){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (File file:files){
            sb.append(file.getAbsoluteFile()+Constants.SPLIT_MARK);
        }
        return sb.toString();
    }
}

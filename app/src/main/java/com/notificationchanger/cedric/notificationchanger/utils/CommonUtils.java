package com.notificationchanger.cedric.notificationchanger.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by dromenwu on 14/12/28.
 */
public class CommonUtils {

    public static void save(String key, Object value) {

    }

    /**
     * 获得存储卡路径
     *
     * @return
     */
    public static String getSdcardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static File getSdcardFile() {
        return new File(Environment.getExternalStorageDirectory().getAbsolutePath());
    }

    public static Uri getAudioContentUri(Context context, File file) {
        String filePath = file.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[] { MediaStore.Audio.Media._ID },
                MediaStore.Audio.Media.DATA + "=?",
                new String[] { filePath }, null);
        cursor.moveToFirst();
        int id = cursor.getInt(cursor
                .getColumnIndex(MediaStore.MediaColumns._ID));
        Uri baseUri = Uri.parse("content://media/external/audio/media");
        return Uri.withAppendedPath(baseUri, "" + id);
    }

    /**
     * 读取一个路径下的所有文件与文件夹
     *
     * @param path 路径
     * @return File[] or null
     */
    public static ArrayList<File> getFilesInPath(String path) {
        File pathFile = new File(path);
        if (pathFile != null) {
            File[] files = pathFile.listFiles();
            ArrayList<File> result = new ArrayList<>();
            for (int i = 0; i < files.length; ++i) {
                result.add(files[i]);
            }
            result.add(0, pathFile.getParentFile());
            return result;
        } else {
            return null;
        }
    }

}

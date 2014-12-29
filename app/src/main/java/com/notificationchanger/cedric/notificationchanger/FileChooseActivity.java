package com.notificationchanger.cedric.notificationchanger;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import com.notificationchanger.cedric.notificationchanger.adapter.AdapterFileChoose;
import com.notificationchanger.cedric.notificationchanger.utils.CommonUtils;

import java.io.File;
import java.util.HashMap;

/**
 * Created by dromenwu on 14/12/28.
 */
public class FileChooseActivity extends Activity {
    private ListView mList = null;
    private String rootPath = null;
    private File[] files = null;
    private AdapterFileChoose adapterFileChoose = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_choose);
        mList = (ListView) findViewById(R.id.fileList);
        rootPath = CommonUtils.getSdcardPath();
        files = CommonUtils.getFilesInPath(rootPath);
        adapterFileChoose=new AdapterFileChoose(FileChooseActivity.this,files);
        mList.setAdapter(adapterFileChoose);
    }

    private void updateCurrentListView(File[] files) {
        if (files == null) {
            return;
        }
        HashMap<String, String> fileNames = new HashMap<>();
        for (File file : files) {
            fileNames.put(file.getName(), file.getAbsolutePath());
        }
    }
}

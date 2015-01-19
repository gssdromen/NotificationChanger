package com.notificationchanger.cedric.notificationchanger;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.notificationchanger.cedric.notificationchanger.adapter.AdapterFileChoose;
import com.notificationchanger.cedric.notificationchanger.base.BaseActivity;
import com.notificationchanger.cedric.notificationchanger.utils.CommonUtils;
import com.notificationchanger.cedric.notificationchanger.utils.DataUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dromenwu on 14/12/28.
 */
public class FileChooseActivity extends BaseActivity implements AdapterView.OnItemClickListener, AdapterFileChoose.OnFileItemSelectedListener {
    private List<String> mMusicSuffix = Arrays.asList("mp3", "wav", "m4a", "m4r");

    private ListView mList = null;
    private String rootPath = null;
    private ArrayList<File> files = new ArrayList<>();
    private ArrayList<File> mSelectedFiles = null;
    private ArrayList<File> mResultFiles = new ArrayList<>();
    private AdapterFileChoose adapterFileChoose = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_choose);

        mList = (ListView) findViewById(R.id.fileList);
        //sdcard的路径为根路径
        rootPath = CommonUtils.getSdcardPath();
        files = CommonUtils.getFilesInPath(rootPath);

        adapterFileChoose = new AdapterFileChoose(FileChooseActivity.this, files);
        adapterFileChoose.setOnFileItemSelectedListener(this);
        mList.setAdapter(adapterFileChoose);
        mList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        File file = files.get(position);
        if (file.isDirectory()) {
            //判断是否已到sd卡根目录
            if (file.getAbsolutePath().equalsIgnoreCase(CommonUtils.getSdcardFile().getParent())) {
                App.showShortToast(getResources().getString(R.string.warning_root));
            } else {
                String path = file.getAbsolutePath();
                files = CommonUtils.getFilesInPath(path);
                adapterFileChoose.updateAdapter(files);
            }
        } else if (file.isFile()) {
            adapterFileChoose.selectThisLine(view);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_file_choose, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_confirm) {
            //清空原来的
            mResultFiles.clear();
            if (!mSelectedFiles.isEmpty()){
                for (File file : mSelectedFiles) {
                    getUsefulMusicFiles(file);
                }
                Intent intent = new Intent();
                DataUtils.saveStoredMusic(mResultFiles, true);
                setResult(RESULT_OK, intent);
                //计数归零
                DataUtils.saveCurNotificationIndex(0);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void getUsefulMusicFiles(File file) {
        if (file == null || file.isDirectory()) {
            return;
        } else if (file.isFile()) {
            //判断后缀名是否为音频文件
            String name = file.getName();
            int index = name.lastIndexOf(".") + 1;
            String suffix = name.substring(index);
            if (mMusicSuffix.contains(suffix)) {
                mResultFiles.add(file);
            }
        }
    }

    @Override
    public void onFileItemSelected(ArrayList<File> selectedFiles) {
        mSelectedFiles = selectedFiles;
    }
}

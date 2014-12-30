package com.notificationchanger.cedric.notificationchanger;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.notificationchanger.cedric.notificationchanger.adapter.AdapterFileChoose;
import com.notificationchanger.cedric.notificationchanger.base.BaseActivity;
import com.notificationchanger.cedric.notificationchanger.utils.CommonUtils;

import java.io.File;

/**
 * Created by dromenwu on 14/12/28.
 */
public class FileChooseActivity extends BaseActivity implements AdapterView.OnItemClickListener{
    private ListView mList = null;
    private String rootPath = null;
    private File[] files = null;
    private AdapterFileChoose adapterFileChoose = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_choose);

        mList = (ListView) findViewById(R.id.fileList);
        //sdcard的路径为根路径
        rootPath = CommonUtils.getSdcardPath();
        files = CommonUtils.getFilesInPath(rootPath);

        adapterFileChoose=new AdapterFileChoose(FileChooseActivity.this,files);
        mList.setAdapter(adapterFileChoose);
        mList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        File file = files[position];
        Toast.makeText(this,file.getAbsolutePath(),Toast.LENGTH_LONG).show();
        if (file.isDirectory()){
            adapterFileChoose.updateAdapter(CommonUtils.getFilesInPath(file.getAbsolutePath()));
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

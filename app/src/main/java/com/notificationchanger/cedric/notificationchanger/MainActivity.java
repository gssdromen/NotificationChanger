package com.notificationchanger.cedric.notificationchanger;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.notificationchanger.cedric.notificationchanger.base.BaseActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static final String SELECTED_FILES_PATHS = "selected_files_paths";

    private Button mAdd = null;
    private ListView mList = null;
    private List<String> mFilesPaths = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mAdd = (Button) findViewById(R.id.addBtn);
        mList = (ListView) findViewById(R.id.listView);
        mAdd.setOnClickListener(this);
    }

    private ArrayList<File> getStoredFiles(){
        
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addBtn:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, FileChooseActivity.class);
//                startActivity(intent);
                startActivityForResult(intent, 0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0 && resultCode==RESULT_OK){
            Bundle bundle = data.getExtras();
            mFilesPaths=Arrays.asList(bundle.getString(SELECTED_FILES_PATHS).split("|"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
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

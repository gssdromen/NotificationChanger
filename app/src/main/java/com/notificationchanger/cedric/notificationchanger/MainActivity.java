package com.notificationchanger.cedric.notificationchanger;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.notificationchanger.cedric.notificationchanger.adapter.AdapterMain;
import com.notificationchanger.cedric.notificationchanger.base.BaseActivity;
import com.notificationchanger.cedric.notificationchanger.utils.DataUtils;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends BaseActivity implements AdapterView.OnItemLongClickListener {

    private ListView mList = null;
    private AdapterMain mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mList = (ListView) findViewById(R.id.listView);

        mAdapter = new AdapterMain(MainActivity.this, DataUtils.getStoredMusic());
        mList.setAdapter(mAdapter);
        mList.setOnItemLongClickListener(this);
        mAdapter.updateView(DataUtils.getStoredMusic());
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        ArrayList<File> files = DataUtils.getStoredMusic();
        files.remove(position);
        DataUtils.saveStoredMusic(files, false);
        mAdapter.updateView(files);
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            mAdapter.updateView(DataUtils.getStoredMusic());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent = new Intent();
        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.add_new:
                intent.setClass(MainActivity.this, FileChooseActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.help:
                intent.setClass(MainActivity.this, HelpActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

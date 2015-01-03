package com.notificationchanger.cedric.notificationchanger.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.notificationchanger.cedric.notificationchanger.R;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by dromenwu on 15/1/3.
 */
public class AdapterMain extends BaseAdapter {
    private Context mContext = null;
    private ArrayList<File> files = new ArrayList<>();

    public AdapterMain(Context context, ArrayList<File> f) {
        super();
        mContext = context;
        files = f;
    }

    public void updateView(ArrayList<File> f) {
        files = f;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return files.size();
    }

    @Override
    public Object getItem(int position) {
        return files.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_main, parent, false);
        }
        ImageView imageView = ViewHolder.getView(convertView, R.id.imgFile);
        TextView textView = ViewHolder.getView(convertView, R.id.nameFile);
        if (files == null) {
            return null;
        }
        File file = files.get(position);
        if (file.isFile()) {
            imageView.setImageResource(R.drawable.ic_file);
        } else {
            imageView.setImageResource(R.drawable.ic_folder);
        }
        textView.setText(file.getName());
        return convertView;
    }
}

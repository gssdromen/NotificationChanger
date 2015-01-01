package com.notificationchanger.cedric.notificationchanger.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.notificationchanger.cedric.notificationchanger.R;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by dromenwu on 14/12/29.
 */
public class AdapterFileChoose extends BaseAdapter implements View.OnClickListener{
    private File[] mFiles = null;
    private Context mContext = null;
    private ArrayList<File> mSelectedFiles = new ArrayList<>();
    private OnFileItemSelectedListener mListener;

    public AdapterFileChoose(Context c, File[] files){
        mContext = c;
        mFiles = files;
        mSelectedFiles.clear();
    }

    public void updateAdapter(File[] files){
        mFiles = files;
        mSelectedFiles.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mFiles.length;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return mFiles[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.listview_file_choose, parent, false);
        }
        ImageView imageView = ViewHolder.getView(convertView, R.id.imgFile);
        TextView textView = ViewHolder.getView(convertView, R.id.nameFile);
        CheckBox checkBox = ViewHolder.getView(convertView, R.id.checkboxFile);
        File file=mFiles[position];
        checkBox.setTag(file);
        checkBox.setOnClickListener(this);
        if (file.isFile()){
            imageView.setImageResource(R.drawable.ic_file);
        }else{
            imageView.setImageResource(R.drawable.ic_folder);
        }
        textView.setText(file.getName());
        return convertView;
    }

    @Override
    public void onClick(View v) {
        File file = (File)v.getTag();
        if (mSelectedFiles.contains(file)){
            mSelectedFiles.remove(file);
        }else{
            mSelectedFiles.add(file);
        }
        mListener.onFileItemSelected(mSelectedFiles);
    }

    public interface OnFileItemSelectedListener{
        public void onFileItemSelected(ArrayList<File> selectedFiles);
    }

    public void setOnFileItemSelectedListener(OnFileItemSelectedListener l){
        mListener = l;
    }
}

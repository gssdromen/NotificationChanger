package com.notificationchanger.cedric.notificationchanger.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
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
public class AdapterFileChoose extends BaseAdapter implements View.OnClickListener {
    private ArrayList<File> mFiles = null;
    private Context mContext = null;
    private ArrayList<File> mSelectedFiles = new ArrayList<>();
    private OnFileItemSelectedListener mListener;

    public AdapterFileChoose(Context c, ArrayList<File> files) {
        mContext = c;
        mFiles = files;
        mSelectedFiles.clear();
    }

    public void updateAdapter(ArrayList<File> files) {
        mFiles = files;
        mSelectedFiles.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mFiles.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return mFiles.get(position);
    }

    public void selectThisLine(View view) {
        CheckBox checkBox = ViewHolder.getView(view, R.id.checkboxFile);
        checkBox.setChecked(!checkBox.isChecked());
        toggle(checkBox);
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
        File file = mFiles.get(position);
        checkBox.setTag(file);
        checkBox.setVisibility(View.VISIBLE);
        checkBox.setOnClickListener(this);
        if (position == 0) {
            imageView.setImageResource(R.drawable.ic_back);
            checkBox.setVisibility(View.GONE);
            textView.setText(mContext.getResources().getString(R.string.parent_dictionary));
            return convertView;
        }
        checkBox.setChecked(mSelectedFiles.contains(file));
        if (file.isFile()) {
            imageView.setImageResource(R.drawable.ic_file);
        } else {
            imageView.setImageResource(R.drawable.ic_folder);
        }
        textView.setText(file.getName());
        return convertView;
    }

    @Override
    public void onClick(View v) {
        toggle(v);
    }

    private void toggle(View v) {
        try {
            CheckBox checkBox = (CheckBox) v;
            File file = (File) v.getTag();
            if (checkBox.isChecked()) {
                if (!mSelectedFiles.contains(file)) {
                    mSelectedFiles.add(file);
                }
            } else {
                mSelectedFiles.remove(file);
            }
            mListener.onFileItemSelected(mSelectedFiles);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public interface OnFileItemSelectedListener {
        public void onFileItemSelected(ArrayList<File> selectedFiles);
    }

    public void setOnFileItemSelectedListener(OnFileItemSelectedListener l) {
        mListener = l;
    }
}

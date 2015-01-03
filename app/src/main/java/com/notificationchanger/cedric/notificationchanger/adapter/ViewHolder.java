package com.notificationchanger.cedric.notificationchanger.adapter;

import android.util.SparseArray;
import android.view.View;

/**
 * 适配器中的 ViewHolder 类
 *
 * @link 参考：http://www.piwai.info/android-adapter-good-practices/#Update
 */

/**
 * Created by dromenwu on 14/12/29.
 */
public class ViewHolder {
    public static <T extends View> T getView(View convertView, int id) {
        SparseArray<View> holder = (SparseArray<View>) convertView.getTag();
        if (holder == null) {
            holder = new SparseArray<View>();
            convertView.setTag(holder);
        }

        View view = holder.get(id);
        if (view == null) {
            view = convertView.findViewById(id);
            holder.put(id, view);
        }
        return (T) view;
    }
}

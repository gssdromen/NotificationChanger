package com.notificationchanger.cedric.notificationchanger.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;

import com.notificationchanger.cedric.notificationchanger.App;
import com.notificationchanger.cedric.notificationchanger.R;
import com.notificationchanger.cedric.notificationchanger.utils.CommonUtils;
import com.notificationchanger.cedric.notificationchanger.utils.DataUtils;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by dromenwu on 15/1/3.
 */
public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        int index = DataUtils.getCurNotificationIndex();
        // 得到我们选择的铃声
        ArrayList<File> files = DataUtils.getStoredMusic();
        if (files.isEmpty()){
            return;
        }
        int count=files.size();
        File file=files.get(index%count);
        App.showShortToast(context.getResources().getString(R.string.next_notification)+file.getName());
        Uri pickedUri= CommonUtils.getAudioContentUri(context, file);
        DataUtils.saveCurNotificationIndex((index+1)%1024);
        // 将我们选择的铃声设置成为默认
        if (pickedUri != null) {
            RingtoneManager.setActualDefaultRingtoneUri(context,
                    RingtoneManager.TYPE_NOTIFICATION, pickedUri);
        }

    }
}

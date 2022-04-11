package com.nhom45.baitap_4.Services;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.nhom45.baitap_4.R;
import com.nhom45.baitap_4.Ultils.Constants;
import com.nhom45.baitap_4.Views.MainActivity;

public class ReminderBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent intentNoti = new Intent(context, MainActivity.class);
        intentNoti.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        @SuppressLint("UnspecifiedImmutableFlag")
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                0,
                intentNoti,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, Constants.CHANNEL_ID_REMINDER)
                .setSmallIcon(R.drawable.ic_linked_camera)
                .setContentTitle("Selfie a photo")
                .setContentText("Hey guy, please selfie a photo")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(Constants.NOTIFICATION_ID, builder.build());
    }

}

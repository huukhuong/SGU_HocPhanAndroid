package com.nhom45.baitap_4.Ultils;

import android.os.Environment;

public class Constants {

    public static final int CAMERA_REQUEST_CODE = 4366;
    public static final int CAMERA_PERMISSION_CODE = 1722;
    public static final String SAVE_DIRECTORY =
            Environment.getExternalStorageDirectory().toString() +
                    "/Android/data/com.nhom45.baitap_4/files/Pictures/";
    public static final String TRASH_DIRECTORY =
            Environment.getExternalStorageDirectory().toString() +
                    "/Android/data/com.nhom45.baitap_4/files/Trash/";
    public static final String CHANNEL_ID_REMINDER = "reminder_broadcast_receiver";
    public static final int NOTIFICATION_ID = 251;
    public static final int TIME_SCHEDULE = 10; // send notification after 10 second

}

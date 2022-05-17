package com.nhom45.baitap_4.Views;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.nhom45.baitap_4.Adapters.ImageAdapter;
import com.nhom45.baitap_4.Models.Image;
import com.nhom45.baitap_4.R;
import com.nhom45.baitap_4.Services.ReminderBroadcast;
import com.nhom45.baitap_4.Ultils.Constants;
import com.nhom45.baitap_4.Ultils.Helpers;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private String currentPhotoPath;

    private ArrayList<Image> listImage;
    private ListView lsvImages;
    private ImageAdapter adapterImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();
        checkPermisson();
        addControls();
        addEvents();
        readAllFile();
    }

    private void addControls() {
        listImage = new ArrayList<>();
        lsvImages = findViewById(R.id.lsvImages);
        adapterImage = new ImageAdapter(this, R.layout.item_list, listImage);
        lsvImages.setAdapter(adapterImage);
    }

    private void addEvents() {
        lsvImages.setOnItemClickListener((adapterView, view, i, l) -> {
            showFullScreenImage(i);
        });
        lsvImages.setOnItemLongClickListener((adapterView, view, i, l) -> {
            deleteImageFile(i);
            return true;
        });
    }

    private void deleteImageFile(int position) {
        new AlertDialog.Builder(this)
                .setTitle("Warning")
                .setMessage("Do you really want to delete this photo?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, (dialog, whichButton) -> {
                    Image image = listImage.get(position);
                    File fileFrom = new File(image.getPath());
                    File trashFolder = new File(Constants.TRASH_DIRECTORY);
                    if (!trashFolder.exists()) {
                        trashFolder.mkdir();
                    }
                    File fileTo = new File(Constants.TRASH_DIRECTORY + fileFrom.getName());
                    fileFrom.renameTo(fileTo);
                    readAllFile();
                })
                .setNegativeButton("Cancel", null).show();
    }

    private void readAllFile() {
        listImage.clear();
        File saveDirectory = new File(Constants.SAVE_DIRECTORY);
        File[] files = saveDirectory.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                String filePath = files[i].getAbsolutePath();
                if (!fileName.isEmpty() && !filePath.isEmpty()) {
                    listImage.add(new Image(fileName, filePath));
                }
            }
            Collections.sort(listImage, (s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));
            adapterImage.notifyDataSetChanged();
        }
    }

    private void takePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, Constants.CAMERA_REQUEST_CODE);
            }
        }

    }

    @SuppressLint("SimpleDateFormat")
    private File createImageFile() throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String fileName = sdf.format(new Date());
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                fileName,
                ".jpg",
                storageDir
        );
        currentPhotoPath = image.getAbsolutePath();
        Log.e("Path", currentPhotoPath);
        return image;
    }

    private void showFullScreenImage(int i) {
        Image imageSelected = listImage.get(i);
        Intent intent = new Intent(this, ViewImageActivity.class);
        intent.putExtra("image", imageSelected);
        startActivity(intent);
    }

    private void checkPermisson() {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            // Kiểm tra quyền camera & đọc/ghi dữ liệu vào thiết bị lưu trữ ngoài.
            int readPermission = ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE);
            int writePermission = ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int cameraPermission = ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA);

            // Nếu không có quyền, cần nhắc người dùng cho phép.
            if (writePermission != PackageManager.PERMISSION_GRANTED ||
                    readPermission != PackageManager.PERMISSION_GRANTED ||
                    cameraPermission != PackageManager.PERMISSION_GRANTED) {
                this.requestPermissions(new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                }, Constants.CAMERA_PERMISSION_CODE);
            }
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "ReminderChannel";
            String description = "Channel notification for Daily Selfie";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(Constants.CHANNEL_ID_REMINDER, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager;
            notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.CAMERA_REQUEST_CODE) {
            showConfirmDialog();
        }
    }

    private void showConfirmDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        @SuppressLint("ResourceType")
        View dialogView = inflater.inflate(R.layout.confirm_save_dialog, findViewById(R.layout.activity_main));
        dialogBuilder.setView(dialogView);
        AlertDialog b = dialogBuilder.create();

        ImageView imgReview = dialogView.findViewById(R.id.imgReview);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        Button btnSave = dialogView.findViewById(R.id.btnSave);

        File image = new File(currentPhotoPath);
        imgReview.setImageBitmap(Helpers.getImageFromPath(image.getPath()));

        btnCancel.setOnClickListener(e -> {
            // delete file
            File file = new File(currentPhotoPath);
            file.delete();
            if (b.isShowing()) {
                b.dismiss();
            }
        });

        btnSave.setOnClickListener(e -> {
            if (b.isShowing()) {
                b.dismiss();
            }
            pushNotification();
        });

        b.show();
        b.setOnDismissListener(e -> {
            readAllFile();
        });
    }

    private void pushNotification() {
        Intent intent = new Intent(this, ReminderBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long timeAtLastTakePhoto = System.currentTimeMillis();
        long delayTime = Constants.TIME_SCHEDULE * 1000;
        alarmManager.set(AlarmManager.RTC_WAKEUP, timeAtLastTakePhoto + delayTime, pendingIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.takePhoto:
                takePhoto();
                break;
            case R.id.trash:
                startActivity(new Intent(MainActivity.this, TrashActivity.class));
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        readAllFile();
    }
}
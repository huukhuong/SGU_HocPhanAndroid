package com.nhom45.baitap_4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.nhom45.baitap_4.Adapter.ImageAdapter;
import com.nhom45.baitap_4.Models.Image;
import com.nhom45.baitap_4.Ultils.Helpers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    static final int CAMERA_REQUEST_CODE = 4366;
    static final int CAMERA_PERMISSION_CODE = 1722;
    static final String SAVE_DIRECTORY =
            Environment.getExternalStorageDirectory().toString() +
                    "/Android/data/com.nhom45.baitap_4/files/Pictures/";

    private String currentPhotoPath;

    private ArrayList<Image> listImage;
    private ListView lsvImages;
    private ImageAdapter adapterImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }

    private void readAllFile() {
        listImage.clear();
        File saveDirectory = new File(SAVE_DIRECTORY);
        File[] files = saveDirectory.listFiles();
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getName();
            String filePath = files[i].getAbsolutePath();
            listImage.add(new Image(fileName, filePath));
        }
        adapterImage.notifyDataSetChanged();
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
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
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
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        @SuppressLint("ResourceType")
        View dialogView = inflater.inflate(
                R.layout.dialog,
                findViewById(R.layout.activity_main));

        ImageView imageView = dialogView.findViewById(R.id.imvDialog);
        imageView.setImageBitmap(Helpers.getImageFromPath(imageSelected.getPath()));

        dialogBuilder.setView(dialogView);
        AlertDialog b = dialogBuilder.create();
        b.show();

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
                }, CAMERA_PERMISSION_CODE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE) {
            readAllFile();
        }
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
        }
        return true;
    }

}
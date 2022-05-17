package com.nhom45.baitap_4.Views;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.nhom45.baitap_4.Adapters.ImageAdapter;
import com.nhom45.baitap_4.Models.Image;
import com.nhom45.baitap_4.R;
import com.nhom45.baitap_4.Ultils.Constants;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class TrashActivity extends AppCompatActivity {

    private ArrayList<Image> listImage;
    private ListView lsvImages;
    private ImageAdapter adapterImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trash);

        addControls();
        addEvents();
    }

    private void addControls() {
        listImage = new ArrayList<>();
        lsvImages = findViewById(R.id.lsvImages);
        adapterImage = new ImageAdapter(this, R.layout.item_list, listImage);
        lsvImages.setAdapter(adapterImage);

        readAllTrashFiles();
    }

    private void readAllTrashFiles() {
        listImage.clear();
        File saveDirectory = new File(Constants.TRASH_DIRECTORY);
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

    private void addEvents() {
        lsvImages.setOnItemClickListener((adapterView, view, position, l) -> {
            recoverImageFile(position);
        });

        lsvImages.setOnItemLongClickListener((adapterView, view, position, l) -> {
            deleteImageFile(position);
            return true;
        });
    }

    private void deleteImageFile(int position) {
        new AlertDialog.Builder(this)
                .setTitle("Warning")
                .setMessage("Do you really want to delete this photo?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, (dialog, whichButton) -> {
                    // delete file
                    Image image = listImage.get(position);
                    File fileFrom = new File(image.getPath());
                    fileFrom.delete();
                    readAllTrashFiles();
                })
                .setNegativeButton("Cancel", null).show();
    }

    private void recoverImageFile(int position) {
        new AlertDialog.Builder(this)
                .setTitle("Warning")
                .setMessage("Do you really want to recover this photo?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, (dialog, whichButton) -> {
                    // recover file
                    Image image = listImage.get(position);
                    File fileFrom = new File(image.getPath());
                    File saveFolder = new File(Constants.SAVE_DIRECTORY);
                    if (!saveFolder.exists()) {
                        saveFolder.mkdir();
                    }
                    File fileTo = new File(Constants.SAVE_DIRECTORY + fileFrom.getName());
                    fileFrom.renameTo(fileTo);
                    readAllTrashFiles();
                })
                .setNegativeButton("Cancel", null).show();
    }

}
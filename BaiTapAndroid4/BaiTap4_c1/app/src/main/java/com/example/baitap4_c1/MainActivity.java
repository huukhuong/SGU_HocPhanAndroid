package com.example.baitap4_c1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText txtName, txtEmail, txtProject;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addControls() {
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtProject = findViewById(R.id.txtProject);
        btnSubmit = findViewById(R.id.btnSubmit);
    }

    private void addEvents() {
        btnSubmit.setOnClickListener(v -> {
            onClickSubmit();
        });
    }

    private void onClickSubmit() {
        String name = txtName.getText().toString();
        String email = txtEmail.getText().toString();
        String project = txtProject.getText().toString();

        Intent intent = new Intent(this, InfomationActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("email", email);
        intent.putExtra("project", project);

        startActivity(intent);
    }

}
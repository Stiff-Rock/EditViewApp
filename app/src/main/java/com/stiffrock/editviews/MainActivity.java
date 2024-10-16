package com.stiffrock.editviews;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button editBtnButton = findViewById(R.id.editBtnButton);
        Button editTvButton = findViewById(R.id.editTvButton);
        Button editLlButton = findViewById(R.id.editLlButton);

        editBtnButton.setOnClickListener(e -> EditBtnView());
        editTvButton.setOnClickListener(e -> EditTvView());
        editLlButton.setOnClickListener(e -> EditLlView());
    }

    private void EditBtnView() {
        Intent intent = new Intent(MainActivity.this, EditButton.class);
        startActivity(intent);
    }

    private void EditTvView() {
        Intent intent = new Intent(MainActivity.this, EditTextView.class);
        startActivity(intent);
    }

    private void EditLlView() {
        Intent intent = new Intent(MainActivity.this, EditLinearLayout.class);
        startActivity(intent);
    }
}
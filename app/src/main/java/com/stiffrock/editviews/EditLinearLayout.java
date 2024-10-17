package com.stiffrock.editviews;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditLinearLayout extends AppCompatActivity {
    private LinearLayout testLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_linear_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        testLinearLayout = findViewById(R.id.testLl);

        String[] arraySpinner = new String[]{"Predeterminado", "Rojo", "Azul", "Verde", "Amarillo"};
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedColor = parent.getItemAtPosition(position).toString();
                changeButtonColor(selectedColor);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(e -> BackToMainActivity());

        EditText editXText = findViewById(R.id.editXText);
        editXText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = s.toString();
                ViewGroup.LayoutParams lLParams = testLinearLayout.getLayoutParams();

                if (input.isEmpty()) {
                    lLParams.width = 310;
                } else {
                    int x = Integer.parseInt(input);
                    lLParams.width = (x <= 0) ? 310 : x;
                }

                testLinearLayout.setLayoutParams(lLParams);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        EditText editYText = findViewById(R.id.editYText);
        editYText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = s.toString();
                ViewGroup.LayoutParams lLParams = testLinearLayout.getLayoutParams();

                if (input.isEmpty()) {
                    lLParams.height = 100;
                } else {
                    int y = Integer.parseInt(input);
                    lLParams.height = (y <= 0) ? 100 : y;
                }

                testLinearLayout.setLayoutParams(lLParams);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void changeButtonColor(String selectedColor) {
        int colorResId;
        switch (selectedColor) {
            case "Rojo":
                colorResId = R.color.red;
                break;
            case "Azul":
                colorResId = R.color.blue;
                break;
            case "Verde":
                colorResId = R.color.green;
                break;
            case "Amarillo":
                colorResId = R.color.yellow;
                break;
            default:
                colorResId = R.color.defaultColor;
                break;
        }
        testLinearLayout.setBackgroundColor(ContextCompat.getColor(EditLinearLayout.this, colorResId));
    }

    public void BackToMainActivity() {
        Intent intent = new Intent(EditLinearLayout.this, MainActivity.class);
        startActivity(intent);
    }
}
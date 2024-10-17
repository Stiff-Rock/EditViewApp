package com.stiffrock.editviews;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditButton extends AppCompatActivity {
    private Button testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_button);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        testButton = findViewById(R.id.testButton);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(e -> BackToMainActivity());

        String[] arraySpinner = new String[]{
                "Predeterminado", "Rojo", "Azul", "Verde", "Amarillo"
        };
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
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

        NumberPicker numberPicker = findViewById(R.id.numberPicker);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(100);
        float textSizeInPixels = testButton.getTextSize();
        float textSizeInSp = textSizeInPixels / getResources().getDisplayMetrics().scaledDensity;
        numberPicker.setValue(Math.round(textSizeInSp));
        numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> testButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, newVal));

        EditText editTextInput = findViewById(R.id.editTextInput);
        editTextInput.setText(testButton.getText());
        editTextInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                testButton.setText(s);
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
        testButton.setBackgroundColor(ContextCompat.getColor(EditButton.this, colorResId));
    }

    public void BackToMainActivity() {
        Intent intent = new Intent(EditButton.this, MainActivity.class);
        startActivity(intent);
    }
}
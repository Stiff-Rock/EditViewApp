package com.stiffrock.editviews;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditTextView extends AppCompatActivity {
    private TextView testTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_text_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        testTextView = findViewById(R.id.testTextView);

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
                ViewGroup.LayoutParams lLParams = testTextView.getLayoutParams();

                if (input.isEmpty()) {
                    lLParams.width = 170;
                } else {
                    int x = Integer.parseInt(input);
                    lLParams.width = (x <= 0) ? 170 : x;
                }

                testTextView.setLayoutParams(lLParams);
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
                ViewGroup.LayoutParams lLParams = testTextView.getLayoutParams();

                if (input.isEmpty()) {
                    lLParams.height = 48;
                } else {
                    int y = Integer.parseInt(input);
                    lLParams.height = (y <= 0) ? 48 : y;
                }

                testTextView.setLayoutParams(lLParams);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        NumberPicker numberPicker = findViewById(R.id.sizeNumberPicker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(100);
        float textSizeInPixels = testTextView.getTextSize();
        float textSizeInSp = textSizeInPixels / getResources().getDisplayMetrics().scaledDensity;
        numberPicker.setValue(Math.round(textSizeInSp));
        numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> testTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, newVal));

        EditText editTextInput = findViewById(R.id.editTextInput);
        editTextInput.setText(testTextView.getText());
        editTextInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                testTextView.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void BackToMainActivity() {
        Intent intent = new Intent(EditTextView.this, MainActivity.class);
        startActivity(intent);
    }
}
package com.example.lab_intents;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ExplicitActivity extends AppCompatActivity {

    Button btnVoRed;
    Button btnOtkazi;
    EditText inputText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit);
        initViews();
        initListeners();
    }

    public void initViews(){
        btnVoRed = findViewById(R.id.btnVoRed);
        btnOtkazi = findViewById(R.id.btnOtkazi);
        inputText = findViewById(R.id.editText);
    }

    public void initListeners(){
        btnOtkazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnVoRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String input = inputText.getText().toString();
                intent.putExtra("inputText", inputText.getText().toString());
                setResult(RESULT_OK, intent);
                Log.e("poraka", inputText.getText().toString());
                finish();
            }
        });
    }

}

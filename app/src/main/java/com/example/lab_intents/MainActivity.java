package com.example.lab_intents;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.EmptyStackException;
import java.util.List;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    TextView textView;

    Logger logger =Logger.getLogger("MainActivity");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();
    }

    public void initViews() {
        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        textView = findViewById(R.id.textView);
    }

    public void initListeners() {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchExplicitActivity();
            }
        });
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                callImplicitActivity();
            }
        });
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                shareActivity();
            }
        });
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                photoActivity();
            }
        });
    }

    public void shareActivity(){
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Mpip Send Title");
        intent.putExtra(Intent.EXTRA_TEXT, "Content send from Main Activity");
        startActivity(Intent.createChooser(intent, "Send Message"));
    }


    public void photoActivity(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(intent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});
        startActivityForResult(chooserIntent, 2);
    }

    public void callImplicitActivity(){
        Intent actionIntent = new Intent();
        actionIntent.setAction("com.example.lab_intents.IMPLICIT_ACTION");
        actionIntent.addCategory("android.intent.category.DEFAULT");
        startActivity(actionIntent);
    }

    public void launchExplicitActivity() {
        Intent intent = new Intent(this, ExplicitActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                Log.e("poraka", data.getStringExtra("inputText"));
                textView.setText(data.getStringExtra("inputText"));
            }
        }
        if(requestCode == 2){
            if(resultCode == RESULT_OK){
                String path = data.getDataString();
                Log.e("slika1", path);
                String [] niza = path.split("/");
                Log.e("slika", niza[6]);
                String imageId = niza[6];
                Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI.buildUpon().appendPath(imageId).build();
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        }
    }
}

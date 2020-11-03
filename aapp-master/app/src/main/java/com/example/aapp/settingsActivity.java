package com.example.aapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import java.io.File;

public class settingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    //https://stackoverflow.com/questions/19422075/open-a-selected-file-image-pdf-programmatically-from-my-android-applicat
    //this was used as a base for the below code but it is outdated and needed to be changed slightly
    public void openReadMe(View view){
        File file = new File(Environment.getExternalStorageDirectory(),"README.txt");
        Uri path = Uri.fromFile(file);
        Intent openIntent = new Intent(Intent.ACTION_VIEW);
        openIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        openIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        openIntent.setDataAndType(path, "application/txt");
        try {
            startActivity(openIntent);
        }
        catch (ActivityNotFoundException e) {

        }
    }
}

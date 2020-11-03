package com.example.aapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    Button button22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button22 = (Button)findViewById(R.id.button22);
        //launchIntroSurvey();
    }

    public void launchIntroSurvey(){
        Intent intent = new Intent(this,surveyActivity.class);
        //button22.setText("");
        //button22.setClickable(false);
        startActivity(intent);
    }

    public void launchMotivational(View view) {
        //d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, motivationalActivity.class);
        startActivity(intent);
    }


    public void launchCalendar(View view) {
        //d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, calendarActivity.class);
        startActivity(intent);
    }

    public void launchAccountabilityBuddy(View view) {
        //d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, accountabilityActivity.class);
        startActivity(intent);
    }

    public void launchSocial(View view) {
        //d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, socialActivity.class);
        startActivity(intent);
    }

    public void launchSettings(View view) {
        //d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, settingsActivity.class);
        startActivity(intent);
    }
}

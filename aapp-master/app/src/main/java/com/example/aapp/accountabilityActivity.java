package com.example.aapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class accountabilityActivity extends AppCompatActivity {

    public static String name1="cheers";
    public static String name2="cheers";
    public static String name3="cheers";
    public static ArrayList<String> names = new ArrayList<String>(2);

    public static String number1="8008675309";
    public static String number2="8008675309";
    public static String number3="8008675309";
    Spinner spinner;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    private String alertText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountability);

        //Shared preferances resources:
        //https://developer.android.com/training/data-storage/shared-preferences
        //https://www.journaldev.com/9412/android-shared-preferences-example-tutorial
        sharedPref = getApplicationContext().getSharedPreferences("AApp",Context.MODE_PRIVATE);
        editor= sharedPref.edit();

        if (sharedPref.contains(name1)){
            name1=sharedPref.getString(name1,"");
        }
        if (sharedPref.contains(name2)){
            name2=sharedPref.getString(name2,"");
        }
        if (sharedPref.contains(name3)){
            name3=sharedPref.getString(name3,"");
        }

        if (names.size() == 0){
            names.add(name1);
            names.add(name2);
            names.add(name3);
        }

        spinner = (Spinner) findViewById(R.id.buddySpinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(accountabilityActivity.this, android.R.layout.simple_spinner_dropdown_item, names);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        addListenerOnSpinnerItemSelection();
    }

    public void launchMessaging(View view) {
        //d(LOG_TAG, "Button clicked!");
        spinner = (Spinner) findViewById(R.id.buddySpinner);
        int x = spinner.getSelectedItemPosition();
        String phoneNum;
        if (x == 0){
            phoneNum = number1;
        }
        else if (x == 1){
            phoneNum = number2;
        }
        else {
            phoneNum = number3;
        }
        Uri uri = Uri.parse(String.format("smsto:%s", phoneNum));
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        //intent.setData(Uri.parse("sms:"));
        startActivity(intent);
    }

    //https://stackoverflow.com/questions/10903754/input-text-dialog-android
    public void addBuddyNumber(View view) {
        spinner = (Spinner) findViewById(R.id.buddySpinner);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Number");
        final View tView = view;
        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        builder.setView(input);
        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int x = spinner.getSelectedItemPosition();
                String value=input.getText().toString();
                if (x==0){
                    editor.putString(number1, value);
                    number1 = value;
                }
                else if (x==1){
                    editor.putString(number2, value);
                    number2 = value;
                }
                else {
                    editor.putString(number3, value);
                    number3 = value;
                }
                editor.commit();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
        addBuddyName(view);
    }

    public void addBuddyName(View view) {
        spinner = (Spinner) findViewById(R.id.buddySpinner);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Name");
        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        builder.setView(input);
        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int x = spinner.getSelectedItemPosition();
                String value = input.getText().toString();
                if (x==0){
                    editor.putString(name1,value);
                    names.set(0, value);
                }
                else if (x==1){
                    editor.putString(name2,value);
                    names.set(1, value);
                }
                else {
                    editor.putString(name3,value);
                    names.set(2, value);
                }
                editor.commit();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    public void removeBuddy(View view) {
        spinner = (Spinner) findViewById(R.id.buddySpinner);
        int x = spinner.getSelectedItemPosition();

        if (x == 0) {
            names.set(0, "");
            editor.putString(number1, "");
            editor.putString(name1, "");

        } else if (x == 1) {
            names.set(1, "");
            editor.putString(number2, "");
            editor.putString(name2, "");
        } else {
            names.set(2, "");
            editor.putString(number3, "");
            editor.putString(name3, "");
        }
        editor.commit();

    }

    public void addListenerOnSpinnerItemSelection() {
        spinner = (Spinner) findViewById(R.id.buddySpinner);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }
}

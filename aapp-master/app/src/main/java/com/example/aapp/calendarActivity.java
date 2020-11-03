package com.example.aapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class calendarActivity extends AppCompatActivity{

    CalendarView calendarView;
    TextView myDate;
    Button saveButton;
    ArrayList<String> dates = new ArrayList<String>();
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    TextView logDates;

    int Day;
    int Month;
    int Year;
    long currentMilli;

    public static String date1="MM/dd/yyyy";
    public static String date2="MM/dd/yyyy";
    public static String date3="MM/dd/yyyy";
    // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
    AlertDialog.Builder builder;

    //https://stackoverflow.com/questions/7057845/save-arraylist-to-sharedpreferences
    //This is a post about storing arraylists in shared preferances, should be useful for saving which days the user drank

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
/*
        sharedPref = getApplicationContext().getSharedPreferences("AApp", Context.MODE_PRIVATE);
        editor= sharedPref.edit();

        if (sharedPref.contains(date1)){
            date1=sharedPref.getString(date1,"");
        }
        if (sharedPref.contains(date2)){
            date2=sharedPref.getString(date2,"");
        }
        if (sharedPref.contains(date3)){
            date3=sharedPref.getString(date3,"");
        }
        if (dates.size() == 0){
            dates.add(date1);
            dates.add(date2);
            dates.add(date3);
        }
*/
        calendarView = (CalendarView)findViewById(R.id.calendarView);
        myDate = (TextView)findViewById(R.id.myDate);
        saveButton = (Button)findViewById(R.id.saveButton);
        builder = new AlertDialog.Builder(this);
        logDates = (TextView)findViewById(R.id.scrollLog);

        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dates.add(myDate.getText().toString());
/*
                if (date1 == ""){
                    date1 = myDate.getText().toString();
                }
                else if(date2 == ""){
                    date2 = myDate.getText().toString();
                }
                else if(date3 == ""){
                    date3 = myDate.getText().toString();
                }
*/
                final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                builder.setTitle("Record Alcohol Consumption - " + myDate.getText().toString());


                builder.setItems(new CharSequence[]

                                {"1 drink", "2-3 drinks", "4-5 drinks", "6+ drinks"},
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Date date = getDate(myDate.getText().toString());
                                switch (which) {
                                    case 0:
                                        logDates.append(myDate.getText().toString() + " 1 drink \n" );
                                        break;
                                    case 1:
                                        logDates.append(myDate.getText().toString() + " 2-3 drinks \n" );
                                        break;
                                    case 2:
                                        logDates.append(myDate.getText().toString() + " 4-5 drinks \n" );
                                        break;
                                    case 3:
                                        logDates.append(myDate.getText().toString() + " 6+ drinks \n" );
                                        break;
                                    default:
                                        throw new IllegalStateException("Unexpected value: " + which);
                                }
                            }
                        });
                builder.create().show();
            }
        });

        calendarView.setOnDateChangeListener((new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = (month + 1) + "/" + dayOfMonth + "/" + year;
                Year = year;
                Month = month;
                Day = dayOfMonth;
                myDate.setText(date);

                saveButton.setText("Save My Date");
            }
        }));
    }

    public Date getDate(String date){

        String dateArray[] = date.split("/");

        int day = Integer.parseInt(dateArray[1]);
        int month = Integer.parseInt(dateArray[0]);
        int year = Integer.parseInt(dateArray[2]);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);

        long time = calendar.getTimeInMillis();

        return new Date(time);
    }


}

package com.example.aapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class motivationalActivity extends AppCompatActivity{

    public static String[] quotes;
    TextView quoteBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motivational);

        quoteBox = (TextView)findViewById(R.id.quoteBox);

        // Create an ArrayAdapter that will contain all list items
        quotes = getResources().getStringArray(R.array.motivationalArray);

        /* Assign the name array to that adapter and
        also choose a simple layout for the list items */
        // Assign the adapter to this ListActivity

        generateRandomMessage();
    }

    public void generateRandomMessage(){
        Random random = new Random();
        int randomNumber = random.nextInt(66);
        quoteBox.setText(quotes[randomNumber]);
    }
    public void generateRandomMessage(View view){
        Random random = new Random();
        int randomNumber = random.nextInt(66);
        quoteBox.setText(quotes[randomNumber]);
    }


}

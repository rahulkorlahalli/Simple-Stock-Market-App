package com.example.courseactivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.text.LineBreaker;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class SecondActivity extends AppCompatActivity {
    //Creating required Variables
    TextView t1,t2,t3,t4;
    TextView cName,cSymbol,cDesc;
    TextView stockDate;
    Button pickDateBtn;
    //For calculation of previous day date
    private static final long ONE_DAY_MILLI_SECONDS = 24 * 60 * 60 * 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //Finding views & initializing variables
        t1 = (TextView) findViewById(R.id.textViewOpen);
        t2 = (TextView) findViewById(R.id.textViewHigh);
        t3 = (TextView) findViewById(R.id.textViewLow);
        t4 = (TextView) findViewById(R.id.textViewClosed);
        cName = (TextView) findViewById(R.id.companyName);
        cSymbol = (TextView) findViewById(R.id.symbol);
        cDesc = (TextView) findViewById(R.id.description);
        stockDate = (TextView) findViewById(R.id.stockDate);
        pickDateBtn = findViewById(R.id.idBtnPickDate);

        //Defining date format
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        //Getting Previous Date - incase API isn't updated with current day details
        Date currentDate = new Date();
        long previousDayMilliSeconds =currentDate.getTime() - ONE_DAY_MILLI_SECONDS;
        Date previousDate = new Date(previousDayMilliSeconds);
        String prevDate = df.format(previousDate);
        //Getting Current Date
        Date c = Calendar.getInstance().getTime();
        String currentformattedDate = df.format(c);

        Intent i = getIntent();
        CompanyData CD = (CompanyData) i.getSerializableExtra("CompanyData");

        //Setting Company Details
        cName.setText(CD.name);
        cSymbol.setText(CD.symbol);
        cDesc.setText(CD.desc);
        cDesc.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);

        HashMap<String, HashMap<String,String>> hashMap = (HashMap<String, HashMap<String,String>>) i.getSerializableExtra("hashMap");
        //If API contains details as of current date
        if (hashMap.containsKey(currentformattedDate)) {
            stockDate.setText("Stock details as of "+ currentformattedDate);
            t1.setText(CD.currency + hashMap.get(currentformattedDate).get("1. open"));
            t2.setText(CD.currency + hashMap.get(currentformattedDate).get("2. high"));
            t3.setText(CD.currency + hashMap.get(currentformattedDate).get("3. low"));
            t4.setText(CD.currency + hashMap.get(currentformattedDate).get("4. close"));
        }
        //If API isn't updated with current day details
        else{
            stockDate.setText("Stock details as of "+ prevDate);
            t1.setText(CD.currency + hashMap.get(prevDate).get("1. open"));
            t2.setText(CD.currency + hashMap.get(prevDate).get("2. high"));
            t3.setText(CD.currency + hashMap.get(prevDate).get("3. low"));
            t4.setText(CD.currency + hashMap.get(prevDate).get("4. close"));
        }
        //Button to change dates & check stock details as of that day
        pickDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();
                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        SecondActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view.
                                String selectedDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                if (hashMap.containsKey(selectedDate)) {
                                    stockDate.setText("Stock details as of "+ selectedDate);
                                    t1.setText(CD.currency + hashMap.get(selectedDate).get("1. open"));
                                    t2.setText(CD.currency + hashMap.get(selectedDate).get("2. high"));
                                    t3.setText(CD.currency + hashMap.get(selectedDate).get("3. low"));
                                    t4.setText(CD.currency + hashMap.get(selectedDate).get("4. close"));
                                }
                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });
    }
}

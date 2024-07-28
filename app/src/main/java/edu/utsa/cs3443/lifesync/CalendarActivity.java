package edu.utsa.cs3443.lifesync;
import java.time.LocalDate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

import edu.utsa.cs3443.lifesync.model.User;

public class CalendarActivity extends AppCompatActivity {
    private User user;
    private CalendarView calendarView;
    private Calendar calendar;
    private LocalDate today = LocalDate.now();
    int currentDate = today.getDayOfMonth();
    int month = today.getMonthValue();
    int year = today.getYear();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calendar);
        calendarView = findViewById(R.id.calendarView);
        user= (User) getIntent().getSerializableExtra("user");
        calendar = Calendar.getInstance();
        setDate(currentDate,month,year);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                Toast.makeText(CalendarActivity.this,month+1 + "/" +day +"/"+year , Toast.LENGTH_SHORT).show();

            }
        });
        //createNavigationBar();

    }
    public void setDate(int day, int month, int year){
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        long milli = calendar.getTimeInMillis();
        calendarView.setDate(milli);
    }
    public void createNavigationBar(){
        ImageButton create =findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start ZoneActivity
                Intent intent = new Intent(getBaseContext(), SecondActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        ImageButton home =findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start ZoneActivity
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        ImageButton calendar =findViewById(R.id.calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start ZoneActivity
                Intent intent = new Intent(getBaseContext(), CalendarActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }

}
package edu.utsa.cs3443.lifesync;
import java.time.LocalDate;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {
    CalendarView calendarView;
    Calendar calendar;
    LocalDate today = LocalDate.now();
    int currentDate = today.getDayOfMonth();
    int month = today.getMonthValue();
    int year = today.getYear();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calendar);
        calendarView = findViewById(R.id.calendarView);
        calendar = Calendar.getInstance();
        setDate(currentDate,month,year);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                Toast.makeText(CalendarActivity.this,month+1 + "/" +day +"/"+year , Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void setDate(int day, int month, int year){
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        long milli = calendar.getTimeInMillis();
        calendarView.setDate(milli);

    }
}
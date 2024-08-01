package edu.utsa.cs3443.lifesync;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import edu.utsa.cs3443.lifesync.model.User;
import edu.utsa.cs3443.lifesync.model.Widget;

public class CalendarActivity extends AppCompatActivity {
    private User user;
    private CalendarView calendarView;
    private Calendar calendar;
    private LocalDate today = LocalDate.now();
    private int currentDate = today.getDayOfMonth();
    private int month = today.getMonthValue();
    private int year = today.getYear();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calendar);
        calendarView = findViewById(R.id.calendarView);
        user = (User) getIntent().getSerializableExtra("user");
        calendar = Calendar.getInstance();
        setDate(currentDate,month,year);



        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                //Toast.makeText(CalendarActivity.this,month+1 + "/" +day +"/"+year , Toast.LENGTH_SHORT).show();

                // Note: month is 0-based, so 0 corresponds to January
                String selectedDay = String.format("%02d", day);
                String selectedMonth = String.format("%02d", month + 1); // Adding 1 since months are 0-based
                String selectedYear = String.valueOf(year);
                String selectedDate = selectedMonth + "/" + selectedDay + "/" + selectedYear;

                //Current day of month in real time
                //Log.d("JMA", String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
                displayWidgetByDay(selectedDate);
            }
        });
        createNavigationBar();

    }

    public void displayWidgetByDay(String date)
    {
        String dayOfWeek = getDayOfWeek(date);

        // Inflate the widget container layout
        LinearLayout weeklyWidgetList = findViewById(R.id.calendarScrollViewLayout).findViewById(R.id.widgetsLayoutCalendarView);
        LayoutInflater inflater = LayoutInflater.from(this);

        // Clear all views from the layout to destroy existing widgets
        weeklyWidgetList.removeAllViews();

        // To create new elements
        View widgetContainer = inflater.inflate(R.layout.widget_container, weeklyWidgetList, false);

        TextView dayOfWeekTextView = (TextView) widgetContainer.findViewById(R.id.dayOfWeekView);
        TextView dayOfMonthTextView = (TextView) widgetContainer.findViewById(R.id.dayOfMonthView);

        for(int i = 0; i < user.getWidgets().toArray().length; i++)
        {
            if(user.getWidgets().get(i).getFormattedDate().equals(date))
            {
                Log.d("JMA", user.getWidgets().get(i).getTitle() +
                        user.getWidgets().get(i).getDescription());
            }
        }
        dayOfWeekTextView.setText(dayOfWeek);
        dayOfMonthTextView.setText(date);
        weeklyWidgetList.addView(widgetContainer);
        for (Widget w : user.getWidgets() )
        {
            if(w.getFormattedDate().equals(date))
            {
                //Log.d("JMA", w.getTitle());
                //Log.d("JMA", w.getDescription());
                //Log.d("JMA", w.getId());
                //Log.d("JMA", w.getFormattedDate());

                View elementsContainer = inflater.inflate(R.layout.element_container, weeklyWidgetList, false);

                TextView elementTextView = (TextView)elementsContainer.findViewById(R.id.ElementView);
                ImageView widgetTypeView = (ImageView)elementsContainer.findViewById(R.id.WidgetTypeView);

                if(w.getType().equals("Note")) widgetTypeView.setImageResource(R.drawable.note);
                if(w.getType().equals("Task")) widgetTypeView.setImageResource(R.drawable.task);
                if(w.getType().equals("Event")) widgetTypeView.setImageResource(R.drawable.event);

                elementTextView.setText(w.getTitle() + ": " + w.getDescription()+ " at " + w.getFormattedStartTime());

                weeklyWidgetList.addView(elementsContainer);
            }
        }
    }

    public static String getDayOfWeek(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        try {
            // Parse the date string into a Date object
            Date date = format.parse(dateStr);

            // Create a Calendar object and set the date
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            // Get the day of the week
            return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setDate(int day, int month, int year){
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        long milli = calendar.getTimeInMillis();
        calendarView.setDate(milli);
    }
    public void createNavigationBar(){
        ImageButton profile =findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start ZoneActivity
                Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        ImageButton notification =findViewById(R.id.notification);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start ZoneActivity
                Intent intent = new Intent(getBaseContext(), NotificationActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

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
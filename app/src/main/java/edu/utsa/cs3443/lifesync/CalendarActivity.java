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

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import edu.utsa.cs3443.lifesync.model.User;
import edu.utsa.cs3443.lifesync.model.Widget;

/**
 * The CalendarActivity class provides a comprehensive view of past activities.
 * It allows users to select a date and view widgets associated with that date.
 */
public class CalendarActivity extends AppCompatActivity {
    private User user; // User object representing the current user
    private CalendarView calendarView; // CalendarView for selecting dates
    private Calendar calendar; // Calendar object for date manipulation
    private LocalDate today = LocalDate.now(); // Today's date
    private int currentDate = today.getDayOfMonth(); // Current day of the month
    private int month = today.getMonthValue(); // Current month
    private int year = today.getYear(); // Current year

    /**
     * Initializes the activity, sets up EdgeToEdge mode, and loads the calendar view.
     *
     * @param savedInstanceState The saved state of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calendar);

        calendarView = findViewById(R.id.calendarView);
        user = (User) getIntent().getSerializableExtra("user"); // Get user object from intent
        calendar = Calendar.getInstance(); // Initialize calendar object

        setDate(currentDate, month, year); // Set the current date in the calendar view

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                //Toast.makeText(CalendarActivity.this, month + 1 + "/" + day + "/" + year, Toast.LENGTH_SHORT).show();

                // Note: month is 0-based, so 0 corresponds to January
                String selectedDay = String.format("%02d", day);
                String selectedMonth = String.format("%02d", month + 1); // Adding 1 since months are 0-based
                String selectedYear = String.valueOf(year);
                String selectedDate = selectedMonth + "/" + selectedDay + "/" + selectedYear;

                displayWidgetByDay(selectedDate); // Display widgets for the selected date
            }
        });

        createNavigationBar(); // Set up navigation bar
    }

    /**
     * Displays widgets for a specific day.
     *
     * @param date The date to display widgets for.
     */
    public void displayWidgetByDay(String date) {
        String dayOfWeek = getDayOfWeek(date); // Get day of the week for the date

        // Inflate the widget container layout
        LinearLayout weeklyWidgetList = findViewById(R.id.calendarScrollViewLayout).findViewById(R.id.widgetsLayoutCalendarView);
        LayoutInflater inflater = LayoutInflater.from(this);

        // Clear all views from the layout to destroy existing widgets
        weeklyWidgetList.removeAllViews();

        // To create new elements
        View widgetContainer = inflater.inflate(R.layout.widget_container, weeklyWidgetList, false);

        TextView dayOfWeekTextView = widgetContainer.findViewById(R.id.dayOfWeekView);
        TextView dayOfMonthTextView = widgetContainer.findViewById(R.id.dayOfMonthView);

        for (int i = 0; i < user.getWidgets().toArray().length; i++) {
            if (user.getWidgets().get(i).getFormattedDate().equals(date)) {
                Log.d("JMA", user.getWidgets().get(i).getTitle() +
                        user.getWidgets().get(i).getDescription());
            }
        }

        dayOfWeekTextView.setText(dayOfWeek);
        dayOfMonthTextView.setText(date);
        weeklyWidgetList.addView(widgetContainer);

        for (Widget w : user.getWidgets()) {
            if (w.getFormattedDate().equals(date)) {
                View elementsContainer = inflater.inflate(R.layout.element_container, weeklyWidgetList, false);

                TextView elementTextView = elementsContainer.findViewById(R.id.ElementView);
                ImageView widgetTypeView = elementsContainer.findViewById(R.id.WidgetTypeView);

                if (w.getType().equals("Note")) widgetTypeView.setImageResource(R.drawable.note);
                if (w.getType().equals("Task")) widgetTypeView.setImageResource(R.drawable.task);
                if (w.getType().equals("Event")) widgetTypeView.setImageResource(R.drawable.event);

                elementTextView.setText(w.getTitle() + ": " + w.getDescription() + " at " + w.getFormattedStartTime());

                weeklyWidgetList.addView(elementsContainer);
            }
        }
    }

    /**
     * Gets the day of the week for a given date.
     *
     * @param dateStr The date string in "MM/dd/yyyy" format.
     * @return The day of the week.
     */
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

    /**
     * Sets the date in the calendar view.
     *
     * @param day   The day of the month.
     * @param month The month (1-based).
     * @param year  The year.
     */
    public void setDate(int day, int month, int year) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        long milli = calendar.getTimeInMillis();
        calendarView.setDate(milli);
    }

    /**
     * Creates the navigation bar with buttons to navigate to different activities.
     */
    public void createNavigationBar() {
        ImageButton profile = findViewById(R.id.profile); // Profile button
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start ProfileActivity
                Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        ImageButton notification = findViewById(R.id.notification); // Notification button
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start NotificationActivity
                Intent intent = new Intent(getBaseContext(), NotificationActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        ImageButton create = findViewById(R.id.create); // Create button
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start SecondActivity
                Intent intent = new Intent(getBaseContext(), SecondActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        ImageButton home = findViewById(R.id.home); // Home button
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start MainActivity
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        ImageButton calendar = findViewById(R.id.calendar); // Calendar button
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start CalendarActivity
                Intent intent = new Intent(getBaseContext(), CalendarActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}

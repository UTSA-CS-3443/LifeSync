package edu.utsa.cs3443.lifesync;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import edu.utsa.cs3443.lifesync.model.Admin;
import edu.utsa.cs3443.lifesync.model.User;
import edu.utsa.cs3443.lifesync.model.Widget;

/**
 * The MainActivity class serves as the main entry point of the LifeSync app.
 * It initializes the user, sets up the navigation bar, and displays widgets for the week.
 */
public class MainActivity extends AppCompatActivity {
    private User user;
    private Admin admin;

    /**
     * Initializes the activity, sets up EdgeToEdge mode, and loads the user account.
     *
     * @param savedInstanceState The saved state of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        user = (User) getIntent().getSerializableExtra("user");

        if (user == null) {
            try {
                admin = new Admin(this);
                user = admin.getUser();
                Toast.makeText(this, "Loading user success", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                // Display a Toast message indicating an error loading user
                Toast.makeText(this, "Error loading user: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
        user.sortWidgetsByDateTime();
        createNavigationBar();
        Date today = new Date();

        // Create a calendar instance and set it to the current date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        // Subtract one day to get yesterday's date
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date yesterday = calendar.getTime();

        displayWeekDate(yesterday);
    }

    /**
     * Displays widgets for a specific day.
     *
     * @param date The date to display widgets for.
     */
    public void displayWidgetByDay(String date) {
        String dayOfWeek = getDayOfWeek(date);
        Log.d("dayOfWeek", dayOfWeek);
        Log.d("date", date);

        // Inflate the widget container layout
        LinearLayout weeklyWidgetList = findViewById(R.id.widgetsLayout);
        LayoutInflater inflater = LayoutInflater.from(this);

        // To create new elements
        View widgetContainer = inflater.inflate(R.layout.widget_container, weeklyWidgetList, false);

        TextView dayOfWeekTextView = widgetContainer.findViewById(R.id.dayOfWeekView);
        TextView dayOfMonthTextView = widgetContainer.findViewById(R.id.dayOfMonthView);
        /*
        for (int i = 0; i < user.getWidgets().toArray().length; i++) {
            if (user.getWidgets().get(i).getFormattedDate().equals(date)) {
                Log.d("JMA", user.getWidgets().get(i).getTitle() + user.getWidgets().get(i).getDescription());
            }
        }

         */
        dayOfWeekTextView.setText(dayOfWeek);
        dayOfMonthTextView.setText(date);
        weeklyWidgetList.addView(widgetContainer);
        for (Widget w : user.getWidgets()) {
            if (w.getFormattedDate().equals(date)) {


                View elementsContainer = inflater.inflate(R.layout.element_container, weeklyWidgetList, false);

                TextView elementTextView = elementsContainer.findViewById(R.id.ElementView);
                ImageView widgetTypeView = elementsContainer.findViewById(R.id.WidgetTypeView);

                if (w.getType().equals("Note")) {
                    widgetTypeView.setImageResource(R.drawable.note);
                    elementTextView.setText(w.getTitle() + ": " + w.getDescription());
                } else if (w.getType().equals("Task")) {
                    widgetTypeView.setImageResource(R.drawable.task);
                    elementTextView.setText(w.getTitle() + ": " + w.getDescription() + " at " + w.getFormattedStartTime());
                } else if (w.getType().equals("Event")) {
                    widgetTypeView.setImageResource(R.drawable.event);
                    elementTextView.setText(w.getTitle() + ": " + w.getDescription() + " at " + w.getFormattedStartTime());
                }
                weeklyWidgetList.addView(elementsContainer);
            }
        }
    }

    /**
     * Displays widgets for the week starting from a given date.
     *
     * @param today The starting date.
     */
    public void displayWeekDate(Date today) {
        SimpleDateFormat stringFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        for (int x = 0; x < 7; x++) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            Date date = calendar.getTime();
            displayWidgetByDay(stringFormat.format(date));
            Log.d("date passes", stringFormat.format(date));
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
     * Creates the navigation bar with buttons to navigate to different activities.
     */
    public void createNavigationBar() {
        ImageButton profile = findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start ProfileActivity
                Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        ImageButton notification = findViewById(R.id.notification);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start NotificationActivity
                Intent intent = new Intent(getBaseContext(), NotificationActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        ImageButton create = findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start SecondActivity
                Intent intent = new Intent(getBaseContext(), SecondActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        ImageButton home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start MainActivity
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        ImageButton calendar = findViewById(R.id.calendar);
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

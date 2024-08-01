package edu.utsa.cs3443.lifesync;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import edu.utsa.cs3443.lifesync.model.User;
import edu.utsa.cs3443.lifesync.model.Widget;

public class MainActivity extends AppCompatActivity {
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        user = (User) getIntent().getSerializableExtra("user");

        if(user == null) {
            try {  // Load Zone from the CSV file into the fleet
                user = LoadingUserAccount(this);
                Toast.makeText(this, "Loading user success", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                // Display a Toast message indicating an error loading zone
                Toast.makeText(this, "Error loading user: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }

            Toast.makeText(this, "Number of widget" + user.getNumberOfWidget(), Toast.LENGTH_LONG).show();
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
        /*
        displayWidgetByDay("07/24/2025");
        displayWidgetByDay("07/25/2025");
        displayWidgetByDay("07/26/2025");
        displayWidgetByDay("07/27/2025");
        displayWidgetByDay("07/28/2025");
        displayWidgetByDay("07/29/2025");
        displayWidgetByDay("07/30/2025");

         */
    }

    public void displayWidgetByDay(String date)
    {
        String dayOfWeek = getDayOfWeek(date);
        Log.d("dayOfWeek", dayOfWeek);
        Log.d("date", date);
        // Inflate the widget container layout
        LinearLayout weeklyWidgetList = findViewById(R.id.widgetsLayout);
        LayoutInflater inflater = LayoutInflater.from(this);

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
                Log.d("startime", w.getFormattedStartTime());
                Log.d("JMA", w.getTitle());
                Log.d("JMA", w.getDescription());
                Log.d("JMA", w.getId());
                Log.d("JMA", w.getFormattedDate());

                View elementsContainer = inflater.inflate(R.layout.element_container, weeklyWidgetList, false);

                TextView elementTextView = (TextView)elementsContainer.findViewById(R.id.ElementView);
                ImageView widgetTypeView = (ImageView)elementsContainer.findViewById(R.id.WidgetTypeView);

                if(w.getType().equals("Note")) {widgetTypeView.setImageResource(R.drawable.note);
                    elementTextView.setText(w.getTitle() + ": " + w.getDescription());
                } else if(w.getType().equals("Task")) {
                    widgetTypeView.setImageResource(R.drawable.task);
                    elementTextView.setText(w.getTitle() + ": " + w.getDescription()+ " at " + w.getFormattedStartTime());
                } else if(w.getType().equals("Event")){
                    widgetTypeView.setImageResource(R.drawable.event);
                    elementTextView.setText(w.getTitle() + ": " + w.getDescription()+ " at " + w.getFormattedStartTime());
                }
                weeklyWidgetList.addView(elementsContainer);
            }
        }
    }
    public void displayWeekDate(Date today){
        SimpleDateFormat stringFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        for(int x = 0; x <7; x++) {
            calendar.add(Calendar.DAY_OF_YEAR, +1);
            Date date = calendar.getTime();
            displayWidgetByDay(stringFormat.format(date));
            Log.d("date passes", stringFormat.format(date));
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

    public void displayWidgetsLegacy(LinearLayout widgetContainer) {
        String previousDate = "";
        String previousWeekday = "";
        LinearLayout widgets = null;
        Date today = new Date();

        // Create a calendar instance and set it to the current date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        // Subtract one day to get yesterday's date
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date yesterday = calendar.getTime();

        for (Widget widget : user.getWidgets()) {
            if(widget.getDate().after(yesterday)) {
                String currentDate = widget.getFormattedDate();
                String currentWeekday = new SimpleDateFormat("EEEE").format(widget.getDate());
                if (!currentDate.equals(previousDate)) {
                    // Add the previous group of widgets to the widgetView
                    if (widgets != null) {

                        View widgetView = LayoutInflater.from(this).inflate(R.layout.widget_container_layout, widgetContainer, false);
                        TextView dateTextView = widgetView.findViewById(R.id.Date);
                        TextView weekDateTextView = widgetView.findViewById(R.id.weekdate);
                        dateTextView.setText(previousDate);
                        weekDateTextView.setText(previousWeekday);
                        LinearLayout widgetGroupContainer = widgetView.findViewById(R.id.widgets);
                        widgetGroupContainer.addView(widgets);
                        widgetContainer.addView(widgetView);
                    }

                    // Create a new LinearLayout for the new date group
                    widgets = new LinearLayout(this);
                    widgets.setOrientation(LinearLayout.VERTICAL);
                    previousDate = currentDate;
                    previousWeekday = currentWeekday;
                }

                // Add the current widget to the current date group
                View announcement = LayoutInflater.from(this).inflate(R.layout.widget_type_display_layout, widgets, false);
                TextView widgetText = announcement.findViewById(R.id.widgetText);
                ImageView widgetImage = announcement.findViewById(R.id.widgetType);
                String image = "@drawable/" + widget.getType().toLowerCase();
                int imageResource = getResources().getIdentifier(image, null, getPackageName());
                Drawable res = getResources().getDrawable(imageResource);
                widgetImage.setImageDrawable(res);
                if (widget.getType() == "Note") {
                    widgetText.setText(widget.getType() + ": " + widget.getTitle());
                    widgets.addView(announcement);
                } else {
                    widgetText.setText(widget.getType() + ": " + widget.getTitle() + " at " + widget.getStartTime());
                    widgets.addView(announcement);
                }
            }
        }

        // Add the last group of widgets to the widgetView
        if (widgets != null) {
            View widgetView = LayoutInflater.from(this).inflate(R.layout.widget_container_layout, widgetContainer, false);
            TextView dateTextView = widgetView.findViewById(R.id.Date);
            TextView weekDateTextView = widgetView.findViewById(R.id.weekdate);
            dateTextView.setText(previousDate);
            weekDateTextView.setText(previousWeekday);

            LinearLayout widgetGroupContainer = widgetView.findViewById(R.id.widgets);
            widgetGroupContainer.addView(widgets);
            widgetContainer.addView(widgetView);
        }
    }
    //create navigation bar
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
    public User LoadingUserAccount(Activity activity){
        AssetManager manager = activity.getAssets();
        String fileName = "AccountInfo.csv";
        try {
            // Open the CSV file from the assets folder
            InputStream file = manager.open(fileName);
            Scanner scan = new Scanner(file);
            // Iterate through each line of the CSV file
            while (scan.hasNextLine()) {
                // Read lines for each user's data
                String name = scan.nextLine().trim();
                String email = scan.nextLine().trim();
                String gender = scan.nextLine().trim();
                String biography = scan.nextLine().trim();

                // Create a new User object

                User user = new User(name, email, biography, gender);
                user.loadWidget(activity);
                return user;
            }
            scan.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error accessing assets
            Toast.makeText(activity, "Error accessing assets: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return null;
    }
}
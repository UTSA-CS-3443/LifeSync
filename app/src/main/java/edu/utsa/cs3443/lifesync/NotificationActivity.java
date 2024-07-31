package edu.utsa.cs3443.lifesync;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;

import edu.utsa.cs3443.lifesync.model.User;
import edu.utsa.cs3443.lifesync.model.Widget;

public class NotificationActivity extends AppCompatActivity {
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notification);
        LinearLayout notificationContainer = findViewById(R.id.notification_container);

        user = (User) getIntent().getSerializableExtra("user");
        user.sortWidgetsByDateTime();
        displayWidgets(notificationContainer);

    }

    public void displayWidgets(LinearLayout notificationContainer) {
        Date today = new Date();

        // Create a calendar instance and set it to the current date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        // Subtract one day to get yesterday's date
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date yesterday = calendar.getTime();

        for (Widget widget : user.getWidgets()) {
            if (widget.getDate().after(yesterday) && widget.getDate().before(today)) {
                View notificationView = LayoutInflater.from(this).inflate(R.layout.notification_test_template, notificationContainer, false);
                TextView widgetTypeAndTitle = notificationView.findViewById(R.id.widget_type_title);
                TextView description = notificationView.findViewById(R.id.description);
                TextView startTime = notificationView.findViewById(R.id.start_time);
                TextView address = notificationView.findViewById(R.id.address);
                TextView guests = notificationView.findViewById(R.id.guests);
                if(widget.getType().equals("Event")){
                    address.setVisibility(View.VISIBLE);
                    guests.setVisibility(View.VISIBLE);
                    address.setText(widget.getLocation());
                    guests.setText(widget.getGuests().toString());
                    widgetTypeAndTitle.setText(widget.getType()+": " +widget.getTitle());
                    startTime.setText("Start time: " + widget.getFormattedStartTime());
                    description.setText(widget.getDescription());
                    notificationContainer.addView(notificationView);
                }else if(widget.getType().equals("Task")){
                    widgetTypeAndTitle.setText(widget.getType()+": "+widget.getTitle());
                    description.setText(widget.getDescription());
                    notificationContainer.addView(notificationView);
                    startTime.setText("Start time: " + widget.getFormattedStartTime());
                }else{
                    startTime.setVisibility(View.GONE);
                    widgetTypeAndTitle.setText(widget.getType()+": "+widget.getTitle());
                    description.setText(widget.getDescription());
                    notificationContainer.addView(notificationView);
                }
            }
        }

    }

    public void createNavigationBar(){


        ImageButton create =findViewById(R.id.create);
        create.setOnClickListener(v -> {
            // Create an Intent to start ZoneActivity
            Intent intent = new Intent(getBaseContext(), SecondActivity.class);

            startActivity(intent);
        });

        ImageButton home =findViewById(R.id.home);
        home.setOnClickListener(v -> {
            // Create an Intent to start ZoneActivity
            Intent intent = new Intent(getBaseContext(), MainActivity.class);

            startActivity(intent);
        });
        ImageButton calendar =findViewById(R.id.calendar);
        calendar.setOnClickListener(v -> {
            // Create an Intent to start ZoneActivity
            Intent intent = new Intent(getBaseContext(), CalendarActivity.class);

            startActivity(intent);
        });
    }


}
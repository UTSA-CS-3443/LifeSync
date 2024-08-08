package edu.utsa.cs3443.lifesync;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import edu.utsa.cs3443.lifesync.model.User;
import edu.utsa.cs3443.lifesync.model.Widget;

/**
 * The NotificationActivity class handles displaying a list of notifications
 * sent from other users, which can be accepted and added to the user's journal
 * or discarded.
 */
public class NotificationActivity extends AppCompatActivity {
    private User user; // User object representing the current user

    /**
     * Initializes the activity, setting up the notifications list and displaying widgets.
     *
     * @param savedInstanceState The saved state of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notification);

        LinearLayout notificationContainer = findViewById(R.id.notification_container);

        user = (User) getIntent().getSerializableExtra("user"); // Get user object from intent
        user.sortWidgetsByDateTime(); // Sort widgets by date and time
        Date today = new Date(); // Get current date
        String weekDateString = new SimpleDateFormat("EEEE").format(today); // Format for day of the week
        String dateString = new SimpleDateFormat("MM/dd/yyyy").format(today); // Format for date

        TextView weekDate = findViewById(R.id.weekdate); // TextView for day of the week
        TextView date = findViewById(R.id.date); // TextView for date

        weekDate.setText(weekDateString);
        date.setText(dateString);

        displayWidgets(notificationContainer); // Display widgets as notifications
        createNavigationBar(); // Set up navigation bar
    }

    /**
     * Displays widgets as notifications.
     *
     * @param notificationContainer The container to display the widgets in.
     */
    public void displayWidgets(LinearLayout notificationContainer) {
        Date today = new Date(); // Get current date

        // Create a calendar instance and set it to the current date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        // Subtract one day to get yesterday's date
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date yesterday = calendar.getTime();

        for (Widget widget : user.getWidgets()) {
            // Check if the widget's date is between yesterday and today
            if (widget.getDate().after(yesterday) && widget.getDate().before(today)) {
                View notificationView = LayoutInflater.from(this).inflate(R.layout.notification_widget_display_template, notificationContainer, false);

                TextView widgetTypeAndTitle = notificationView.findViewById(R.id.widget_type_title); // TextView for widget type and title
                TextView description = notificationView.findViewById(R.id.description); // TextView for description
                TextView startTime = notificationView.findViewById(R.id.start_time); // TextView for start time
                TextView address = notificationView.findViewById(R.id.address); // TextView for address
                TextView guests = notificationView.findViewById(R.id.guests); // TextView for guests
                ImageButton deleteBtn = notificationView.findViewById(R.id.delete_button); // Button for deleting the widget

                deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CreateAlertDialog(user, widget); // Create alert dialog to confirm deletion
                    }
                });

                if (widget.getType().equals("Event")) {
                    address.setVisibility(View.VISIBLE);
                    guests.setVisibility(View.VISIBLE);
                    address.setText("Address: " + widget.getLocation());
                    guests.setText("Guests: " + widget.getGuests().toString().replace("[", "").replace("]", ""));
                    widgetTypeAndTitle.setText(widget.getType() + ": " + widget.getTitle());
                    startTime.setText("Start time: " + widget.getFormattedStartTime());
                    description.setText("Description: " + "\n" + widget.getDescription());
                } else if (widget.getType().equals("Task")) {
                    guests.setVisibility(View.GONE);
                    address.setVisibility(View.GONE);
                    widgetTypeAndTitle.setText(widget.getType() + ": " + widget.getTitle());
                    description.setText("Description: " + "\n" + widget.getDescription());
                    startTime.setText("Start time: " + widget.getFormattedStartTime());
                } else {
                    startTime.setVisibility(View.GONE);
                    guests.setVisibility(View.GONE);
                    address.setVisibility(View.GONE);
                    widgetTypeAndTitle.setText(widget.getType() + ": " + widget.getTitle());
                    description.setText(widget.getDescription());
                }

                notificationContainer.addView(notificationView); // Add the notification view to the container
            }
        }
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
                // Refresh the current activity
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

    /**
     * Creates an alert dialog to confirm deletion of a widget.
     *
     * @param user   The user object.
     * @param widget The widget to be deleted.
     */
    public void CreateAlertDialog(User user, Widget widget) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this widget?");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                user.removeWidget(widget);
                //Toast.makeText(NotificationActivity.this, "The widget has been deleted", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(NotificationActivity.this, "Go back", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create();
        builder.show();
    }
}

package edu.utsa.cs3443.lifesync;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        Date today = new Date();
        String weekDateString = new SimpleDateFormat("EEEE").format(today);
        displayWidgets(notificationContainer);
        String dateString = new SimpleDateFormat("MM/dd/yyyy").format(today);
        TextView weekDate = findViewById(R.id.weekdate);
        TextView date = findViewById(R.id.date);
        weekDate.setText(weekDateString);
        date.setText(dateString);
        createNavigationBar();
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

                View notificationView = LayoutInflater.from(this).inflate(R.layout.notification_widget_display_template, notificationContainer, false);
                TextView widgetTypeAndTitle = notificationView.findViewById(R.id.widget_type_title);
                TextView description = notificationView.findViewById(R.id.description);
                TextView startTime = notificationView.findViewById(R.id.start_time);
                TextView address = notificationView.findViewById(R.id.address);
                TextView guests = notificationView.findViewById(R.id.guests);
                ImageButton deleteBtn = notificationView.findViewById(R.id.delete_button);
                deleteBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        CreateAlertDialog(user, widget);
                    }
                });
                if(widget.getType().equals("Event")){
                    address.setVisibility(View.VISIBLE);
                    guests.setVisibility(View.VISIBLE);
                    address.setText("Address: " + widget.getLocation());
                    guests.setText("Guests: " + widget.getGuests().toString().replace("[", "").replace("]", ""));
                    widgetTypeAndTitle.setText(widget.getType()+": " +widget.getTitle());
                    startTime.setText("Start time: " + widget.getFormattedStartTime());
                    description.setText("Description: " + "\n" +widget.getDescription());
                    notificationContainer.addView(notificationView);
                }else if(widget.getType().equals("Task")){
                    guests.setVisibility(View.GONE);
                    address.setVisibility(View.GONE);
                    widgetTypeAndTitle.setText(widget.getType()+": "+widget.getTitle());
                    description.setText("Descrition: " + "\n" +widget.getDescription());
                    notificationContainer.addView(notificationView);
                    startTime.setText("Start time: " + widget.getFormattedStartTime());
                }else{
                    startTime.setVisibility(View.GONE);
                    guests.setVisibility(View.GONE);
                    address.setVisibility(View.GONE);
                    widgetTypeAndTitle.setText(widget.getType()+": "+widget.getTitle());
                    description.setText(widget.getDescription());
                    notificationContainer.addView(notificationView);
                }
            }
        }

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
    public void CreateAlertDialog(User user, Widget widget){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this widget?");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                user.removeWidget(widget);
                Toast.makeText(NotificationActivity.this, "The widget has been deleted", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                Toast.makeText(NotificationActivity.this, "Go back", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create();
        builder.show();
    }

}
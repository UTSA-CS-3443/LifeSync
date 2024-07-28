package edu.utsa.cs3443.lifesync;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
        LinearLayout widgetContainer = findViewById(R.id.widget_container);
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
        user.sortWidgetByDate();
        createNavigationBar();

        for(Widget widget: user.getWidgets()){
            View widgetView = LayoutInflater.from(this).inflate(R.layout.widget_container_layout, widgetContainer, false);
            TextView Date = widgetView.findViewById(R.id.Date) ;
            Date.setText(widget.getDate() + widget.getType());
            widgetContainer.addView(widgetView);
        }

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
                User user = new User(name, email, gender, biography);
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
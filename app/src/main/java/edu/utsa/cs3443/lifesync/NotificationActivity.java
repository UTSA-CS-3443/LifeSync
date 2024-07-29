package edu.utsa.cs3443.lifesync;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    private ListView lvNotifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        lvNotifications = findViewById(R.id.lv_notifications);

        // Example data, replace with our own data
        ArrayList<String> notifications = new ArrayList<>();
        notifications.add("Notification 1");
        notifications.add("Notification 2");
        notifications.add("Notification 3");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notifications);
        lvNotifications.setAdapter(adapter);
    }
}

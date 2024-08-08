package edu.utsa.cs3443.lifesync;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.lifesync.model.User;

/**
 * The ProfileEditActivity class allows the user to edit their profile information
 * such as name, email, gender, and biography. It also sets up the navigation
 * bar for navigating to other activities.
 */
public class ProfileEditActivity extends AppCompatActivity {
    private User user; // User object representing the current user
    private EditText editName, editEmail, editGender, editBio; // EditText fields for profile editing

    /**
     * Initializes the activity, setting up the profile editing form and
     * the navigation bar.
     *
     * @param savedInstanceState The saved state of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Enable edge-to-edge display
        setContentView(R.layout.activity_profile_edit);

        // Retrieve user object from intent
        user = (User) getIntent().getSerializableExtra("user");

        // Initialize EditText fields
        editName = findViewById(R.id.edit_name);
        editEmail = findViewById(R.id.edit_email);
        editGender = findViewById(R.id.edit_gender);
        editBio = findViewById(R.id.edit_biography);

        // Set current user information in EditText fields
        editName.setText(user.getName());
        editEmail.setText(user.getEmail());
        editGender.setText(user.getGender());
        editBio.setText(user.getBiography());

        // Set up the update button to save changes
        Button updateButton = findViewById(R.id.update);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve updated information from EditText fields
                String newName = editName.getText().toString().trim();
                String newEmail = editEmail.getText().toString().trim();
                String newGender = editGender.getText().toString().trim();
                String newBio = editBio.getText().toString().trim();

                // Update user object with new information
                user.setName(newName);
                user.setEmail(newEmail);
                user.setGender(newGender);
                user.setBiography(newBio);
            }
        });

        // Set up the navigation bar
        createNavigationBar();
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

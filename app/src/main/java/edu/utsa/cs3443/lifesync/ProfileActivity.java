package edu.utsa.cs3443.lifesync;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.lifesync.model.User;

/**
 * The ProfileActivity class displays the user's profile information including
 * name, gender, email, and a personal biography. It also sets up the navigation
 * bar for navigating to other activities.
 */
public class ProfileActivity extends AppCompatActivity {
    private User user; // User object representing the current user

    /**
     * Initializes the activity, setting up the profile information display and
     * the navigation bar.
     *
     * @param savedInstanceState The saved state of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Enable edge-to-edge display
        setContentView(R.layout.activity_profile);

        // Retrieve user object from intent
        user = (User) getIntent().getSerializableExtra("user");

        // Get user details
        String name = user.getName();
        String email = user.getEmail();
        String gender = user.getGender();
        String biography = user.getBiography();

        // Set user details in TextViews
        TextView userName = findViewById(R.id.name);
        TextView userGender = findViewById(R.id.gender);
        TextView userEmail = findViewById(R.id.email);
        TextView userBio = findViewById(R.id.biography);
        ImageView userProfilePicture = findViewById(R.id.profile_image);

        // Determine profile image based on gender
        String image = "@drawable/" + user.getGender().toLowerCase() + "_profile";
        int imageResource = getResources().getIdentifier(image, null, getPackageName());

        // If the image resource is not found, use the default image
        if (imageResource == 0) {
            image = "@drawable/default_profile";
            imageResource = getResources().getIdentifier(image, null, getPackageName());
        }

        // Set the profile image
        Drawable res = getResources().getDrawable(imageResource);
        userProfilePicture.setImageDrawable(res);

        // Set text for user details
        userName.setText(name);
        userGender.setText(gender);
        userEmail.setText(email);
        userBio.setText(biography);

        // Set up the navigation bar
        createNavigationBar();
    }

    /**
     * Creates the navigation bar with buttons to navigate to different activities.
     */
    public void createNavigationBar() {
        ImageButton edit = findViewById(R.id.edit); // Edit button
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start ProfileEditActivity
                Intent intent = new Intent(getBaseContext(), ProfileEditActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        ImageButton profile = findViewById(R.id.profile); // Profile button
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Refresh the current activity
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

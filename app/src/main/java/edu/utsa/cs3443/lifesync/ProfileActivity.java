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
    private User user;

    /**
     * Initializes the activity, setting up the profile information display and
     * the navigation bar.
     *
     * @param savedInstanceState The saved state of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        user = (User) getIntent().getSerializableExtra("user");
        String name = user.getName();
        String email = user.getEmail();
        String gender = user.getGender();
        String biography = user.getBiography();
        TextView userName = findViewById(R.id.name);
        TextView userGender = findViewById(R.id.gender);
        TextView userEmail = findViewById(R.id.email);
        TextView userBio = findViewById(R.id.biography);

        ImageView userProfilePicture = findViewById(R.id.profile_image);
        String image = "@drawable/" + user.getGender().toLowerCase() + "_profile";
        int imageResource = getResources().getIdentifier(image, null, getPackageName());
        // If the image resource is not found, use the default image
        if (imageResource == 0) {     // If not the image, use the default image
            image = "@drawable/default_profile";
            imageResource = getResources().getIdentifier(image, null, getPackageName());
        }
        Drawable res = getResources().getDrawable(imageResource);
        userProfilePicture.setImageDrawable(res);
        userName.setText(name);
        userGender.setText(gender);
        userEmail.setText(email);
        userBio.setText(biography);
        createNavigationBar();
    }

    /**
     * Creates the navigation bar with buttons to navigate to different activities.
     */
    public void createNavigationBar() {
        ImageButton edit = findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start ProfileEditActivity
                Intent intent = new Intent(getBaseContext(), ProfileEditActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

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

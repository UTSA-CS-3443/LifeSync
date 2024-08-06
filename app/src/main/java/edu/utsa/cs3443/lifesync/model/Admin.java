package edu.utsa.cs3443.lifesync.model;

import android.app.Activity;
import android.content.res.AssetManager;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
    private User user;

    public Admin(User user) {
        this.user = user;
    }
    public Admin(Activity activity) throws FileNotFoundException {
        this.user = LoadingUserAccount(activity);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User LoadingUserAccount(Activity activity) throws FileNotFoundException {
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

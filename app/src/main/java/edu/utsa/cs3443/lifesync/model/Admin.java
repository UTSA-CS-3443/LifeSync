package edu.utsa.cs3443.lifesync.model;

import android.app.Activity;
import android.content.res.AssetManager;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Represents an admin responsible for managing user accounts.
 */
public class Admin {
    private User user;
    /**
     * Constructs an Admin with the specified user.
     *
     * @param user the user associated with this admin
     */
    public Admin(User user) {
        this.user = user;
    }

    /**
     * Constructs an Admin by loading user account information from the assets folder.
     *
     * @param activity the activity context used to access the assets
     * @throws FileNotFoundException if the account information file is not found
     */
    public Admin(Activity activity) throws FileNotFoundException {
        this.user = LoadingUserAccount(activity);
    }

    /**
     * Returns the user associated with this admin.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user associated with this admin.
     *
     * @param user the user to be associated with this admin
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Loads user account information from the assets folder.
     *
     * @param activity the activity context used to access the assets
     * @return the loaded user or null if an error occurs
     * @throws FileNotFoundException if the account information file is not found
     */
    public User LoadingUserAccount(Activity activity) throws FileNotFoundException {
        // Get the AssetManager from the activity
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
                // Return the created User object
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

package edu.utsa.cs3443.lifesync;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import edu.utsa.cs3443.lifesync.model.User;

/**
 * The SecondActivity class allows users to create new widgets such as tasks, events, or notes.
 * It also sets up the navigation bar for navigating to other activities.
 */
public class SecondActivity extends AppCompatActivity {
    private User user; // User object representing the current user
    /**
     * Initializes the activity, setting up the widget creation form and the navigation bar.
     *
     * @param savedInstanceState The saved state of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        user = (User) getIntent().getSerializableExtra("user"); // Retrieve user object from intent
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widgetcreation_layout);
        EdgeToEdge.enable(this); // Enable edge-to-edge display

        RadioGroup typeSelector, repeatTaskChoice; // RadioGroups for selecting type and repeat options
        EditText title, addGuests, description, StartTime, reminderTime, location, date, repeatTime; // EditText fields for widget details
        LinearLayout detailsContainer; // Container for additional details

        // Initialize views
        title = findViewById(R.id.title);
        typeSelector = findViewById(R.id.type_selector);
        addGuests = findViewById(R.id.add_guests);
        description = findViewById(R.id.description);
        StartTime = findViewById(R.id.StartTime);
        reminderTime = findViewById(R.id.reminder_time);
        location = findViewById(R.id.location);
        date = findViewById(R.id.date);
        detailsContainer = findViewById(R.id.details_container);
        repeatTaskChoice = findViewById(R.id.repeatTaskChoice);
        repeatTime = findViewById(R.id.repeat_time);

        // Handle repeatTaskChoice RadioGroup
        repeatTaskChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int repeatOption) {
                if (repeatOption == R.id.Task_repeat) {
                    repeatTime.setVisibility(View.VISIBLE);
                } else {
                    repeatTime.setVisibility(View.GONE);
                }
            }
        });

        // Handle typeSelector RadioGroup
        typeSelector.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.task_radio_button) {
                    location.setVisibility(View.GONE);
                    detailsContainer.setVisibility(View.VISIBLE);
                    addGuests.setVisibility(View.GONE);
                    repeatTaskChoice.setVisibility(View.VISIBLE);
                    // Set repeatTime visibility based on repeatTaskChoice
                    if (repeatTaskChoice.getCheckedRadioButtonId() == R.id.Task_repeat) {
                        repeatTime.setVisibility(View.VISIBLE);
                    } else {
                        repeatTime.setVisibility(View.GONE);
                    }
                } else if (checkedId == R.id.event_radio_button) {
                    location.setVisibility(View.VISIBLE);
                    detailsContainer.setVisibility(View.VISIBLE);
                    addGuests.setVisibility(View.VISIBLE);
                    repeatTaskChoice.setVisibility(View.GONE);
                    repeatTime.setVisibility(View.GONE);
                } else if (checkedId == R.id.note_radio_button) {
                    detailsContainer.setVisibility(View.GONE);
                    addGuests.setVisibility(View.GONE);
                    repeatTaskChoice.setVisibility(View.GONE);
                    repeatTime.setVisibility(View.GONE);
                }
            }
        });

        // Handle complete button click
        Button completeButton = findViewById(R.id.complete_button);
        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedTypeId = typeSelector.getCheckedRadioButtonId();
                String newTitle = title.getText().toString().trim();
                String newDescription = description.getText().toString().trim();
                if (newTitle.equals("") && newDescription.equals("")) {
                    Toast.makeText(SecondActivity.this, "Unable to create an empty widget! Try again!", Toast.LENGTH_SHORT).show();
                } else {
                    if (selectedTypeId == R.id.task_radio_button) {
                        // Collect task attributes
                        int repeatChoice = repeatTaskChoice.getCheckedRadioButtonId();
                        String taskStartTime = StartTime.getText().toString().trim();
                        String taskDate = date.getText().toString().trim();
                        String taskReminderTime = reminderTime.getText().toString().trim();

                        if (repeatChoice == R.id.Task_repeat) {
                            String taskRepeatTime = repeatTime.getText().toString().trim();
                            handleTaskDataRepeat(user, newTitle, newDescription, taskDate, taskReminderTime, taskRepeatTime, taskStartTime);
                        } else {
                            // Handle task data
                            handleTaskDataNoRepeat(user, newTitle, newDescription, taskDate, taskReminderTime, taskStartTime);
                        }
                    } else if (selectedTypeId == R.id.event_radio_button) {
                        // Collect event attributes
                        String eventLocation = location.getText().toString().trim();
                        String eventDate = date.getText().toString().trim();
                        String eventGuests = addGuests.getText().toString().trim();
                        String eventStartTime = StartTime.getText().toString().trim();
                        String eventReminderTime = reminderTime.getText().toString().trim();

                        // Handle event data
                        handleEventData(user, newTitle, newDescription, eventLocation, eventGuests, eventDate, eventReminderTime, eventStartTime);
                    } else if (selectedTypeId == R.id.note_radio_button) {
                        // Handle note data
                        handleNoteData(newTitle, newDescription);
                    }
                }
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
                // Refresh the current activity
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
     * Handles task data for tasks that repeat.
     *
     * @param user              The user object.
     * @param title             The task title.
     * @param description       The task description.
     * @param taskDate          The task date.
     * @param reminderTimeBefore The reminder time before the task.
     * @param repeatTime        The repeat time for the task.
     * @param startTime         The start time of the task.
     */
    private void handleTaskDataRepeat(User user, String title, String description, String taskDate, String reminderTimeBefore, String repeatTime, String startTime) {
        if (dateFormatValidation(taskDate) && timeFormatValidation(startTime) && timeFormatValidation(reminderTimeBefore) && repeatDateFormatValidation(repeatTime)) {
            try {
                user.createNewTask(title, "", description, taskDate, reminderTimeBefore, repeatTime, startTime);
                Toast.makeText(this, "Number of widgets: " + user.getNumberOfWidget(), Toast.LENGTH_LONG).show();
                Toast.makeText(this, "Create new task successful", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "Failed to create task: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Invalid format of date or time, please enter mm/dd/yyyy or hh:mm", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Handles task data for tasks that do not repeat.
     *
     * @param user              The user object.
     * @param title             The task title.
     * @param description       The task description.
     * @param taskDate          The task date.
     * @param reminderTimeBefore The reminder time before the task.
     * @param startTime         The start time of the task.
     */
    private void handleTaskDataNoRepeat(User user, String title, String description, String taskDate, String reminderTimeBefore, String startTime) {
        if (dateFormatValidation(taskDate) && timeFormatValidation(startTime) && timeFormatValidation(reminderTimeBefore)) {
            try {
                user.createNewTask(title, "", description, taskDate, reminderTimeBefore, "", startTime);
                Toast.makeText(this, "Create new task successful", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(this, "Failed to create task: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Invalid format of date or time, please enter mm/dd/yyyy or hh:mm", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Handles event data.
     *
     * @param user              The user object.
     * @param title             The event title.
     * @param description       The event description.
     * @param address           The event address.
     * @param guests            The event guests.
     * @param eventDate         The event date.
     * @param reminderTimeBefore The reminder time before the event.
     * @param startTime         The start time of the event.
     */
    private void handleEventData(User user, String title, String description, String address, String guests, String eventDate, String reminderTimeBefore, String startTime) {
        if (dateFormatValidation(eventDate) && timeFormatValidation(startTime) && timeFormatValidation(reminderTimeBefore)) {
            try {
                user.createNewEvent(title, "", description, address, guests, eventDate, reminderTimeBefore, startTime);
                Toast.makeText(this, "Create new event successful", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(this, "Failed to create event: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Invalid format of date or time, please enter mm/dd/yyyy or hh:mm", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Handles note data.
     *
     * @param title       The note title.
     * @param description The note description.
     */
    private void handleNoteData(String title, String description) {
        try {
            user.createNewNote(title, "", description);
            Toast.makeText(this, "Create new note successful", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Failed to create note: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Validates the date format.
     *
     * @param date The date string.
     * @return true if the date is in the correct format, false otherwise.
     */
    private boolean dateFormatValidation(String date) {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        format.setLenient(false);
        try {
            format.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Validates the repeat date format.
     *
     * @param repeatDate The repeat date string.
     * @return true if all dates in the string are in the correct format, false otherwise.
     */
    private boolean repeatDateFormatValidation(String repeatDate) {
        String[] dates = repeatDate.split(",");
        for (String date : dates) {
            if (!dateFormatValidation(date)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Validates the time format.
     *
     * @param time The time string.
     * @return true if the time is in the correct format, false otherwise.
     */
    private boolean timeFormatValidation(String time) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        try {
            LocalTime.parse(time, format);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}

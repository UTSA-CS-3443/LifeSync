package edu.utsa.cs3443.lifesync;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.lifesync.model.Task;
import edu.utsa.cs3443.lifesync.model.User;

public class SecondActivity extends AppCompatActivity {
    private User user;
    private RadioGroup typeSelector, repeatTaskChoice;
    private EditText title, addGuests, description, time, location, date, repeatTime;
    private LinearLayout detailsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        user= (User) getIntent().getSerializableExtra("user");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_layout);

        // Initialize views
        title = findViewById(R.id.title);
        typeSelector = findViewById(R.id.type_selector);
        addGuests = findViewById(R.id.add_guests);
        description = findViewById(R.id.description);
        time = findViewById(R.id.time);
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
        Button completeButton = findViewById(R.id.complete_button);
        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedTypeId = typeSelector.getCheckedRadioButtonId();
                String newTitle = title.getText().toString().trim();
                String newDescription = description.getText().toString().trim();

                if (selectedTypeId == R.id.task_radio_button) {
                    // Collect task attributes
                    int repeatChoice = repeatTaskChoice.getCheckedRadioButtonId();
                    String taskTime = time.getText().toString().trim();
                    String taskLocation = location.getText().toString().trim();
                    String taskDate = date.getText().toString().trim();

                    if( repeatChoice == R.id.Task_repeat){
                        String taskRepeatTime = repeatTime.getText().toString().trim();
                        handleTaskDataRepeat(newTitle, newDescription, taskTime, taskLocation, taskDate, taskRepeatTime);
                    }

                    else {
                        // Handle task data
                        handleTaskDataNoRepeat(newTitle, newDescription, taskTime, taskLocation, taskDate);
                    }
                } else if (selectedTypeId == R.id.event_radio_button) {
                    // Collect event attributes
                    String eventTime = time.getText().toString().trim();
                    String eventLocation = location.getText().toString().trim();
                    String eventDate = date.getText().toString().trim();
                    String eventGuests = addGuests.getText().toString().trim();

                    // Handle event data
                    handleEventData(newTitle, newDescription, eventTime, eventLocation, eventDate, eventGuests);

                } else if (selectedTypeId == R.id.note_radio_button) {
                    // Handle note data
                    handleNoteData(newTitle, newDescription);
                }
            }
        });
    }

    private void handleTaskDataRepeat(String title, String description, String time, String location, String date, String repeatTime) {
        // Handle task data
//        Task task = new Task(title, description, time, location, date, repeatTime);
//        user.addWidget();
        Toast.makeText(this, "Repeat Task saved: " + title , Toast.LENGTH_SHORT).show();
    }

    private void handleTaskDataNoRepeat(String title, String description, String time, String location, String date) {
        // Handle task data

        Toast.makeText(this, "Non Repeat Task saved: " + title , Toast.LENGTH_SHORT).show();
    }

    private void handleEventData(String title, String description, String time, String location, String date, String guests) {
        //Handle event data
        Toast.makeText(this, "Event saved: " + title, Toast.LENGTH_SHORT).show();
    }

    private void handleNoteData(String title, String description) {
        //Handle note data
        Toast.makeText(this, "Note saved: " + title, Toast.LENGTH_SHORT).show();
    }
    
}

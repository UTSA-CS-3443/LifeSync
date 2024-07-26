package edu.utsa.cs3443.lifesync;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private RadioGroup typeSelector, repeatTaskChoice;
    private EditText addGuests, description, time, location, date, repeatTime;
    private LinearLayout detailsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_layout);


        typeSelector = findViewById(R.id.type_selector);
        addGuests = findViewById(R.id.add_guests);
        description = findViewById(R.id.description);
        time = findViewById(R.id.time);
        location = findViewById(R.id.location);
        date = findViewById(R.id.date);
        detailsContainer = findViewById(R.id.details_container);
        repeatTaskChoice = findViewById(R.id.repeatTaskChoice);
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
        repeatTime = findViewById(R.id.repeat_time);


        typeSelector.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.task_radio_button) {
                    detailsContainer.setVisibility(View.VISIBLE);
                    addGuests.setVisibility(View.VISIBLE);
                    repeatTaskChoice.setVisibility(View.VISIBLE);



                }else if(checkedId == R.id.event_radio_button){
                    detailsContainer.setVisibility(View.VISIBLE);
                    addGuests.setVisibility(View.VISIBLE);
                    repeatTaskChoice.setVisibility(View.GONE);
                    repeatTime.setVisibility(View.GONE);
                }
                else if (checkedId == R.id.note_radio_button) {
                    detailsContainer.setVisibility(View.GONE);
                    addGuests.setVisibility(View.GONE);
                    repeatTaskChoice.setVisibility(View.GONE);
                    repeatTime.setVisibility(View.GONE);


                }
            }
        });
    }
}
package edu.utsa.cs3443.lifesync;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private RadioGroup typeSelector;
    private EditText addGuests, description, time, location, date;
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

        
        typeSelector.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.note_radio_button) {
                    detailsContainer.setVisibility(View.GONE);
                    addGuests.setVisibility(View.GONE);
                } else {
                    detailsContainer.setVisibility(View.VISIBLE);
                    addGuests.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
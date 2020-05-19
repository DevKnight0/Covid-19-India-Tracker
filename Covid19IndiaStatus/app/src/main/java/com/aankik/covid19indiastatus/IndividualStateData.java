package com.aankik.covid19indiastatus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.NumberFormat;
import java.util.Objects;

public class IndividualStateData extends AppCompatActivity {
    TextView perStateConfirmed, perStateActive,
            perStateDeceased, perStateNewConfirmed,
            perStateNewRecovered, perStateNewDeceased,
            perStateNewActive, perStateRecovered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_state_data);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String stateName = intent.getStringExtra("STATE_NAME");
        String stateConfirmed = intent.getStringExtra("STATE_CONFIRMED");
        String stateActive = intent.getStringExtra("STATE_ACTIVE");
        String stateDeceased = intent.getStringExtra("STATE_DECEASED");
        String stateNewConfirmed = intent.getStringExtra("STATE_NEW_CONFIRMED");
        String stateNewRecovered = intent.getStringExtra("STATE_NEW_RECOVERED");
        String stateNewDeceased = intent.getStringExtra("STATE_NEW_RECOVERED");
        String stateRecovery = intent.getStringExtra("STATE_RECOVERED");


        perStateConfirmed = findViewById(R.id.perstate_confirmed_textview);
        perStateActive = findViewById(R.id.perstate_active_textView);
        perStateRecovered = findViewById(R.id.perstate_recovered_textView);
        perStateDeceased = findViewById(R.id.perstate_death_textView);
        perStateNewConfirmed = findViewById(R.id.perstate_confirmed_new_textView);
        perStateNewRecovered = findViewById(R.id.perstate_recovered_new_textView);
        perStateNewDeceased = findViewById(R.id.perstate_death_new_textView);
        perStateNewActive= findViewById(R.id.perstate_active_new_textView);

        Objects.requireNonNull(getSupportActionBar()).setTitle(stateName);
        perStateConfirmed.setText(stateConfirmed);
        perStateActive.setText(stateActive);
        perStateDeceased.setText(stateDeceased);
        perStateNewConfirmed.setText("+" + stateNewConfirmed);
        perStateNewRecovered.setText("+" + stateNewRecovered);
        perStateNewDeceased.setText("+" + stateNewDeceased);
        perStateRecovered.setText(stateRecovery);
        int newActive = (Integer.parseInt(stateNewConfirmed)) - ((Integer.parseInt(stateNewRecovered))
                + Integer.parseInt(stateNewDeceased));
        perStateNewActive.setText("+"+ NumberFormat.getInstance().format(newActive));

    }

}

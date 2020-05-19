package com.aankik.covid19indiastatus;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;

public class FrontView extends AppCompatActivity  implements ExtractJsonData.OnDataAvaialabe{

    private List<StateDataModel> stateDataModels;

    TextView indiaConfirmed, indiaActive, indiaDeceased, indiaNewConfirmed,
            indiaNewRecovered, indiaNewDeceased, indiaRecovered,indiaNewActice;

    Button stateData;
    @Override
    protected void onResume() {
        super.onResume();
        ExtractJsonData extractJsonData= new ExtractJsonData(this);
        extractJsonData.execute("https://api.covid19india.org/data.json");

        stateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), PerStateDataList.class);
                intent.putExtra("stateDataModels", (Serializable) stateDataModels);
                startActivity(intent);
            }
        });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Covid-19 India");
        indiaConfirmed = findViewById(R.id.India_confirmed_textview);
        indiaActive = findViewById(R.id.India_active_textView);
        indiaRecovered = findViewById(R.id.India_recovered_textView);
        indiaDeceased = findViewById(R.id.India_death_textView);
        indiaNewConfirmed = findViewById(R.id.India_confirmed_new_textView);
        indiaNewRecovered = findViewById(R.id.India_recovered_new_textView);
        indiaNewDeceased = findViewById(R.id.India_death_new_textView);
        indiaNewActice= findViewById(R.id.India_active_new_textView);
        stateData= findViewById(R.id.state_data);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.refresh){
            Toast.makeText(FrontView.this,"Refreshed Data",Toast.LENGTH_LONG).show();

            onResume();
        }

        else{
            Toast.makeText(FrontView.this,"error",Toast.LENGTH_LONG).show();

        }
       return super.onOptionsItemSelected(item);


    }

    @Override
    public void onDataAvailable(List<StateDataModel> data) {
        stateDataModels=data;
        indiaConfirmed.setText(data.get(0).getConfirmed());
        indiaActive.setText(data.get(0).getActive());
        indiaDeceased.setText(data.get(0).getDeceased());
        indiaNewConfirmed.setText("+" + data.get(0).getNewConfirmed());
        indiaNewRecovered.setText("+" + data.get(0).getNewRecovered());
        indiaNewDeceased.setText("+" + data.get(0).getNewDeceased());
        indiaRecovered.setText(data.get(0).getRecovered());
        int newActive = (Integer.parseInt(data.get(0).getNewConfirmed())) - ((Integer.parseInt(data.get(0).getNewRecovered()))
                + Integer.parseInt(data.get(0).getNewDeceased()));
        indiaNewActice.setText("+"+ NumberFormat.getInstance().format(newActive));


        data.remove(0);
//        mJsonData.loadNewData(data);
    }
}

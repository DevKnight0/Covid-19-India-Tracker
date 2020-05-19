package com.aankik.covid19indiastatus;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PerStateDataList extends AppCompatActivity  {
    private List<StateDataModel> stateDataModels;
    private Covid19Adapter mJsonData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        stateDataModels= (List<StateDataModel>) intent.getSerializableExtra("stateDataModels");

        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.cnt_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mJsonData= new Covid19Adapter(stateDataModels,this);
        recyclerView.setAdapter(mJsonData);
    }



    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.refresh) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

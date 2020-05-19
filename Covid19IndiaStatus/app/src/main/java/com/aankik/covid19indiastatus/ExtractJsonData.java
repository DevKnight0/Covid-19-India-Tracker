package com.aankik.covid19indiastatus;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class ExtractJsonData extends AsyncTask<String,Void, List<StateDataModel>> implements GetRawData.OnDownloadComplete {

interface OnDataAvaialabe {
    void onDataAvailable(List<StateDataModel> data);
}

    private List<StateDataModel> mStateDataModel = null;
    private  List<StateDataModel> frontView=null;
//    private String mBaseUrl;
    private final OnDataAvaialabe mCallBack;
    private static final String TAG = "ExtractJsonData";

    public ExtractJsonData(OnDataAvaialabe callback) {
        this.mCallBack= callback;
//        this.mBaseUrl = mBaseUrl;
    }

    @Override
    protected List<StateDataModel> doInBackground(String... strings) {
        String destinationUri= createUri(strings[0]);
        Log.d(TAG, "doInBackground: destination url is "+destinationUri);

        GetRawData getRawData =new GetRawData(this);
//        getRawData.execute(destinationUri);
//        becoz of asynctask the thread is already running on background thread so it wont let us create new thread
        getRawData.runInSameThread(destinationUri);
        Log.d(TAG, "doInBackground: ends");
        return mStateDataModel;
    }

    @Override
    protected void onPostExecute(List<StateDataModel> model) {

        if(mCallBack!=null){
            mCallBack.onDataAvailable(model);
        }
    }



    @Override
    public void onDownloadComplete(String data) {

        mStateDataModel = new ArrayList<>();

        try {
            Log.d(TAG, "onDownloadComplete: data "+ data);
            JSONObject jsonData = new JSONObject(data);
            JSONArray jsonArray = jsonData.getJSONArray("statewise");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject statewise = jsonArray.getJSONObject(i);

                    String stateName = statewise.getString("state");
                    String stateConfirmed = statewise.getString("confirmed");
                    String stateActive = statewise.getString("active");
                    String stateDeceased = statewise.getString("deaths");
                    String stateRecovered = statewise.getString("recovered");
                    String stateNewConfirmed = statewise.getString("deltaconfirmed");
                    String stateNewRecovered = statewise.getString("deltarecovered");
                    String stateNewDeceased = statewise.getString("deltadeaths");
                    String stateLastUpdate = statewise.getString("lastupdatedtime");

                    mStateDataModel.add(new StateDataModel(stateName,stateConfirmed,stateActive,stateDeceased,stateNewConfirmed,stateNewRecovered,stateNewDeceased
                                            ,stateLastUpdate,stateRecovered));

                Log.d(TAG, "onDownloadComplete: Data is "+ mStateDataModel.toString());

            }



        } catch (JSONException e) {
            e.printStackTrace();

        }


    }

    private String createUri(String search) {
        Log.d(TAG, "createURL: "+ search);
        return Uri.parse(search).buildUpon()
                .build().toString();
    }
}

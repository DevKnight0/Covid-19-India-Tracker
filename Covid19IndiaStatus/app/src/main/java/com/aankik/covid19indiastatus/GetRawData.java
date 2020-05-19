package com.aankik.covid19indiastatus;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class GetRawData extends AsyncTask<String, Void,String>  {

    interface OnDownloadComplete {
        void onDownloadComplete(String data);
    }
    private OnDownloadComplete mCallback;
    private static final String TAG = "GetRawData";
    public GetRawData(OnDownloadComplete mCallback) {
        this.mCallback = mCallback;
    }

    @Override
    protected void onPostExecute(String s) {
        if(mCallback!=null)
            mCallback.onDownloadComplete(s);
    }

    public void runInSameThread(String s){
        onPostExecute(doInBackground(s));
    }
    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection connection=null;
        BufferedReader reader=null;

        if(strings==null){
            return null;
        }

        try{

            URL url=new URL(strings[0]);
            Log.d(TAG, "doInBackground: url is*** "+url.toString());
            connection=(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");//type of result we want ,Default is get
            connection.connect();
            int responseCode=connection.getResponseCode();
            Log.d(TAG, "doInBackground: resposecode is: " +responseCode);
            StringBuilder result=new StringBuilder();
            reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while(null!=(line=reader.readLine())){
                result.append(line+'\n');
            }
            return result.toString();

        }catch (MalformedURLException e){
            Log.e(TAG, "doInBackground: Invalid Url"+e.getMessage() );
        }catch (IOException e){
            Log.e(TAG, "doInBackground: IO Exception Reading Data"+e.getMessage());
        }catch (SecurityException e){
            Log.e(TAG, "doInBackground: Security Exception Needs Permission"+e.getMessage());
        }finally {
            if(connection!=null){
                connection.disconnect();
            }
            if(reader!=null){
                try {
                    reader.close();
                }catch (IOException e){
                    Log.e(TAG, "doInBackground: Error Closing Stream"+e.getMessage() );
                }
            }
        }

        return null;
    }


}

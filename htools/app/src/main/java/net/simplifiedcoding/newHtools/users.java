package net.simplifiedcoding.newHtools;



import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.net.SocketFactory;

import me.legrange.mikrotik.ApiConnection;


public class users {


    final   String LOG_TAG = "mLog";
    public  List<Map<String, String>> result = null;
    public  ArrayList arrayList = new ArrayList();
    public  String count ="";
    MyTask  mt;

    public String comandoRb(){
        mt = new MyTask();
        mt.execute();
        System.out.println("USER" + count);
        System.out.println("comando" + result);
        return count;
    }
    class MyTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(LOG_TAG, "startOn");
            //tvResult.setText("Begin");
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                try {
                    Log.d(LOG_TAG, "start");


                    ApiConnection con = ApiConnection.connect(SocketFactory.getDefault(), Config.HOST, ApiConnection.DEFAULT_PORT, 200);

                    Log.d(LOG_TAG, "start2");
                    con.login(Config.USERNAME, Config.PASSWORD);

                    if (con.isConnected()) {
                        //tvResult.setText("OK!");
                        Log.d(LOG_TAG, "isConnected");
                    }



                    result = con.execute("/ip/hotspot/active/print count-only");
                    //result = con.execute("/ip/arp/print");


                    for (Map<String, String> res : result) {

                        System.out.println("ATIVOS " + res.values());
                        arrayList.add(count);
                        count = res.values().toString();
                    }
                    System.out.println(count);
                    con.close();
                } catch (Exception e) {
                    Log.d(LOG_TAG, "error");
                    Log.d(LOG_TAG, e.getMessage());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            System.out.println("FINAL " +  count);
            System.out.println("FINAL " +  result);
            Log.d(LOG_TAG, "FIM");
        }
    }

}

package net.simplifiedcoding.newHtools;



import android.os.AsyncTask;
import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.net.SocketFactory;

import me.legrange.mikrotik.ApiConnection;


public class users {


    final  String LOG_TAG = "mLog";
    MyTask mt;

    public void comandoRb(){
        mt = new MyTask();
        mt.execute();
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

                List<Map<String, String>> result = null;

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

                    String count ="";
                    for (Map<String, String> res : result) {

                        System.out.println("ATIVOS " + res.values());
                        System.out.println("ATIVOSS " + res.values().toString());
//                        Log.d("RESU",res.toString());
//                        count = res.get(1).toString();
//                        Log.d("RESU0",res.get(0));
//                        Log.d("RESU1",res.get(1));
//                        Log.d("RESU0STRIN",res.get(0).toString());
//                        Log.d("RESU1TRING",res.get(1).toString());

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
            Log.d(LOG_TAG, "FIM");
        }
    }

}

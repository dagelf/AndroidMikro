package net.simplifiedcoding.newHtools;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import net.simplifiedcoding.bottomnavigationexample.R;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.SocketFactory;

import me.legrange.mikrotik.ApiConnection;

public class DashboardFragment extends Fragment {
    //ListView listView;

    final   String LOG_TAG = "mLog";
    public List<Map<String, String>> result = null;
    public List<Map<String, String>> result1 = null;
    public  ArrayList arrayList = new ArrayList();
    public  String count ="";
    public TextView txtOn;
    public TextView txtCad;
    MyTask mt;
    MyTask mt2;

    ArrayList<Pessoa> pessoas= new ArrayList<>();
    DashAdapter dashAdapter;
    private Activity activity;
    String resultado;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vDash = inflater.inflate(R.layout.fragment_dashboard, container, false);
         txtOn = (TextView) vDash.findViewById(R.id.texOnline);
         txtCad = (TextView) vDash.findViewById(R.id.txtCad);
         mt = new MyTask();
         mt2 = new MyTask();
         mt2.execute();
        return vDash;
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
                    ApiConnection con1 = ApiConnection.connect(SocketFactory.getDefault(), Config.HOST, ApiConnection.DEFAULT_PORT, 200);

                    Log.d(LOG_TAG, "start2");
                    con.login(Config.USERNAME, Config.PASSWORD);
                    con1.login(Config.USERNAME, Config.PASSWORD);

                    if (con.isConnected()) {
                        //tvResult.setText("OK!");
                        Log.d(LOG_TAG, "isConnected");

                        result = con.execute("/ip/hotspot/active/print count-only");
                        for (Map<String, String> res : result) {

                            System.out.println("ATIVOS " + res.values());
                            txtOn.setText(res.values().toString());

                        }
                    }

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
    class MyTask2 extends AsyncTask<Void,Void,Void> {
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

                        result = con.execute("/ip/hotspot/user/print count-only");
                        for (Map<String, String> res : result) {

                            System.out.println("cadas " + res.values());
                            txtCad.setText(res.values().toString());

                        }
                    }

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

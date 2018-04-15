package br.com.ineedsolutions.androidmikro;
import me.legrange.mikrotik.*;
import me.legrange.mikrotik.ApiConnection;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

import java.lang.Object;
import java.util.List;
import java.util.Map;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button btnConnect;
    final  String LOG_TAG = "mLog";
    MyTask mt;



    private Connection conClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnConnect = findViewById(R.id.button);
       btnConnect.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mt = new MyTask();
               mt.execute();
               Toast.makeText(getApplicationContext(), "Exemplo Toast", Toast.LENGTH_SHORT).show();
           }
       });

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


                    ApiConnection con = ApiConnection.connect(SocketFactory.getDefault(),Config.HOST,ApiConnection.DEFAULT_PORT,200);

                    Log.d(LOG_TAG, "start2");
                    con.login(Config.USERNAME, Config.PASSWORD);

                    if (con.isConnected()) {
                        //tvResult.setText("OK!");
                        Log.d(LOG_TAG, "isConnected");
                    }
                    result = con.execute("/interface/print");
                    for (Map<String, String> res : result) {
                        Log.d(LOG_TAG, res.toString());
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
           Log.d(LOG_TAG,"FIM");
        }


    }
}





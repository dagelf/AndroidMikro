package net.simplifiedcoding.newHtools;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.simplifiedcoding.bottomnavigationexample.R;

import javax.net.SocketFactory;

import me.legrange.mikrotik.ApiConnection;


public class users {

    private FirebaseAuth mAuth;
    private EditText editEmail;
    private EditText editSenha;
    private EditText editNome;
    private EditText editFone;
    private EditText editData;
    private Button btnSalvar;

    Button btnConnect;
    final  String LOG_TAG = "mLog";
    MyTask mt;








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
                    final String usuario = editEmail.getText().toString().trim();
                    final String senha = editSenha.getText().toString().trim();
                    final String nome = editNome.getText().toString().trim();
                    System.out.println("VAI");
                    System.out.println(nome);
                    System.out.println(usuario);
                    System.out.println(senha);

                    result = con.execute("/ip/hotspot/user/add name="+usuario+" password="+senha);
                    //result = con.execute("/ip/arp/print");
                    System.out.println(result);
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
            Log.d(LOG_TAG, "FIM");
        }
    }

}

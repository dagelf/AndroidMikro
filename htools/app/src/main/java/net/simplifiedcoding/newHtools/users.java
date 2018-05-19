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


public class users extends Fragment {

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





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vUser = inflater.inflate(R.layout.fragment_users, container, false);
        mAuth = FirebaseAuth.getInstance();
        editEmail = vUser.findViewById(R.id.cadEmail);
        editNome = vUser.findViewById(R.id.cadNome);
        editSenha = vUser.findViewById(R.id.cadSenha);
        editData = vUser.findViewById(R.id.cadData);
        btnSalvar = (Button) vUser.findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d(LOG_TAG, "ANTES TOAST");
                Toast toast = Toast.makeText(getView().getContext(), "Clickko - "+editNome.getText().toString().trim(),Toast.LENGTH_LONG);
                toast.show();
                mt = new MyTask();
                mt.execute();
                  salvar();

            }
        });
        return vUser;
    }

    private void salvar(){

        final String usuario = editEmail.getText().toString().trim();
        String senha = editSenha.getText().toString().trim();
        String nome = editNome.getText().toString().trim();

        String data = editData.getText().toString().trim();

        if(usuario.equals("")){
            editEmail.setError("Preencha este Campo !");
            editEmail.requestFocus();
            return;
        }
        if(senha.equals("")){
            editSenha.setError("Preencha este Campo !");
            editSenha.requestFocus();
            return;
        }
        if(nome.equals("")){
            editSenha.setError("Preencha este Campo !");
            editSenha.requestFocus();
            return;
        }


        mAuth.createUserWithEmailAndPassword(usuario, senha)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            FirebaseDatabase database = FirebaseDatabase.getInstance();
//                            DatabaseReference userRef = database.getReference("users/" + user.getUid());
//
//                            Map<String, Object> userInfos = new HashMap<>();
//                            userInfos.put("usuario",usuario);
//                            userInfos.put("email",usuario);
//                            userRef.setValue(userInfos);
//                            //finish();

                        }else{
                            try{
                                throw  task.getException();

                            }catch (FirebaseAuthWeakPasswordException e){
                                editSenha.setError("Senha Fraca");
                                editSenha.requestFocus();
                            }catch (FirebaseAuthInvalidCredentialsException e){
                                editEmail.setError("Email invalido !");
                                editEmail.requestFocus();
                            }catch (FirebaseAuthUserCollisionException e){
                                editEmail.setError("Email ja existe !");
                                editEmail.requestFocus();
                            }catch (Exception e){
                                Log.e("Cadastro", e.getMessage());
                            }
                        }
                    }

                });

//        mAuth.createUserWithEmailAndPassword(usuario,senha)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//
//                });
//
//        Toast toast = Toast.makeText(getView().getContext(), "Clickko - "+nome,Toast.LENGTH_LONG);
//        toast.show();
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
            Log.d(LOG_TAG, "FIM");
        }
    }







}

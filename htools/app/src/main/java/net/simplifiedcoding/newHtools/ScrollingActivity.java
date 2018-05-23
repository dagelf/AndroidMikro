package net.simplifiedcoding.newHtools;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import net.simplifiedcoding.bottomnavigationexample.R;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ScrollingActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText editEmail;
    private EditText editSenha;
    private EditText editNome;
    private RadioGroup editSexo;
    private EditText editData;
    private EditText editDown;
    private EditText editUp;
    private CheckBox ativo;

    final  String LOG_TAG = "mLog";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editEmail = findViewById(R.id.cadEmail);
        editNome = findViewById(R.id.cadNome);
        editSenha = findViewById(R.id.cadSenha);
        editData = findViewById(R.id.cadData);
        editSexo = findViewById(R.id.radioGroup2);
        editDown = findViewById(R.id.cadDown);
        editUp = findViewById(R.id.cadUp);
        ativo = findViewById(R.id.checkAtivo);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                salvar();
            }
        });
    }


    private void salvar(){

        final String nome = editNome.getText().toString().trim();
        final String usuario = editEmail.getText().toString().trim();
        final String senha = editSenha.getText().toString().trim();
        final String data = editData.getText().toString();

        Toast toast = Toast.makeText(this,nome+usuario+senha+data,Toast.LENGTH_LONG);
        toast.show();



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
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
//                            mt = new users.MyTask();
//                            mt.execute();
//                            Toast toast = Toast.makeText(getView().getContext(), "Usuario Cadastrado com Sucesso ! - "+editNome.getText().toString().trim(),Toast.LENGTH_LONG);
//                            toast.show();

                            FirebaseUser user = mAuth.getCurrentUser();
                            FirebaseDatabase database = FirebaseDatabase.getInstance();


                            DatabaseReference userRef = database.getReference("users/" + user.getUid());

                            Map<String, Object> userInfos = new HashMap<>();
                            userInfos.put("usuario",nome);
                            userInfos.put("email",usuario);
                            userRef.setValue(userInfos);
                            finish();

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

    }
}

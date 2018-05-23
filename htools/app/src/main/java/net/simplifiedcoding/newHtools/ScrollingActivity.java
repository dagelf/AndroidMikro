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
import android.widget.ListView;
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
    private RadioButton Masc;
    private RadioButton Fem;
    private EditText editData;
    private EditText editDown;
    private EditText editUp;
    private CheckBox checkAtivo;
    String sexo;
    String ativo = "N";

    final  String LOG_TAG = "mLog";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();
        editEmail = findViewById(R.id.cadEmail);
        editNome = findViewById(R.id.cadNome);
        editSenha = findViewById(R.id.cadSenha);
        editData = findViewById(R.id.cadData);
        Masc = (RadioButton) findViewById(R.id.radioMas);
        Fem = (RadioButton) findViewById(R.id.radioFem);
        editDown = findViewById(R.id.cadDown);
        editUp = findViewById(R.id.cadUp);
        checkAtivo = findViewById(R.id.checkAtivo);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Masc.isChecked()) {
                    sexo = "M";

                }if (Fem.isChecked()) {
                    sexo = "F";
                }
                if (checkAtivo.isChecked()){
                    ativo="S";
                }

                salvar();

            }
        });
    }

    private void salvar(){

        final String nome = editNome.getText().toString().trim();
        final String usuario = editEmail.getText().toString().trim();
        final String senha = editSenha.getText().toString().trim();
        final String data = editData.getText().toString();
        final String sexocheck = sexo.toString();
        final String ativocheck = ativo.toString();
        final String download = editDown.getText().toString().trim();
        final String upload = editUp.getText().toString().trim();
        if(nome.equals("")){
            editNome.setError("Preencha este Campo !");
            editNome.requestFocus();
            return;
        }

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
        FirebaseUser user = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userRef = database.getReference("users/" + user.getUid());

        Map<String, Object> userInfos = new HashMap<>();
        userInfos.put("usuario",nome);
        userInfos.put("email",usuario);
        userInfos.put("senha",senha);
        userInfos.put("data",data);
        userInfos.put("sexo",sexocheck);
        userInfos.put("status",ativocheck);
        userInfos.put("download",download);
        userInfos.put("upload",upload);
        userRef.setValue(userInfos);
        finish();
        Toast toast = Toast.makeText(this,"Usuario Cadastrado !",Toast.LENGTH_LONG);
        toast.show();

    }
}

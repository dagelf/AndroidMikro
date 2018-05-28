package net.simplifiedcoding.newHtools;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import net.simplifiedcoding.bottomnavigationexample.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.net.SocketFactory;

import me.legrange.mikrotik.ApiConnection;


public class UpdateUser extends AppCompatActivity {
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
    Pessoa pessoa;
    String sexo;
    String enable = "yes";
    String ativo = "Bloqueado";
    String uuid;
    MyTask mt;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    final  String LOG_TAG = "mLog";
//    public UpdateUser(Pessoa pessoa){
//        this.pessoa = pessoa;
//    }

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
        //this.getIntent().getStringExtra("infos");

        ///
        editNome.setText(this.getIntent().getStringExtra("usuario"));
        editEmail.setText(this.getIntent().getStringExtra("email"));
        editDown.setText(this.getIntent().getStringExtra("download"));
        editUp.setText(this.getIntent().getStringExtra("upload"));
        editSenha.setText(this.getIntent().getStringExtra("senha"));
        editData.setText(this.getIntent().getStringExtra("data"));
        String sexo1;
        String ativo1;
        sexo1 = this.getIntent().getStringExtra("sexo");
        ativo1 = this.getIntent().getStringExtra("status");
        uuid = this.getIntent().getStringExtra("uuid");



        if (sexo1.trim().equals("M")){
            Masc.setChecked(true);
        }else{
            Fem.setChecked(true);
        }

        if(ativo1.trim().equals("Ativo")){
            checkAtivo.setChecked(true);
        }

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
                    ativo="Ativo";
                    enable = "no";

                }else{
                    enable="yes";
                }

                salvar();



            }
        });
    }

    private void salvar(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
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


        Pessoa pp = new Pessoa();
        pp.setUid(uuid);
        pp.setDownload(download);
        pp.setUpload(upload);
        pp.setUsuario(nome);
        pp.setEmail(usuario);
        pp.setData(data);
        pp.setSexo(sexocheck);
        pp.setStatus(ativocheck);
        pp.setSenha(senha);

         databaseReference.child("users").child(pp.getUid()).setValue(pp);

        Toast.makeText(this, "Atualizar", Toast.LENGTH_SHORT).show();


//        String uuid;
//        uuid = UUID.randomUUID().toString();
//        FirebaseUser user = mAuth.getCurrentUser();
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference userRef = database.getReference("users/" + uuid);
//
//        Map<String, Object> userInfos = new HashMap<>();
//        userInfos.put("usuario",nome);
//        userInfos.put("email",usuario);
//        userInfos.put("senha",senha);
//        userInfos.put("data",data);
//        userInfos.put("sexo",sexocheck);
//        userInfos.put("status",ativocheck);
//        userInfos.put("download",download);
//        userInfos.put("upload",upload);
//        userInfos.put("uid",uuid);
//        userRef.setValue(userInfos);
        mt = new MyTask();
        mt.execute();
        finish();
        Toast toast = Toast.makeText(this,"Usuario Atualizado !",Toast.LENGTH_LONG);
        toast.show();

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
                    final String usuario = editEmail.getText().toString().trim();
                    final String senha = editSenha.getText().toString().trim();
                    final String nome = editNome.getText().toString().trim();
                    final String down = editDown.getText().toString().trim();
                    final String up = editUp.getText().toString().trim();
                    final String status = enable;



                    System.out.println(nome);
                    System.out.println(usuario);
                    System.out.println(senha);
                    System.out.println(status);
                    System.out.println(down);
                    System.out.println(up);


                    result = con.execute("/ip hotspot/user/disable/numbers=teste");
                    //result = con.execute("/ip/hotspot/user/set password=" + senha + " disabled="+status + " limit-bytes-out="+up+ " limit-bytes-in=" + down  +" " + usuario);
                    System.out.println("aki " +result.toString());

// mt = new MyTask();
//                    mt.execute();
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

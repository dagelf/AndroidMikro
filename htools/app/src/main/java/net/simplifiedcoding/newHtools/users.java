package net.simplifiedcoding.newHtools;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import net.simplifiedcoding.bottomnavigationexample.R;



public class users extends Fragment {

    private FirebaseAuth mAuth;
    private EditText editEmail;
    private EditText editSenha;
    private EditText editNome;
    private Button btnSalvar;





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vUser = inflater.inflate(R.layout.fragment_users, container, false);
        mAuth = FirebaseAuth.getInstance();
        editEmail = vUser.findViewById(R.id.cadEmail);
        editNome = vUser.findViewById(R.id.cadNome);
        editSenha = vUser.findViewById(R.id.cadSenha);
        btnSalvar = (Button) vUser.findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });
        return vUser;
    }

    private void salvar(){


        Toast toast = Toast.makeText(getView().getContext(), "Clickko",Toast.LENGTH_LONG);
        toast.show();
    }



}

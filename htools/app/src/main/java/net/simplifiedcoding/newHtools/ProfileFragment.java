package net.simplifiedcoding.newHtools;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import net.simplifiedcoding.bottomnavigationexample.R;


import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class ProfileFragment extends Fragment {
    EditText editDown, editUp,editNome,editTimeout;
    ListView listV_dados;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private List<Perfis> listPerfil = new ArrayList<Perfis>();
    private ArrayAdapter<Perfis> arrayAdapterPerfil;
    private ArrayList<String> perfils;


    Perfis perfilSelecionada;
    /// menu
    private FirebaseAuth mAuth;
    Menu menu;
    View vProfile;
    public ProfileFragment(){

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         vProfile = inflater.inflate(R.layout.fragment_profile, container, false);
         editNome = (EditText) vProfile.findViewById(R.id.editNome);
         editDown = (EditText) vProfile.findViewById(R.id.editDonwload);
         editUp = (EditText) vProfile.findViewById(R.id.editUpload);
         editTimeout = (EditText) vProfile.findViewById(R.id.editTimeout);
         listV_dados = (ListView) vProfile.findViewById(R.id.listV_dados) ;
       // inicializarFirebase();
        perfils = new ArrayList<>();

        eventoDatabase();
        listV_dados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                perfilSelecionada = (Perfis) parent.getItemAtPosition(position);
                editDown.setText(perfilSelecionada.getDownload());
                editUp.setText(perfilSelecionada.getUpload());
            }
        });


        return vProfile;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_novo:
                Perfis p = new Perfis();
                p.setUid(UUID.randomUUID().toString());
                p.setNome(editNome.getText().toString().trim());
                p.setTimeout(editTimeout.getText().toString().trim());
                p.setDownload(editDown.getText().toString().trim());
                p.setUpload(editUp.getText().toString().trim());

                databaseReference.child("perfil").child(p.getUid()).setValue(p);
                limparCampos();



                limparCampos();
                Toast.makeText(getActivity(), "Novo Perfil" , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_atualiza:

                Perfis pp = new Perfis();
                pp.setUid(perfilSelecionada.getUid());
                pp.setDownload(editDown.getText().toString().trim());
                pp.setUpload(editUp.getText().toString().trim());
                pp.setNome(editNome.getText().toString().trim());
                pp.setTimeout(editTimeout.getText().toString().trim());
                databaseReference.child("perfil").child(pp.getUid()).setValue(pp);
                limparCampos();
                Toast.makeText(getActivity(), "Atualizar", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_deleta:
                Perfis pd = new Perfis();
                pd.setUid(perfilSelecionada.getUid());
                databaseReference.child("perfil").child(pd.getUid()).removeValue();
                limparCampos();
                Toast.makeText(getActivity(), "Deletar", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void eventoDatabase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.child("perfil").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listPerfil.clear();
                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Perfis p = objSnapshot.getValue(Perfis.class);
                    listPerfil.add(p);
                }
                System.out.println("LISTA   " + listPerfil.toString());



                arrayAdapterPerfil= new ArrayAdapter<Perfis>(
                        getActivity(),
                        android.R.layout.simple_list_item_1,listPerfil);
                listV_dados.setAdapter(arrayAdapterPerfil);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void inicializarFirebase() {
        //FirebaseApp.initializeApp(getActivity());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
    private void limparCampos() {
        editDown.setText("");
        editUp.setText("");
    }

    }



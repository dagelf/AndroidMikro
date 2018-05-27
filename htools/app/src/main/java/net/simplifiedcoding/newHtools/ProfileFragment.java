package net.simplifiedcoding.newHtools;

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
    EditText editDown, editUp;
    ListView listV_dados;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private List<Perfis> listPerfil = new ArrayList<Perfis>();
    private ArrayAdapter<Perfis> arrayAdapterPerfil;

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
         editDown = (EditText) vProfile.findViewById(R.id.editNome);
         editUp = (EditText) vProfile.findViewById(R.id.editEmail);
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

//                FirebaseUser user = mAuth.getCurrentUser();
                String uuid;
                String downlod = editDown.getText().toString().trim();
                String upload = editUp.getText().toString().trim();
                uuid = UUID.randomUUID().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference userRef = database.getReference("perfil/" + uuid);
                System.out.println("UUID  ====> " + uuid);

                Map<String, Object> userInfos = new HashMap<>();
                userInfos.put("download",downlod);
                userInfos.put("upload",upload);
                System.out.println("USER " + userInfos.toString());
                userRef.setValue(userInfos);
//                Perfis p = new Perfis();
//                p.setUid(UUID.randomUUID().toString());
//                p.setDownload("TESTE");
////                p.setDownload(editDown.getText().toString());
////                p.setUpload(editUp.getText().toString());
//                p.setUpload("TESTE");
//                System.out.println(p.toString());
//                databaseReference.child("Perfil").child(p.getUid()).setValue(p);
                limparCampos();
                Toast.makeText(getActivity(), "Novo", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_atualiza:
                Toast.makeText(getActivity(), "Atualizar", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_deleta:
                Toast.makeText(getActivity(), "Deletar", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void eventoDatabase() {
        databaseReference.child("Pessoa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listPerfil.clear();
                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Perfis p = objSnapshot.getValue(Perfis.class);
                    listPerfil.add(p);
                }
//                arrayAdapterPerfil= new ArrayAdapter<Pessoa>(getActivity(),
//                        android.R.layout.simple_list_item_1,listPerfil);
//                listV_dados.setAdapter(arrayAdapterPessoa);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(getActivity());
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }
    private void limparCampos() {
        editDown.setText("");
        editUp.setText("");
    }

    }



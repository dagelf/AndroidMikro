package net.simplifiedcoding.newHtools;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import net.simplifiedcoding.bottomnavigationexample.R;

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


public class ProfileFragment extends Fragment {
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

    private String uid;
    private String usuario;


    private ArrayList<String> seguindo;

    private ChildEventListener tweetEventListener;

    public ProfileFragment(){

    }

//    @Override
//    public void onAttach(Activity. activity) {
//        super.onAttach(activity);
//        Log.d("Ciclo", "Fragment: Metodo onAttach() chamado");
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Ciclo", "Fragment: Metodo onCreate() chamado");
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("Ciclo", "Fragment: Metodo onActivityCreated() chamado");
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null) getActivity().finish();

        //getUserInfo();
        Log.d("Ciclo", "Fragment: Metodo onStart() chamado" + user);
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d("Ciclo", "Fragment: Metodo onResume() chamado");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Ciclo", "Fragment: Metodo onPause() chamado");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("Ciclo", "Fragment: Metodo onStop() chamado");
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("Ciclo", "Fragment: Metodo onSavedInstanceState() chamado");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Ciclo", "Fragment: Metodo onDestroyView() chamado");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Ciclo", "Fragment: Metodo onDestroy() chamado");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("Ciclo", "Fragment: Metodo onDetach() chamado");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vProfile = inflater.inflate(R.layout.fragment_profile, container, false);
            //inflater.inflate(R.layout.fragment_profile, null);
        ListView listView = (ListView) vProfile.findViewById(R.id.listView);
        String[] values = new String[] { "Joao Manuel",
                "Maria da Silca",
                "Chica da Silva",
                "Leao Lobo",
                "Mr. Android",
                "Roberto Marinho",
                "Marcinho da DP.",
                "Sr. Gulp"
        };

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                values
        );
        listView.setAdapter(listViewAdapter);



        //new
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        //if(user != null) {
            //getActivity().finish();
           // getUserInfo();
        ///}



    ////////////////
        return vProfile;
    }
//
//    @Override
//    protected void OnStart(){
//        super.OnStart();
//    }





    private void getUserInfo(){
        uid = mAuth.getCurrentUser().getUid();

        DatabaseReference userRef = database.getReference("users/" + uid);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                usuario = dataSnapshot.child("usuario").getValue(String.class);
                Toast toast = Toast.makeText(getView().getContext(), "Usuario - "+ usuario,Toast.LENGTH_LONG);
                toast.show();
                Log.d("USUARIOS", usuario);

                //seguindo.clear();
//                for(DataSnapshot s:dataSnapshot.child("seguindo").getChildren()){
//                    seguindo.add(s.getValue(String.class));
//                }

//                TextView headerUsuario = findViewById(R.id.headerUsuario);
//                headerUsuario.setText(usuario);
//
//                TextView headerSeguindo = findViewById(R.id.headerSeguindo);
//                headerSeguindo.setText("Seguindo: " + seguindo.size());
//
//                setTweetListener();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    }

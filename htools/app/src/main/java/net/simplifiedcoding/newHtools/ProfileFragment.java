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
import java.util.List;


public class ProfileFragment extends Fragment {
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

    private String uid;
    private String usuario;

    private List <Usuarios> listUsuario = new ArrayList<>();
    private ArrayAdapter<Usuarios> arrayAdapterUsuario;

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

        //        ListView listView = (ListView) vProfile.findViewById(R.id.listView);


        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();



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


        Log.d("Ciclo", "Fragment: Metodo onStart() chamado" + user.getUid());
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
    public  class Usuarios {


        public String getUsuario() {
            return usuario;
        }

        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }

        public String usuario;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String email;

        public String uid;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }



        @Override
        public String toString() {
            return usuario;
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vProfile = inflater.inflate(R.layout.fragment_profile, container, false);

        seguindo = new ArrayList<>();
        System.out.println("print se" + seguindo.toString());
        System.out.println("Quantidade " + seguindo.size());
            //inflater.inflate(R.layout.fragment_profile, null);
        final ListView listView = (ListView) vProfile.findViewById(R.id.listView);
//        String[] values = new String[] { "Joao Manuel",
//                "Maria da Silca",
//                "Chica da Silva",
//                "Leao Lobo",
//                "Mr. Android",
//                "Roberto Marinho",
//                "Marcinho da DP.",
//                "Sr. Gulp"
//        };
//
//        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
//                getActivity(),
//                android.R.layout.simple_list_item_1,
//                values
//        );
//        listView.setAdapter(listViewAdapter);

        DatabaseReference userRef = database.getReference("users/");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    String user;
                    String mail;
                    Usuarios s = new Usuarios();
                    user = objSnapshot.child("usuario").getValue(String.class);
                    mail = objSnapshot.child("email").getValue(String.class);
                    s.setUsuario(user);
                    s.setEmail(mail);
                    listUsuario.add(s);
                    seguindo.add(user);
                     //Usuarios s = objSnapshot.getValue(Usuarios.class);

//                    System.out.println(s.toString());
//                    System.out.println(p.email);
//                    System.out.println(p.usuario);

                    //usuario =  objSnapshot.getValue(String.class);
                    //usuario = dataSnapshot.child("usuario").getValue(String.class);
                    // System.out.println(objSnapshot.getValue().toString());

                    //seguindo.add(s.);
                    //seguindo.add(objSnapshot.getValue().toString());

                }

                System.out.println(listUsuario);
                ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1, seguindo);
                listView.setAdapter(listViewAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //new
        //FirebaseUser user = mAuth.getCurrentUser();
        //if(user != null) {
            //getActivity().finish();
           // getUserInfo();
        ///}



    ////////////////
        return vProfile;
    }





    private void getUserInfo(){
        //uid = mAuth.getCurrentUser().getUid();

        DatabaseReference userRef = database.getReference("users/");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){


                   Usuarios s = objSnapshot.getValue(Usuarios.class);
                   /// System.out.println(s.toString());
//                    System.out.println(p.email);
//                    System.out.println(p.usuario);

                    //usuario =  objSnapshot.getValue(String.class);
                    //usuario = dataSnapshot.child("usuario").getValue(String.class);
                   // System.out.println(objSnapshot.getValue().toString());

                    //seguindo.add(s.);
                  //seguindo.add(objSnapshot.getValue().toString());

                }
                    //System.out.println(seguindo);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                usuario = dataSnapshot.child("usuario").getValue(String.class);
//                seguindo.clear();
//                for(DataSnapshot s:dataSnapshot.child("seguindo").getChildren()){
//                    seguindo.add(s.getValue(String.class));
//                }
////                System.out.println(usuario);
//
////                Toast toast = Toast.makeText(getView().getContext(), "Usuario - "+ usuario,Toast.LENGTH_LONG);
////                toast.show();
//                Log.d("usuario", usuario);
//                Log.d("lista", seguindo.toString());
//
//
////                TextView headerUsuario = findViewById(R.id.headerUsuario);
////                headerUsuario.setText(usuario);
////
////                TextView headerSeguindo = findViewById(R.id.headerSeguindo);
////                headerSeguindo.setText("Seguindo: " + seguindo.size());
////
////                setTweetListener();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


    }

    }

package net.simplifiedcoding.newHtools;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by jeferson on 22/05/18.
 */

public class FirebaseClient {
    ListView listView;
    Context c;
    CustomAdapter customAdapter;
    int count;
    public int size = 0 ;


    ArrayList<Pessoa> pessoas= new ArrayList<>();
    private FirebaseAuth mAuth;
    Pessoa p = new Pessoa();
    DatabaseReference userRef ;
//    FirebaseUser user = mAuth.getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public  FirebaseClient(Context c, ListView listView)
    {
        this.c= c;
        this.listView= listView;
        }

    public  void refreshdata()
    {
        Pessoa p = new Pessoa();
        Pessoa p1 = new Pessoa();
        p.setEmail("joao@gmail.com");
        p.setUsuario("Joao Araujo de Sousa");

        pessoas.add(p);


        customAdapter = new CustomAdapter(c, pessoas);
        listView.setAdapter((ListAdapter) customAdapter);
    }

    public void getUsers(){
        userRef = database.getReference("users/");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getupdates(dataSnapshot);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
    public void getupdates(DataSnapshot dataSnapshot){
        pessoas.clear();
        for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
            String user;
            String mail;
            Pessoa p = new Pessoa();

            user = objSnapshot.child("usuario").getValue(String.class);
            mail = objSnapshot.child("email").getValue(String.class);
            p.setUsuario(user);
            p.setEmail(mail);
            p.setDownload("300");
            p.setUpload("300");
            p.setStatus("ativo");

            pessoas.add(p);

        }
        //count


        if(pessoas.size()>0)
        {
            customAdapter = new CustomAdapter(c, pessoas);
            listView.setAdapter((ListAdapter) customAdapter);


        }else
        {
            Toast.makeText(c, "Nada Aqui, Sorry !", Toast.LENGTH_SHORT).show();
        }
    }

    public void usersCount(){
        userRef = database.getReference("users/");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    String user;
                    String sexo;


                    user = objSnapshot.child("usuario").getValue(String.class);
                    sexo = objSnapshot.child("sexo").getValue(String.class);
                    p.setUsuario(user);
                    p.setSexo(sexo);
                    size =size +1;

                    pessoas.add(p);

                }
                count = pessoas.size();
                System.out.println(pessoas.size());
                System.out.println(size);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
        System.out.println("FINAL" + pessoas.size());
        System.out.println(size);

    }


}

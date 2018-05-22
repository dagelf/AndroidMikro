package net.simplifiedcoding.newHtools;

import android.content.Context;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by jeferson on 22/05/18.
 */

public class FirebaseClient {
    ListView listView;
    Context c;
    CustomAdapter customAdapter;

    ArrayList<Pessoa> pessoas= new ArrayList<>();
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

    public  FirebaseClient(Context c, ListView listView)
    {
        this.c= c;
        this.listView= listView;
         FirebaseAuth mAuth;
         FirebaseDatabase database;


    }

    public  void refreshdata()
    {
        Pessoa p = new Pessoa();
        Pessoa p1 = new Pessoa();
        p.setEmail("joao@gmail.com");
        p.setNome("Joao Araujo de Sousa");
        p1.setEmail("rosa@gmail.com");
        p1.setNome("Rosa Araujo de Sousa");
        pessoas.add(p);
        pessoas.add(p1);
        pessoas.add(p1);
        pessoas.add(p);
        pessoas.add(p1);
        pessoas.add(p1);
        pessoas.add(p);
        pessoas.add(p1);
        pessoas.add(p);
        pessoas.add(p1);
        pessoas.add(p);
        pessoas.add(p1);
        pessoas.add(p1);

        customAdapter = new CustomAdapter(c, pessoas);
        listView.setAdapter((ListAdapter) customAdapter);
    }
}

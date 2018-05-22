package net.simplifiedcoding.newHtools;

import android.content.Context;
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


        //Firebase.setAndroidContext(c);

    }
}

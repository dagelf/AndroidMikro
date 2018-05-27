package net.simplifiedcoding.newHtools;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.simplifiedcoding.bottomnavigationexample.R;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;

/**
 * Created by jeferson on 23/05/18.
 */

public class DashAdapter  {
    Context c;
    View view;
    DatabaseReference userRef ;
    FirebaseDatabase  database = FirebaseDatabase.getInstance();
    ArrayList<Pessoa> pessoas= new ArrayList<>();
    private FirebaseAuth mAuth;
    Pessoa p = new Pessoa();
    int qtdM =0;
    int qtdF =0;

    public DashAdapter(Context c, View view) {
        this.c = c;
        this.view = view;
    }

    public void usersCount(){

        PieChart mPieChart = (PieChart) view.findViewById(R.id.piechart);
        mPieChart.addPieSlice(new PieModel("Masculino", 150, Color.parseColor("#56B7F1")));
        mPieChart.addPieSlice(new PieModel("Femino", 45, Color.parseColor("#FE6DA8")));


        userRef = database.getReference("users/");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    String user;
                    String sexo;
                    user = objSnapshot.child("usuario").getValue(String.class);
                    sexo = objSnapshot.child("sexo").getValue(String.class);
                    System.out.println(sexo);
                    p.setNome(user);
                    p.setSexo(sexo);
                    pessoas.add(p);

                }
                for(int i = 0; i < pessoas.size(); i++)
                {
                    System.out.println(pessoas.get(i).getSexo());
                }

//                for (Pessoa p : pessoas){
//                    //System.out.println(p.getSexo());
//                    String sex = p.getSexo();
//                    if(sex.equals("M")){
//                        qtdM = qtdM + 1;
//                    }  if(sex.equals("F")){
//                        qtdF = qtdF + 1;
//                    }
                }
//                System.out.println("M " + qtdM);
//                System.out.println("F " + qtdF);




            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });



    }

}
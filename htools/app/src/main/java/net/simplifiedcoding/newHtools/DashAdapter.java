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

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.BarModel;
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
    int qtdAtivo = 0;
    int qtdBloqueado = 0;

    public DashAdapter(Context c, View view) {
        this.c = c;
        this.view = view;
    }

    public void dashCount(){



        userRef = database.getReference("users/");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    String user;
                    String sexo;
                    String status;
                    user = objSnapshot.child("usuario").getValue(String.class);
                    sexo = objSnapshot.child("sexo").getValue(String.class);
                    status = objSnapshot.child("status").getValue(String.class);

                    String check = status.trim();
                    String sex = sexo.trim();
                    if(sex.equals("M")){
                        qtdM = qtdM + 1;
                    }
                     if(sex.equals("F")){
                        qtdF = qtdF + 1;
                    }
                    if (check.equals("Ativo")){
                         qtdAtivo = qtdAtivo +1;
                    }if (check.equals("Bloqueado")){
                        qtdBloqueado = qtdBloqueado +1;
                    }
                    p.setNome(user);
                    p.setSexo(sexo);
                    pessoas.add(p);

                }


                PieChart mPieChart = (PieChart) view.findViewById(R.id.piechart);
                mPieChart.addPieSlice(new PieModel("Masculino", qtdM, Color.parseColor("#56B7F1")));
                mPieChart.addPieSlice(new PieModel("Femino", qtdF, Color.parseColor("#FE6DA8")));

                System.out.println("M " + qtdM);
                System.out.println("F " + qtdF);
                System.out.println("A " + qtdAtivo);
                System.out.println("B " + qtdBloqueado);
                BarChart mBarChart = (BarChart) view.findViewById(R.id.barchart);

                mBarChart.addBar(new BarModel(qtdAtivo, 0xFF56B7F1));
                mBarChart.addBar(new BarModel(qtdBloqueado,  0xFF343456).getLegendLabel("dfdsf"));


                mBarChart.startAnimation();

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });



    }

}
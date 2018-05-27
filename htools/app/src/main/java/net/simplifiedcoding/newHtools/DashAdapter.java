package net.simplifiedcoding.newHtools;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
import java.util.List;
import java.util.Map;

import javax.net.SocketFactory;

import me.legrange.mikrotik.ApiConnection;

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
    TextView textView;
    Pessoa p = new Pessoa();
    int qtdM =0;
    int qtdF =0;
    int qtdAtivo = 0;
    int qtdBloqueado = 0;
    int qtdUser = 0;
    MyTask mt;
    public List<Map<String, String>> result = null;
    public TextView txtOn;

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
                    p.setUsuario(user);
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


                mBarChart.addBar(new BarModel("Ativos",qtdAtivo, 0xFF56B7F1));
                mBarChart.addBar(new BarModel("Bloqueados",qtdBloqueado,  0xFF343456));

                mBarChart.startAnimation();

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });



    }
    public void userCount(){
        mt = new MyTask();
        mt.execute();



        userRef = database.getReference("users/");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    String user;
                    user = objSnapshot.child("usuario").getValue(String.class);

                    pessoas.add(p);
                    qtdUser = qtdUser +1;

                }
                String total;
                total  = Integer.toString(qtdUser);

                textView = (TextView) view.findViewById(R.id.txtCad);
                textView.setText(total);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });



    }

    class MyTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //tvResult.setText("Begin");
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                try {



                    ApiConnection con = ApiConnection.connect(SocketFactory.getDefault(), Config.HOST, ApiConnection.DEFAULT_PORT, 200);
                    txtOn = (TextView) view.findViewById(R.id.texOnline);


                    con.login(Config.USERNAME, Config.PASSWORD);


                    if (con.isConnected()) {
                        //tvResult.setText("OK!");

                        String online = "";
                        result = con.execute("/ip/hotspot/active/print count-only");
                        for (Map<String, String> res : result) {

                            System.out.println("ATIVOS " + res.values());
                            online = res.values().toString();

                        }
                        txtOn.setText(online);
                    }

                    con.close();
                } catch (Exception e) {

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);


        }
    }
}
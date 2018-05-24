package net.simplifiedcoding.newHtools;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import net.simplifiedcoding.bottomnavigationexample.R;


import java.util.ArrayList;

public class DashboardFragment extends Fragment {
    //ListView listView;

    ArrayList<Pessoa> pessoas= new ArrayList<>();
    DashAdapter dashAdapter;
    private Activity activity;

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vDash = inflater.inflate(R.layout.fragment_notifications, container, false);
        final ListView listView = (ListView) vDash.findViewById(R.id.listView);

        final FloatingActionButton bntPlus = (FloatingActionButton) vDash.findViewById(R.id.btNovo);
        bntPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getView().getContext(), "Click evento ",Toast.LENGTH_LONG);
                toast.show();
                Intent it = new Intent(getActivity(), ScrollingActivity.class);
                startActivity(it);
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.activity_montar_lista_inferior, new FavoritosFragment());
//                transaction.commit();


            }
        });
//        View convertview;
//        ViewGroup viewGroup;
//
//        if (inflater == null) {
//            inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        }
//        if (convertview == null) {
//            convertview = inflater.inflate(R.layout.list_item, viewGroup, false);
//
//        }

        Pessoa p = new Pessoa();
        Pessoa p1 = new Pessoa();
        p.setEmail("joao@gmail.com");
        p.setNome("Joao Araujo de Sousa");
        p1.setEmail("rosa@gmail.com");
        p1.setNome("Rosa Araujo de Sousa");
        pessoas.add(p);
        pessoas.add(p1);

        dashAdapter = new DashAdapter(getActivity(), pessoas);
        listView.setAdapter((ListAdapter) dashAdapter);
//        FirebaseClient f = new FirebaseClient(getActivity(),listView);
//        f.getUsers();
//        Integer c =  f.usersCount();
//        System.out.print("TOTAL " + c);
        return vDash;
    }
}

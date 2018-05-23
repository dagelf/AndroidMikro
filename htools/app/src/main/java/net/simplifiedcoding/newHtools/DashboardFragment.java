package net.simplifiedcoding.newHtools;

import android.app.Activity;
import android.app.FragmentTransaction;
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

/**
 * Created by Belal on 1/23/2018.
 */

public class DashboardFragment extends Fragment {
    //ListView listView;

    ArrayList<Pessoa> pessoas= new ArrayList<>();
    CustomAdapter customAdapter;
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
//                Intent it = new Intent(this, MainActivity.class);
//                startActivity(it);
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.activity_montar_lista_inferior, new FavoritosFragment());
//                transaction.commit();


            }
        });
//        customAdapter = new CustomAdapter(getActivity(), pessoas);
//        listView.setAdapter((ListAdapter) customAdapter);
        FirebaseClient f = new FirebaseClient(getActivity(),listView);
        f.getUsers();
        Integer c =  f.usersCount();
        System.out.print("TOTAL " + c);
        return vDash;
    }
}

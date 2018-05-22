package net.simplifiedcoding.newHtools;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import net.simplifiedcoding.bottomnavigationexample.R;


import java.util.ArrayList;

/**
 * Created by Belal on 1/23/2018.
 */

public class DashboardFragment extends Fragment {
    ListView listView;

    ArrayList<Pessoa> pessoas= new ArrayList<>();
    CustomAdapter customAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vDash = inflater.inflate(R.layout.fragment_notifications, container, false);

        Pessoa p = new Pessoa();
        p.setEmail("joaooa");
        p.setNome("fsdfsdf");
        pessoas.add(p);
        pessoas.add(p);
        pessoas.add(p);
        pessoas.add(p);
        pessoas.add(p);
        pessoas.add(p);
        pessoas.add(p);
        pessoas.add(p);
        pessoas.add(p);
        pessoas.add(p);
        pessoas.add(p);
        pessoas.add(p);
        pessoas.add(p);


        final ListView listView = (ListView) vDash.findViewById(R.id.listView);
        String[] values = new String[] { "Joao Manuel",
                "Maria da Silca",
                "Chica da Silva",
                "Leao Lobo",
                "Mr. Android",
                "Roberto Marinho",
                "Marcinho da DP.",
                "Sr. Gulp"
        };

//        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
//                getActivity(),
//                android.R.layout.simple_list_item_1,
//                values
//        );
       // listView.setAdapter(listViewAdapter);

        customAdapter = new CustomAdapter(getActivity(), pessoas);
        listView.setAdapter((ListAdapter) customAdapter);


        return vDash;
    }
}

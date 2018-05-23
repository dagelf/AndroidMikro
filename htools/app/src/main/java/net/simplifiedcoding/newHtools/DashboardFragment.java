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
    //ListView listView;

    ArrayList<Pessoa> pessoas= new ArrayList<>();
    CustomAdapter customAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vDash = inflater.inflate(R.layout.fragment_notifications, container, false);
        final ListView listView = (ListView) vDash.findViewById(R.id.listView);
//        customAdapter = new CustomAdapter(getActivity(), pessoas);
//        listView.setAdapter((ListAdapter) customAdapter);
        FirebaseClient f = new FirebaseClient(getActivity(),listView);
        f.getUsers();
        f.usersCount();
        return vDash;
    }
}

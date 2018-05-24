package net.simplifiedcoding.newHtools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import net.simplifiedcoding.bottomnavigationexample.R;

import java.util.ArrayList;

/**
 * Created by jeferson on 23/05/18.
 */

public class UsuariosFragment extends Fragment {
    ArrayList<Pessoa> pessoas= new ArrayList<>();
    CustomAdapter customAdapter;
    private Activity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vDash = inflater.inflate(R.layout.fragment_notifications, container, false);
        final ListView listView = (ListView) vDash.findViewById(R.id.listView);

        final FloatingActionButton bntPlus = (FloatingActionButton) vDash.findViewById(R.id.btNovo);
        bntPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast toast = Toast.makeText(getView().getContext(), "Click evento ",Toast.LENGTH_LONG);
//                toast.show();
                Intent it = new Intent(getActivity(), ScrollingActivity.class);
                startActivity(it);
            }
        });

        FirebaseClient f = new FirebaseClient(getActivity(),listView);
        f.getUsers();
//        Integer c =  f.usersCount();
//        System.out.print("TOTAL " + c);
        return vDash;
    }

}

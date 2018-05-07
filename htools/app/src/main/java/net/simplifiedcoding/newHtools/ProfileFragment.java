package net.simplifiedcoding.newHtools;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.simplifiedcoding.bottomnavigationexample.R;

/**
 * Created by Belal on 1/23/2018.
 */

public class ProfileFragment extends Fragment {

    public ProfileFragment(){

    }
    private int fragment_profile;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vProfile = inflater.inflate(R.layout.fragment_profile, container, false);
            //inflater.inflate(R.layout.fragment_profile, null);
        ListView listView = (ListView) vProfile.findViewById(R.id.listView);
        String[] values = new String[] { "Joao Manuel",
                "Maria da Silca",
                "Chica da Silva",
                "Leao Lobo",
                "Mr. Android",
                "Roberto Marinho",
                "Marcinho da DP.",
                "Sr. Gulp"
        };

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                values
        );
        listView.setAdapter(listViewAdapter);


        return vProfile;
    }
}

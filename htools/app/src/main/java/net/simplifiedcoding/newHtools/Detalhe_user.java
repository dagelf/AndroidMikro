package net.simplifiedcoding.newHtools;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.simplifiedcoding.bottomnavigationexample.R;



public class Detalhe_user extends Fragment {

    public Detalhe_user() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vDetalhe = inflater.inflate(R.layout.fragment_profile, container, false);
        return vDetalhe;
    }



}

package net.simplifiedcoding.newHtools;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import net.simplifiedcoding.bottomnavigationexample.R;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.SocketFactory;

import me.legrange.mikrotik.ApiConnection;

public class DashboardFragment extends Fragment {
    //ListView listView;

    final   String LOG_TAG = "mLog";
    public List<Map<String, String>> result = null;
    public List<Map<String, String>> result1 = null;
    public  ArrayList arrayList = new ArrayList();
    public  String count ="";
    public TextView txtOn;
    public TextView txtCad;
   // MyTask mt;

    ArrayList<Pessoa> pessoas= new ArrayList<>();
    DashAdapter dashAdapter;
    private Activity activity;
    String resultado;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vDash = inflater.inflate(R.layout.fragment_dashboard, container, false);
         txtOn = (TextView) vDash.findViewById(R.id.texOnline);
         txtCad = (TextView) vDash.findViewById(R.id.txtCad);
         users u = new users(getActivity(),txtCad);

         DashAdapter da = new DashAdapter(getActivity(),vDash);
         da.userCount();
    //     u.comandoRb();
//         mt = new MyTask();
//         mt.execute();
        return vDash;
    }



}

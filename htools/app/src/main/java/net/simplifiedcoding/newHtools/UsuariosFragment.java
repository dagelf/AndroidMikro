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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import net.simplifiedcoding.bottomnavigationexample.R;

import java.util.ArrayList;

/**
 * Created by jeferson on 23/05/18.
 */

public class UsuariosFragment extends Fragment {
    //ArrayList<Pessoa> pessoas= new ArrayList<>();
    Pessoa pessoaSelecionada;
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pessoaSelecionada = (Pessoa) parent.getItemAtPosition(position);
//                Toast toast = Toast.makeText(getActivity(), pessoaSelecionada.getUsuario() + pessoaSelecionada.getEmail(),Toast.LENGTH_LONG);
//                toast.show();
                ArrayList<Pessoa> pessoas= new ArrayList<>();
                pessoas.add(pessoaSelecionada);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("pessoa", pessoas);
                Intent intent = new Intent(getActivity(), UpdateUser.class);
                String parametros =  pessoaSelecionada.getUsuario();
                intent.putExtra("infos", parametros);

                startActivity(intent);

            }
        });

//        ArrayList<Pessoa> pessoas= new ArrayList<>();
//        pessoas = f.lista();
//        System.out.println(" PESSSSOAS  "+pessoas.toString());
//        System.out.println(" PESSSSOAS  "+pessoas);
//        Integer c =  f.usersCount();
//        System.out.print("TOTAL " + c);
        return vDash;
    }

}

package net.simplifiedcoding.newHtools.Adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.simplifiedcoding.bottomnavigationexample.R;
import net.simplifiedcoding.newHtools.Pessoa;

import java.util.List;

public class AdapterCursosPersonalizado extends BaseAdapter {

    private final List<Pessoa> pessoas;
    private final Activity act;


    public AdapterCursosPersonalizado(List<Pessoa> pessoas, Activity act) {
        this.pessoas = pessoas;
        this.act = act;
    }



    @Override
    public int getCount() {
        return pessoas.size();
    }

    @Override
    public Object getItem(int position) {
        return pessoas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = act.getLayoutInflater()
                .inflate(R.layout.list_item, parent, false);


        Pessoa pessoa = pessoas.get(position);

//        TextView nome = (TextView)
//                view.findViewById(R.id.lista_curso_personalizada_nome);
//        TextView descricao = (TextView)
//                view.findViewById(R.id.lista_curso_personalizada_descricao);
//        ImageView imagem = (ImageView)
//                view.findViewById(R.id.lista_curso_personalizada_imagem);
//
//        nome.setText(pessoa.getNome());
//        descricao.setText(pessoa.getEmail());
//        imagem.setImageResource(R.drawable.ic_people);

        return view;
    }


}
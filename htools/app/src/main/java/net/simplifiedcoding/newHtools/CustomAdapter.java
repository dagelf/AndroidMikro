package net.simplifiedcoding.newHtools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import net.simplifiedcoding.bottomnavigationexample.R;

/**
 * Created by Admin on 5/26/2017.
 */

public class CustomAdapter extends BaseAdapter {
    Context c;
    ArrayList<Pessoa> pessoas;
    LayoutInflater inflater;


    public CustomAdapter(Context c, ArrayList<Pessoa> pessoas) {
        this.c = c;
        this.pessoas = pessoas;
    }


    @Override
    public int getCount() {
        return pessoas.size();
    }

    @Override
    public Object getItem(int i) {
        return pessoas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertview, ViewGroup viewGroup) {

        if (inflater== null)
        {
            inflater=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } if(convertview==null)
        {
            convertview= inflater.inflate(R.layout.list_item,viewGroup,false);

        }

        MyHolder holder= new MyHolder(convertview);
        holder.nameTxt.setText(pessoas.get(i).getNome());
        holder.mailTxt.setText(pessoas.get(i).getEmail());

        return convertview;
    }
}

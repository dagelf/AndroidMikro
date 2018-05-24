package net.simplifiedcoding.newHtools;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.simplifiedcoding.bottomnavigationexample.R;

/**
 * Created by jeferson on 22/05/18.
 */

public class MyHolder {

    TextView nameTxt;
    TextView mailTxt;
    TextView statusTxt;
    TextView downTxt;
    TextView upTxt;
    TextView countSize;
    ImageView img;
    public MyHolder(View itemView) {


        nameTxt= (TextView) itemView.findViewById(R.id.nameTxt);
        mailTxt = (TextView) itemView.findViewById(R.id.mailTxt);
        statusTxt = (TextView) itemView.findViewById(R.id.nameStatus);
        downTxt =  (TextView) itemView.findViewById(R.id.textDown);
        upTxt =  (TextView) itemView.findViewById(R.id.textUp);


        ////item_card
        countSize = (TextView) itemView.findViewById(R.id.counttxt);





    }
}

package net.simplifiedcoding.newHtools;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import net.simplifiedcoding.bottomnavigationexample.R;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.PieModel;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Belal on 1/23/2018.
 */

public class HomeFragment extends Fragment {
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;



    private List<Pessoa> listUsuario = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard

//        PieChart mPieChart = (PieChart) findViewById(R.id.piechart);
//
//        mPieChart.addPieSlice(new PieModel("Freetime", 15, Color.parseColor("#FE6DA8")));
//        mPieChart.addPieSlice(new PieModel("Sleep", 25, Color.parseColor("#56B7F1")));
//        mPieChart.addPieSlice(new PieModel("Work", 35, Color.parseColor("#CDA67F")));
//        mPieChart.addPieSlice(new PieModel("Eating", 9, Color.parseColor("#FED70E")));
//
//        mPieChart.startAnimation();
 View vhome = inflater.inflate(R.layout.fragment_home, container, false);
//        BarChart mBarChart = (BarChart) vhome.findViewById(R.id.barchart);
//
//        mBarChart.addBar(new BarModel(50, 0xFF123456));
//        mBarChart.addBar(new BarModel(36,  0xFF343456));
//
//        mBarChart.addBar(new BarModel(3.3f, 0xFF563456));
//        mBarChart.addBar(new BarModel(1.1f, 0xFF873F56));
//        mBarChart.addBar(new BarModel(2.7f, 0xFF56B7F1));
//        mBarChart.addBar(new BarModel(2.f,  0xFF343456));
//        mBarChart.addBar(new BarModel(0.4f, 0xFF1FF4AC));
//        mBarChart.addBar(new BarModel(4.f,  0xFF1BA4E6));
//
//        mBarChart.startAnimation();


        ListView listView = (ListView) vhome.findViewById(R.id.listView);
        PieChart mPieChart = (PieChart) vhome.findViewById(R.id.piechart);
//        mPieChart.addPieSlice(new PieModel("Masculino", 50, Color.parseColor("#56B7F1")));
//        mPieChart.addPieSlice(new PieModel("Femino", 45, Color.parseColor("#FE6DA8")));
//

        ValueLineChart mCubicValueLineChart = (ValueLineChart) vhome.findViewById(R.id.cubiclinechart);

        ValueLineSeries series = new ValueLineSeries();
        series.setColor(0xFF56B7F1);


        series.addPoint(new ValueLinePoint("Apr", 5));
        series.addPoint(new ValueLinePoint("Mai", 16));
        series.addPoint(new ValueLinePoint("Jun", 10));
        series.addPoint(new ValueLinePoint("Jul", 13));
        series.addPoint(new ValueLinePoint("Aug", 4));
        series.addPoint(new ValueLinePoint("Sep", 5));
        series.addPoint(new ValueLinePoint("Oct", 10));
        series.addPoint(new ValueLinePoint("Nov", 4));
        series.addPoint(new ValueLinePoint("Dec", 8));

        mCubicValueLineChart.addSeries(series);
        mCubicValueLineChart.startAnimation();
        DashAdapter d = new DashAdapter(getActivity(),vhome);
        d.usersCount();
        return vhome;
    }
}

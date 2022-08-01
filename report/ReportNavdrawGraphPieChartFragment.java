package com.example.edithapp.report;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.edithapp.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


public class ReportNavdrawGraphPieChartFragment extends Fragment {
    PieChart pieChart;
    public ReportNavdrawGraphPieChartFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.report_navdraw_graph_piechart_fragment, container, false);
        pieChart  = view.findViewById(R.id.report_piechat);
        setData();
        return view;
    }

    void setData(){
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(50.5f, "Expense"));
        entries.add(new PieEntry(50.5f, "Income"));

        PieDataSet set = new PieDataSet(entries, "2021");
        PieData data = new PieData(set);
        set.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.setData(data);
        pieChart.animateXY(5000, 5000);
    }
}
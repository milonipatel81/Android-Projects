package com.example.edithapp.report;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.edithapp.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ReportNavdrawGraphBarChartFragment extends Fragment {
    BarChart chart;
    public ReportNavdrawGraphBarChartFragment() { }
    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.report_navdraw_graph_barchart_fragment, container, false);
        chart = view.findViewById(R.id.report_barchart);
        setData();
        return view;
    }

    void setData(){
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(2022,100));

        entries.add(new BarEntry(2021,200));
        BarDataSet bardataset = new BarDataSet(entries, "Income Expense");
        chart.animateY(5000);
        XAxis xAxis = chart.getXAxis();
        //change the position of x-axis to the bottom
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        BarData data = new BarData(bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.setData(data);
    }
}
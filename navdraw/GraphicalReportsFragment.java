
package com.example.edithapp.navdraw;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.edithapp.R;
import com.example.edithapp.report.ReportNavdrawGraphBarChartFragment;
import com.example.edithapp.report.ReportNavdrawGraphPieChartFragment;
import com.example.edithapp.room.CategoryDatabase;

public class GraphicalReportsFragment extends Fragment {
    CategoryDatabase categoryDatabase;
    Button pie,bar;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FrameLayout frameLayout;
    public GraphicalReportsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.navdraw_fragment_graphical_reports, container, false);  
        pie = view.findViewById(R.id.navbar_grepo_pie_btn);
        bar = view.findViewById(R.id.navbar_grepo_bar_btn);
        frameLayout = view.findViewById(R.id.navbar_grepo_chart_loder);

        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        try{
            pie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    frameLayout.removeAllViewsInLayout();
                    fragmentTransaction.add(R.id.navbar_grepo_chart_loder,new ReportNavdrawGraphPieChartFragment());
                    fragmentTransaction.commit();
                }
            });

            bar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    frameLayout.removeAllViewsInLayout();
                    fragmentTransaction.add(R.id.navbar_grepo_chart_loder,new ReportNavdrawGraphBarChartFragment());
                    fragmentTransaction.commit();
                }
            });
        }catch(Exception e){;}

        return view;
    }

}

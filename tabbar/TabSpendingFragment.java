package com.example.edithapp.tabbar;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edithapp.R;
import com.example.edithapp.room.Category;
import com.example.edithapp.room.CategoryDao;
import com.example.edithapp.room.CategoryDatabase;
import com.example.edithapp.room.Transection;
import com.example.edithapp.room.TransectionDao;

import java.util.List;
//import static androidx.transition.R.anim.*;


public class TabSpendingFragment extends Fragment {
    Button E,I;
    CategoryDatabase categoryDatabase;
    TextView EX,IC,BL;
    ProgressBar PB;
    public TabSpendingFragment() { }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setEnterTransition(android.R.anim.fade_in);
        //setExitTransition(android.R.anim.fade_out);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabbar_fragment_tab_spending, container, false);
        E = view.findViewById(R.id.btnSpendingExpense);
        I = view.findViewById(R.id.btnSpendingIncome);
        EX = view.findViewById(R.id.txtSpendingExpense);
        IC = view.findViewById(R.id.txtSpendingIncome);
        BL = view.findViewById(R.id.txtSpendingBalance);
        PB = view.findViewById(R.id.pBar);

        float icAmt= 0, exAmt =0;
        categoryDatabase = CategoryDatabase.getAppDatabase(getContext());
        TransectionDao tdao = categoryDatabase.transectionDao();
        CategoryDao cdao = categoryDatabase.categoryDao();
        List<Category> ctdata = cdao.getAllCategory();
        List<Transection> tsdata = tdao.getAllCategory();

        for(Category c: ctdata){
            for(Transection t:tsdata){
                if(c.getType().equals("Expense") && c.getName().equals(t.getCategory())){
                    exAmt+=t.getAmount();
                } else {
                    icAmt+=t.getAmount();
                }
            }
        }
        float blAmt = 0;
        if(exAmt>icAmt){
            blAmt=exAmt-icAmt;
        }else{
            blAmt=icAmt-exAmt;
        }
        BL.setText(String.valueOf(blAmt));
        EX.setText(String.valueOf(exAmt));
        IC.setText(String.valueOf(icAmt));
        float t = ((icAmt+exAmt)/icAmt)*100;
        PB.setMax((int)icAmt+(int)exAmt);
        PB.setProgress((int)icAmt);

        E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),SpendingFormExpenseActivity.class));
            }
        });

        I.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),SpendingFormActivity.class));
            }
        });
        return view;
    }

}
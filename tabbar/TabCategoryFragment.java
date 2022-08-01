package com.example.edithapp.tabbar;

import static androidx.transition.R.anim;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.edithapp.R;
import com.example.edithapp.room.CategoryDao;
import com.example.edithapp.room.CategoryDatabase;
import com.example.edithapp.rview.Category;
import com.example.edithapp.rview.CategoryAdapter;
import com.google.android.material.button.MaterialButtonToggleGroup;
import java.util.ArrayList;
import java.util.List;


public class TabCategoryFragment extends Fragment {

    public TabCategoryFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setEnterTransition(anim.fragment_fade_enter);
        //setExitTransition(anim.fragment_fade_exit);
    }
    RecyclerView recyclerView;
    CategoryDatabase categoryDatabase;
    CategoryAdapter adapter;
    Button btnExpense,btnIncome,btnAdd;
    MaterialButtonToggleGroup materialButtonToggleGroup;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabbar_fragment_tab_category, container, false);
        recyclerView = view.findViewById(R.id.recycler);
        categoryDatabase = CategoryDatabase.getAppDatabase(view.getContext());
        // get button
        btnExpense = view.findViewById(R.id.btnExpense);
        btnIncome = view.findViewById(R.id.btnIncome);
        btnAdd = view.findViewById(R.id.btnAddCategory);
        materialButtonToggleGroup = view.findViewById(R.id.btnGroupIE);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),CategoryFormActivity.class));
            }
        });
        // add data into recycler view
         addData("Expense");


        btnIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData("Income");
            }
        });
        btnExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData("Expense");

            }
        });
        return view;
    }

    public void addData(String option){
        try{
            CategoryDao dao = categoryDatabase.categoryDao();
            List<com.example.edithapp.room.Category> db= dao.getAllCategory();
            List<Category> id = new ArrayList<>();
            for(com.example.edithapp.room.Category c:db){
                if(option.equals(c.getType()))
                    id.add(new Category(R.drawable.book,c.getName()));
            }

            if(db.size()>0) {
                adapter = new CategoryAdapter(id, getContext());
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapter);
            }
        } catch (Exception ignored){;}
    }

    @Override
    public void onResume() {
        super.onResume();
        try{
            materialButtonToggleGroup.check(R.id.btnExpense);
        }catch (Exception e){;}finally {
            addData("Expense");
        }

    }


}
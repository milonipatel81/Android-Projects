package com.example.edithapp.tabbar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.edithapp.R;
import com.example.edithapp.room.Category;
import com.example.edithapp.room.CategoryDao;
import com.example.edithapp.room.CategoryDatabase;
import com.example.edithapp.room.Transection;
import com.example.edithapp.room.TransectionDao;
import com.example.edithapp.rview.TransectionAdapter;
import com.example.edithapp.rview.TransectionR;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class TabTransactionFragment extends Fragment {

    RecyclerView recyclerView;
    CategoryDatabase categoryDatabase;
    TextView IC,EX;
    TransectionAdapter adapter;

    public TabTransactionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabbar_fragment_tab_transaction, container, false);
        recyclerView = view.findViewById(R.id.recyclerTrans);
        IC = view.findViewById(R.id.tabbar_trans_income_view);
        EX = view.findViewById(R.id.tabbar_trans_expense_view);

        categoryDatabase = CategoryDatabase.getAppDatabase(view.getContext());
        TransectionDao dao = categoryDatabase.transectionDao();
        List<Transection> tlist = dao.getAllCategory();
        List<TransectionR> id = new ArrayList<>();
        for (Transection t:tlist){
            id.add(new TransectionR(R.drawable.book,t.getCategory(),t.getAmount(),t.getDate()));
        }
        try {
            adapter = new TransectionAdapter(id,view.getContext());
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
        } catch (Exception e){;}

        // top amount set
        float icAmt= 0, exAmt =0;
        CategoryDao cdao = categoryDatabase.categoryDao();
        List<Category> ctdata = cdao.getAllCategory();
        for(Category c: ctdata){
            for(Transection t:tlist){
                if(c.getType().equals("Expense") && c.getName().equals(t.getCategory())){
                    exAmt+=t.getAmount();
                } else {
                    icAmt+=t.getAmount();
                }
            }
        }
        IC.setText(String.valueOf(icAmt));
        EX.setText(String.valueOf(exAmt));

        return view;
    }
}
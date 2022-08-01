
package com.example.edithapp.navdraw;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edithapp.R;
import com.example.edithapp.room.Category;
import com.example.edithapp.room.CategoryDao;
import com.example.edithapp.room.CategoryDatabase;
import com.example.edithapp.room.MonthlyRepo;
import com.example.edithapp.room.Transection;
import com.example.edithapp.room.TransectionDao;
import com.gkemon.XMLtoPDF.PdfGenerator;
import com.gkemon.XMLtoPDF.PdfGeneratorListener;
import com.gkemon.XMLtoPDF.model.FailureResponse;
import com.gkemon.XMLtoPDF.model.SuccessResponse;

import java.util.List;

public class PDFReportsFragment extends Fragment {
    TableLayout table;
    Context context;
    CategoryDatabase database;
    CategoryDao categoryDao;
    TransectionDao transectionDao;
    List<Category> list_income,list_expense;
    List<Transection> list_trans_all;
    Button ALL,MON,CAT,PRINT;
    public PDFReportsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.navdraw_fragment_pdf_reports, container, false);  
        table = view.findViewById(R.id.navbar_pdf_table_main);
        ALL = view.findViewById(R.id.navbar_pdf_repo1);
        MON = view.findViewById(R.id.navbar_pdf_repo2);
        CAT = view.findViewById(R.id.navbar_pdf_repo3);
        PRINT = view.findViewById(R.id.navbar_pdf_print);
        context = getContext();

        database =  CategoryDatabase.getAppDatabase(view.getContext());

        categoryDao = database.categoryDao();
        transectionDao = database.transectionDao();

        list_expense = categoryDao.getByTypeCategory("Expense");
        list_income = categoryDao.getByTypeCategory("Income");
        list_trans_all = transectionDao.getAllCategory();
        setYearlyRepo();
        PRINT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                print();
            }
        });

        ALL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                table.removeAllViews();
                setYearlyRepo();
            }
        });
        MON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                table.removeAllViews();
                setMonthlyRepo();
            }
        });
        CAT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                table.removeAllViews();
                setDataCategoryRepo();
            }
        });

        return view;
    }
    void print(){
        PdfGenerator.getBuilder()
                .setContext(context)
                .fromViewIDSource()
                .fromViewID(getActivity(), R.id.navbar_pdf_table_main)
                .setFileName("Test-PDF").
                setFolderNameOrPath("Test-PDF-folder")
                .openPDAfterGeneration(true)
                .build(new PdfGeneratorListener() {
                    @Override
                    public void onStartPDFGeneration() {
                    }
                    @Override
                    public void onFailure(FailureResponse failureResponse) {
                        super.onFailure(failureResponse);
                    }
                    @Override
                    public void showLog(String log) {
                        super.showLog(log);
                    }
                    @Override
                    public void onFinishPDFGeneration() {
                    }
                    @Override
                    public void onSuccess(SuccessResponse response) {
                        super.onSuccess(response);
                    }
                });
    }
    View getTextView(String lable,int color){
        TextView txt = new TextView(context);
        txt.setText(lable);
        txt.setTextColor(color);
        txt.setGravity(Gravity.CENTER);
        ViewGroup.LayoutParams params = new TableRow.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,1f);
        txt.setLayoutParams(params);
        return txt;
    }

    TableRow getRow(String[] lables, int color,int padding,int txtColor){
        TableRow row = new TableRow(context);
        row.setPadding(padding,padding,padding,padding);
        row.setBackgroundColor(color);
        for(int i=0; i<lables.length; i++) {
            row.addView(getTextView(lables[i], txtColor));
        }
        return row;
    }

    void setYearlyRepo(){
        String[] head =new String[]{"Sr. No","Date","Name","Amount","Category"};
        table.addView(getRow(head,Color.GRAY,5,Color.WHITE));

        List<String> list_data = transectionDao.getyearlyRepo();
        list_data.remove(list_data.size()-1);
        int i=1;
        for(String r: list_data){
            String[] data = r.split(",");
            //Toast.makeText(context, data[0]+data[1]+data[2], Toast.LENGTH_SHORT).show();
            table.addView(getRow(new String[]{
                    String.valueOf(i),
                    data[0],
                    data[3],
                    data[1],
                    data[2],
            },Color.WHITE,5,Color.BLACK));
            i+=1;
        }
    }

    void setMonthlyRepo(){
        String[] head =new String[]{"Sr. No","Date","Amount","Category"};
        table.addView(getRow(head,Color.GRAY,5,Color.WHITE));

        List<String> list_data = transectionDao.getMonthlyRepo();
        list_data.remove(list_data.size()-1);
        int i=1;
        for(String r: list_data){
            String[] data = r.split(",");
            //Toast.makeText(context, data[0]+data[1]+data[2], Toast.LENGTH_SHORT).show();
            table.addView(getRow(new String[]{
                    String.valueOf(i),
                    data[0],
                    data[1],
                    data[2],
            },Color.WHITE,5,Color.BLACK));
            i+=1;
        }
    }

    void setDataCategoryRepo(){
        String[] head =new String[]{"Sr. No","Category","Amount"};
        table.addView(getRow(head,Color.GRAY,5,Color.WHITE));

        float i_amount=0f,e_amount=0f;
        for(Transection t:list_trans_all){
            if(list_expense.contains(t.getCategory())){
                e_amount+=t.getAmount();
            } else {
                i_amount+=t.getAmount();
            }
        }
        table.addView(getRow(new String[]{"1","Income",String.valueOf(i_amount)},Color.WHITE,5,Color.BLACK));
        table.addView(getRow(new String[]{"2","Expense",String.valueOf(e_amount)},Color.WHITE,5,Color.BLACK));
    }
}

package com.example.edithapp.tabbar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edithapp.R;
import com.example.edithapp.room.Category;
import com.example.edithapp.room.CategoryDao;
import com.example.edithapp.room.CategoryDatabase;
import com.example.edithapp.room.Transection;
import com.example.edithapp.room.TransectionDao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SpendingFormActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtDate;
    final Calendar myCalendar = Calendar.getInstance();
    TextView txtDateSelect;
    Button save;
    Spinner spinner;
    CategoryDatabase categoryDatabase;
    EditText editAmt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabbar_activity_spending_form);
        txtDateSelect = findViewById(R.id.tabbar_income_form_date);
        txtDateSelect.setOnClickListener(this);
        save = findViewById(R.id.tabbar_button_spending_income_save);
        spinner = findViewById(R.id.tabbar_spin_income);
        categoryDatabase = CategoryDatabase.getAppDatabase(getApplicationContext());
        editAmt = findViewById(R.id.tabbar_txt_income_amount);
        CategoryDao dao = categoryDatabase.categoryDao();
        TransectionDao tdao = categoryDatabase.transectionDao();
        List<Transection> t = tdao.getAllCategory();

        List<Category> db= dao.getByTypeCategory("Income");
        ArrayList<String> data = new ArrayList<>();
        for(Category c:db) {
            data.add(c.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),R.layout.tabbar_spinner_item,data);
        spinner.setAdapter(adapter);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String c = spinner.getSelectedItem().toString();
                float f = Float.parseFloat(editAmt.getText().toString());
                String d = txtDateSelect.getText().toString();
                TransectionDao tdao = categoryDatabase.transectionDao();
                Transection transection = new Transection();
                transection.setAmount(f);
                transection.setCategory(c);
                transection.setDate(d);
                tdao.insert(transection);
            }
        });
    }
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            TextView editText= findViewById(R.id.tabbar_income_form_date);
            editText.setText(dayOfMonth+"-"+monthOfYear +"-"+year);
        }
    };


    @Override
    public void onClick(View v) {
        DatePickerDialog datePickerDialog=  new DatePickerDialog(this,R.style.DialogTheme, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}
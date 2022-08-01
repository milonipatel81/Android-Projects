package com.example.edithapp.tabbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.edithapp.R;
import com.example.edithapp.room.Category;
import com.example.edithapp.room.CategoryDao;
import com.example.edithapp.room.CategoryDatabase;

public class CategoryFormActivity extends AppCompatActivity {

    RadioButton genderradioButton;
    RadioGroup radioGroup;
    EditText txt;
    CategoryDatabase categoryDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabbar_activity_category_form);
        radioGroup=(RadioGroup)findViewById(R.id.tabbar_radiogroup);
        txt = findViewById(R.id.tabbar_txt_category);
        categoryDatabase = CategoryDatabase.getAppDatabase(getApplicationContext());
    }
    public void onclickbuttonMethod(View v){
        int selectedId = radioGroup.getCheckedRadioButtonId();
        genderradioButton = (RadioButton) findViewById(selectedId);
        if(selectedId!=-1) {
            Category c = new Category();
            c.setName(txt.getText().toString());
            c.setType((String) genderradioButton.getText());
            CategoryDao dao = categoryDatabase.categoryDao();
            dao.insert(c);
            Toast.makeText(getApplicationContext(),genderradioButton.getText(), Toast.LENGTH_SHORT).show();
            finish();
        }

    }
}
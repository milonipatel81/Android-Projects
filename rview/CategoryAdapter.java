package com.example.edithapp.rview;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edithapp.R;

import java.util.List;

import com.example.edithapp.room.CategoryDao;
import com.example.edithapp.room.CategoryDatabase;
import com.example.edithapp.rview.Category;

public class CategoryAdapter  extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    private List<Category> listdata;
    private Context context;
    private CategoryAdapter.ViewHolder holder;
    private int position;

    public  CategoryAdapter(List<Category> listdata, Context context){
        this.listdata = listdata;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.rview_list_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Category myListData = listdata.get(position);
        holder.textView.setText(listdata.get(position).getName());
        holder.imageView.setImageResource(listdata.get(position).getImage());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creating a popup menu
                PopupMenu popup = new PopupMenu(context, holder.relativeLayout);
                //inflating menu from xml resource
                popup.inflate(R.menu.rview_category_list_item_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.rview_category_item_delete:
                                createDeleteAlert();
                                //handle menu1 click
                                break;
                            case R.id.rview_category_item_edit:
                                String name= myListData.getName();
                                String category = "Expense";
                                createEditDialog(name,category);
                                //handle menu2 click
                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();
            }
        });
    }

    EditText E_N;
    RadioGroup E_C;
    RadioButton E_C_E,E_C_I,E_C_B;
    CategoryDatabase categoryDatabase;
    void createEditDialog(String N,String C){
       AlertDialog.Builder builder = new AlertDialog.Builder(context);
       LayoutInflater inflater = LayoutInflater.from(context);

       View view = inflater.inflate(R.layout.tabbar_category_edit_form_modal,null,false);
       E_N = view.findViewById(R.id.tabbar_edit_txt_category);
       E_C = view.findViewById(R.id.tabbar_edit_radiogroup);
       E_C_E = view.findViewById(R.id.tabbar_edit_radio_expense);
       E_C_I = view.findViewById(R.id.tabbar_edit_radio_income);
       categoryDatabase = CategoryDatabase.getAppDatabase(context);

       E_N.setText(N);
       if(C.equals("Expense")){
           E_C_E.setChecked(true);
       } else {
           E_C_I.setChecked(true);
       }
       builder.setView(view)
               // Add action buttons
               .setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int id) {
                       // sign in the user ...
                       int selected = E_C.getCheckedRadioButtonId();
                       E_C_B = view.findViewById(selected);
                       String category = E_C_B.getText().toString();
                       String name = E_N.getText().toString();
                       // Category
                       com.example.edithapp.room.Category c = new com.example.edithapp.room.Category();
                       c.setName(name);
                       c.setType(category);
                       // Dao
                       CategoryDao dao = categoryDatabase.categoryDao();
                       dao.update(c);

                       //

                   }
               })
               .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       dialog.dismiss();
                   }
               });
        builder.show();
   }
    void createDeleteAlert(){
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Are you sure want to delete?");
        // alert.setMessage("Message");

        alert.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Your action here
            }
        });

        alert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });

        alert.show();
    }
    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public  static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageView);
            this.textView = itemView.findViewById(R.id.textView);
            this.relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }
}

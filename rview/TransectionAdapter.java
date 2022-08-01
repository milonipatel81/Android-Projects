package com.example.edithapp.rview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.edithapp.R;
import java.util.List;

public class TransectionAdapter extends RecyclerView.Adapter<TransectionAdapter.ViewHolder> {
    private List<TransectionR> list;
    private Context context;
    private  TransectionAdapter.ViewHolder holder;
    private int position;

    public TransectionAdapter(List<TransectionR> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.rview_list_row_trans,parent,false);
        ViewHolder viewHolder=new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TransectionR listData = list.get(position);
        holder.category.setText(list.get(position).getCategory());
        holder.amount.setText(String.valueOf(list.get(position).getAmount()));
        holder.icon.setImageResource(list.get(position).getImage());
        holder.date.setText(list.get(position).getDate());
        // click listener
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: "+listData.getCategory(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // Create view holder
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView icon;
        public TextView category,date,amount;
        public RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.icon = itemView.findViewById(R.id.imageViewTrans);
            this.category = itemView.findViewById(R.id.textViewTrans);
            this.date = itemView.findViewById(R.id.textViewDateTrans);
            this.amount = itemView.findViewById(R.id.textViewAmountTrans);
            this.relativeLayout = itemView.findViewById(R.id.relativeLayoutTrans);
        }
    }
}

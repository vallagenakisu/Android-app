package com.example.mainprojectpersonallifetracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainprojectpersonallifetracker.Class.ExpenseClass;
import com.example.mainprojectpersonallifetracker.R;

import java.util.List;

public class expenseAdapter extends RecyclerView.Adapter<expenseAdapter.myadapter> {
    private List<ExpenseClass> mylist;
    private Context context;


    public expenseAdapter(List<ExpenseClass> mylist, Context context) {
        this.mylist = mylist;
        this.context = context;
    }

    @Override
    public expenseAdapter.myadapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView =  inflater.inflate(R.layout.expenseadapter,parent,false);
        return new myadapter(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull expenseAdapter.myadapter holder, int position) {
        ExpenseClass item = mylist.get(position);
        holder.amount.setText(String.valueOf(item.getAmount()));
        holder.description.setText(item.getDescription());
        holder.title.setText(item.getType());
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public class myadapter extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView amount,description,title;

        public myadapter(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardviewexpense);
            amount = itemView.findViewById(R.id.expenseamount);
            description = itemView.findViewById(R.id.expensedescription);
            title = itemView.findViewById(R.id.expensetitle);
        }
    }
}

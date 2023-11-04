package com.example.mainprojectpersonallifetracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainprojectpersonallifetracker.R;

import java.util.List;

public class todoadapter extends RecyclerView.Adapter<todoadapter.myadapter> {
    private Context context;
    private List<String> mylist;
    private LayoutInflater inflater;

    public todoadapter(Context context, List<String> mylist) {
        this.context = context;
        this.mylist = mylist;
        inflater = LayoutInflater.from(context);
    }

    public class myadapter extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        CardView cardView;
        public myadapter(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox);
            cardView=itemView.findViewById(R.id.card);
        }
    }


    @Override
    public myadapter onCreateViewHolder( ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(inflater.getContext());
        View item = inflater.inflate(R.layout.todo_adapter,parent,false);
        return new myadapter(item);
    }

    @Override
    public void onBindViewHolder( myadapter holder, int position)
    {
        String item = mylist.get(position);
        holder.checkBox.setText(item);
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }


}

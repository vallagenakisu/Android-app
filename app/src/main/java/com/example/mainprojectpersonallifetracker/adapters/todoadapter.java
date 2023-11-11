package com.example.mainprojectpersonallifetracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainprojectpersonallifetracker.Class.TodoClass;
import com.example.mainprojectpersonallifetracker.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class todoadapter extends RecyclerView.Adapter<todoadapter.myadapter> {
    private Context context;
    private List<TodoClass> mylist;


    public todoadapter(Context context, List<TodoClass> mylist) {
        this.context = context;
        this.mylist = mylist;
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
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View item = inflater.inflate(R.layout.todo_adapter,parent,false);
        return new myadapter(item);
    }

    @Override
    public void onBindViewHolder( myadapter holder, int position)
    {
        TodoClass item = mylist.get(position);
        holder.checkBox.setText(item.getTask());
        holder.checkBox.setChecked(item.getStatus()==1);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Task");
                    reference.child(item.getId()).child("status").setValue(1);
                }
                else
                {
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Task");
                    reference.child(item.getId()).child("status").setValue(0);
                }

            }
        });

    }

    @Override
    public int getItemCount() {

        return mylist.size();
    }



}

package com.example.mainprojectpersonallifetracker.mainfragment;


import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mainprojectpersonallifetracker.Class.TodoClass;
import com.example.mainprojectpersonallifetracker.MainActivity;
import com.example.mainprojectpersonallifetracker.R;
import com.example.mainprojectpersonallifetracker.adapters.todoadapter;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class TodoFragment extends Fragment {

    private RecyclerView recyclerView;
    private FloatingActionButton add;
    private TextView text;
    private EditText TaskEdit;
    private List<String> mylist;
    private DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_todo, container, false);
        return view;

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler);
        add = view.findViewById(R.id.addButton);
        TaskEdit = view.findViewById(R.id.taskEdit);
        mylist = new ArrayList<>();
        todoadapter adapter = new todoadapter(getContext(),mylist);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);




        reference = FirebaseDatabase.getInstance().getReference().child("Task");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.dialoguetodo,null);
                AlertDialog alertDialog = new MaterialAlertDialogBuilder(getContext())
                        .setView(view1)
                        .setTitle("Add Task")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText text2 = view1.findViewById(R.id.taskadd);
                                String task = text2.getText().toString();
                                TodoClass obj = new TodoClass(task,0);

                                reference.child(task).setValue(obj);


                            }
                        })
                        .create();
                alertDialog.show();
            }




    });

};}
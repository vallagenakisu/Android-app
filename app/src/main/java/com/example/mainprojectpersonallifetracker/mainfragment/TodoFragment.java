package com.example.mainprojectpersonallifetracker.mainfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mainprojectpersonallifetracker.R;
import com.example.mainprojectpersonallifetracker.adapters.dialogueboxTodo;
import com.example.mainprojectpersonallifetracker.adapters.todoadapter;
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

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                dialogueboxTodo dialogueboxTodo = new dialogueboxTodo() ;
                dialogueboxTodo.show(getChildFragmentManager(),"TaskAdd");
            }

            DatabaseReference reference =  FirebaseDatabase.getInstance().getReference().child("Todo").child("Tasks");



    });

};}
package com.example.mainprojectpersonallifetracker.mainfragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mainprojectpersonallifetracker.Class.ExpenseClass;
import com.example.mainprojectpersonallifetracker.R;
import com.example.mainprojectpersonallifetracker.adapters.expenseAdapter;
import com.example.mainprojectpersonallifetracker.adapters.todoadapter;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class expenseFragment extends Fragment {
    private Button expenseButton,statusButton;
    private TextView PreviousBalance,CurrentBalance,LastTransaction;
    private RecyclerView recyclerView;
    private List<ExpenseClass> mylist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_expense, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mylist = new ArrayList<>();
        expenseButton = view.findViewById(R.id.addExpense);
        statusButton = view.findViewById(R.id.status);
        PreviousBalance = view.findViewById(R.id.previousBalance);
        CurrentBalance = view.findViewById(R.id.currentBalance);
        LastTransaction = view.findViewById(R.id.lastTransaction);
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        expenseAdapter adapter = new expenseAdapter(mylist,getContext());
        recyclerView.setAdapter(adapter);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Expense");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mylist.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    ExpenseClass expenseClass = dataSnapshot.getValue(ExpenseClass.class);
                    mylist.add(expenseClass);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        expenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogue(reference);
            }
        });

}

    private void showDialogue(DatabaseReference reference)
    {
        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.expensedialogue,null);
        Spinner spinner = view1.findViewById(R.id.spinner);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Bills");
        arrayList.add("Food");
        arrayList.add("Snacks");
        arrayList.add("Groceries");
        arrayList.add("Hangout");
        arrayList.add("Others");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),R.layout.spinnerview,R.id.spinnerText,arrayList);
        spinner.setAdapter(adapter);
        AlertDialog alertDialog = new MaterialAlertDialogBuilder(getContext())
                .setView(view1)
                .setTitle("Add Expense")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //nothing
                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView amount = view1.findViewById(R.id.amount);
                        TextView description = view1.findViewById(R.id.details);
                        Spinner spinner = view1.findViewById(R.id.spinner);
                        String type = spinner.getSelectedItem().toString();
                        String amount1 = amount.getText().toString();
                        String description1 = description.getText().toString();
                        ExpenseClass expenseClass = new ExpenseClass(Integer.parseInt(amount1),description1,type);
                        reference.push().setValue(expenseClass);
                    }
                })
                .create();
        alertDialog.show();

    }
    }
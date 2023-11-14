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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mainprojectpersonallifetracker.Class.BalanceClass;
import com.example.mainprojectpersonallifetracker.Class.ExpenseClass;
import com.example.mainprojectpersonallifetracker.R;
import com.example.mainprojectpersonallifetracker.adapters.expenseAdapter;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class expenseFragment extends Fragment {
    private Button expenseButton,statusButton,addBalance;
    private TextView PreviousBalance,CurrentBalance,LastTransaction , date;
    private EditText balance;
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

        //initializing everything
        mylist = new ArrayList<>();
        expenseButton = view.findViewById(R.id.addExpense);
        statusButton = view.findViewById(R.id.status);
        PreviousBalance = view.findViewById(R.id.previousBalance);
        CurrentBalance = view.findViewById(R.id.currentBalance);
        LastTransaction = view.findViewById(R.id.lastTransaction);
        recyclerView = view.findViewById(R.id.recycler);
        date = view.findViewById(R.id.date);
        balance = view.findViewById(R.id.addBalance);
        addBalance = view.findViewById(R.id.addButton);

        //setting up recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        expenseAdapter adapter = new expenseAdapter(mylist, getContext());
        recyclerView.setAdapter(adapter);


        //getting database references
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Expense");
        DatabaseReference reference1 = reference.child("MainBalance");


        //setting up calendar
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        date.setText(currentDate);


        //addbalance from editText and send Mainbalance to database

        addBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Balance = balance.getText().toString();
                BalanceClass balanceClass = new BalanceClass(Integer.parseInt(Balance), Integer.parseInt(Balance), 0);
                reference1.setValue(balanceClass);
            }
        });
        // initialize previous balance and currentbalance from database
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                BalanceClass balanceClass = snapshot.getValue(BalanceClass.class);
                PreviousBalance.setText(String.valueOf(balanceClass.getPreviousBalance()));
                CurrentBalance.setText(String.valueOf(balanceClass.getCurrentBalance()));
                LastTransaction.setText(String.valueOf(balanceClass.getLastTransaction()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //getting data from database and setting up recycler view with list
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mylist.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ExpenseClass expenseClass = dataSnapshot.getValue(ExpenseClass.class);
                    mylist.add(expenseClass);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //add button click and showing dialogue
        expenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.expensedialogue, null);
                Spinner spinner = view1.findViewById(R.id.spinner);
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add("Bills");
                arrayList.add("Food");
                arrayList.add("Snacks");
                arrayList.add("Groceries");
                arrayList.add("Hangout");
                arrayList.add("Others");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.spinnerview, R.id.spinnerText, arrayList);
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


                                EditText amount = view1.findViewById(R.id.amount);
                                EditText description = view1.findViewById(R.id.details);
                                Spinner spinner = view1.findViewById(R.id.spinner);


                                String type = spinner.getSelectedItem().toString();
                                String amount1 = amount.getText().toString();
                                String description1 = description.getText().toString();
                                ExpenseClass expenseClass = new ExpenseClass(Integer.parseInt(amount1), description1, type);

                                String PB = PreviousBalance.getText().toString();
                                String CB = CurrentBalance.getText().toString();


                                BalanceClass newbalanceClass = new BalanceClass(Integer.parseInt(CB), Integer.parseInt(CB) - Integer.parseInt(amount1), Integer.parseInt(amount1));
                                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("Expense").child("MainBalance");


                                reference1.setValue(newbalanceClass);
                                reference.push().setValue(expenseClass);

                            }
                        })
                        .create();
                alertDialog.show();

            }
        });
    }

}


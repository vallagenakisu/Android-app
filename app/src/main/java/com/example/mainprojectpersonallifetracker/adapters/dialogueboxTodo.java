package com.example.mainprojectpersonallifetracker.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.mainprojectpersonallifetracker.R;
import com.google.firebase.database.FirebaseDatabase;

public class dialogueboxTodo extends AppCompatDialogFragment
{
    private EditText gettask;





    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialoguetodo,null);
        builder.setView(v).setTitle("Add Task")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String task = gettask.getText().toString();
                        FirebaseDatabase.getInstance().getReference().child("Todo").child("Tasks").push().setValue(task);

                    }
                })

        ;
        gettask = v.findViewById(R.id.taskadd);
        return builder.create();
    }
}

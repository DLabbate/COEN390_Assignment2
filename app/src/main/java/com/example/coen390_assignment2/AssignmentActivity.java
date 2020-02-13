package com.example.coen390_assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.coen390_assignment2.Database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class AssignmentActivity extends AppCompatActivity {

    public static final String TAG = "AssignmentActivity";
    RecyclerView assignmentRecyclerView;
    RecyclerView.Adapter assignmentAdapter;
    RecyclerView.LayoutManager linearLayoutManager;
    List<Assignment> assignments;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        databaseHelper = new DatabaseHelper(this);
        assignments = databaseHelper.getAllAssignments();
        for (int i = 0; i<databaseHelper.getAllAssignments().size();i++)
        {
            Log.d(TAG,"Assignment title: " + assignments.get(i).title + "Assignment Grade " +
                    assignments.get(i).getGrade());
        }

        databaseHelper.insertAssignment(new Assignment(1,1,"assignment1",100));
        databaseHelper.insertAssignment(new Assignment(2,2,"assignment2",75));

        assignmentRecyclerView = findViewById(R.id.assignment_recycler_view);
        assignmentAdapter = new AssignmentAdapter(assignments,AssignmentActivity.this);
        linearLayoutManager = new LinearLayoutManager(this);
        assignmentRecyclerView.setAdapter(assignmentAdapter);
        assignmentRecyclerView.setLayoutManager(linearLayoutManager);
    }



}

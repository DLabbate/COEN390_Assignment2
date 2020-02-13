package com.example.coen390_assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.TextView;

import com.example.coen390_assignment2.Database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class AssignmentActivity extends AppCompatActivity {

    public static final String TAG = "AssignmentActivity";
    public static int CURRENT_COURSE_ID; //Used to display the assignments pertaining to this ID (not others!)
    public static String CURRENT_COURSE_TITLE; //Used to display the current course title
    RecyclerView assignmentRecyclerView;
    RecyclerView.Adapter assignmentAdapter;
    RecyclerView.LayoutManager linearLayoutManager;
    List<Assignment> assignments;
    DatabaseHelper databaseHelper;
    TextView courseTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        databaseHelper = new DatabaseHelper(this);
        setupUI();

        if (getIntent().hasExtra("current_course") && getIntent().hasExtra("course_title"))
        {
            CURRENT_COURSE_ID = getIntent().getIntExtra("current_course",0);
            CURRENT_COURSE_TITLE = getIntent().getStringExtra("course_title");
        }

        updateCourseTitle();

        assignments = databaseHelper.getAllAssignments();
        for (int i = 0; i<databaseHelper.getAllAssignments().size();i++)
        {
            Log.d(TAG,"Assignment title: " + assignments.get(i).title + "Assignment Grade " +
                    assignments.get(i).getGrade());
        }

        assignmentRecyclerView = findViewById(R.id.assignment_recycler_view);
        assignmentAdapter = new AssignmentAdapter(databaseHelper.getAssignment(CURRENT_COURSE_ID),AssignmentActivity.this);
        linearLayoutManager = new LinearLayoutManager(this);
        assignmentRecyclerView.setAdapter(assignmentAdapter);
        assignmentRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void setupUI()
    {
        courseTitle = findViewById(R.id.course_title_text_view);
    }

    private void updateCourseTitle()
    {
        courseTitle.setText(CURRENT_COURSE_TITLE);
    }



}

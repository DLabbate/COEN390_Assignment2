package com.example.coen390_assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.coen390_assignment2.Database.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
    FloatingActionButton addAssignmentFAB;
    Button deleteCourseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        databaseHelper = new DatabaseHelper(this); //Create an reference to an instance of DatabaseHelper
        setupUI(); //Connects View references to corresponding Views in the xml
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Displays the up navigation to return to MainActivity

        if (getIntent().hasExtra("current_course") && getIntent().hasExtra("course_title"))
        {
            CURRENT_COURSE_ID = getIntent().getIntExtra("current_course",0); //Retrieves the course code
            CURRENT_COURSE_TITLE = getIntent().getStringExtra("course_title"); //Retrieves the course title
        }

        updateCourseTitle(); //Updates the TextView at the top of the activity to display the appropriate course
        loadAssignmentRecyclerView(); //Loads the assignment recycler view
    }

    private void setupUI()
    {
        courseTitle = findViewById(R.id.course_title_text_view);
        addAssignmentFAB = findViewById(R.id.fab_add_assignment);
        deleteCourseButton = findViewById(R.id.btn_delete_course);

        addAssignmentFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertAssignmentDialogFragment insertAssignmentDialogFragment = new InsertAssignmentDialogFragment();
                insertAssignmentDialogFragment.show(getSupportFragmentManager(),"Insert Course Fragment");
            }
        });

        deleteCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteCourse(CURRENT_COURSE_ID);
                Intent intent = new Intent(AssignmentActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updateCourseTitle()
    {
        courseTitle.setText(CURRENT_COURSE_TITLE);
    }

    public void loadAssignmentRecyclerView()
    {
        assignmentRecyclerView = findViewById(R.id.assignment_recycler_view);
        assignmentAdapter = new AssignmentAdapter(databaseHelper.getAssignment(CURRENT_COURSE_ID),AssignmentActivity.this);
        linearLayoutManager = new LinearLayoutManager(this);
        assignmentRecyclerView.setAdapter(assignmentAdapter);
        assignmentRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadAssignmentRecyclerView();
        updateCourseTitle();
    }
}

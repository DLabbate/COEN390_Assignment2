package com.example.coen390_assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;

import com.example.coen390_assignment2.Database.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Course> courses;
    RecyclerView courseRecyclerView;
    RecyclerView.Adapter courseAdapter;
    RecyclerView.LayoutManager linearLayoutManager;
    DatabaseHelper databaseHelper;
    TextView overallAverageTextView;
    FloatingActionButton addCourseFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this); //Reference to a DatabaseHelper
        setupUI(); //Connectes View references to appropriate elements in xml
        updateOverallAverage(); //Updates the OVERALL average of ALL assignments in the DB
        loadRecyclerView(); //Loads to list of courses
    }

    private void setupUI()
    {
        overallAverageTextView = findViewById(R.id.overall_average_text_view);
        addCourseFAB = findViewById(R.id.fab_add_course);

        addCourseFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertCourseDialogFragment insertCourseDialogFragment = new InsertCourseDialogFragment();
                insertCourseDialogFragment.show(getSupportFragmentManager(),"Insert Course Fragment");
            }
        });
    }

    private void updateOverallAverage()
    {
        overallAverageTextView.setText
                ("Overall Average: " + Assignment.getAssignmentAverage(databaseHelper.getAllAssignments()));
    }

    public void loadRecyclerView()
    {
        courseRecyclerView = findViewById(R.id.course_recycler_view);
        courseAdapter = new CourseAdapter(databaseHelper.getAllCourses(),MainActivity.this);
        linearLayoutManager = new LinearLayoutManager(this);
        courseRecyclerView.setAdapter(courseAdapter);
        courseRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadRecyclerView();
        updateOverallAverage();
    }
}

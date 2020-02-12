package com.example.coen390_assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Course> courses;
    RecyclerView courseRecyclerView;
    RecyclerView.Adapter courseAdapter;
    RecyclerView.LayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        courses = new ArrayList<Course>();
        courses.add(new Course("mini cap","coen390"));
        courses.add(new Course("cap","coen490"));

        courseRecyclerView = findViewById(R.id.course_recycler_view);
        courseAdapter = new CourseAdapter(courses);
        linearLayoutManager = new LinearLayoutManager(this);
        courseRecyclerView.setAdapter(courseAdapter);
        courseRecyclerView.setLayoutManager(linearLayoutManager);
    }
}

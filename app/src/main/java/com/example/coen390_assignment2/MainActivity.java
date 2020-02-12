package com.example.coen390_assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;

import com.example.coen390_assignment2.Database.DatabaseHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Course> courses;
    RecyclerView courseRecyclerView;
    RecyclerView.Adapter courseAdapter;
    RecyclerView.LayoutManager linearLayoutManager;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);

        databaseHelper.insertCourse(new Course(1,"test1","test1"));
        databaseHelper.insertCourse(new Course(1,"test2","test2"));
        databaseHelper.insertCourse(new Course(1,"test3","test3"));

        courses = new ArrayList<Course>();
        courses.add(new Course(0,"mini cap","coen390"));
        courses.add(new Course(0,"cap","coen490"));

        courseRecyclerView = findViewById(R.id.course_recycler_view);
        courseAdapter = new CourseAdapter(databaseHelper.getAllCourses(),MainActivity.this);
        linearLayoutManager = new LinearLayoutManager(this);
        courseRecyclerView.setAdapter(courseAdapter);
        courseRecyclerView.setLayoutManager(linearLayoutManager);
    }
}

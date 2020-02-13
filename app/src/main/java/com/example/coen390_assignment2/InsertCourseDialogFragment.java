package com.example.coen390_assignment2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class InsertCourseDialogFragment extends DialogFragment {

    EditText courseTitleEditText;
    EditText courseCodeEditText;
    Button saveCourseButton;
    Button cancelCourseButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert_course,container,false);

        courseTitleEditText = view.findViewById(R.id.course_title_edit_text);
        courseCodeEditText = view.findViewById(R.id.course_code_edit_text);
        saveCourseButton = view.findViewById(R.id.btn_save_course);
        cancelCourseButton = view.findViewById(R.id.btn_cancel_course);

        saveCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cancelCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;

    }
}

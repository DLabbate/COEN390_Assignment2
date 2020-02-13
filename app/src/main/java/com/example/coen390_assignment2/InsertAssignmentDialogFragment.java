package com.example.coen390_assignment2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.coen390_assignment2.Database.DatabaseHelper;

public class InsertAssignmentDialogFragment extends DialogFragment {

    EditText assignmentTitleEditText;
    EditText assignmentGradeEditText;
    Button cancelButton;
    Button saveButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert_assignment_dialog,container,false);

        assignmentTitleEditText = view.findViewById(R.id.assignment_title_edit_text);
        assignmentGradeEditText = view.findViewById(R.id.assignment_grade_edit_text);
        cancelButton = view.findViewById(R.id.btn_cancel_assignment);
        saveButton = view.findViewById(R.id.btn_save_assignment);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String assignment_title = assignmentTitleEditText.getText().toString();
                String assignment_grade = (assignmentGradeEditText.getText().toString());
                int current_courseID = ((AssignmentActivity)getActivity()).CURRENT_COURSE_ID;
                DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
                //Parameter needs context (activity that is hosting the fragment)
                //getActivity() returns the activity hosting the fragment
                if (!((assignment_title.equals("")) || (assignment_grade.equals(""))))
                {
                    double assignment_grade_double = new Double(assignmentGradeEditText.getText().toString());
                    Log.d("Insert Assignment Frag","Adding New Assignment: " + assignment_title + " " + assignment_grade_double);
                    databaseHelper.insertAssignment(new Assignment(current_courseID,assignment_title,assignment_grade_double));
                    ((AssignmentActivity)getActivity()).loadAssignmentRecyclerView();
                    getDialog().dismiss();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        return view;
    }
}

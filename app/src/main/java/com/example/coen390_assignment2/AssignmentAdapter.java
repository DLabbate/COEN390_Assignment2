package com.example.coen390_assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.ViewHolder> {

    private Context context;
    protected List<Assignment> assignments;

    public AssignmentAdapter(List<Assignment> assignments,Context context)
    {
        this.context = context;
        this.assignments = assignments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_view_holder,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.assignment_grade.setText(Double.toString(assignments.get(position).getGrade()));
        holder.assignment_title.setText(assignments.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return assignments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView assignment_title;
        public TextView assignment_grade;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            assignment_title = itemView.findViewById(R.id.assignment_title_text_view);
            assignment_grade = itemView.findViewById(R.id.assignment_grade_text_view);
        }
    }
}

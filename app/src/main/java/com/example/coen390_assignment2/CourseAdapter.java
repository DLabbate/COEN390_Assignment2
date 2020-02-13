package com.example.coen390_assignment2;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coen390_assignment2.Database.DatabaseHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    private Context context;
    public static final String TAG = "CourseAdapter";
    List<Course> courses;

    public CourseAdapter(List<Course> courses,Context context)
    {
        this.courses = courses;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_view_holder,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.course_title.setText(courses.get(position).getCourseTitle());
        holder.course_id.setText(courses.get(position).getCourseCode());

        //Next we set up the onClickListener for each CardView
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Clicking RecyclerView item");
                Intent intent = new Intent(context,AssignmentActivity.class);
                intent.putExtra("course_title",courses.get(position).getCourseTitle() + " " + courses.get(position).getCourseCode());
                intent.putExtra("current_course",courses.get(position).getID());
                context.startActivity(intent);
            }
        });

        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        List<Assignment> assignments = databaseHelper.getAssignment(courses.get(position).getID());
        double course_avg = Assignment.getAssignmentAverage(assignments);
        holder.course_average.setText("Assignment Average: " + course_avg);
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView course_title;
        public TextView course_id;
        public TextView course_average;
        public CardView parentLayout; //This is a reference to the linear layout of each view holder
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            course_title = itemView.findViewById(R.id.course_title_text_view);
            course_id = itemView.findViewById(R.id.course_id_text_view);
            parentLayout = itemView.findViewById(R.id.course_card_view);
            course_average = itemView.findViewById(R.id.course_assignment_average_text_view);
        }
    }
}

package com.example.coen390_assignment2;

import java.util.List;

public class Assignment {
    protected int assignmentID;
    protected int courseID;
    protected String title;
    protected double grade;

    public Assignment(int assignmentID, int courseID, String title,double grade)
    {
        this.assignmentID = assignmentID;
        this.courseID = courseID;
        this.title = title;
        this.grade = grade;
    }

    public Assignment(int courseID, String title,double grade)
    {
        this.assignmentID = 0;
        this.courseID = courseID;
        this.title = title;
        this.grade = grade;
    }

    public int getAssignentID() {
        return assignmentID;
    }

    public void setAssignentID(int assignentID) {
        this.assignmentID = assignentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }


    public static double getAssignmentAverage(List<Assignment> assignments)
    {
        //This method is used to calculate the overall average, when given a list of assignments
        int n = assignments.size();
        double sum = 0;
        for (int i = 0; i<assignments.size(); i++)
        {
            sum += assignments.get(i).getGrade();
        }

        return sum/n;
    }
}

package com.example.coen390_assignment2;

public class Course {

    protected int ID;
    protected String courseTitle;
    protected String courseCode;

    public Course(int ID,String courseTitle,String courseCode)
    {
        this.ID = ID;
        this.courseTitle = courseTitle;
        this.courseCode = courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

}

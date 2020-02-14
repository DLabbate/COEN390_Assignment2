package com.example.coen390_assignment2.Database;

public class DBConfig {

    /*
    This class is used to store information pertaining to the course database. This includes
    table names and column names.
     */

    public static final String DATABASE_NAME = "courses-db";
    public static final int DATABASE_VERSION = 1;

    //Course table info********************************************************
    public static final String COURSE_TABLE_NAME = "course";
    public static final String COLUMN_COURSE_ID = "_id";
    public static final String COLUMN_COURSE_TITLE = "title";
    public static final String COLUMN_COURSE_CODE = "code";
    //*************************************************************************


    //Assignment table info****************************************************
    public static final String ASSIGNMENT_TABLE_NAME = "assignment";
    public static final String COLUMN_ASSIGNMENT_ID = "assignment_id";
    public static final String COLUMN_ASSIGNMENT_COURSE_ID = "course_id";
    public static final String COLUMN_ASSIGNMENT_TITLE = "assignment_title";
    public static final String COLUMN_ASSIGNMENT_GRADE = "assignment_grade";
    //*************************************************************************
}

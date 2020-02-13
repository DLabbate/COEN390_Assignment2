package com.example.coen390_assignment2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.coen390_assignment2.Assignment;
import com.example.coen390_assignment2.Course;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private Context context;

    public DatabaseHelper(Context context)
    {
        super(context,DBConfig.DATABASE_NAME,null,DBConfig.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_COURSE = "CREATE TABLE " + DBConfig.COURSE_TABLE_NAME
                + " (" + DBConfig.COLUMN_COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DBConfig.COLUMN_COURSE_TITLE + " TEXT NOT NULL, "
                + DBConfig.COLUMN_COURSE_CODE + " TEXT NOT NULL)";

        String CREATE_ASSIGNMENT_COURSE = "CREATE TABLE " + DBConfig.ASSIGNMENT_TABLE_NAME
                + " (" + DBConfig.COLUMN_ASSIGNMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DBConfig.COLUMN_ASSIGNMENT_COURSE_ID + " TEXT NOT NULL, "
                + DBConfig.COLUMN_ASSIGNMENT_TITLE + " TEXT NOT NULL, "
                + DBConfig.COLUMN_ASSIGNMENT_GRADE + " INTEGER)";

        db.execSQL(CREATE_TABLE_COURSE);
        db.execSQL(CREATE_ASSIGNMENT_COURSE);
        Log.d(TAG,CREATE_TABLE_COURSE);
        Log.d(TAG,CREATE_ASSIGNMENT_COURSE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertCourse(Course course)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = -1;

        ContentValues contentValues = new ContentValues();

        contentValues.put(DBConfig.COLUMN_COURSE_TITLE,course.getCourseTitle());
        contentValues.put(DBConfig.COLUMN_COURSE_CODE,course.getCourseCode());

        try
        {
            id = db.insertOrThrow(DBConfig.COURSE_TABLE_NAME,null,contentValues);
        }
        catch (SQLException e)
        {
            Log.d(TAG,"ERROR" + e);
            Toast.makeText(context,"ERROR ADDING COURSE"+ e,Toast.LENGTH_SHORT).show();
        }
        finally
        {
            db.close();
        }
        return id;
    }

    public List<Course> getAllCourses()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try
        {
            cursor = db.query(DBConfig.COURSE_TABLE_NAME,null,null,null,null,null,null);

            if (cursor != null)
            {
                if (cursor.moveToFirst())
                {
                    List<Course> courses = new ArrayList<Course>();
                    do
                    {
                        int id = cursor.getInt(cursor.getColumnIndex(DBConfig.COLUMN_COURSE_ID));
                        String title = cursor.getString(cursor.getColumnIndex(DBConfig.COLUMN_COURSE_TITLE));
                        String code = cursor.getString(cursor.getColumnIndex(DBConfig.COLUMN_COURSE_CODE));
                        courses.add(new Course(id,title,code));
                    }
                    while(cursor.moveToNext());

                    return courses;
                }
            }

        }
        catch (SQLException e)
        {
            Log.d(TAG,"ERROR READING COURSES");
            Toast.makeText(context,"ERROR READING COURSES"+ e,Toast.LENGTH_SHORT).show();
        }
        finally
        {
            if (cursor != null)
            {
                cursor.close();
            }
            db.close();
        }

        return Collections.emptyList();
    }


    public long insertAssignment(Assignment assignment)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = -1;

        ContentValues contentValues = new ContentValues();

        contentValues.put(DBConfig.COLUMN_ASSIGNMENT_TITLE,assignment.getTitle());
        contentValues.put(DBConfig.COLUMN_ASSIGNMENT_GRADE,assignment.getGrade());
        contentValues.put(DBConfig.COLUMN_ASSIGNMENT_COURSE_ID,assignment.getCourseID());

        try
        {
            id = db.insertOrThrow(DBConfig.ASSIGNMENT_TABLE_NAME,null,contentValues);
        }
        catch (SQLException e)
        {
            Log.d(TAG,"ERROR" + e);
            Toast.makeText(context,"ERROR ADDING ASSIGNMENT"+ e,Toast.LENGTH_SHORT).show();
        }
        finally
        {
            db.close();
        }
        return id;
    }

    public List<Assignment> getAssignment(int courseID)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        String query = "SELECT  * FROM " + DBConfig.ASSIGNMENT_TABLE_NAME + " WHERE "
                + DBConfig.COLUMN_ASSIGNMENT_COURSE_ID + " = " + courseID;

        try
        {
            //cursor = db.query(DBConfig.COURSE_TABLE_NAME,null,null,null,null,null,null);
            cursor = db.rawQuery(query,null);

            if (cursor != null)
            {
                if (cursor.moveToFirst())
                {
                    List<Assignment> assignments = new ArrayList<Assignment>();
                    do
                    {
                        int assignment_id = cursor.getInt(cursor.getColumnIndex(DBConfig.COLUMN_ASSIGNMENT_ID));
                        int course_id = cursor.getInt(cursor.getColumnIndex(DBConfig.COLUMN_ASSIGNMENT_COURSE_ID));
                        String title = cursor.getString(cursor.getColumnIndex(DBConfig.COLUMN_ASSIGNMENT_TITLE));
                        double grade = cursor.getDouble(cursor.getColumnIndex(DBConfig.COLUMN_ASSIGNMENT_GRADE));
                        assignments.add(new Assignment(assignment_id,course_id,title,grade));
                    }
                    while(cursor.moveToNext());

                    return assignments;
                }
            }

        }
        catch (SQLException e)
        {
            Log.d(TAG,"ERROR READING ASSIGNMENTS");
            Toast.makeText(context,"ERROR READING ASSIGNMENTS"+ e,Toast.LENGTH_SHORT).show();
        }
        finally
        {
            if (cursor != null)
            {
                cursor.close();
            }
            db.close();
        }

        return Collections.emptyList();
    }

    public List<Assignment> getAllAssignments()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try
        {
            cursor = db.query(DBConfig.ASSIGNMENT_TABLE_NAME,null,null,null,null,null,null);

            if (cursor != null)
            {
                if (cursor.moveToFirst())
                {
                    List<Assignment> assignments = new ArrayList<Assignment>();
                    do
                    {
                        int assignment_id = cursor.getInt(cursor.getColumnIndex(DBConfig.COLUMN_ASSIGNMENT_ID));
                        int course_id = cursor.getInt(cursor.getColumnIndex(DBConfig.COLUMN_ASSIGNMENT_COURSE_ID));
                        String title = cursor.getString(cursor.getColumnIndex(DBConfig.COLUMN_ASSIGNMENT_TITLE));
                        double grade = cursor.getDouble(cursor.getColumnIndex(DBConfig.COLUMN_ASSIGNMENT_GRADE));
                        assignments.add(new Assignment(assignment_id,course_id,title,grade));
                    }
                    while(cursor.moveToNext());

                    return assignments;
                }
            }

        }
        catch (SQLException e)
        {
            Log.d(TAG,"ERROR READING ASSIGNMENTS");
            Toast.makeText(context,"ERROR READING ASSIGNMENTS"+ e,Toast.LENGTH_SHORT).show();
        }
        finally
        {
            if (cursor != null)
            {
                cursor.close();
            }
            db.close();
        }

        return Collections.emptyList();
    }
}

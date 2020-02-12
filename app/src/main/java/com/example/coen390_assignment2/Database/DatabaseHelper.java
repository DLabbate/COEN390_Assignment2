package com.example.coen390_assignment2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.coen390_assignment2.Course;

import java.util.ArrayList;
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
                + DBConfig.COLUMN_COURSE_TITLE + " TEXT NOT NULL,"
                + DBConfig.COLUMN_COURSE_CODE + " TEXT NOT NULL)";

        Log.d(TAG,CREATE_TABLE_COURSE);
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
        List<Course> courses = new ArrayList<Course>();

        try
        {
            cursor = db.query(DBConfig.COURSE_TABLE_NAME,null,null,null,null,null,null);

            if (cursor != null)
            {
                do
                {
                    int id = cursor.getInt(cursor.getColumnIndex(DBConfig.COLUMN_COURSE_ID));
                    String title = cursor.getString(cursor.getColumnIndex(DBConfig.COLUMN_COURSE_TITLE));
                    String code = cursor.getString(cursor.getColumnIndex(DBConfig.COLUMN_COURSE_CODE));
                }
                while(cursor.moveToNext());
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



        return courses;
    }
}

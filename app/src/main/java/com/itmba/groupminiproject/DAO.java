package com.itmba.groupminiproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DAO extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="Student.db";
    private static final int DATABASE_VERSION=1;

    public static final String TABLE_NAME= "Students";

    public static final String  COLUMN_NUMBER= "Student_Number";
    public static final String COLUMN_NAME = "Student_Name";
    public static final String COLUMN_SURNAME= "Student_Surname";
    public static final String COLUMN_PHONE= "Student_Phone";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_NUMBER + " INTEGER PRIMARY KEY, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_SURNAME + " TEXT, " +
                    COLUMN_PHONE + " INTEGER);";

    public DAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

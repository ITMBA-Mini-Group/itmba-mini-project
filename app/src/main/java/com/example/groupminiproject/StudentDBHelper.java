package com.example.groupminiproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DatabaseName = "studentdb.db";
    private static final int DatabaseVersion = 1;

    public static final String TableName = "students";
    public static final String ColumnID = "_id";
    public static final String ColumnStudentNumber = "student_number";
    public static final String ColumnName = "name";
    public static final String ColumnSurname = "surname";
    public static final String ColumnPhone = "phone";

    public static final String TableCreate =
            "CREATE TABLE " + TableName + " (" +
            ColumnID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ColumnStudentNumber + " TEXT UNIQUE NOT NULL, " +
            ColumnName + " TEXT NOT NULL, " +
            ColumnSurname + " TEXT NOT NULL, " +
            ColumnPhone + " TEXT NOT NULL);";

    public MyDatabaseHelper(Context context) {
        super(context, DatabaseName, null, DatabaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TableCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(sqLiteDatabase);
    }
}

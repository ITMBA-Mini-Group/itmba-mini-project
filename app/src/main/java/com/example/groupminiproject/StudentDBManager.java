package com.example.groupminiproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class MyDatabaseManager {

    private SQLiteDatabase database;
    private MyDatabaseHelper dbHelper;

    public MyDatabaseManager(Context context) {
        dbHelper = new MyDatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertData(String studentNumber, String name, String surname, String phone) {
        ContentValues values = new ContentValues();
        values.put(MyDatabaseHelper.ColumnStudentNumber, studentNumber);
        values.put(MyDatabaseHelper.ColumnName, name);
        values.put(MyDatabaseHelper.ColumnSurname, surname);
        values.put(MyDatabaseHelper.ColumnPhone, phone);
        return database.insert(MyDatabaseHelper.TableName, null, values);
    }

    public Cursor getAllData() {
        return database.query(MyDatabaseHelper.TableName, null, null, null, null, null, null);
    }

    public boolean updateData(long id, String studentNumber, String name, String surname, String phone) {
        ContentValues values = new ContentValues();
        values.put(MyDatabaseHelper.ColumnStudentNumber, studentNumber);
        values.put(MyDatabaseHelper.ColumnName, name);
        values.put(MyDatabaseHelper.ColumnSurname, surname);
        values.put(MyDatabaseHelper.ColumnPhone, phone);
        return database.update(MyDatabaseHelper.TableName, values, MyDatabaseHelper.ColumnID + "=" + id, null) > 0;
    }

    public boolean deleteData(long id) {
        return database.delete(MyDatabaseHelper.TableName, MyDatabaseHelper.ColumnID + "=" + id, null) > 0;
    }

    public Cursor searchByStudentNumber(String studentNumber) {
        String selection = MyDatabaseHelper.ColumnStudentNumber + "=?";
        String[] selectionArgs = { studentNumber };
        return database.query(MyDatabaseHelper.TableName, null, selection, selectionArgs, null, null, null);
    }
}


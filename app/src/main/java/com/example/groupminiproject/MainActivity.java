package com.example.groupminiproject;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MyDatabaseManager dbManager;
    private EditText edtStdntNum, edtName, edtSurname, edtPhonenumber;
    private ArrayAdapter<Student> adapter;
    private final ArrayList<Student> studentList = new ArrayList<>();
    private long selectedStudentId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtStdntNum = findViewById(R.id.edtStdntNum);
        edtName = findViewById(R.id.edtName);
        edtSurname = findViewById(R.id.edtSurname);
        edtPhonenumber = findViewById(R.id.edtPhonenumber);
        ListView listViewStudents = findViewById(R.id.listViewStudents);

        dbManager = new MyDatabaseManager(this);
        dbManager.open();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentList);
        listViewStudents.setAdapter(adapter);
        updateListView();

        findViewById(R.id.btnAdd).setOnClickListener(view -> addStudent());

        findViewById(R.id.btnModify).setOnClickListener(view -> {
            if (selectedStudentId != -1) {
                modifyStudent(selectedStudentId);
            } else {
                Toast.makeText(MainActivity.this, "Select a student to modify", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btnDelete).setOnClickListener(view -> {
            if (selectedStudentId != -1) {
                deleteStudent(selectedStudentId);
            } else {
                Toast.makeText(MainActivity.this, "Select a student to delete", Toast.LENGTH_SHORT).show();
            }
        });

        listViewStudents.setOnItemClickListener((adapterView, view, position, id) -> {
            Student selectedStudent = studentList.get(position);
            selectedStudentId = selectedStudent.getId();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close();
    }

    private void addStudent() {
        String studentNumber = edtStdntNum.getText().toString();
        String name = edtName.getText().toString();
        String surname = edtSurname.getText().toString();
        String phone = edtPhonenumber.getText().toString();

        if (studentNumber.isEmpty() || name.isEmpty() || surname.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Make sure all fields have a value", Toast.LENGTH_SHORT).show();
            return;
        }

        long result = dbManager.insertData(studentNumber, name, surname, phone);
        if (result > 0) {
            Toast.makeText(this, "Student has been added", Toast.LENGTH_SHORT).show();
            updateListView();
        } else {
            Toast.makeText(this, "Failed to add student", Toast.LENGTH_SHORT).show();
        }
    }

    private void modifyStudent(long id) {
        String studentNumber = edtStdntNum.getText().toString();
        String name = edtName.getText().toString();
        String surname = edtSurname.getText().toString();
        String phone = edtPhonenumber.getText().toString();

        if (studentNumber.isEmpty() || name.isEmpty() || surname.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean result = dbManager.updateData(id, studentNumber, name, surname, phone);
        if (result) {
            Toast.makeText(this, "Student modified", Toast.LENGTH_SHORT).show();
            updateListView();
        } else {
            Toast.makeText(this, "Failed to modify student", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteStudent(long id) {
        boolean result = dbManager.deleteData(id);

        if (result) {
            Toast.makeText(this, "Student deleted", Toast.LENGTH_SHORT).show();
            updateListView();
        } else {
            Toast.makeText(this, "Failed to delete student", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateListView() {
        studentList.clear();
        Cursor cursor = dbManager.getAllData();
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") long id = cursor.getLong(cursor.getColumnIndex(MyDatabaseHelper.ColumnID));
                @SuppressLint("Range") String studentNumber = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.ColumnStudentNumber));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.ColumnName));
                @SuppressLint("Range") String surname = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.ColumnSurname));
                @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.ColumnPhone));

                studentList.add(new Student(id, studentNumber, name, surname, phone));
            } while (cursor.moveToNext());
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }
}

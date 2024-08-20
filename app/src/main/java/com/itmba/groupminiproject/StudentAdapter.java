package com.itmba.groupminiproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {

    ArrayList<Student> studentList = new ArrayList<>();
    public StudentAdapter(@NonNull Context context, int resource, @NonNull List<Student> objects) {
        super(context, resource, objects);
        studentList = (ArrayList<Student>) objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout, parent, false);
        }

        TextView nameView, stdntNumView, surnameView, phoneNumberView = null;
        nameView = currentItemView.findViewById(R.id.nameView);
        stdntNumView = currentItemView.findViewById(R.id.stdntNumView);
        surnameView = currentItemView.findViewById(R.id.surnameView);
        phoneNumberView = currentItemView.findViewById(R.id.phoneNumberView);
        // of the recyclable view is null then inflate the custom layout for the same


        Student currentStudent = studentList.get(position);

        stdntNumView.setText(currentStudent.getStudntNumber());
        surnameView.setText(currentStudent.getSurname());
        phoneNumberView.setText(currentStudent.getPhoneNUmber());
        nameView.setText(currentStudent.getName());


        return currentItemView;
    }
}

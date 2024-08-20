package com.itmba.groupminiproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edtStdntNum, edtName, edtSurname, edtPhoneNumber = null;
    Button btnAdd, btnModify, btnDelete = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtStdntNum = findViewById(R.id.edtStdntNum);
        edtName = findViewById(R.id.edtName);
        edtSurname = findViewById(R.id.edtSurname);
        edtPhoneNumber = findViewById(R.id.edtPhonenumber);
    }



    private Boolean validateEditText(){
        if ((edtStdntNum.getText().toString().isEmpty()) || (edtSurname.getText().toString().isEmpty()) || (edtName.getText().toString().isEmpty()) || (edtPhoneNumber.getText().toString().isEmpty()))
        {
            return(false);
        }
        else {
            return(true);
        }
    }
}
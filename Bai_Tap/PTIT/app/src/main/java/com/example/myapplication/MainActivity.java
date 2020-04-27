package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtName;
    RadioButton rdbMale, rdbFemale;
    CheckBox cb_1, cb_2, cb_3, cb_4;
    ImageButton imgbFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edtName);
        rdbMale = findViewById(R.id.rdbMale);
        rdbFemale = findViewById(R.id.rdbFemale);

        cb_1 = findViewById(R.id.cb_1);
        cb_2 = findViewById(R.id.cb_2);
        cb_3 = findViewById(R.id.cb_3);
        cb_4 = findViewById(R.id.cb_4);

        imgbFile = findViewById(R.id.imgbFile);

        setEvent();
    }

    private void setEvent() {
        imgbFile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgbFile:
                String fullInfo = "";
                String fullName, gender, info;
                fullName = edtName.getText().toString().trim();
                gender = "Unknown";
                info = "\n";
                try {
                    if (fullName.length() <= 3) {
                        edtName.selectAll();
                        edtName.requestFocus();
                        Toast.makeText(getApplicationContext(), "Full name must not be less than 3 characters", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (rdbMale.isChecked()) {
                        gender = rdbMale.getText().toString();
                    }
                    if (rdbFemale.isChecked()) {
                        gender = rdbFemale.getText().toString();
                    }
                    if (cb_1.isChecked()) info += cb_1.getText().toString() + "\n";
                    if (cb_2.isChecked()) info += cb_2.getText().toString() + "\n";
                    if (cb_3.isChecked()) info += cb_3.getText().toString() + "\n";
                    if (cb_4.isChecked()) info += cb_4.getText().toString() + "\n";
                    fullInfo += "Họ Tên: " + fullName + "\n";
                    fullInfo += "Giới tính: " + gender + "\n";
                    fullInfo += "info: " + info;

                    Toast.makeText(getApplicationContext(), fullInfo, Toast.LENGTH_LONG).show();
                }
                catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}

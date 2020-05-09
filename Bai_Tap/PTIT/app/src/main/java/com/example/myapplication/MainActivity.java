package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edtA,edtB,edtResult;
    private Button btnAdd,btnMinus,btnX,btnSlash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtResult = findViewById(R.id.edtResult);

        btnAdd = findViewById(R.id.btnAdd);
        btnMinus = findViewById(R.id.btnMinus);
        btnX = findViewById(R.id.btnX);
        btnSlash = findViewById(R.id.btnSlash);
    }



    @Override
    public void onClick(View v) {

        int a = Integer.parseInt(edtA.getText().toString());
        int b = Integer.parseInt(edtB.getText().toString());

        switch (v.getId()) {
            case R.id.btnAdd:
                add(a,b);
                break;
            case R.id.btnMinus:
                minus(a,b);
                break;
            case R.id.btnX:
                multi(a,b);
                break;
            case R.id.btnSlash:
                if (b == 0) {
                    Toast.makeText(this, "b ko chia duoc 0", Toast.LENGTH_SHORT).show();
                    return;
                }
                splash(a,b);
                break;
        }
    }

    private void add(int a, int b) {
        edtResult.setText((a+b) + "");
    }

    private void minus(int a, int b) {
        edtResult.setText(  (a-b) + "");
    }

    private void multi(int a, int b) {
        edtResult.setText(  (a*b) + "");
    }

    private void splash(int a, int b) {
        edtResult.setText( (a*1.0/b) + "");
    }
}

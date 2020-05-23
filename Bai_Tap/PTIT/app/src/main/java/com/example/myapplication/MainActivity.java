package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtId, edtDay, edtMoney;
    Spinner spUser, spnLoaiDichVu;
    Button btnThem, btnXoa, btnSua, btnThoat;
    ListView lsvShow;
    ArrayList user = new ArrayList();
    ArrayList loaiSach = new ArrayList();
    ArrayList<model> dv = new ArrayList<>();
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }

    private void setEvent() {
        final DatabaseHandler sqlite = new DatabaseHandler(this);

        user.add("Nguyễn Văn A");
        user.add("Nguyễn Văn B");
        user.add("Nguyễn Văn C");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, user);
        spUser.setAdapter(adapter);

        loaiSach.add("Thay Main");
        loaiSach.add("Thay Camera");
        loaiSach.add("Vệ Sinh");
        loaiSach.add("Cài Win");
        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, loaiSach);
        spnLoaiDichVu.setAdapter(adapter1);

        //final ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, phieuMuonData);
        final CustomAdapter adapter2 = new CustomAdapter(this, R.layout.item, dv);
        lsvShow.setAdapter(adapter2);


        sqlite.getAllStudents(dv);
        Log.d("22222", String.valueOf(dv));
        adapter2.notifyDataSetChanged();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtId.getText().toString().equals("")) {
                    edtId.setError("Bạn phải nhập ID");
                    edtId.requestFocus();
                    return;
                }
                if(edtDay.getText().toString().equals("")) {
                    edtDay.setError("Bạn phải nhập ngày");
                    edtDay.requestFocus();
                    return;
                }
                model model = new model();
                model.setId(edtId.getText().toString().trim());
                model.setDay(edtDay.getText().toString().trim());
                model.setMoney(edtMoney.getText().toString().trim());
                model.setUser(spUser.getSelectedItem().toString());
                model.setLoaiDV(spnLoaiDichVu.getSelectedItem().toString());
                sqlite.addStudent(model);
                dv.add(model);
                Log.d("3333333", String.valueOf(dv));
                adapter2.notifyDataSetChanged();


            }
        });

        lsvShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(),0);
                index = position;
                edtId.setText(dv.get(position).getId()+"");
                edtDay.setText(dv.get(position).getDay()+"");
                edtMoney.setText(dv.get(position).getMoney()+"");
                spUser.setSelection(user.indexOf(dv.get(position).getUser()));
                spnLoaiDichVu.setSelection(loaiSach.indexOf(dv.get(position).getLoaiDV()));
            }
        });

        lsvShow.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dv.remove(position);
                adapter2.notifyDataSetChanged();
                return false;
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index != -1 && dv.isEmpty()) {
                    return;
                }
                sqlite.deleteStudent(dv.get(index).getId());
                dv.remove(index);
                adapter2.notifyDataSetChanged();

            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index != -1 && dv.isEmpty()) {
                    return;
                }
                model model = dv.get(index);
                model.setId(edtId.getText().toString().trim());
                model.setDay(edtDay.getText().toString().trim());
                model.setMoney(edtMoney.getText().toString().trim());
                model.setUser(spUser.getSelectedItem().toString());
                model.setLoaiDV(spnLoaiDichVu.getSelectedItem().toString());
                adapter2.notifyDataSetChanged();
                sqlite.updateStudent(model);
            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @SuppressLint("CutPasteId")
    private void setControl() {
        edtId = findViewById(R.id.edtId);
        edtDay = findViewById(R.id.edtDay);
        edtMoney = findViewById(R.id.edtMoney);
        spUser = findViewById(R.id.spUser);
        spnLoaiDichVu = findViewById(R.id.spnLoaiDichVu);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        btnThoat = findViewById(R.id.btnThoat);
        lsvShow = findViewById(R.id.lsvShow);
    }

}

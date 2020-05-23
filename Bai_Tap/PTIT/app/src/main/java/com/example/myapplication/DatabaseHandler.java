package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "MayTinh";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_NAME = "MT";

    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "date";
    private static final String KEY_Money = "money";
    private static final String KEY_User = "user";
    private static final String KEY_LoaiDV = "loaiDV";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void addStudent(model student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, student.getId());
        values.put(KEY_DATE, student.getDay());
        values.put(KEY_Money, student.getMoney());
        values.put(KEY_User, student.getUser());
        values.put(KEY_LoaiDV, student.getLoaiDV());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void getAllStudents(ArrayList<model> DV) {

        String query = "SELECT * FROM " + TABLE_NAME;
        Log.d("3333333", "aaa");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {

            Log.d("3333333", String.valueOf(cursor.getInt(1)));

            model student = new model(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));

            DV.add(student);
            cursor.moveToNext();
        }

    }

    public void updateStudent(model student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_ID, student.getId());
        values.put(KEY_DATE, student.getDay());
        values.put(KEY_Money, student.getMoney());
        values.put(KEY_User, student.getUser());
        values.put(KEY_LoaiDV, student.getLoaiDV());

        db.update(TABLE_NAME, values, KEY_ID + " = ?", new String[] { String.valueOf(student.getId()) });
        db.close();
    }

    public void deleteStudent(String studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?", new String[] { String.valueOf(studentId) });
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_students_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT)", TABLE_NAME, KEY_ID, KEY_DATE, KEY_Money, KEY_User, KEY_LoaiDV);
        db.execSQL(create_students_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_students_table);

        onCreate(db);
    }
}
package com.nhom45.baitap_3.Ultils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nhom45.baitap_3.Models.History;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CurrencySQLiteHelpers extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CurrencyManager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "histories";

    private static final String ID = "id";
    private static final String CODE_FROM = "code_from";
    private static final String VALUE_INPUT = "input";
    private static final String CODE_TO = "code_to";
    private static final String VALUE_OUTPUT = "output";
    private static final String CREATED_AT = "created_at";

    public CurrencySQLiteHelpers(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                TABLE_NAME, ID, CODE_FROM, VALUE_INPUT, CODE_TO, VALUE_OUTPUT, CREATED_AT);
        sqLiteDatabase.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String drop_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        sqLiteDatabase.execSQL(drop_table);
        onCreate(sqLiteDatabase);
    }

    public void add(History history) {
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        ContentValues values = new ContentValues();
        values.put(CODE_FROM, history.getCodeFrom());
        values.put(VALUE_INPUT, history.getValueInput());
        values.put(CODE_TO, history.getCodeTo());
        values.put(VALUE_OUTPUT, history.getValueOutput());
        values.put(CREATED_AT, sdf.format(date));

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<History> getList() {
        ArrayList<History> historyList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + ID + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            History history = new History(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5));
            historyList.add(history);
            cursor.moveToNext();
        }
        return historyList;
    }
}

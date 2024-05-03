package com.example.finalprojectcs373;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SQLiteManager extends SQLiteOpenHelper {

    private static SQLiteManager sqLiteManager;
    private static final String DATABASE_NAME = "EntryDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Entry";
    private static final String ID_FIELD = "id";
    private static final String TITLE_FIELD = "title";
    private static final String BODY_FIELD = "body";
    private static final String CREATED_ON_FIELD = "createdOn";

    @SuppressLint("SimpleDateFormat")
    private static final DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static SQLiteManager instanceOfDatabase(Context context) {
        if(sqLiteManager == null) {
            sqLiteManager = new SQLiteManager(context);
        }

        return sqLiteManager;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sql;
        sql = new StringBuilder()
                .append("CREATE TABLE ")
                .append(TABLE_NAME)
                .append("(")
                .append(ID_FIELD)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(TITLE_FIELD)
                .append(" TEXT, ")
                .append(BODY_FIELD)
                .append(" TEXT, ")
                .append(CREATED_ON_FIELD)
                .append(" TEXT)");

        sqLiteDatabase.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addEntryToDatabase(Entry entry) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(ID_FIELD, entry.getId());
        contentValues.put(TITLE_FIELD, entry.getTitle());
        contentValues.put(BODY_FIELD, entry.getBody());
        contentValues.put(CREATED_ON_FIELD, entry.getCreatedOn());

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public void deleteEntryInDB(Entry entry) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        StringBuilder sql;
        sql = new StringBuilder()
                .append("DELETE FROM ")
                .append(TABLE_NAME)
                .append(" WHERE ")
                .append(ID_FIELD)
                .append(" = ")
                .append(entry.getId());

        sqLiteDatabase.execSQL(sql.toString());
    }

    public void updateEntryInDB(Entry entry) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(ID_FIELD, entry.getId());
        contentValues.put(TITLE_FIELD, entry.getTitle());
        contentValues.put(BODY_FIELD, entry.getBody());
        contentValues.put(CREATED_ON_FIELD, entry.getCreatedOn());

        sqLiteDatabase.update(TABLE_NAME, contentValues, ID_FIELD + " =? ", new String[]{String.valueOf(entry.getId())});
    }

    public void populateEntryListArray() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null)){
            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    int id = result.getInt(0);
                    String title = result.getString(1);
                    String body = result.getString(2);
                    String createdOn = result.getString(3);

                    Entry entry = new Entry(id, title, body, createdOn);
                    Entry.entryArrayList.add(entry);
                }
            }
        } catch (Exception error1){
            error1.printStackTrace();
        }
    }

    private String getStringFromDate(Date date) {
        if (date == null) {
            return null;
        }

        return dateFormat.format(date);
    }

    private Date getDateFromString(String string) {
        try {
            return dateFormat.parse(string);
        } catch (ParseException | NullPointerException e) {
            return null;
        }
    }
}

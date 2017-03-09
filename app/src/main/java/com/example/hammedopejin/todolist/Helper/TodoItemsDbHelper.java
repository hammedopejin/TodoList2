package com.example.hammedopejin.todolist.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by hammedopejin on 3/1/17.
 */

public class TodoItemsDbHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "todoItemManager";

    //Table name
    private static final String TABLE_ITEMS = "items";

    //Table Columns names
    private static final String TASK_ID = "_id";
    private static final String TASK_NAME = "task";
    private static final String TASK_DAY = "day";
    private static final String TASK_MONTH = "month";
    private static final String TASK_YEAR = "year";
    private static final String TASK_NOTE = "note";
    private static final String TASK_PRI = "priority";
    private static final String TASK_STAT = "status";

    public TodoItemsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_ITEMS + " ( " + TASK_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TASK_NAME + " TEXT, " + TASK_DAY + " TEXT, " + TASK_MONTH + " TEXT, " +
                TASK_YEAR + " TEXT, " + TASK_NOTE + " TEXT, " + TASK_PRI + " TEXT, " +
                TASK_STAT + " TEXT )";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);

        // Create tables again
        onCreate(db);
    }


    // Adding new item
    public void addItem(String task, String day, String month, String year, String note, String pri, String stat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TASK_NAME, task);
        values.put(TASK_DAY, day);
        values.put(TASK_MONTH, month);
        values.put(TASK_YEAR, year);
        values.put(TASK_NOTE, note);
        values.put(TASK_PRI, pri);
        values.put(TASK_STAT, stat);

        // Inserting Row
        //db.insertWithOnConflict(TABLE_ITEMS, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.insert(TABLE_ITEMS, null, values);
        db.close(); // Closing database connection
    }

    // Updating database with item
    public void updateItem(String task, String day, String month, String year, String note, String pri, String stat, int flag) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TASK_NAME, task);
        values.put(TASK_DAY, day);
        values.put(TASK_MONTH, month);
        values.put(TASK_YEAR, year);
        values.put(TASK_NOTE, note);
        values.put(TASK_PRI, pri);
        values.put(TASK_STAT, stat);

        // updating row
        db.update(TABLE_ITEMS, values,TASK_ID +"="+ flag, null);

    }

    // Getting single _id
    public int getID(String iValue) {
        SQLiteDatabase db = this.getReadableDatabase();
        int item = 0;
        try {
            String selectQuery = "SELECT " + TASK_ID + " FROM " + TABLE_ITEMS + " WHERE " + TASK_NAME + "= '" + iValue + "'";
            Cursor cursor = db.rawQuery(selectQuery, null);

            //looping through all rows and adding to list

            if (cursor.moveToFirst()) {

                do {
                    item = cursor.getInt(0);

                }

                while (cursor.moveToNext());
            }
            return item;

        } catch (Exception e) {
            item = 0;
            return item;

        } finally {
            db.close();
        }
    }


    // Getting single item
    public ArrayList<String> getAll(String iValue) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> items = new ArrayList<String>();
        try {
            String selectQuery = "SELECT * FROM " + TABLE_ITEMS + " WHERE " + TASK_NAME + "= '" + iValue + "'";
            Cursor cursor = db.rawQuery(selectQuery, null);

            //looping through all rows and adding to list

            if (cursor.moveToFirst()) {

                do {
                    items.add(cursor.getString(1));
                    items.add(cursor.getString(2));
                    items.add(cursor.getString(3));
                    items.add(cursor.getString(4));
                    items.add(cursor.getString(5));
                    items.add(cursor.getString(6));
                    items.add(cursor.getString(7));
                }

                while (cursor.moveToNext());
            }
            return items;

        } catch (Exception e) {
            items = new ArrayList<String>();
            return items;

        } finally {
            db.close();
        }
    }

    // Getting single item
    public ArrayList<String> getItem() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> items = new ArrayList<String>();
        try {
            String selectQuery = "SELECT * FROM " + TABLE_ITEMS + " ORDER BY CASE " +
                    " WHEN priority = "+ "'HIGH' THEN 0" +
                    " WHEN priority = "+ "'MEDIUM' THEN 1" +
                    " WHEN priority = "+ "'LOW' THEN 2" +
                    " END";
            Cursor cursor = db.rawQuery(selectQuery, null);

            //looping through all rows and adding to list

            if (cursor.moveToFirst()) {

                do {
                    items.add(cursor.getString(1));
                    items.add(cursor.getString(6));
                }

                while (cursor.moveToNext());


            }
            return items;

        } catch (Exception e) {
            items = new ArrayList<String>();
            return items;

        } finally {
            db.close();
        }
    }

    // Deleting item
    public void deleteItem(String val) {
        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL("delete from "+ TABLE_ITEMS);
        db.delete(TABLE_ITEMS, TASK_NAME + " = ? ",
                new String[]{val});


        db.close();
    }
}

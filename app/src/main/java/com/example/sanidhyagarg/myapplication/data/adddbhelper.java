package com.example.sanidhyagarg.myapplication.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.sanidhyagarg.myapplication.data.cashContract.addentry;

/**
 * Created by Sanidhya Garg on 02-07-2017.
 */

public class adddbhelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME="addmoney.db";
    private static final int DATABASE_VERSION = 1;

public String h="CREATE TABLE " + addentry.TABLE_NAME + " ("
            + addentry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +addentry.TYPE+" TEXT, "
            +addentry.DATE+" TEXT, "
            +addentry.MONEY_ADDED + " INTEGER NOT NULL, "
            + addentry.DESCRIPTION + " TEXT); ";
    public adddbhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_ADD_TABLE =  "CREATE TABLE " + addentry.TABLE_NAME + " ("
                + addentry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +addentry.TYPE+" TEXT, "
                +addentry.DATE+" TEXT, "
                +addentry.MONEY_ADDED + " INTEGER NOT NULL, "
                + addentry.DESCRIPTION + " TEXT); ";
h=SQL_CREATE_ADD_TABLE;

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_ADD_TABLE);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }}

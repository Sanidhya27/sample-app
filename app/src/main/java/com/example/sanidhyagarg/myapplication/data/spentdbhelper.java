package com.example.sanidhyagarg.myapplication.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.sanidhyagarg.myapplication.data.cashContract.spententry;

/**
 * Created by Sanidhya Garg on 02-07-2017.
 */

public class spentdbhelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME="spentmoney.db";
    private static final int DATABASE_VERSION = 1;

    public spentdbhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_SPENT_TABLE =  "CREATE TABLE " + spententry.TABLE_NAME + " ("
                + spententry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +spententry.TYPE+" TEXT, "
                +spententry.DATE+" TEXT, "
                +spententry.MONEY_SPENT + " INTEGER NOT NULL, "
                + spententry.DESCRIPTION + " TEXT); ";


        // Execute the SQL statement
        db.execSQL(SQL_CREATE_SPENT_TABLE);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }}

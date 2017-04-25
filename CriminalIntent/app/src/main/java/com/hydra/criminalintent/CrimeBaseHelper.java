package com.hydra.criminalintent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import datebase.CrimeDbSchema.CrimeDbSchema;
import datebase.CrimeDbSchema.CrimeDbSchema.CrimeTable;

/**
 * Created by Hydra on 2017/4/26.
 */

public class CrimeBaseHelper extends SQLiteOpenHelper{

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "crimeBase.db";

    public CrimeBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        db.execSQL("create table" + CrimeTable.NAME + "("  +
        "_id integer primary key autoincrement," +
        CrimeTable.Cols.UUID + "," +
        CrimeTable.Cols.DATE + "," +
        CrimeTable.Cols.SOLVED + "," +
        CrimeTable.Cols.TITLE + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

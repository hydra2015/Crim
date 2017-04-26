package com.hydra.criminalintent.bean;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.hydra.criminalintent.CrimeBaseHelper;
import com.hydra.criminalintent.CrimeCursorWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import datebase.CrimeDbSchema.CrimeDbSchema;

/**
 * Created by Hydra on 2017/4/18.
 */

public class CrimeLab {

    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;

    private Context mContext;
    private SQLiteDatabase helper;

    //单例构造方法
    private CrimeLab(Context ctx){

        mContext = ctx.getApplicationContext();
        helper = new CrimeBaseHelper(mContext).getWritableDatabase();

        mCrimes = new ArrayList<>();
        //添加数据
//        for (int i = 0; i < 100; i++){
//            Crime crime = new Crime();
//            crime.setTitle("事件" + i);
//            crime.setSolved(i % 2 == 0 );
//            mCrimes.add(crime);
//        }
    }

    //添加crime
    public void addCrime(Crime crime){
//        mCrimes.add(crime);
        ContentValues values = getContentValues(crime);

        helper.insert(CrimeDbSchema.CrimeTable.NAME, null, values);
    }
    //获取单例对象
    public static CrimeLab get(Context ctx){
        if (sCrimeLab == null){
            sCrimeLab = new CrimeLab(ctx);
        }
        return sCrimeLab;
    }
    //获取集合
    public List<Crime> getCrimes() {
//        return mCrimes;
//        return  new ArrayList<>();
        List<Crime> crimes = new ArrayList<>();
    //查询所有数据
        CrimeCursorWrapper cusor = queryCrimes(null, null);
//        遍历数据
        cusor.moveToFirst();
        try {
            while (!cusor.isAfterLast()){
                crimes.add(cusor.getCrime());
                cusor.moveToNext();
            }
        }finally {
            cusor.close();
        }
        return crimes;

    }
    //获取对象
    public Crime getCrime(UUID id){
//        for (Crime crime : mCrimes ) {
//            Log.e("crime.getId", crime.getId().toString());
//            Log.e("id", id+"");
//            if (crime.getId().equals(id)){
//                Log.e("事件", "getCrime");
//                return crime;
//            }
//        }
//        Log.e("事件", "返回空了");
        CrimeCursorWrapper cursor = queryCrimes(CrimeDbSchema.CrimeTable.Cols.UUID + "= ?" ,
                new String[] { id.toString()});
        try {
            if (cursor.getCount() == 0){
                return  null;
            }
            cursor.moveToFirst();
            return cursor.getCrime();
        }finally {
            cursor.close();
        }

    }

    public void updateCrime(Crime crime){
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);

        helper.update(CrimeDbSchema.CrimeTable.NAME, values, CrimeDbSchema.CrimeTable.Cols.UUID + "= ?",new String [] { uuidString});
    }

    //数据库的操作通过contentvalues操作
    private ContentValues getContentValues(Crime crime){
        ContentValues values = new ContentValues();
        values.put(CrimeDbSchema.CrimeTable.Cols.DATE, crime.getDate().getTime());
        values.put(CrimeDbSchema.CrimeTable.Cols.UUID, crime.getId().toString());
        values.put(CrimeDbSchema.CrimeTable.Cols.TITLE, crime.getTitle());
        values.put(CrimeDbSchema.CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);
        values.put(CrimeDbSchema.CrimeTable.Cols.SUSPECT, crime.getSuspect());

        return values;
    }

//    public Cursor query(
//            String table,
//            String[] columns,
//            String where,
//            String[] whereArgs,
//            String groupBy,
//            String having,
//            String orderBy,
//            String limit)
//    查询数据
//    private Cursor queryCrimes(String whereClause, String [] whereargs){
    //其实也就省去了新建该类的麻烦
      private CrimeCursorWrapper queryCrimes(String whereClause, String [] whereargs){
        Cursor cursor = helper.query(CrimeDbSchema.CrimeTable.NAME,
                null,
                whereClause,
                whereargs,
                null,
                null,
                null
        );
        return new CrimeCursorWrapper(cursor);
    }
}

package com.hydra.criminalintent.bean;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Hydra on 2017/4/18.
 */

public class CrimeLab {

    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;

    //单例构造方法
    private CrimeLab(Context ctx){
        mCrimes = new ArrayList<>();
       //添加数据
        for (int i = 0; i < 100; i++){
            Crime crime = new Crime();
            crime.setTitle("事件" + i);
            crime.setSolved(i % 2 == 0 );
            mCrimes.add(crime);
        }
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
        return mCrimes;
    }
    //获取对象
    public Crime getCrime(UUID id){
        for (Crime crime : mCrimes ) {
            Log.e("crime.getId", crime.getId().toString());
            Log.e("id", id+"");
            if (crime.getId().equals(id)){
                Log.e("事件", "getCrime");
                return crime;
            }
        }
        Log.e("事件", "返回空了");
        return null;
    }
}

package com.hydra.criminalintent.bean;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Hydra on 2017/4/17.
 */

public class Crime {

    //一定的范围内（从特定的名字空间到全球）唯一的机器生成的标识符
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean isSolved;

    public void setDate(Date date) {
        mDate = date;
    }

    public UUID getId() {
        return mId;
    }

    public Crime(){
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }
}

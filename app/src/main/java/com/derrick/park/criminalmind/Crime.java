package com.derrick.park.criminalmind;

import java.util.Date;
import java.util.UUID;

/**
 * Created by park on 2017-05-31.
 */

public class Crime {
    // UUID makes unique ID values
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private boolean mRequirePolice;


    // getter
    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public Date getDate() {
        return mDate;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public boolean ismRequiresPolice() {
        return mRequirePolice;
    }

    // setter
    public void setTitle(String title) {
        mTitle = title;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public void setmRequiresPolice(boolean mRequiresPolice) {
        this.mRequirePolice = mRequiresPolice;
    }
}

package com.example.kisannetwork.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shobhit on 22/7/17.
 */

public class MessageHistory implements Parcelable {

    public static final Creator<MessageHistory> CREATOR = new Creator<MessageHistory>() {
        @Override
        public MessageHistory createFromParcel(Parcel in) {
            return new MessageHistory(in);
        }

        @Override
        public MessageHistory[] newArray(int size) {
            return new MessageHistory[size];
        }
    };

    private String name;
    private String otp;
    private String timeStamp;

    private MessageHistory(Parcel in) {
        name = in.readString();
        otp = in.readString();
        timeStamp = in.readString();
    }

    public MessageHistory() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(otp);
        dest.writeString(timeStamp);
    }
}

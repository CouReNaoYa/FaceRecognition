package com.example.facerecognition.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class BaseTextHttpResult1<T> implements Parcelable {

    /**
     * code : 200
     * msg : success
     * data : {"id":33,"author":2,"authorName":"E","classify":1,"classifyName":"默认分类","status":0,"createTime":"2020-10-11 21:33:37","updateTime":"2020-10-11 21:33:37","name":"androidx.appcompat.widget.AppCompatEditText{6d387e4 VFED..CL. ........ 0,0-1080,83 #7f07005f app:id/et_creat_text_title aid=1073741824}","content":"androidx.appcompat.widget.AppCompatEditText{6a5fb02 VFED..CL. .F...... 0,110-1080,660 #7f07005e app:id/et_creat_text_content aid=1073741825}","summary":"androidx.appcompat.widget.AppCompatEditText{6a5fb02VFED..CL..F......0,110-1080,660#7f07005eapp:id/et_creat_text_contentaid=1073741825}"}
     */

    private int code;
    private String msg;
    private T data;

    protected BaseTextHttpResult1(Parcel in) {
        code = in.readInt();
        msg = in.readString();
    }

    public static final Creator<BaseTextHttpResult1> CREATOR = new Creator<BaseTextHttpResult1>() {
        @Override
        public BaseTextHttpResult1 createFromParcel(Parcel in) {
            return new BaseTextHttpResult1(in);
        }

        @Override
        public BaseTextHttpResult1[] newArray(int size) {
            return new BaseTextHttpResult1[size];
        }
    };

    public BaseTextHttpResult1() {

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeString(msg);
    }
}

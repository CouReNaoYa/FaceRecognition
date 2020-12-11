package com.example.facerecognition.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class FaceResult implements Parcelable {


    /**
     * request_id : 1607671973,73590c8a-534f-404d-b5d1-c56308fa977e
     * time_used : 63
     * faces : [{"face_token":"7357f18fb42325c5d5d82b56fbbd9329","face_rectangle":{"top":198,"left":138,"width":255,"height":255},"attributes":{"gender":{"value":"Female"},"age":{"value":33}}}]
     * image_id : /qITE9JC0dhtIAfH7761CQ==
     * face_num : 1
     */

    private String request_id;
    private int time_used;
    private String image_id;
    private int face_num;
    private List<FacesBean> faces;

    public FaceResult(Parcel in) {
    }
    public FaceResult() {
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FaceResult> CREATOR = new Creator<FaceResult>() {
        @Override
        public FaceResult createFromParcel(Parcel in) {
            return new FaceResult(in);
        }

        @Override
        public FaceResult[] newArray(int size) {
            return new FaceResult[size];
        }
    };

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public int getTime_used() {
        return time_used;
    }

    public void setTime_used(int time_used) {
        this.time_used = time_used;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public int getFace_num() {
        return face_num;
    }

    public void setFace_num(int face_num) {
        this.face_num = face_num;
    }

    public List<FacesBean> getFaces() {
        return faces;
    }

    public void setFaces(List<FacesBean> faces) {
        this.faces = faces;
    }

}

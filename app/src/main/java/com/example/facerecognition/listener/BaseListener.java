package com.example.facerecognition.listener;

public interface  BaseListener<T>{

        void getSuccess(T t);
        void getFailed(Throwable throwable);


}

package com.example.facerecognition.util;

import android.os.Build;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageToBase64 {
    public static void main(String[] args) {
        String s = ImageToBase64ByLocal("图片路径");
        System.out.println(s);
    }
    public ImageToBase64(String string){
        ImageToBase64ByLocal(string);
    }
    public static String ImageToBase64ByLocal(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();

        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }
}
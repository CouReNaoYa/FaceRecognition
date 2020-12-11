package com.example.facerecognition.persent;

import android.widget.Toast;

import com.example.facerecognition.bean.FaceResult;
import com.example.facerecognition.listener.OnGetFaceListener;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class FacePersent {
    public String post(String url, String var1, String var2, String var3, String var4,final OnGetFaceListener listener) {
        HttpURLConnection con = null;
        Map<String, String> params = new HashMap<>();
        params.put("api_key", var1);
        params.put("api_secret", var2);
        params.put("image_base64", var3);
        params.put("return_attributes", var4);


        // 构建请求参数
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> e : params.entrySet()) {
            sb.append(e.getKey());
            sb.append("=");
            sb.append(e.getValue());
            sb.append("&");
        }

        // 尝试发送请求
        try {
            URL u = new URL(url);
            con = (HttpURLConnection) u.openConnection();
            // POST 只能为大写，严格限制，post会不识别
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            DataOutputStream osw = new DataOutputStream(con.getOutputStream());
            osw.flush();

            osw.close();
            // 读取返回内容
            StringBuilder buffer = new StringBuilder();

            //一定要有返回值，否则无法把请求发送给server端。
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
//            String temp;
//            while ((temp = br.readLine()) != null) {
//                buffer.append(temp);
//                buffer.append("\n");
//            }
//
//            return buffer.toString();

            StringBuilder a = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                a.append(line);
            }
            String result = a.toString();
            System.out.println(result);

            // 判断请求是否成功
            if (con.getResponseCode() == 200) {
                // 获取返回的数据
                FaceResult faceResult=new FaceResult();
                Gson gson = new Gson();
                faceResult = gson.fromJson(result, faceResult.getClass());
                listener.getSuccess(faceResult);
                //System.out.println("json转换成bean过程"+loginResult.getData().getName());
                // Log.e(TAG, "Post方式请求成功，result--->" + result);
            } else {
                System.out.println("请求失败");                listener.getFailed(new IllegalAccessException("网络请求失败"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return null;
    }
}

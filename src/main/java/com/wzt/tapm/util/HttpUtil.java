package com.wzt.tapm.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;

public class HttpUtil {
    public JSONObject object;
    Callback callback = new Callback() {
        @Override
        public void onResponse(Call call, Response response) throws IOException {
            assert response.body() != null;
            String responseData = response.body().string();
            object = JSON.parseObject(responseData);
        }

        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
        }
    };

    private void sendGet(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .get()
                .build();
        client.newCall(request).enqueue(callback);
    }
    private void sendPost(String address, okhttp3.Callback callback,RequestBody body) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
    public  void OKHttpWithCallBack(boolean isGet,String address,Callback callback,RequestBody body){
        if (isGet){
            sendGet(address,callback);
        }else {
            sendPost(address,callback,body);
        }
    }
    public  JSONObject OKHttpWithoutCallBack(boolean isGet,String address, RequestBody body){
        if (isGet){
            sendGet(address,callback);
        }else {
            sendPost(address,callback,body);
        }
        return object;
    }
}

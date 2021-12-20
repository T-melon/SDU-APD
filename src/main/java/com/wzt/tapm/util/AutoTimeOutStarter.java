package com.wzt.tapm.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AutoTimeOutStarter {

    private static final String BASE_URL = "http://localhost:8081";
    private static JSONArray data ;
    private static final HttpUtil httpUtil = new HttpUtil();

    public static void main(String[] args) {
        Callback updateListCallback = new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.body() != null){
                    String responseData = response.body().string();
                    JSONObject object = JSON.parseObject(responseData);
                    data = object.getJSONArray("data");
                    if (data.size()>0){
                        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
                        sdf.applyPattern("yyyy-MM-dd");
                        Date date = new Date();
                        String DayNow = sdf.format(date);
                        //处理数据
                        for (int i = 0; i < data.size(); i++) {
                            JSONObject jsonObject = data.getJSONObject(i);
                            String ddl = jsonObject.getString("ddl");
                            if (ddl.compareTo(DayNow) < 0){
                                String status = jsonObject.getString("status");
                                FormBody formBody;
                                if (status.equals("2")){
                                    formBody = new FormBody.Builder()
                                            .add("demand_id",jsonObject.getString("demand_id"))
                                            .add("commit","需求已超时")
                                            .build();
                                    timeOutFrom2To6(formBody);
                                }
                                if (status.equals("4")){
                                    formBody = new FormBody.Builder()
                                            .add("demand_id",jsonObject.getString("demand_id"))
                                            .add("commit","需求已超时")
                                            .build();
                                    timeOutFrom4To6(formBody);
                                }
                            }
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                //打印异常栈
                e.printStackTrace();
            }
        };

        Thread t = new Thread(() ->{

            while (true) {
                updateList(updateListCallback);
                try {
                    Thread.sleep(86400*1000);//一天
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    private static void updateList(Callback updateListCallback){
        FormBody formBody = new FormBody.Builder().build();
        httpUtil.OKHttpWithCallBack(false,BASE_URL+"/getDemandDdlData", updateListCallback,formBody);
    }
    private static void timeOutFrom2To6(FormBody formBody){

        httpUtil.OKHttpWithoutCallBack(false,BASE_URL+"/demand/from2to6",formBody);
    }
    private static void timeOutFrom4To6(FormBody formBody){
        httpUtil.OKHttpWithoutCallBack(false,BASE_URL+"/demand/from4to6",formBody);
    }
}

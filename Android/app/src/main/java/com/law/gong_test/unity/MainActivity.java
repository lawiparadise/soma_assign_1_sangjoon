package com.law.gong_test.unity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.law.gong_test.R;
import com.law.gong_test.act.FriendActivity;
import com.law.gong_test.act.PresentActivity;
import com.law.gong_test.async.MyAsyncCallbackSimple;
import com.law.gong_test.async.MyAsyncExecutor;
import com.law.gong_test.common.Common;
import com.law.gong_test.kakao.GlobalApplication;
import com.unity3d.player.UnityPlayer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.concurrent.Callable;

public class MainActivity extends UnityPlayerActivity {

    public static final String TAG = "MainActivity";
    //
    public String signUpTime = "";
    public String playTime = "";
    public String endTime = "";

    //
    String name;
    public static String id;
    String img;
    String first_install_time;

    //
    Context context;

    //
    Common common;

    //
    LinkedHashMap<String, String> hashMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;

        common = Common.getInstance();

        Intent intent = getIntent();
        name = intent.getStringExtra("Nickname");
        id = intent.getStringExtra("id");

        name = "수미";
        id = "20";
        img = intent.getStringExtra("img");

        Toast.makeText(getApplicationContext(), "Nick : " + name, Toast.LENGTH_SHORT).show();
        if(GlobalApplication.firstCnt == 0){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
            first_install_time = df.format(new Date());
            new MyAsyncExecutor<String>((Activity)context).setCallable(first).setCallback(firstBack).execute("true");

            GlobalApplication.firstCnt = 1;
        }
    }

    Callable<String> first = new Callable<String>() {
        @Override
        public String call() throws Exception {
            Common common = Common.getInstance();
            LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();

//            Nickname = "¿ÖÁö¿Ö";
//            Nickname = "왜지왜";
/*            hashMap.put("kind", "first");
            hashMap.put("id", id);
            hashMap.put("name", Nickname);
            hashMap.put("first_install_time", first_install_time);*/

            //
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("kind", "first");
            jsonObject.addProperty("id", id);
            jsonObject.addProperty("name", name);
            jsonObject.addProperty("img", img);
            jsonObject.addProperty("first_install_time",first_install_time);

            return common.connect(Common.MAIN_URL, jsonObject);
        }
    };

    MyAsyncCallbackSimple<String> firstBack = new MyAsyncCallbackSimple<String>(){
        @Override
        public void onResult(String result) {
            if(result.equals("fail")){
                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
                Log.e("callbackSimple", "connect fail");
                return;
            }
        }
    };

    public void getSignUpTime(final String endTime){
        signUpTime = endTime;
        handler.sendEmptyMessage(0);
    }
    public void getPlayTime(final String endTime){
        playTime = endTime;
        handler.sendEmptyMessage(1);
    }
    public void getEndTime(final String time){
        endTime = time;
        handler.sendEmptyMessage(2);
    }

    public void addFriend(){
        handler.sendEmptyMessage(3);
    }
    public void QuitApp(){
        handler.sendEmptyMessage(4);
    }

    public void rangking(){
        handler.sendEmptyMessage(5);
    }
    public void present(){
        handler.sendEmptyMessage(6);
    }

    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
//                    UnityPlayer.UnitySendMessage("Main Camera", "AndroidPhoneNum", PhoneNumber);
//                    Toast.makeText(getApplicationContext(), "0, " + signUpTime, Toast.LENGTH_SHORT).show();

                    break;
                case 1:
//                    Toast.makeText(getApplicationContext(), "1, "+playTime, Toast.LENGTH_SHORT).show();

                    break;
                case 2:
//                    Toast.makeText(getApplicationContext(), "2, "+endTime, Toast.LENGTH_SHORT).show();
                    new MyAsyncExecutor<String>((Activity)context).setCallable(callable).setCallback(callbackSimple).execute("true");

                    break;
                case 3:
//                    Toast.makeText(getApplicationContext(), "3, addFriend", Toast.LENGTH_SHORT).show();
                    new MyAsyncExecutor<String>((Activity)context).setCallable(friendCall).setCallback(friendCallBack).execute("true");

                    break;
                case 4:
//                    Toast.makeText(getApplicationContext(), "4 Quit", Toast.LENGTH_SHORT).show();
                    finishMessage();
                    break;
                case 5:
//                    Toast.makeText(getApplicationContext(), "5 rangking", Toast.LENGTH_SHORT).show();
                    new MyAsyncExecutor<String>((Activity)context).setCallable(rankCall).setCallback(rankCallBack).execute("true");
                    break;
                case 6:
//                    Toast.makeText(getApplicationContext(), "6 present", Toast.LENGTH_SHORT).show();
                    new MyAsyncExecutor<String>((Activity)context).setCallable(presentCall).setCallback(presentCallBack).execute("true");
                    break;
                default:
                    break;
            }
        }
    };

    Callable<String> callable = new Callable<String>() {
        @Override
        public String call() throws Exception {
            Common common = Common.getInstance();


            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("kind", "score");
            jsonObject.addProperty("id", id);
            jsonObject.addProperty("signup_time", signUpTime);
            jsonObject.addProperty("play_time", playTime);
            jsonObject.addProperty("end_time", endTime);
            jsonObject.addProperty("score", playTime);

            return common.connect(Common.MAIN_URL, jsonObject);
        }
    };

    MyAsyncCallbackSimple<String> callbackSimple = new MyAsyncCallbackSimple<String>(){
        @Override
        public void onResult(String result) {
            if(result.equals("fail")){
                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
                Log.e("callbackSimple", "connect fail");
                return;
            }
        }
    };


    Callable<String> rankCall = new Callable<String>() {
        @Override
        public String call() throws Exception {
            Common common = Common.getInstance();

            LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
            hashMap.put("kind", "rank");
            return common.connect(Common.MAIN_URL, hashMap);
        }
    };

    MyAsyncCallbackSimple<String> rankCallBack = new MyAsyncCallbackSimple<String>(){
        @Override
        public void onResult(String result) {
            if(result.equals("fail")){
                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
                Log.e("callbackSimple", "connect fail");
                return;
            } else{
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            }
        }
    };



    Callable<String> friendCall = new Callable<String>() {
        @Override
        public String call() throws Exception {
            Common common = Common.getInstance();

            LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
            hashMap.put("kind", "friend");

            return common.connect(Common.MAIN_URL, hashMap);
        }
    };

    MyAsyncCallbackSimple<String> friendCallBack = new MyAsyncCallbackSimple<String>(){
        @Override
        public void onResult(String result) {
            if(result.equals("fail")){
                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
                Log.e("callbackSimple", "connect fail");
                return;
            } else{
//                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, FriendActivity.class);
                intent.putExtra("list", result);
                startActivity(intent);

            }
        }
    };


    Callable<String> presentCall = new Callable<String>() {
        @Override
        public String call() throws Exception {
            Common common = Common.getInstance();

            LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
            hashMap.put("kind", "friend");

            return common.connect(Common.MAIN_URL, hashMap);
        }
    };

    MyAsyncCallbackSimple<String> presentCallBack = new MyAsyncCallbackSimple<String>(){
        @Override
        public void onResult(String result) {
            if(result.equals("fail")){
                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
                Log.e("callbackSimple", "connect fail");
                return;
            } else{
//                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, PresentActivity.class);
                intent.putExtra("list", result);
                startActivity(intent);

            }
        }
    };







    public void finishMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("종료?");
        builder.setCancelable(false);
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UnityPlayer.UnitySendMessage("Main Camera", "FinishApp", "");
            }
        });
        builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }

}

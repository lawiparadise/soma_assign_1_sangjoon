package com.law.gong_test.async;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;


import com.law.gong_test.view.Indicator;

import java.util.concurrent.Callable;

/**
 * Created by GDGO on 2016-02-05.
 * AsyncTask를 만들고 외부 객체인 callable과 callback AsyncTask 안에서 실행시켜 준다. 그리고 그 실행 결과는 MainActivity 안에서 변경할 수 있다. AsyncTask의 함수화.
 */
public class MyAsyncExecutor<T> extends AsyncTask<String, Integer, T> implements Notifyable {

    private Callable<T> callable;
    private MyAsyncCallbackSimple<T> callbackSimple;
    private Activity activity;
    private Indicator mIndicator;

    private String state = null;

    public MyAsyncExecutor(Activity activity){
        this.activity = activity;
        mIndicator = new Indicator(activity);

    }

    public MyAsyncExecutor(){

    }
    public MyAsyncExecutor<T> setCallable(Callable<T> callable){
        this.callable = callable;
        return this;
    }

    public MyAsyncExecutor<T> setCallback(MyAsyncCallbackSimple callback){
        this.callbackSimple = callback;
        return this;
    }
    @Override
    protected T doInBackground(String... params) {
        try{
            if(String.valueOf(params[0]).equals("false")){
                if (mIndicator != null && mIndicator.isShowing()) {
                    mIndicator.hide();
                }
            } else{
                if (!mIndicator.isShowing()) {
                    mIndicator.show();
                }
            }
            return callable.call();
        } catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    @Override
    protected void onPostExecute(T t) {
        if (mIndicator != null && mIndicator.isShowing()) {
            mIndicator.hide();
        }

        if(callbackSimple != null){
            callbackSimple.onResult(t);
        }
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        Log.e("My onProgress", Integer.toString(values[0]));
    }

    @Override
    public void notifyProgress(int val) {
        super.publishProgress(val);
    }
}

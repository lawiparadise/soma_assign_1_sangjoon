package com.law.gong_test.kakao;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.Display;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.kakao.auth.KakaoSDK;

/**
 * Created by GDGO on 2016-07-14.
 */
public class GlobalApplication extends Application {
    private static GlobalApplication mInstance = null;
    private static volatile Activity currentActivity = null;
    private ImageLoader imageLoader;

    //
    public  static int firstCnt = 0;

    public static Activity getCurrentActivity() {
        Log.d("TAG", "++ currentActivity : " + (currentActivity != null ? currentActivity.getClass().getSimpleName() : ""));
        return currentActivity;
    }

    public static void setCurrentActivity(Activity currentActivity) {
        GlobalApplication.currentActivity = currentActivity;
    }

    public static GlobalApplication getGlobalApplicationContext() {
        if(mInstance == null)
            throw new IllegalStateException("this application does not inherit GlobalApplication");
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        KakaoSDK.init(new KakaoSDKAdapter());
        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {
            final LruCache<String, Bitmap> imageCache = new LruCache<String, Bitmap>(3);

            @Override
            public void putBitmap(String key, Bitmap value) {
                imageCache.put(key, value);
            }

            @Override
            public Bitmap getBitmap(String key) {
                return imageCache.get(key);
            }
        };

        imageLoader = new ImageLoader(requestQueue, imageCache);
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        mInstance = null;
    }
    public static Display mDisplay;

    public static void setDisplay(Display display) {
        mDisplay = display;
    }
    public static int getDisplayWidth(){
        return mDisplay.getWidth();
    }

    public static int getDisplayHeight(){
        return mDisplay.getHeight();
    }
    public int resize_Height(int width, int height, int resize_width){
        return (this.getDisplayHeight()*resize_width)/getDisplayWidth();
    }
}

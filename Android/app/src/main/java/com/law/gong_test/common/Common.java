package com.law.gong_test.common;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by GDGO on 2016-02-03.
 */
public class Common extends HttpConnect {

        public static String BASE_URL = "http://52.78.4.220";
    public static final String MAIN_URL = BASE_URL + "/temp/Main";

//    public static String BASE_URL = "http://192.168.43.6:8080";
//    public static final String MAIN_URL = BASE_URL + "/SoMaProject/Main";

    public static ArrayList<Activity> actList = new ArrayList<Activity>();

    //그냥 GET
    public String connect(String url) {
        return getStringFromInputstream(getInputstreamFromUrl(url));
    }

    //key와 value 있는 GET , GET은 HashMap으로 한다.
    public String connect(String url, LinkedHashMap<String, String> keyVal) {
        return getStringFromInputstream(getInputstreamFromUrl(getUrlForGET2(url, keyVal)));
    }

    //POST , POST는 json으로 한다.
    public String connect(String url, JsonObject keyVal) {
        return getStringFromInputstream(getInputstreamFromUrl(url, keyVal));
    }

    //MULTI, Mulit의 Post는 HashMap으로 한다.
    public String connect(String url, LinkedHashMap<String, String> keyVal, LinkedHashMap<String, String> fileMap) {
        return getStringFromInputstream(getInputstreamFromUrl(url, keyVal, fileMap));
    }


    private Common() {
    }

    private static class Singleton {
        private static final Common common = new Common();
    }

    public static Common getInstance() {
        return Singleton.common;
    }

    //view로 바꿔도 되나?
    public static void bindTextScrolling(TextView view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    // 터치가 눌렸을때 터치 이벤트를 활성화한다.
                    case MotionEvent.ACTION_DOWN:
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    // 터치가 끝났을때 터치 이벤트를 비활성화한다 [원상복구]
                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;

                }
                return false;
            }
        });
    }

    public static void bindEditTextScrolling(EditText view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    // 터치가 눌렸을때 터치 이벤트를 활성화한다.
                    case MotionEvent.ACTION_DOWN:
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    // 터치가 끝났을때 터치 이벤트를 비활성화한다 [원상복구]
                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return false;
            }
        });
    }

    public static int exifOrientationToDegrees(int exifOrientation)
    {
        if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_90)
        {
            return 90;
        }
        else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_180)
        {
            return 180;
        }
        else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_270)
        {
            return 270;
        }
        return 0;
    }

    public static Bitmap rotate(Bitmap bitmap, int degrees)
    {
        if(degrees != 0 && bitmap != null)
        {
            Matrix m = new Matrix();
            m.setRotate(degrees, (float) bitmap.getWidth() / 2,
                    (float) bitmap.getHeight() / 2);

            try
            {
                Bitmap converted = Bitmap.createBitmap(bitmap, 0, 0,
                        bitmap.getWidth(), bitmap.getHeight(), m, true);
                if(bitmap != converted)
                {
                    bitmap.recycle();
                    bitmap = converted;
                }
            }
            catch(OutOfMemoryError ex)
            {
                // 메모리가 부족하여 회전을 시키지 못할 경우 그냥 원본을 반환합니다.
            }
        }
        return bitmap;
    }

}

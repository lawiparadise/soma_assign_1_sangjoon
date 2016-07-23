package com.law.gong_test.kakao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.kakao.auth.ErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.helper.log.Logger;
import com.law.gong_test.unity.MainActivity;

public class KakaoSignupActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestMe();
    }
    protected void requestMe() { //유저의 정보를 받아오는 함수
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get user info. msg=" + errorResult;
                Logger.d(message);

                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                if (result == ErrorCode.CLIENT_ERROR_CODE) {
                    finish();
                } else {
                    redirectLoginActivity();
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                redirectLoginActivity();
            }

            @Override
            public void onNotSignedUp() {
            } // 카카오톡 회원이 아닐 시 showSignup(); 호출해야함

            @Override
            public void onSuccess(UserProfile userProfile) {  //성공 시 userProfile 형태로 반환
                Logger.d("UserProfile : " + userProfile);

//                Log.e("MY", userProfile.getNickname());

                redirectMainActivity(userProfile); // 로그인 성공시 MainActivity로
            }
        });
    }

    private void redirectMainActivity(UserProfile userProfile) {
        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("name", userProfile.getNickname());
        intent.putExtra("id", Long.toString(userProfile.getId()));
        intent.putExtra("img", userProfile.getThumbnailImagePath());

        startActivity(intent);
        finish();
    }

    protected void redirectLoginActivity() {
        final Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }


}

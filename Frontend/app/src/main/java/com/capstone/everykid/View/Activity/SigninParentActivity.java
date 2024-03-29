package com.capstone.everykid.View.Activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;

import com.capstone.everykid.Model.CreateAccountItem;
import com.capstone.everykid.Model.LoginRequest;
import com.capstone.everykid.Model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.capstone.everykid.R;
import com.capstone.everykid.RetrofitClient;
import com.capstone.everykid.Model.G;

public class SigninParentActivity extends AppCompatActivity {

    EditText userID, userPW;
    Button signinBtn, createBtn;
    private RetrofitClient retrofitClient;
    private com.capstone.everykid.RetrofitAPI.RetrofitAPI RetrofitAPI;
    CreateAccountItem createAccountItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_parent);

        userID = (EditText) findViewById(R.id.userID);
        userPW = (EditText) findViewById(R.id.userPW);
        signinBtn = (Button) findViewById(R.id.signinparent_btn);
        createBtn = (Button) findViewById(R.id.signupparent_btn);

        if (!getPreferenceString("autoLoginId").equals("") && !getPreferenceString("autoLoginPw").equals("")) {
            checkAutoLogin(getPreferenceString("autoLoginId"));
        }

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SigninParentActivity.this, SignupActivity.class);
                intent.putExtra("User", "Parent");
                startActivity(intent);
            }
        });

        //로그인 버튼
        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = userID.getText().toString();
                String pw = userPW.getText().toString();

                if (id.trim().length() == 0 || pw.trim().length() == 0 || id == null || pw == null) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(SigninParentActivity.this);
                    builder.setTitle("알림")
                            .setMessage("로그인 정보를 입력바랍니다.")
                            .setPositiveButton("확인", null)
                            .create()
                            .show();
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                } else {
                    //로그인 통신
                    login();
                }

            }
        });
    }

    public void login() {
        String userId = userID.getText().toString().trim();
        String userPassword = userPW.getText().toString().trim();

        //loginRequest에 사용자가 입력한 id와 pw를 저장
        LoginRequest loginRequest = new LoginRequest(userId, userPassword);

        //retrofit 생성
        retrofitClient = RetrofitClient.getInstance();
        RetrofitAPI = RetrofitClient.getRetrofitInterface();

        //loginRequest에 저장된 데이터와 함께 init에서 정의한 getLoginResponse 함수를 실행한 후 응답을 받음
        RetrofitAPI.getLoginResponse(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                Log.d("retrofit", "Data fetch success");

                //통신 성공
                if (response.isSuccessful() && response.body() != null) {

                    //response.body()를 result에 저장
                    LoginResponse result = response.body();

                    //받은 토큰 저장
                    String token = result.getToken();

                    if (result.getStatus().equals(200)) {
                        String userId = userID.getText().toString();
                        String userPassword = userPW.getText().toString();

                        //다른 통신을 하기 위해 token 저장
                        setPreference("token", token);

                        setPreference("autoLoginId", userId);
                        setPreference("autoLoginPw", userPassword);
<<<<<<< HEAD
=======
                        setPreference("autoUser","p");
>>>>>>> 77b65ff8e852b6338b288b0944a443f47c37751e

                        createAccountItem.User = "p";
                        createAccountItem.Name=result.getPname();
                        createAccountItem.Email=result.getPemail();
<<<<<<< HEAD

                        String phone =result.getPphone();
                        createAccountItem.Phone=phone;

                        createAccountItem.Id=userId;

                        createAccountItem.K_name=result.getKname();
                        createAccountItem.K_phone=result.getKphone();
                        createAccountItem.K_address=result.getKaddress();
                        createAccountItem.K_kid=Long.parseLong(result.getKkid());
                        createAccountItem.Tname=result.getPtname();
                        createAccountItem.P_kid=Long.parseLong(result.getPkid());
                        createAccountItem.C_name=result.getC_name();
                        createAccountItem.C_age=result.getC_age();
                        //채팅
                        SharedPreferences preferences= getSharedPreferences("chataccount",MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferences.edit();

                        editor.putString("chat_name",createAccountItem.Name);

                        editor.commit();


=======
                        createAccountItem.Phone=result.getPphone();
                        createAccountItem.Id=userId;
                        createAccountItem.K_name=result.getKname();
                        createAccountItem.K_phone=result.getKphone();
                        createAccountItem.K_address=result.getKaddress();
                        createAccountItem.Tname=result.getPtname();
                        createAccountItem.C_img=result.getC_img();

                        createAccountItem.C_name=result.getC_name();
                        createAccountItem.C_age=result.getC_age();
                        //로그인할 때 가끔씩 NumberFormatException이 생김. 이유를 모르겠음.
                        try {
                            createAccountItem.K_kid = Long.parseLong(result.getKkid());
                            createAccountItem.P_kid=Long.parseLong(result.getPkid());
                        } catch (NumberFormatException e) {
                            return;
                        }

                        //채팅
                        SharedPreferences preferences= getSharedPreferences("chataccount",MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putString("chat_name",createAccountItem.Name);
                        editor.commit();

>>>>>>> 77b65ff8e852b6338b288b0944a443f47c37751e
                        Toast.makeText(SigninParentActivity.this, createAccountItem.Name + "님 환영합니다.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SigninParentActivity.this, MainParent.class);
                        intent.putExtra("userId", userId);
                        startActivity(intent);
                        SigninParentActivity.this.finish();

                    } else if(result.getStatus().equals(0)) {

                        String userId = userID.getText().toString();
                        String userPassword = userPW.getText().toString();

                        //다른 통신을 하기 위해 token 저장
                        setPreference(token, token);

                        setPreference("autoLoginId", userId);
                        setPreference("autoLoginPw", userPassword);
<<<<<<< HEAD

                        createAccountItem.User = "p";
                        createAccountItem.Name=result.getPname();
                        createAccountItem.Email=result.getPemail();

                        String phone =result.getPphone();
                        createAccountItem.Phone=phone;

                        createAccountItem.Id=userId;

                        createAccountItem.K_name=result.getKname();
                        createAccountItem.K_phone=result.getKphone();
                        createAccountItem.K_address=result.getKaddress();
                        createAccountItem.P_kid=Long.parseLong(result.getPkid());
                        createAccountItem.C_name=result.getC_name();
                        createAccountItem.C_age=result.getC_age();
                        //로그인할 때 가끔씩 NumberFormatException이 생김. 이유를 모르겠음.
                        try {
                            createAccountItem.K_kid = Long.parseLong(result.getKkid());
=======
                        setPreference("autoUser","p");
                        createAccountItem.User = "p";
                        createAccountItem.Name=result.getPname();
                        createAccountItem.Email=result.getPemail();
                        createAccountItem.Phone=result.getPphone();
                        createAccountItem.Id=userId;
                        createAccountItem.K_name=result.getKname();
                        createAccountItem.K_phone=result.getKphone();
                        createAccountItem.K_address=result.getKaddress();
                        createAccountItem.Tname=result.getPtname();
                        createAccountItem.C_name=result.getC_name();
                        createAccountItem.C_age=result.getC_age();
                        createAccountItem.C_img=result.getC_img();
                        //로그인할 때 가끔씩 NumberFormatException이 생김. 이유를 모르겠음.
                        try {
                            createAccountItem.K_kid = Long.parseLong(result.getKkid());
                            createAccountItem.P_kid=Long.parseLong(result.getPkid());
>>>>>>> 77b65ff8e852b6338b288b0944a443f47c37751e
                        } catch (NumberFormatException e) {
                            return;
                        }

                        Toast.makeText(SigninParentActivity.this, createAccountItem.Name + "님 환영합니다.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SigninParentActivity.this, MainParent.class);
                        intent.putExtra("userId", userId);
                        startActivity(intent);
                        SigninParentActivity.this.finish();
                    }else if (result.getStatus().equals(300)) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(SigninParentActivity.this);
                        builder.setTitle("알림")
                                .setMessage("아이디가 존재하지 않습니다.")
                                .setPositiveButton("확인", null)
                                .create()
                                .show();
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    } else if (result.getStatus().equals(400)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SigninParentActivity.this);
                        builder.setTitle("알림")
                                .setMessage("비밀번호가 일치하지 않습니다.")
                                .setPositiveButton("확인", null)
                                .create()
                                .show();
                    } else {

                        AlertDialog.Builder builder = new AlertDialog.Builder(SigninParentActivity.this);
                        builder.setTitle("알림")
                                .setMessage("예기치 못한 오류가 발생하였습니다.")
                                .setPositiveButton("확인", null)
                                .create()
                                .show();
                    }
                }
            }

            //통신 실패
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SigninParentActivity.this);
                builder.setTitle("알림")
                        .setMessage("예기치 못한 오류가 발생하였습니다.\n 고객센터에 문의바랍니다.")
                        .setPositiveButton("확인", null)
                        .create()
                        .show();
            }
        });
    }


    //데이터를 내부 저장소에 저장하기
    public void setPreference(String key, String value) {
        SharedPreferences pref = getSharedPreferences("DATA_STORE", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    //내부 저장소에 저장된 데이터 가져오기
    public String getPreferenceString(String key) {
        SharedPreferences pref = getSharedPreferences("DATA_STORE", MODE_PRIVATE);
        return pref.getString(key, "");
    }

    //키보드 숨기기
    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(userID.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(userPW.getWindowToken(), 0);
    }

    //화면 터치 시 키보드 내려감
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    //자동 로그인 유저
    public void checkAutoLogin(String id) {
        String userId = id;

        String userPassword = getPreferenceString("autoLoginPw");

        //loginRequest에 사용자가 입력한 id와 pw를 저장
        LoginRequest loginRequest = new LoginRequest(userId, userPassword);

        //retrofit 생성
        retrofitClient = RetrofitClient.getInstance();
        RetrofitAPI = RetrofitClient.getRetrofitInterface();

        //loginRequest에 저장된 데이터와 함께 init에서 정의한 getLoginResponse 함수를 실행한 후 응답을 받음
        RetrofitAPI.getLoginResponse(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                Log.d("retrofit", "Data fetch success");

                //통신 성공
                if (response.isSuccessful() && response.body() != null) {

                    //response.body()를 result에 저장
                    LoginResponse result = response.body();

                    //받은 토큰 저장
                    String token = result.getToken();


                    String userId =id;
                    String userPassword = getPreferenceString("autoLoginPw");

                    //다른 통신을 하기 위해 token 저장
                    setPreference(token, token);

                    setPreference("autoLoginId", userId);
                    setPreference("autoLoginPw", userPassword);
<<<<<<< HEAD

                    createAccountItem.User = "p";
                    createAccountItem.Name=result.getPname();
                    createAccountItem.Email=result.getPemail();

                    String phone =result.getPphone();
                    createAccountItem.Phone=phone;

                    createAccountItem.Id=userId;
                    createAccountItem.C_name=result.getC_name();
                    createAccountItem.C_age=result.getC_age();
=======
                    setPreference("autoUser","p");
                    createAccountItem.User = "p";
                    createAccountItem.Name=result.getPname();
                    createAccountItem.Email=result.getPemail();
                    createAccountItem.Phone=result.getPphone();
                    createAccountItem.Id=userId;
>>>>>>> 77b65ff8e852b6338b288b0944a443f47c37751e
                    createAccountItem.K_name=result.getKname();
                    createAccountItem.K_phone=result.getKphone();
                    createAccountItem.K_address=result.getKaddress();
                    createAccountItem.Tname=result.getPtname();
<<<<<<< HEAD
                    createAccountItem.P_kid=Long.parseLong(result.getPkid());
                    //로그인할 때 가끔씩 NumberFormatException이 생김. 이유를 모르겠음.
                    try {
                        createAccountItem.K_kid = Long.parseLong(result.getKkid());
=======
                    createAccountItem.C_name=result.getC_name();
                    createAccountItem.C_age=result.getC_age();
                    createAccountItem.C_img=result.getC_img();
                    //로그인할 때 가끔씩 NumberFormatException이 생김. 이유를 모르겠음.
                    try {
                        createAccountItem.K_kid = Long.parseLong(result.getKkid());
                        createAccountItem.P_kid=Long.parseLong(result.getPkid());
>>>>>>> 77b65ff8e852b6338b288b0944a443f47c37751e
                    } catch (NumberFormatException e) {
                        return;
                    }
                    Toast.makeText(getApplication(), createAccountItem.Name + "님 환영합니다.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplication(), MainParent.class);
                    intent.putExtra("userId", userId);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    SigninParentActivity.this.finish();


                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(SigninParentActivity.this);
//                builder.setTitle("알림")
//                        .setMessage("예기치 못한 오류가 발생하였습니다.\n 고객센터에 문의바랍니다.")
//                        .setPositiveButton("확인", null)
//                        .create()
//                        .show();
            }

        });

    }


}

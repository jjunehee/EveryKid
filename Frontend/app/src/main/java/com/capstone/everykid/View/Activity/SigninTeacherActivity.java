package com.capstone.everykid.View.Activity;

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
import com.capstone.everykid.Model.LoginRequestTeacher;
import com.capstone.everykid.Model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.capstone.everykid.Model.LoginResponseTeacher;
import com.capstone.everykid.R;
import com.capstone.everykid.RetrofitClient;

public class SigninTeacherActivity extends AppCompatActivity {

    EditText userID, userPW;
    Button signinBtn, createBtn;
    String userId, userPwd;
    private RetrofitClient retrofitClient;
    private com.capstone.everykid.RetrofitAPI.RetrofitAPI RetrofitAPI;

    CreateAccountItem createAccountItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_teacher);


        userID = (EditText) findViewById(R.id.userID);
        userPW = (EditText) findViewById(R.id.userPW);
        signinBtn = (Button) findViewById(R.id.signinteacher_btn);
        createBtn = (Button) findViewById(R.id.signupteacher_btn);

        if (!getPreferenceString("autoLoginId").equals("") && !getPreferenceString("autoLoginPw").equals("")) {
              checkAutoLogin(getPreferenceString("autoLoginId"));
        }

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SigninTeacherActivity.this, SignupActivity.class);
                intent.putExtra("User", "Teacher");
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

                    AlertDialog.Builder builder = new AlertDialog.Builder(SigninTeacherActivity.this);
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
        LoginRequestTeacher loginRequestTeacher = new LoginRequestTeacher(userId, userPassword);

        //retrofit 생성
        retrofitClient = RetrofitClient.getInstance();
        RetrofitAPI = RetrofitClient.getRetrofitInterface();

        //loginRequest에 저장된 데이터와 함께 init에서 정의한 getLoginResponse 함수를 실행한 후 응답을 받음: 선생님 로그인
        RetrofitAPI.getLogin2Response(loginRequestTeacher).enqueue(new Callback<LoginResponseTeacher>() {
            @Override
            public void onResponse(Call<LoginResponseTeacher> call, Response<LoginResponseTeacher> response) {

                Log.d("retrofit", "Data fetch success");

                //통신 성공
                if (response.isSuccessful() && response.body() != null) {

                    //response.body()를 result에 저장
                    LoginResponseTeacher result = response.body();

                    //받은 토큰 저장
                    String token = result.getTokenT();

                    //여기서부터 status값 받아와서 해야하는데 일단..
<<<<<<< HEAD
                    String userId = userID.getText().toString();
=======

                    if (result.getStatus().equals(0)) {
                        String userId = userID.getText().toString();
>>>>>>> 77b65ff8e852b6338b288b0944a443f47c37751e
                    String userPassword = userPW.getText().toString();
                    setPreference("token", token);
                    setPreference("autoLoginId", userId);
                    setPreference("autoLoginPw", userPassword);
<<<<<<< HEAD

=======
                        setPreference("autoUser","t");
                    createAccountItem.User = "t";
                    createAccountItem.Name = result.getTname();
                    createAccountItem.Email = result.getTemail();
                    createAccountItem.Phone = result.getTphone();
                    createAccountItem.Id = result.getTid();
                    createAccountItem.K_name=result.getKnameT();
                    createAccountItem.K_phone=result.getKphoneT();
                    createAccountItem.K_address=result.getKaddressT();

                    //로그인할 때 가끔씩 NumberFormatException이 생김. 이유를 모르겠음.
                    try {
                        createAccountItem.K_kid = Long.parseLong(result.getKkid());
                    } catch (NumberFormatException e) {
                        return;
                    }
                    System.out.println(result.getKkid());

                    Toast.makeText(SigninTeacherActivity.this, createAccountItem.Name + "선생님 환영합니다.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SigninTeacherActivity.this, MainParent.class);
                    intent.putExtra("userId", userId);
                    startActivity(intent);
                    SigninTeacherActivity.this.finish();

                    }else if(result.getStatus().equals(200)) {
                  String userId = userID.getText().toString();
                    String userPassword = userPW.getText().toString();
                    setPreference("token", token);
                    setPreference("autoLoginId", userId);
                    setPreference("autoLoginPw", userPassword);
                        setPreference("autoUser","t");
>>>>>>> 77b65ff8e852b6338b288b0944a443f47c37751e
                    createAccountItem.User = "t";
                    createAccountItem.Name = result.getTname();
                    createAccountItem.Email = result.getTemail();
                    createAccountItem.Phone = result.getTphone();
                    createAccountItem.Id = result.getTid();
                    createAccountItem.K_name=result.getKnameT();
                    createAccountItem.K_phone=result.getKphoneT();
                    createAccountItem.K_address=result.getKaddressT();

                    //로그인할 때 가끔씩 NumberFormatException이 생김. 이유를 모르겠음.
                    try {
                        createAccountItem.K_kid = Long.parseLong(result.getKkid());
                    } catch (NumberFormatException e) {
                        return;
                    }
                    System.out.println(result.getKkid());

                    Toast.makeText(SigninTeacherActivity.this, createAccountItem.Name + "선생님 환영합니다.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SigninTeacherActivity.this, MainParent.class);
                    intent.putExtra("userId", userId);
                    startActivity(intent);
                    SigninTeacherActivity.this.finish();

<<<<<<< HEAD

//                    if (result.getStatus().equals(0)) {
//                        String userId = userID.getText().toString();
//                        String userPassword = userPW.getText().toString();
//
//                        //다른 통신을 하기 위해 token 저장
//                        setPreference(token, token);
//
//                        setPreference("autoLoginId", userId);
//                        setPreference("autoLoginPw", userPassword);
//
//                        String name =result.getTname().toString();
//
//
//                        Toast.makeText(SigninTeacherActivity.this, userId + "님 환영합니다.", Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(SigninTeacherActivity.this, MainParent.class);
//                        intent.putExtra("userId", userId);
//                        startActivity(intent);
//                        SigninTeacherActivity.this.finish();
//
//                    }else if(result.getStatus().equals(200)) {
//                        String userId = userID.getText().toString();
//                        String userPassword = userPW.getText().toString();
//
//                        //다른 통신을 하기 위해 token 저장
//                        setPreference(token, token);
//
//                        setPreference("autoLoginId", userId);
//                        setPreference("autoLoginPw", userPassword);
//
//                        createAccountItem.Name=result.getTname();
//                        createAccountItem.Email=result.getTemail();
//                        createAccountItem.Phone=result.getTphone();
//                        createAccountItem.Id=result.getTid();
//
////                        createAccountItem.K_name=result.getKname();
////                        createAccountItem.K_phone=result.getKphone();
////                        createAccountItem.K_address=result.getKaddress();
//
//
//                        Toast.makeText(SigninTeacherActivity.this, userID + "님 환영합니다.", Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(SigninTeacherActivity.this, MainParent.class);
//                        intent.putExtra("userId", userId);
//                        startActivity(intent);
//                        SigninTeacherActivity.this.finish();
//
//                    } else if (result.getStatus().equals(300)) {
//
//                        AlertDialog.Builder builder = new AlertDialog.Builder(SigninTeacherActivity.this);
//                        builder.setTitle("알림")
//                                .setMessage("아이디가 존재하지 않습니다.")
//                                .setPositiveButton("확인", null)
//                                .create()
//                                .show();
//                        AlertDialog alertDialog = builder.create();
//                        alertDialog.show();
//
//                    } else if (result.getStatus().equals(400)) {
//                        AlertDialog.Builder builder = new AlertDialog.Builder(SigninTeacherActivity.this);
//                        builder.setTitle("알림")
//                                .setMessage("비밀번호가 일치하지 않습니다.")
//                                .setPositiveButton("확인", null)
//                                .create()
//                                .show();
//                    } else {
//
//                        AlertDialog.Builder builder = new AlertDialog.Builder(SigninTeacherActivity.this);
//                        builder.setTitle("알림")
//                                .setMessage("예기치 못한 오류가 발생하였습니다.")
//                                .setPositiveButton("확인", null)
//                                .create()
//                                .show();
//
//                    }
=======
                    } else if (result.getStatus().equals(300)) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(SigninTeacherActivity.this);
                        builder.setTitle("알림")
                                .setMessage("아이디가 존재하지 않습니다.")
                                .setPositiveButton("확인", null)
                                .create()
                                .show();
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    } else if (result.getStatus().equals(400)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SigninTeacherActivity.this);
                        builder.setTitle("알림")
                                .setMessage("비밀번호가 일치하지 않습니다.")
                                .setPositiveButton("확인", null)
                                .create()
                                .show();
                    } else {

                        AlertDialog.Builder builder = new AlertDialog.Builder(SigninTeacherActivity.this);
                        builder.setTitle("알림")
                                .setMessage("예기치 못한 오류가 발생하였습니다.")
                                .setPositiveButton("확인", null)
                                .create()
                                .show();

                    }
>>>>>>> 77b65ff8e852b6338b288b0944a443f47c37751e
                }
            }

            //통신 실패
            @Override
            public void onFailure(Call<LoginResponseTeacher> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SigninTeacherActivity.this);
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
        String userPassword =getPreferenceString("autoLoginPw");

        //loginRequest에 사용자가 입력한 id와 pw를 저장
        LoginRequestTeacher loginRequestTeacher = new LoginRequestTeacher(userId, userPassword);

        //retrofit 생성
        retrofitClient = RetrofitClient.getInstance();
        RetrofitAPI = RetrofitClient.getRetrofitInterface();

        //loginRequest에 저장된 데이터와 함께 init에서 정의한 getLoginResponse 함수를 실행한 후 응답을 받음: 선생님 로그인
        RetrofitAPI.getLogin2Response(loginRequestTeacher).enqueue(new Callback<LoginResponseTeacher>() {
            @Override
            public void onResponse(Call<LoginResponseTeacher> call, Response<LoginResponseTeacher> response) {

                Log.d("retrofit", "Data fetch success");

                //통신 성공
                if (response.isSuccessful() && response.body() != null) {

                    //response.body()를 result에 저장
                    LoginResponseTeacher result = response.body();

                    //받은 토큰 저장
                    String token = result.getTokenT();

                    //여기서부터 status값 받아와서 해야하는데 일단..
                    setPreference("token", token);
                    setPreference("autoLoginId", userId);
                    setPreference("autoLoginPw", userPassword);
<<<<<<< HEAD

=======
                    setPreference("autoUser","t");
>>>>>>> 77b65ff8e852b6338b288b0944a443f47c37751e
                    createAccountItem.User = "t";
                    createAccountItem.Name = result.getTname();
                    createAccountItem.Email = result.getTemail();
                    createAccountItem.Phone = result.getTphone();
                    createAccountItem.Id = result.getTid();
                    createAccountItem.K_name=result.getKnameT();
                    createAccountItem.K_phone=result.getKphoneT();
                    createAccountItem.K_address=result.getKaddressT();

                    //로그인할 때 가끔씩 NumberFormatException이 생김. 이유를 모르겠음.
                    try {
                        createAccountItem.K_kid = Long.parseLong(result.getKkid());
                    } catch (NumberFormatException e) {
                        return;
                    }
                    System.out.println(result.getKkid());

                    Toast.makeText(SigninTeacherActivity.this, createAccountItem.Name + "선생님 환영합니다.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SigninTeacherActivity.this, MainParent.class);
                    intent.putExtra("userId", userId);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    SigninTeacherActivity.this.finish();

                }
            }

            //통신 실패
            @Override
            public void onFailure(Call<LoginResponseTeacher> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SigninTeacherActivity.this);
                builder.setTitle("알림")
                        .setMessage("예기치 못한 오류가 발생하였습니다.\n 고객센터에 문의바랍니다.")
                        .setPositiveButton("확인", null)
                        .create()
                        .show();
            }
        });


    }

}

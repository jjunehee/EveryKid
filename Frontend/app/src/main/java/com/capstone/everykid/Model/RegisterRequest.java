package com.capstone.everykid.Model;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest { //회원가입 값 요청
    final static private String URL = "http://localhost:8080/parents/new"; //서버 url 설정(php 파일 연동)
    private Map<String, String> parameters;

    public RegisterRequest(String userID, String userPassword, String userName,
                           String userEmail,int userPhone, String Nickname, String K_ID,
                           Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
        parameters.put("userName",userName);
        parameters.put("userEmail",userEmail);
        parameters.put("userPhonenumber",userPhone+"");
        parameters.put("Nickname",Nickname);
        parameters.put("kindergartenID",K_ID);
    }
    @Override
    public Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}
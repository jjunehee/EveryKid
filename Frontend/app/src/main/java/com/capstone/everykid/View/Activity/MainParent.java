package com.capstone.everykid.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.view.MenuItem;

import com.capstone.everykid.R;
import com.google.android.material.navigation.NavigationBarView;
import android.os.Bundle;

public class MainParent extends AppCompatActivity {

    HomeFragment homeFragment;
    ChatFragment chatFragment;
    ListFragment listFragment;
    ProfileFragment profileFragment;
    CommunityFragment communityFragment;
    int flag = 0;
<<<<<<< HEAD:Frontend/app/src/main/java/com/capstone/everykid/View/Activity/MainParent.java
=======
    int child =0;
    int childadd =0;
>>>>>>> 77b65ff8e852b6338b288b0944a443f47c37751e:Frontend/app/src/main/java/com/capstone/everykid/MainParent.java

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_main);
        Intent intent = getIntent();
<<<<<<< HEAD:Frontend/app/src/main/java/com/capstone/everykid/View/Activity/MainParent.java
        try {
            flag = intent.getExtras().getInt("cmFlag");
=======

        try {
            flag = intent.getExtras().getInt("cmFlag");
            child = intent.getExtras().getInt("child");
>>>>>>> 77b65ff8e852b6338b288b0944a443f47c37751e:Frontend/app/src/main/java/com/capstone/everykid/MainParent.java
        } catch(NullPointerException e) {

        }

        homeFragment=new HomeFragment();
        chatFragment=new ChatFragment();
        listFragment=new ListFragment();
        profileFragment=new ProfileFragment();
        communityFragment=new CommunityFragment();

        NavigationBarView navigationBarView = findViewById(R.id.bottom_navigationview);
        if(flag == 1) {//게시글을 등록하고 온 경우
            getSupportFragmentManager().beginTransaction().replace(R.id.containers, communityFragment).commit();
            navigationBarView.setSelectedItemId(R.id.community);
            flag = 0;
<<<<<<< HEAD:Frontend/app/src/main/java/com/capstone/everykid/View/Activity/MainParent.java
        } else {
=======
        } else if(child ==1){
            child = 0;
            getSupportFragmentManager().beginTransaction().replace(R.id.containers,profileFragment).commit();
            navigationBarView.setSelectedItemId(R.id.profile);
        }
        else if(childadd==1){
            childadd = 0;
            getSupportFragmentManager().beginTransaction().replace(R.id.containers,profileFragment).commit();
            navigationBarView.setSelectedItemId(R.id.profile);
        }
        else {
>>>>>>> 77b65ff8e852b6338b288b0944a443f47c37751e:Frontend/app/src/main/java/com/capstone/everykid/MainParent.java
            getSupportFragmentManager().beginTransaction().replace(R.id.containers, homeFragment).commit();
            navigationBarView.setSelectedItemId(R.id.home);
        }

<<<<<<< HEAD:Frontend/app/src/main/java/com/capstone/everykid/View/Activity/MainParent.java
=======



>>>>>>> 77b65ff8e852b6338b288b0944a443f47c37751e:Frontend/app/src/main/java/com/capstone/everykid/MainParent.java
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.chatting:
                        //1) 선생님과 대화가 처음이거나 예전에 종료되었을때
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, chatFragment).commit();
                        //2) 선생님이 아직 대화 종료를 안함 (추가해야됨)
                        return true;
                    case R.id.community:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, communityFragment).commit();
                        return true;
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, homeFragment).commit();
                        return true;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, profileFragment).commit();
                        return true;
                    case R.id.list:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, listFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }
}
package com.example.androidotp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    private static final long SPLASH_SCREEN_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

try{
        new Handler().postDelayed(() -> {


            FirebaseUser auth = FirebaseAuth.getInstance().getCurrentUser();
            Intent i;
            if(auth==null) {
                i = new Intent(SplashScreen.this,
                        Send_OTP_Activity.class);
                //Intent is used to switch from one activity to another.



            }
            else{
                i = new Intent(SplashScreen.this,
                        MainActivity.class);
                //Intent is used to switch from one activity to another.
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);


            }
            startActivity(i);
            finish();
            //the current activity will get finished.
        }, SPLASH_SCREEN_TIME_OUT);



    } catch (Exception exception) {
        exception.printStackTrace();
}
    }
}
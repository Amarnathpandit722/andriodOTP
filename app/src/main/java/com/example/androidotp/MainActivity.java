package com.example.androidotp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button btn,send_otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
try {


    btn = findViewById(R.id.logout);
    btn.setOnClickListener(v -> {
        FirebaseAuth.getInstance().signOut();
        finish();
    });

    send_otp = findViewById(R.id.SendOTP);
    send_otp.setOnClickListener(v -> {
        startActivity(new Intent(MainActivity.this, Send_OTP_Activity.class));
        FirebaseAuth.getInstance().signOut();
        finish();
    });
}catch (Exception e){
    e.printStackTrace();
    Log.d("e","Error in Loading");
}



    }
}
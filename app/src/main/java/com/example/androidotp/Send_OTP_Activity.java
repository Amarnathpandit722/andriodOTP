package com.example.androidotp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;


public class Send_OTP_Activity extends AppCompatActivity {

    CountryCodePicker ccp;
    EditText inputMobile;
    Button buttonGetOTP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
ccp= findViewById(R.id.ccp);
        inputMobile = findViewById(R.id.inputMobile);
        buttonGetOTP = findViewById(R.id.buttonGetOTP);
        ProgressBar progressBar =findViewById(R.id.progressBar);
        ccp.registerCarrierNumberEditText(inputMobile);

        buttonGetOTP.setOnClickListener(v-> {
            if (inputMobile.getText().toString().isEmpty()) {
                Toast.makeText(Send_OTP_Activity.this, "Please Enter the Mobile Number", Toast.LENGTH_SHORT).show();
                return;
            }else{
                Intent intent = new Intent(Send_OTP_Activity.this,Verify_OTP_Activity.class);
                intent.putExtra("mobile",ccp.getFullNumberWithPlus().replace(" ",""));

                    startActivity(intent);
            }
            buttonGetOTP.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);


        });
    }


}

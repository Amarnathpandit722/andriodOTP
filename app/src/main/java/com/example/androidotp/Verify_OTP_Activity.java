package com.example.androidotp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
public class Verify_OTP_Activity extends AppCompatActivity {

    private EditText code1,code2,code3,code4,code5,code6;
     TextView textView;
    private Button btnVerify;
    private ProgressBar progressBar;
    private  String verificationId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        startVerification();
       setMobileText();
        setOtpInput();
        initiateOTP();
        //setVerificationId();
        setListener();




    }

    private void initiateOTP() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(setMobileText()
                , 120, TimeUnit.SECONDS, this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        String code = phoneAuthCredential.getSmsCode();
                        if(code!=null){
                            progressBar.setVisibility(View.VISIBLE);
                            verifyCode(code);
                        }
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(Verify_OTP_Activity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        verificationId=s;
                    }
                }

        );

    }

    private void setListener() {

        btnVerify.setOnClickListener(v -> {

            if(  code1.getText().toString().trim().isEmpty()
                    || code2.getText().toString().trim().isEmpty()
                    || code3.getText().toString().trim().isEmpty()
                    ||code4.getText().toString().trim().isEmpty()
                    ||code5.getText().toString().trim().isEmpty()
                    ||            code1.getText().toString().trim().isEmpty()
            ){
                Toast.makeText(this, "Please Enter Valid Code", Toast.LENGTH_SHORT).show();
                return;
            }
            String code = code1.getText().toString()+code2.getText().toString()+code3.getText().toString()+
                    code4.getText().toString()+code5.getText().toString()+code6.getText().toString();
            verificationId=code;
            if(verificationId!=null) {
                progressBar.setVisibility(View.VISIBLE);
                btnVerify.setVisibility(View.INVISIBLE);
          //  String code_mobile=setMobileText();
                findViewById(R.id.textResetOTP).setOnClickListener(v1 -> {

                    PhoneAuthOptions options = PhoneAuthOptions.newBuilder()
                            .setPhoneNumber(setMobileText())
                            .setTimeout(120L, TimeUnit.SECONDS)
                            .setActivity(this)
                            .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                    String code = phoneAuthCredential.getSmsCode();
                                    if(code!=null){
                                        progressBar.setVisibility(View.VISIBLE);
                                        verifyCode(code);
                                    }
                                    progressBar.setVisibility(View.GONE);

                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {

                                    Toast.makeText(Verify_OTP_Activity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    verificationId= s;
                                    Toast.makeText(Verify_OTP_Activity.this, "OTP Sent Again", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .build();

                    PhoneAuthProvider.verifyPhoneNumber(options);


                });

            }

        });




    }

    private void verifyCode(String code) {


        PhoneAuthCredential credential= PhoneAuthProvider.getCredential(verificationId,code);
        signInUserByCredential(credential);
    }

    private void signInUserByCredential(PhoneAuthCredential credential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(task ->
                {
                    progressBar.setVisibility(View.GONE);
                    btnVerify.setVisibility(View.VISIBLE);
                    if(task.isSuccessful()){
                        Intent intent = new Intent(Verify_OTP_Activity.this,MainActivity.class);
                        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(this, "Code Is Invalid", Toast.LENGTH_SHORT).show();
                    }
                });
    }

   // private void setVerificationId() {  verificationId= getIntent().getStringExtra("s");   }

    private void setOtpInput() {
        code1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty())
                {
                    code2.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        code2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty())
                {
                    code3.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        code3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty())
                {
                    code4.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        code4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty())
                {
                    code5.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        code5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty())
                {
                    code6.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




    }

    private String setMobileText() {

       // textView.setText(String.format("+91-%s",getIntent().getStringExtra("mobile")));
     return  getIntent().getStringExtra("mobile").toString();


    }

    private void startVerification() {
        code1 = findViewById(R.id.inputCode1);
        code2 = findViewById(R.id.inputCode2);
        code3 = findViewById(R.id.inputCode3);
        code4 = findViewById(R.id.inputCode4);
        code5 = findViewById(R.id.inputCode5);
        code6 = findViewById(R.id.inputCode6);

        textView=findViewById(R.id.textResetOTP);
        btnVerify = findViewById(R.id.buttonVerify);
        progressBar =findViewById(R.id.progressBar);

    }



}
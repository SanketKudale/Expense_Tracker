package com.wecoders.expensetracker_wecoders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    TextInputLayout mFullName,mEmail,mPassword,mPhone;
    Button mRegisterbtn;
    FirebaseAuth FAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullName = (TextInputLayout) findViewById(R.id.fullname);
        mPhone=(TextInputLayout) findViewById(R.id.phone);
        mEmail = (TextInputLayout) findViewById(R.id.email);
        mPassword = (TextInputLayout) findViewById(R.id.password);

        mRegisterbtn=(Button) findViewById(R.id.register);

        FAuth=FirebaseAuth.getInstance();


        mRegisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String Name = mFullName.getEditText().getText().toString();
                final String Email = mEmail.getEditText().getText().toString();
                final String Password = mPassword.getEditText().getText().toString();
                final String Phone = mPhone.getEditText().getText().toString();

                if(TextUtils.isEmpty(Email))
                {
                    mEmail.setError("Email is Required");
                    return;

                }
                if(TextUtils.isEmpty(Password))
                {
                    mPassword.setError("Password is Required");
                    return;
                }
                if(Password.length()<6)
                {
                    mPassword.setError("Password must be greater then 6");
                    return;
                }

                FAuth.createUserWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(Register.this, Login.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Registration failed! Please try again later", Toast.LENGTH_LONG).show();
                                }
                            }
                        });





            }




        });

    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(Register.this,Login.class));
    }

}
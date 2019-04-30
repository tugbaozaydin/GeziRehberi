package com.tugbaozaydin.myapplication.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tugbaozaydin.myapplication.R;

public class LoginActivity extends AppCompatActivity  {

    private Toolbar actionbarLogin;
    private EditText txtEmail, txtPassword;
    private Button btnLogin;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;

    public void init() {
        actionbarLogin = (Toolbar) findViewById(R.id.actionbarLogin);
        setSupportActionBar(actionbarLogin);
        getSupportActionBar().setTitle("Giriş Yap");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtEmail = (EditText) findViewById(R.id.txtEmailLogin);
        txtPassword = (EditText) findViewById(R.id.txtPasswordLogin);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();

            }
        });
    }

    private void loginUser() {
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Email Alanı Boş Olamaz !", Toast.LENGTH_LONG).show();

        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Şifre Alanı Boş Olamaz !", Toast.LENGTH_LONG).show();

        } else {
            btnLogin.setEnabled(false);//Giriş Yaptıktan sonra tekrar kullanılmasn diye
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Giriş Başarılı !", Toast.LENGTH_LONG).show();
                        Intent mainIntent = new Intent(LoginActivity.this, CountryActivity.class);
                        startActivity(mainIntent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Giriş Başarısız !", Toast.LENGTH_LONG).show();
                    btnLogin.setEnabled(true);
                    }
                }
            });
        }
    }
}

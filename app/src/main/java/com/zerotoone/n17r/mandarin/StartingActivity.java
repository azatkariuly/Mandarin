package com.zerotoone.n17r.mandarin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartingActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sPref;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    ProgressBar mProgress;

    private EditText mEmailEditText, mPasswordEditText;
    private TextInputLayout inputLayoutEmail, inputLayoutPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Intent intent = new Intent(StartingActivity.this, MainActivity.class);
                    startActivity(intent);

                } else {
                    // User is signed out
                }
                // ...
            }
        };

        inputLayoutEmail = (TextInputLayout) findViewById(R.id.inputLayoutEmail);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.inputLayoutPassword);

        mProgress = (ProgressBar) findViewById(R.id.progress);

        mEmailEditText = (EditText) findViewById(R.id.et_mail);
        mPasswordEditText = (EditText) findViewById(R.id.et_password);

        mEmailEditText.addTextChangedListener(new MyTextWatcher(mEmailEditText));
        mPasswordEditText.addTextChangedListener(new MyTextWatcher(mPasswordEditText));

        findViewById(R.id.btn_LogIn).setOnClickListener(this);
        findViewById(R.id.btn_SignUp).setOnClickListener(this);

        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null) {
            Intent intent = new Intent(StartingActivity.this, MainActivity.class);
            intent.putExtra("email", mEmailEditText.getText().toString());
            startActivity(intent);
        }



    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_LogIn) {
            if(submitForm()){
                mProgress.setVisibility(View.VISIBLE);
                logIn(mEmailEditText.getText().toString(), mPasswordEditText.getText().toString());
            }
        } else if (v.getId() == R.id.btn_SignUp) {
            startActivity(new Intent(StartingActivity.this, SignUpActivity.class));
        }
    }

    public void logIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(StartingActivity.this, MainActivity.class);
                    intent.putExtra("email", mEmailEditText.getText().toString());
                    startActivity(intent);
                } else {
                    mProgress.setVisibility(View.INVISIBLE);
                    Toast.makeText(StartingActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }






    private boolean submitForm() {

        if (!validateEmail()) {
            return false;
        }

        if (!validatePassword()) {
            return false;
        }

        return true;
    }



    private boolean validateEmail() {
        String email = mEmailEditText.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(mEmailEditText);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (mPasswordEditText.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(mPasswordEditText);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.inputLayoutEmail:
                    validateEmail();
                    break;
                case R.id.inputLayoutPassword:
                    validatePassword();
                    break;
            }
        }

    }
}


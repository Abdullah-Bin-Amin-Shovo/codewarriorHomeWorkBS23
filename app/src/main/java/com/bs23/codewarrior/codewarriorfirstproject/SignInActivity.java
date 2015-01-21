package com.bs23.codewarrior.codewarriorfirstproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bs23.codewarrior.codewarriorfirstproject.model.LoginResponse;
import com.bs23.codewarrior.codewarriorfirstproject.model.User;
import com.bs23.codewarrior.codewarriorfirstproject.service.AuthService;
import com.bs23.codewarrior.codewarriorfirstproject.service.PreferenceService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import roboguice.activity.RoboActivity;
import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_sign_in)
public class SignInActivity extends RoboActivity {

    @InjectView(R.id.emailEditText)
    private EditText emailEditText;
    
    @InjectView(R.id.passwordEditText)
    private EditText passwordEditText;
    
    @InjectView(R.id.signInButton)
    private Button signInButton;
    
    @InjectView(R.id.newAccountTextView)
    private TextView newAccountTextView;
    
    @Inject
    private AuthService authService;
    
    @Inject
    PreferenceService preferenceService;

    private Context context;
    
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        signInButton.setOnClickListener(clickListener);
        newAccountTextView.setOnClickListener(clickListener);
    }


    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            switch (v.getId()) {
                case R.id.signInButton:
                    doSignIn();
                    break;
                case R.id.newAccountTextView:
                    registerUser();
                    break;
            }
        }
    };

    private void registerUser() {
        Intent intent = new Intent(context, RegisterActivity.class);
        startActivity(intent);

    }

    private void gotoDashboard() {
        Intent intent = new Intent(context, DashboardActivity.class);
        startActivity(intent);
    }


    private void doSignIn(){
        String grant_type = "password";
        if(isValidInput()) {
            final ProgressDialog progress = ProgressDialog.show(this, "Please wait",
                    "Signing in...", true);

            authService.getAuthToken(emailEditText.getText().toString(), passwordEditText.getText().toString(), grant_type, new Callback<LoginResponse>() {
                @Override
                public void success(LoginResponse loginResponse, Response response) {
                    preferenceService.SaveAuthPreferences(loginResponse);
                    progress.dismiss();
                    gotoDashboard();

                    finish();
                }

                @Override
                public void failure(RetrofitError error) {
                    RetrofitError e = error;
                    progress.dismiss();
                    Toast.makeText(context, "Invalid login", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private boolean isValidInput(){
        String email = emailEditText.getText().toString();
        if (!isValidEmail(email)) {
            emailEditText.setError("Invalid Email");
            return false;
        }

        final String pass = passwordEditText.getText().toString();
        if (!isValidPassword(pass)) {
            passwordEditText.setError("Invalid Password");
            return false;
        }

        return true;
    }

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 6) {
            return true;
        }
        return false;
    }
}

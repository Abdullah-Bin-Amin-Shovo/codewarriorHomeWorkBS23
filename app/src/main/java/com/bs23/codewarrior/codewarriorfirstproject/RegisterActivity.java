package com.bs23.codewarrior.codewarriorfirstproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bs23.codewarrior.codewarriorfirstproject.model.User;
import com.bs23.codewarrior.codewarriorfirstproject.service.AuthService;
import com.bs23.codewarrior.codewarriorfirstproject.service.PreferenceService;
import com.google.inject.Inject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedInput;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_register)
public class RegisterActivity extends RoboActivity {
    
    @InjectView(R.id.myEmailEditText)
    EditText emailEditText;
    
    @InjectView(R.id.passwordEditText)
    EditText passwordEditText;
    

    @InjectView(R.id.registerButton)
    Button registerButton;

    @InjectView(R.id.loginTextView)
    TextView loginTextView;

    
    @Inject
    private AuthService authService;
    
    @Inject
    PreferenceService preferenceService;
        
    private User user;

    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = new User(emailEditText.getText().toString(), passwordEditText.getText().toString(), passwordEditText.getText().toString());
                registerUser();
                System.out.println("Clicked");
            }
        });
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoLogin();
            }
        });

    }

    private void gotoLogin() {
        finish();
    }

    public void registerUser() {

        if(isValidInput()) {

            final ProgressDialog progress = ProgressDialog.show(this, "Please wait",
                    "Signing up...", true);

            authService.registerUser(user, new Callback<User>() {
                @Override
                public void success(User user, Response response) {
                    progress.dismiss();
                    Toast.makeText(context, "Sign up successful. Please login to continue", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void failure(RetrofitError error) {
                    progress.dismiss();
                    RetrofitError e = error;
                    TypedInput body = e.getResponse().getBody();
                    try {
                        BufferedReader reader = null;
                        try {
                            reader = new BufferedReader(new InputStreamReader(body.in()));
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        StringBuilder out = new StringBuilder();
                        String newLine = System.getProperty("line.separator");
                        String line;
                        while ((line = reader.readLine()) != null) {
                            out.append(line);
                            out.append(newLine);
                        }

                        // Prints the correct String representation of body.
                        System.out.println(out);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
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
            passwordEditText.setError("Password is too short");
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

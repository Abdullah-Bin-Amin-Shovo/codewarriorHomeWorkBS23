package com.bs23.codewarrior.codewarriorfirstproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedInput;
import roboguice.activity.RoboActionBarActivity;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.fragment_register)
public class RegisterActivity extends RoboActivity {
    
    @InjectView(R.id.myEmailEditText)
    EditText emailEditText;
    
    @InjectView(R.id.passwordEditText)
    EditText passwordEditText;
    
    @InjectView(R.id.confrimPasswordEditText)
    EditText confrimPasswordEditText;
    
    @InjectView(R.id.registerButton)
    Button registerButton;

    @InjectView(R.id.loginTextView)
    TextView loginTextView;

    
    @Inject
    private AuthService authService;
    
    @Inject
    PreferenceService preferenceService;
        
    private User user;

    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = new User(emailEditText.getText().toString(), passwordEditText.getText().toString(), confrimPasswordEditText.getText().toString());
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
        Intent intent = new Intent(this.getApplicationContext(), SignInActivity.class);
        startActivity(intent);
    }

    public void registerUser() {

        authService.registerUser(user, new Callback<User>() {
            @Override
            public void success(User user, Response response) {

                System.out.println("success");
                Toast.makeText(getApplicationContext(),"User Registered; status="+response.getStatus(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

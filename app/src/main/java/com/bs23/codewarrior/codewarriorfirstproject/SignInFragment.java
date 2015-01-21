package com.bs23.codewarrior.codewarriorfirstproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bs23.codewarrior.codewarriorfirstproject.model.LoginResponse;
import com.bs23.codewarrior.codewarriorfirstproject.model.User;
import com.bs23.codewarrior.codewarriorfirstproject.service.AuthService;
import com.bs23.codewarrior.codewarriorfirstproject.service.PreferenceService;

import javax.inject.Inject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

/**
 * Created by bs-110 on 1/18/2015.
 */
public class SignInFragment extends RoboFragment {

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
    
    private User user;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_in, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
        Intent intent = new Intent(this.getActivity(), RegisterActivity.class);
        startActivity(intent);
    }


    private void doSignIn(){
        String grant_type = "password";
        authService.getAuthToken(emailEditText.getText().toString(), passwordEditText.getText().toString(), grant_type, new Callback<LoginResponse>() {
            @Override
            public void success(LoginResponse loginResponse, Response response) {
                String accessToken = loginResponse.access_token;
                preferenceService.SetPreferenceValue(PreferenceService.ACCESS_TOKEN, accessToken );
                Toast.makeText(getActivity().getApplicationContext(),"Login Success , token:"+accessToken, Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {
                RetrofitError e = error;

            }
        } );
        Toast.makeText(getActivity().getApplicationContext(),"Sign in Clicked", Toast.LENGTH_LONG).show();
    }
}

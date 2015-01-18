package com.bs23.codewarrior.codewarriorfirstproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    @InjectView(R.id.forgotPassTextView)
    private TextView forgotPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_in, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signInButton.setOnClickListener(clickListener);
    }


    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            switch (v.getId()) {
                case R.id.signInButton:
                    doSignIn();
                    break;
            }
        }
    };

    private void doSignIn(){

    }
}
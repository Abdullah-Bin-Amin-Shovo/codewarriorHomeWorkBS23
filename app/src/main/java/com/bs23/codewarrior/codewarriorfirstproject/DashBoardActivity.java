package com.bs23.codewarrior.codewarriorfirstproject;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bs23.codewarrior.codewarriorfirstproject.model.UserProfile;
import com.bs23.codewarrior.codewarriorfirstproject.service.AuthService;
import com.bs23.codewarrior.codewarriorfirstproject.service.PreferenceService;
import com.google.inject.Inject;

import java.util.concurrent.Callable;

import javax.security.auth.callback.Callback;

import retrofit.RetrofitError;
import retrofit.client.Response;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_dash_board)
public class DashBoardActivity extends RoboActivity {

    @Inject
    PreferenceService preferenceService;
    
    @Inject
    private AuthService authService;
    
    @InjectView (R.id.buttonEditProfile)
    private Button buttonEditProfile;
    
    private UserProfile userProfileData;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if(!preferenceService.isSignedIn(PreferenceService.IS_SIGNIN)){
            
            startActivity(new Intent(DashBoardActivity.this, SignInActivity.class));
            
        }
        getUserData();
        
        buttonEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoardActivity.this, UpdateProfileActivity.class));
            }
        });
        
        
        
    }

    private void getUserData() {
        authService.getProfile(new retrofit.Callback<UserProfile>() {
            @Override
            public void success(UserProfile userProfile, Response response) {
                userProfileData = userProfile;
                
                preferenceService.SetPreferenceValue(PreferenceService.ID,String.valueOf(userProfileData.getId()));
                
                /*if(isStringIsNull(userProfile.getFirstName())||isStringIsNull(userProfile.getFirstName())||isStringIsNull(userProfile.getFirstName())||isStringIsNull(userProfile.getFirstName())){
                    //buttonEditProfile.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(),"Please update your profile Info",Toast.LENGTH_LONG).show();
                }*/

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    
    private boolean isStringIsNull(String string){
        if(string != null && !string.isEmpty()){
            
            return false;
        } 
        return true;
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dash_board, menu);
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

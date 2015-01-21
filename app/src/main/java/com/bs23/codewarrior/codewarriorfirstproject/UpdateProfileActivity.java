package com.bs23.codewarrior.codewarriorfirstproject;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bs23.codewarrior.codewarriorfirstproject.model.User;
import com.bs23.codewarrior.codewarriorfirstproject.model.UserProfile;
import com.bs23.codewarrior.codewarriorfirstproject.service.AuthService;
import com.bs23.codewarrior.codewarriorfirstproject.service.PreferenceService;
import com.google.inject.Inject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_update_profile)
public class UpdateProfileActivity extends RoboActivity {

    @InjectView(R.id.txtFname)
    EditText fName;

    @InjectView(R.id.txtLname)
    EditText lName;

    @InjectView(R.id.txtPhone)
    EditText phone;

    @InjectView(R.id.txtAddress)
    EditText address;


    @InjectView(R.id.btnSubmit)
    Button btnSubmit;
    
    @InjectView(R.id.btnCancel)
    Button btnCancel;

   

    @Inject
    private AuthService authService;

    @Inject
    PreferenceService preferenceService;

    private UserProfile userProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        btnCancel.setOnClickListener(clickListener);
        btnSubmit.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            switch (v.getId()) {
                case R.id.btnCancel:
                   finish();
                    break;
                case R.id.btnSubmit:
                    uploadData();
                    break;
            }
        }
    };

    private void uploadData() {
        
        userProfile = new UserProfile(Integer.parseInt(preferenceService.GetPreferenceValue(PreferenceService.ID)),
                fName.getText().toString(),
                lName.getText().toString(),
                phone.getText().toString(),
                address.getText().toString()
                );

        Toast.makeText(getApplicationContext(),"upload called",Toast.LENGTH_LONG).show();
        //userProfile.setId(Integer.parseInt(preferenceService.GetPreferenceValue(PreferenceService.ID)));
        authService.updateProfile(userProfile, new Callback<Object>() {
            @Override
            public void success(Object o, Response response) {
                Toast.makeText(getApplicationContext(),"Data Update Successfully",Toast.LENGTH_LONG).show();
                System.out.println("success");
                finish();
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("failure");
                Toast.makeText(getApplicationContext(),"Can't Update Data",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

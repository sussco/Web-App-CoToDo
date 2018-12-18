package com.mobileapps.group15.cotodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

import com.firebase.ui.auth.AuthUI;

import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;

import java.util.ArrayList;
import java.util.List;

//import com.firebase.ui.auth.ResultCodes;
public class AuthActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(this, SignedInActivity.class));
            finish();
        } else {
            authenticateUser();
        }
    }

    private void authenticateUser() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setAvailableProviders(getProviderList())
                        .setIsSmartLockEnabled(true)
                        .build(),
                REQUEST_CODE);
    }

    private List<AuthUI.IdpConfig> getProviderList() {

        List<AuthUI.IdpConfig> providers = new ArrayList<>();

        providers.add(
                new AuthUI.IdpConfig.EmailBuilder().build());
        return providers;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IdpResponse response = IdpResponse.fromResultIntent(data);

        if (requestCode == REQUEST_CODE) {

            if (resultCode == RESULT_OK) {
                startActivity(new Intent(this, SignedInActivity.class));
                return;
            }
        } else {
            if (response == null) {
                //User cancelled Sign-in
                return;
            }

            if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                //Device has no network connection
                return;
            }

            if (response.getError().getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                //Unknown error occurred
                return;
            }
        }
    }

}

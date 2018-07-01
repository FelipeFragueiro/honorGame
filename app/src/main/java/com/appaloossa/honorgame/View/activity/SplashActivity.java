package com.appaloossa.honorgame.View.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.appaloossa.honorgame.R;
import com.appaloossa.honorgame.View.activity.Capitan.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        final FirebaseUser currentUser = mAuth.getCurrentUser();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SplashActivity context = SplashActivity.this;
                Intent intent = null;
//                if (UserManager.getInstance().isUserLoggedIn()) {
                if (currentUser != null) {
                    intent = new Intent(context, MainActivity.class);
                } else {
                    intent = new Intent(context, LoginActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, getResources().getInteger(R.integer.splash_screen_stay_time_in_seconds) * 1000);
    }
}

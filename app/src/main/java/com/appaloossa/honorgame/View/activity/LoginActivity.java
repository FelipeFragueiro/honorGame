package com.appaloossa.honorgame.View.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.appaloossa.honorgame.R;
import com.appaloossa.honorgame.View.activity.Arbitro.ArbitroMainActivity;
import com.appaloossa.honorgame.View.activity.Capitan.MainActivity;
import com.appaloossa.honorgame.base.HonorBaseActivity;
import com.appaloossa.honorgame.model.Equipo;
import com.appaloossa.honorgame.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends HonorBaseActivity {

    private static final String TAG = "LoginActivity";

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @BindView(R.id.login_email_edit_text)
    EditText mEmailField;

    @BindView(R.id.login_password_edit_text)
    EditText mPasswordField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    @OnClick(R.id.login_btn_submit)
    public void onSubmitClick() {
        signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
    }

    @OnClick(R.id.login_btn_create_account)
    public void onCreateAccountClick() {
        goToCreateAccountActivity();
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressDialog();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            mDatabase.child(User.modelName).child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    User user = dataSnapshot.getValue(User.class);
                                    if (user != null) {
                                        goToMainActivity();
                                    } else {
                                        mAuth.signOut();
                                        showErrorMessage(getString(R.string.login_dialog_message_auth_failed));
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    mAuth.signOut();
                                    showErrorMessage(getString(R.string.login_dialog_message_auth_failed));
                                }
                            });

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            showErrorMessage(getString(R.string.login_dialog_message_auth_failed));
                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            showErrorMessage(getString(R.string.login_dialog_message_auth_failed));
                        }
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }

    private void goToMainActivity() {
        String mAuth;
        mAuth = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference firebaseDataRef = firebaseDatabase.getReference();
        final DatabaseReference team = firebaseDataRef.child("users").child(mAuth).child("usuarioTipo");
        team.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue().toString().equals("0")){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else if (dataSnapshot.getValue().toString().equals("1")){
                    startActivity(new Intent(LoginActivity.this, ArbitroMainActivity.class));
                }




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });










    }

    private void goToCreateAccountActivity() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}

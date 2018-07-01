package com.appaloossa.honorgame.View.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.appaloossa.honorgame.R;
import com.appaloossa.honorgame.base.HonorBaseActivity;
import com.appaloossa.honorgame.model.Coordenadas;
import com.appaloossa.honorgame.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends HonorBaseActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private RadioButton radioButtonPeriodista;
    private RadioButton radioButtonReferi;
    private RadioButton radioButtonCapi;
    private RadioButton radioButtonTeamMen;
    private RadioButton radioButtonTeamWomen;
    private EditText nombreYapellido;
    private EditText dni;
    private EditText mail;
    private EditText contraseña;
    private EditText repeContraseña;
    private Button confirmar;
    private Button cancelar;
    private String email;
    private Boolean hombre;
    private Boolean mujer;
    private Integer capitan;
    private Integer periodista;
    private Integer arbitro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        //RadioButtons
        radioButtonCapi = (RadioButton)findViewById(R.id.sign_up_radio_button_captain);
        radioButtonReferi = (RadioButton)findViewById(R.id.sign_up_radio_button_referee);
        radioButtonPeriodista = (RadioButton)findViewById(R.id.sign_up_radio_button_journalist);
        radioButtonTeamMen = (RadioButton)findViewById(R.id.sign_up_radio_button_team_men);
        radioButtonTeamWomen = (RadioButton)findViewById(R.id.sign_up_radio_button_team_woman);

        //EditTexts
        nombreYapellido = (EditText)findViewById(R.id.sign_up_name_edit_text);
        dni = (EditText)findViewById(R.id.sign_up_dni_edit_text);
        mail = (EditText)findViewById(R.id.sign_up_email_edit_text);
        contraseña = (EditText)findViewById(R.id.sign_up_password_edit_text);
        repeContraseña = (EditText)findViewById(R.id.sign_up_confirm_password_edit_text);

        //Buttons
        confirmar = (Button)findViewById(R.id.sign_up_btn_submit);
        cancelar = (Button)findViewById(R.id.sign_up_btn_cancel);



        //SetOnClick
        radioButtonTeamMen.setOnClickListener(this);
        radioButtonTeamWomen.setOnClickListener(this);
        radioButtonCapi.setOnClickListener(this);
        radioButtonPeriodista.setOnClickListener(this);
        radioButtonReferi.setOnClickListener(this);
        confirmar.setOnClickListener(this);
        cancelar.setOnClickListener(this);

        //other





    }

    @Override
    public void onClick(View v) {
        String contraseña1 = contraseña.getText().toString();
        String repecontra1 = repeContraseña.getText().toString();
        if (v.getId() == radioButtonCapi.getId()){
            Toast.makeText(getApplicationContext(), "choice: Capitan",
                    Toast.LENGTH_SHORT).show();
            radioButtonReferi.setChecked(false);
            radioButtonPeriodista.setChecked(false);
            capitan = 1;
            periodista = 0;
            arbitro = 0;
        }
        if (v.getId() == radioButtonReferi.getId()){
            Toast.makeText(getApplicationContext(), "choice: Referi",
                    Toast.LENGTH_SHORT).show();
            radioButtonCapi.setChecked(false);
            radioButtonPeriodista.setChecked(false);
            capitan = 0;
            periodista = 0;
            arbitro = 1;
        }
        if (v.getId() == radioButtonPeriodista.getId()){
            Toast.makeText(getApplicationContext(), "choice: periodista",
                    Toast.LENGTH_SHORT).show();
            radioButtonCapi.setChecked(false);
            radioButtonReferi.setChecked(false);
            capitan = 0;
            periodista = 1;
            arbitro = 0;
        }
        if (v.getId() == radioButtonTeamMen.getId()){
            Toast.makeText(getApplicationContext(), "choice: TeamMen",
                    Toast.LENGTH_SHORT).show();
            radioButtonTeamWomen.setChecked(false);
            hombre = true;
            mujer = false;
        }
        if (v.getId() == radioButtonTeamWomen.getId()){
            Toast.makeText(getApplicationContext(), "choice: TeamWomen",
                    Toast.LENGTH_SHORT).show();
            radioButtonTeamMen.setChecked(false);
            mujer = true;
            hombre = false;
        }
        if (v.getId()==confirmar.getId()){
            Toast.makeText(getApplicationContext(), "choice: confirmar",
                    Toast.LENGTH_SHORT).show();
            if (!(contraseña1.equals(repecontra1))){
                Toast.makeText(getApplicationContext(), "Contraseña y repetición de contraseña no valido",
                        Toast.LENGTH_SHORT).show();
            } else {

                    String sMail = mail.getText().toString();
                    String sContra = contraseña.getText().toString();
                mAuth.createUserWithEmailAndPassword(sMail, sContra)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("TAG", "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    cargarDatosAFirebase();
                                    //startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                                    //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }
                                // ...
                            }
                        });
            }
        }
        if (v.getId()==cancelar.getId()){
            Toast.makeText(getApplicationContext(), "choice: cancelar",
                    Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SignUpActivity.this,LoginActivity.class));

        }


    }

    private void cargarDatosAFirebase(){
        Integer tipoDeUsuario = null;
        //firebase
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        //FireBaseStorage
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        DatabaseReference user = databaseReference.child("users");

        DatabaseReference newUserRef = user.push();

        User userToUpLoad = new User();
        userToUpLoad.setDni(dni.getText().toString());
        userToUpLoad.setMail(mail.getText().toString());

        if (hombre == true){
            userToUpLoad.setSexoFutbol(0);
        }else {
            userToUpLoad.setSexoFutbol(1);
        }
        userToUpLoad.setUsername(nombreYapellido.getText().toString());

        if (capitan == 1){
            tipoDeUsuario = 0;
        }else if (arbitro == 1){
            tipoDeUsuario = 1;
        }else {
            tipoDeUsuario = 2;
        }
        userToUpLoad.setUsuarioTipo(tipoDeUsuario);

        //faltaCompletar.
        Coordenadas coordenadas = new Coordenadas();
            coordenadas.setLatitude(0.0);
            coordenadas.setLongitude(0.0);

            userToUpLoad.setCbu("");
            userToUpLoad.setCiudad("");
            userToUpLoad.setCoordenadas(coordenadas);

        //newUserRef.setValue(userToUpLoad);
        user.getRef().child(mAuth.getCurrentUser().getUid()).setValue(userToUpLoad);
        //team.getRef().child(mAuth.getCurrentUser().getUid()).setValue(equipoToUpLoad);









    }
}


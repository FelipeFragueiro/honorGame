package com.appaloossa.honorgame.View.activity.Arbitro;

import android.annotation.SuppressLint;
import android.media.Rating;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.appaloossa.honorgame.R;
import com.appaloossa.honorgame.model.Equipo;
import com.appaloossa.honorgame.model.ReviewReferee;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class ResumenArbitroActivity extends AppCompatActivity implements View.OnClickListener {
    //ids Goles de cada equipo
    private FirebaseAuth mAuth;

    private String key;

    private TextView tv_nombreEquipo1_goles;
    private TextView tv_numeroDeGoles_equipo1;
    private TextView tv_nombreEquipo2_goles;
    private TextView tv_numeroDeGoles_equipo2;
    private TextView tv_nombreEquipo1_amonestaciones;
    private TextView tv_numeroDeAmonestacionesRojas_Equipo1;
    private TextView tv_nombreEquipo2_amonestaciones;
    private TextView tv_numeroDeAmonestacionesAmarilla_Equipo1;
    private TextView tv_numeroDeAmonestacionesRojas_Equipo2;
    private TextView tv_numeroDeAmonestacionesAmarilla_Equipo2;
    private TextView tv_goleadores_title_equipo1;
    private TextView tv_goleadores_title_equipo2;
    private TextView tv_nombreEquipo1_evaluacion;
    private TextView tv_nombreEquipo2_evaluacion;

    private RecyclerView recyclerView_amonestaciones_Equipo1;
    private RecyclerView recyclerView_amonestaciones_Equipo2;
    private RecyclerView recyclerView_goleadores_equipo1;
    private RecyclerView recyclerView_goleadores_equipo2;

    private RatingBar ratingBar_equipo1;
    private RatingBar ratingBar_equipo2;

    private Button bttn_enviarResumenAFB;
    private Button bttn_agregarGoles_equipo1;
    private Button bttn_restarGoles_equipo1;
    private Button bttn_agregarGoles_equipo2;
    private Button bttn_restarGoles_equipo2;
    private Button bttn_agregarAmonestacion_roja_Equipo1;
    private Button bttn_restarAmonestacion_roja_Equipo1;
    private Button bttn_agregarAmonestacion_amarilla_Equipo1;
    private Button bttn_restarAmonestacion_amarilla_Equipo1;
    private Button bttn_agregarAmonestacion_roja_Equipo2;
    private Button bttn_restarAmonestacion_roja_Equipo2;
    private Button bttn_agregarAmonestacion_amarilla_Equipo2;
    private Button bttn_restarAmonestacion_amarilla_Equipo2;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_arbitro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         Bundle bundle = getIntent().getExtras();
            key =  bundle.getString("key");

        //Equipo1
         tv_nombreEquipo1_goles = (TextView)findViewById(R.id.tv_nombreDeEquipo1_crearResumen_arbitro);
         tv_numeroDeGoles_equipo1 = (TextView)findViewById(R.id.tv_numeroDeGoles_equipo1_crearResumen_arbitro);
         bttn_agregarGoles_equipo1 = (Button) findViewById(R.id.bttn_añadirNumeroGol_Equipo1_crearResumen);
         bttn_restarGoles_equipo1 = (Button)findViewById(R.id.bttn_restarNumeroGol_Equipo1_crearResumen);

        //Equipo2
         tv_nombreEquipo2_goles = (TextView)findViewById(R.id.tv_nombreDeEquipo2_crearResumen_arbitro);
         tv_numeroDeGoles_equipo2 = (TextView)findViewById(R.id.tv_numeroDeGoles_equipo2_crearResumen_arbitro);
         bttn_agregarGoles_equipo2 = (Button) findViewById(R.id.bttn_añadirNumeroGol_Equipo2_crearResumen);
         bttn_restarGoles_equipo2 = (Button)findViewById(R.id.bttn_restarNumeroGol_Equipo2_crearResumen);

        //ids amonestaciones

        //Parte De Amonestaciones Equipo 1.
         tv_nombreEquipo1_amonestaciones = (TextView)findViewById(R.id.tv_nombreDeEquipo1_amonestaciones_crearResumen_arbitro);
         recyclerView_amonestaciones_Equipo1 = (RecyclerView)findViewById(R.id.recyclerView_amonestacionesEquipo1_crearResumen_arbitro);
        //amonestaciones Rojas. Equipo 1.
         tv_numeroDeAmonestacionesRojas_Equipo1 = (TextView)findViewById(R.id.tv_numeroDeAmonestaciones_Rojas_crearResumen);
         bttn_agregarAmonestacion_roja_Equipo1 = (Button)findViewById(R.id.bttn_añadirNumeroAmonestacionRoja_Equipo1_crearResumen);
         bttn_restarAmonestacion_roja_Equipo1 = (Button)findViewById(R.id.bttn_restarNumeroAmonestacionRoja_Equipo1_crearResumen);
        //amonesraciones Amarillas. Equipo 1.
         tv_numeroDeAmonestacionesAmarilla_Equipo1 = (TextView)findViewById(R.id.tv_numeroDeAmonestaciones_Amarillas_crearResumen);
         bttn_agregarAmonestacion_amarilla_Equipo1 = (Button)findViewById(R.id.bttn_añadirNumeroAmonestacionAmarilla_Equipo1_crearResumen);
         bttn_restarAmonestacion_amarilla_Equipo1 = (Button)findViewById(R.id.bttn_restarNumeroAmonestacionAmarilla_Equipo1_crearResumen);


        //Parte Amonestaciones Equipo 2.
         tv_nombreEquipo2_amonestaciones = (TextView)findViewById(R.id.tv_nombreDeEquipo2_amonestaciones_crearResumen_arbitro);
         recyclerView_amonestaciones_Equipo2 = (RecyclerView)findViewById(R.id.recyclerView_amonestacionesEquipo2_crearResumen_arbitro);
        //amonestaciones Rojas. Equipo 2.
         tv_numeroDeAmonestacionesRojas_Equipo2 = (TextView)findViewById(R.id.tv_numeroDeAmonestaciones_equipo2_Rojas_crearResumen);
         bttn_agregarAmonestacion_roja_Equipo2 = (Button)findViewById(R.id.bttn_añadirNumeroAmonestacionRoja_Equipo2_crearResumen);
         bttn_restarAmonestacion_roja_Equipo2 = (Button)findViewById(R.id.bttn_restarNumeroAmonestacionRoja_Equipo2_crearResumen);
        //amonestaciones Amarillas. Equipo 2.
         tv_numeroDeAmonestacionesAmarilla_Equipo2 = (TextView)findViewById(R.id.tv_numeroDeAmonestaciones_equipo2_Amarillas_crearResumen);
         bttn_agregarAmonestacion_amarilla_Equipo2 = (Button)findViewById(R.id.bttn_añadirNumeroAmonestacionAmarilla_Equipo2_crearResumen);
         bttn_restarAmonestacion_amarilla_Equipo2 = (Button)findViewById(R.id.bttn_restarNumeroAmonestacionAmarilla_Equipo2_crearResumen);

        //Goleadores
        //Equipo 1
        tv_goleadores_title_equipo1 = (TextView)findViewById(R.id.tv_nombreEquipo1_goleadores_crearResumen_arbitro);
        recyclerView_goleadores_equipo1 = (RecyclerView) findViewById(R.id.recyclerView_goleadoresEquipo1_crearResumen_arbitro);
        //Equipo 2
        tv_goleadores_title_equipo2 = (TextView)findViewById(R.id.tv_nombreEquipo2_goleadores_crearResumen_arbitro);
        recyclerView_goleadores_equipo2 = (RecyclerView)findViewById(R.id.recyclerView_goleadoresEquipo2_crearResumen_arbitro);

        //Evaluacion
        //Equipo 1
        tv_nombreEquipo1_evaluacion = (TextView)findViewById(R.id.tv_nombreEquipo1_evaluacion_crearResumen_arbitro);
        ratingBar_equipo1 = (RatingBar)findViewById(R.id.ratingBar_evaluacionEquipo1_crearResumen_arbitro);
        //Equipo 2
        tv_nombreEquipo2_evaluacion = (TextView)findViewById(R.id.tv_nombreEquipo2_evaluacion_crearResumen_arbitro);
        ratingBar_equipo2 = (RatingBar)findViewById(R.id.ratingBar_evaluacionEquipo2_crearResumen_arbitro);

        bttn_enviarResumenAFB = (Button)findViewById(R.id.bttn_enviarResumenAFB);

        //SetOnClickListener
            bttn_enviarResumenAFB.setOnClickListener(this);
            //Agregar/Restar Goles
                bttn_agregarGoles_equipo1.setOnClickListener(this);
                bttn_restarGoles_equipo1.setOnClickListener(this);

                bttn_agregarGoles_equipo2.setOnClickListener(this);
                bttn_restarGoles_equipo2.setOnClickListener(this);

            //Amonestaciones Rojas
                bttn_agregarAmonestacion_roja_Equipo1.setOnClickListener(this);
                bttn_restarAmonestacion_roja_Equipo1.setOnClickListener(this);

                bttn_agregarAmonestacion_roja_Equipo2.setOnClickListener(this);
                bttn_restarAmonestacion_roja_Equipo2.setOnClickListener(this);

            //Aamonestaciones Amarillas
                bttn_agregarAmonestacion_amarilla_Equipo1.setOnClickListener(this);
                bttn_restarAmonestacion_amarilla_Equipo1.setOnClickListener(this);

                bttn_agregarAmonestacion_roja_Equipo2.setOnClickListener(this);
                bttn_restarAmonestacion_amarilla_Equipo2.setOnClickListener(this);






    }



    private void cargarResumenAFB(){
        Integer rating1 = ratingBar_equipo1.getNumStars();
        Integer rating2 = ratingBar_equipo2.getNumStars();


        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        DatabaseReference review = databaseReference.child("reviewReferee");
        String userid = mAuth.getCurrentUser().getUid();

         ReviewReferee reviewToUpLoad = new ReviewReferee();

         reviewToUpLoad.setGoalsTeamA(tv_numeroDeGoles_equipo1.getText().toString());
         reviewToUpLoad.setGoalsTeamB(tv_numeroDeGoles_equipo2.getText().toString());
         reviewToUpLoad.setPartidoID(key);
         reviewToUpLoad.setRedPlayersTeamA(tv_numeroDeAmonestacionesRojas_Equipo1.getText().toString());
         reviewToUpLoad.setRedPlayersTeamB(tv_numeroDeAmonestacionesRojas_Equipo2.getText().toString());
         reviewToUpLoad.setYellowPlayersTeamA(tv_numeroDeAmonestacionesAmarilla_Equipo1.getText().toString());
         reviewToUpLoad.setYellowPayersTeamB(tv_numeroDeAmonestacionesAmarilla_Equipo2.getText().toString());
         reviewToUpLoad.setTeamARating(String.valueOf(rating1));
         reviewToUpLoad.setTeamBRating(String.valueOf(rating2));

        review.getRef().push().setValue(reviewToUpLoad);
    }

    @Override
    public void onClick(View v) {

        if (v.getId()==bttn_enviarResumenAFB.getId()){

            cargarResumenAFB();
            Toast.makeText(this,"Enviado.",Toast.LENGTH_SHORT).show();

        }

        if (v.getId() == bttn_agregarGoles_equipo1.getId()){

        }

        if (v.getId() == bttn_agregarGoles_equipo2.getId()){

        }

        if (v.getId() == bttn_restarGoles_equipo1.getId()){

        }

        if (v.getId() == bttn_restarGoles_equipo2.getId()){

        }

        if (v.getId() == bttn_agregarAmonestacion_amarilla_Equipo1.getId()){

        }

        if (v.getId() == bttn_agregarAmonestacion_amarilla_Equipo2.getId()){

        }

        if (v.getId() == bttn_restarAmonestacion_amarilla_Equipo1.getId()){

        }

        if (v.getId() == bttn_restarAmonestacion_amarilla_Equipo2.getId()){

        }

        if (v.getId() == bttn_agregarAmonestacion_roja_Equipo1.getId()){

        }

        if (v.getId() == bttn_agregarAmonestacion_roja_Equipo2.getId()){

        }

        if (v.getId() == bttn_restarAmonestacion_roja_Equipo1.getId()){

        }

        if (v.getId() == bttn_restarAmonestacion_roja_Equipo2.getId()){

        }





    }
}

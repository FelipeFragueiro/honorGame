package com.appaloossa.honorgame.View.activity.Capitan;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.appaloossa.honorgame.R;
import com.appaloossa.honorgame.View.adapter.CapitanAdapter.AdapterJugadores;
import com.appaloossa.honorgame.model.Equipo;
import com.appaloossa.honorgame.model.Player;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.widget.LinearLayout.VERTICAL;

public class EquipoArmadoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private Button buttonComenzarDesafio;
    private Button bttn_agregarJugadores;
    private TextView tv_titleNombre;
    private TextView tv_nombreEquipo;
    private List<Equipo> unEquipolist = new ArrayList<>();
    private Equipo otroEquipo;
    private String mAuth;
    private List<Player> playerList = new ArrayList<>();
    private int contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo_armado_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface poppinsBold = Typeface.createFromAsset(this.getAssets(),"fonts/Poppins-Bold.ttf");
        Typeface poppinsRegular = Typeface.createFromAsset(this.getAssets(),"fonts/Poppins-Regular.ttf");
        Typeface poppinsSemiBold = Typeface.createFromAsset(this.getAssets(),"fonts/Poppins-SemiBold.ttf");
        Typeface poppinsLight = Typeface.createFromAsset(this.getAssets(),"fonts/Poppins-Light.ttf");
        Typeface poppinsMedium = Typeface.createFromAsset(this.getAssets(),"fonts/Poppins-Medium.ttf");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_equipo_armado);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_equipo_armado);
        navigationView.setNavigationItemSelectedListener(this);
        buttonComenzarDesafio = (Button) findViewById(R.id.bttn_desafiarEquipos);
        bttn_agregarJugadores = (Button) findViewById(R.id.bttn_agregarJugadores);
        tv_titleNombre = (TextView)findViewById(R.id.title_nombreEquipo);
        tv_nombreEquipo = (TextView)findViewById(R.id.tv_nombre_equipo);

        tv_titleNombre.setTypeface(poppinsRegular);
        tv_nombreEquipo.setTypeface(poppinsLight);
        buttonComenzarDesafio.setOnClickListener(this);
        bttn_agregarJugadores.setOnClickListener(this);


        cargarEquiposFB();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_equipo_armado);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            startActivity(new Intent(EquipoArmadoActivity.this, MainActivity.class));

        } else if (id == R.id.nav_gallery) {

            startActivity(new Intent(EquipoArmadoActivity.this, PartidosActivity.class));


        } else if (id == R.id.nav_slideshow) {
            startActivity(new Intent(EquipoArmadoActivity.this, RankingActivity.class));

        } else if (id == R.id.nav_manage) {
            Toast.makeText(this, "ya estas aca", Toast.LENGTH_LONG).show();


        } else if (id == R.id.nav_send) {
            signOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_equipo_armado);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void cargarEquiposFB() {
        contador = 0;
        mAuth = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference firebaseDataRef = firebaseDatabase.getReference();
        final DatabaseReference team = firebaseDataRef.child("teams").child(mAuth).child("players");

        team.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshotChildren : dataSnapshot.getChildren()) {
                    Player player = snapshotChildren.getValue(Player.class);
                    player.setContador(contador);
                    playerList.add(player);
                    contador = contador + 1;

                }

                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_jugadoresEquipo);
                AdapterJugadores adapterJugadores = new AdapterJugadores(getApplicationContext());

                adapterJugadores.setPlayersList(playerList);

                if (!(playerList.size() <= 5)) {
                    
                    bttn_agregarJugadores.setVisibility(View.GONE);
                }

                recyclerView.setAdapter(adapterJugadores);


                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), VERTICAL, false);
                recyclerView.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*team.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                *//*for (DataSnapshot snapshotChildren : dataSnapshot.getChildren()) {
                    Equipo team = snapshotChildren.getValue(Equipo.class);

                    unEquipolist.add(team);

                }
                    cargarJugadores();*//*
                Equipo team = dataSnapshot.getValue(mAuth);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }




    private void signOut() {
        //mAuth.signOut();
        finish();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == buttonComenzarDesafio.getId()) {
            //agregarDesafioAFB();
            startActivity(new Intent(EquipoArmadoActivity.this, MainActivity.class));
        }

        if (v.getId() == bttn_agregarJugadores.getId()) {
            startActivity(new Intent(EquipoArmadoActivity.this, EquipoActivity.class));
        }
    }







}

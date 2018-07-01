package com.appaloossa.honorgame.View.activity.Capitan;

import android.content.Intent;
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

import com.appaloossa.honorgame.R;
import com.appaloossa.honorgame.View.adapter.CapitanAdapter.AdapterRanking;
import com.appaloossa.honorgame.model.Equipo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static android.widget.LinearLayout.VERTICAL;

public class RankingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterRanking.EquipoListener{
    private List<Equipo> equipoList = new ArrayList<>();
    private EquipoListenerR equipoListener;
    private RecyclerView recyclerView;
    private AdapterRanking adapterRanking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_ranking);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_ranking);
        navigationView.setNavigationItemSelectedListener(this);

        adapterRanking = new AdapterRanking(getApplicationContext(),this);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView_ranking);
        cargarEquipos();



        //cargar lista de jugadores


        Collections.sort(equipoList, new Comparator<Equipo>() {
            @Override
            public int compare(Equipo o1, Equipo o2) {
                return o1.getScore().compareTo(o2.getScore());
            }
        });
        Collections.reverse(equipoList);


        adapterRanking.setEquipoList(equipoList);
        recyclerView.setAdapter(adapterRanking);



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);




        // mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_ranking);
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
            startActivity(new Intent(RankingActivity.this,MainActivity.class));

        } else if (id == R.id.nav_gallery) {

            //startActivity(new Intent(MainActivity.this,PartidosActivity.class));
                startActivity(new Intent(RankingActivity.this,PartidosActivity.class));


        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(RankingActivity.this,EquipoActivity.class));

        }  else if (id == R.id.nav_send) {
            signOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_ranking);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void cargarEquipos(){
        final String mAuth;
        mAuth = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference firebaseDataRef = firebaseDatabase.getReference();
        final DatabaseReference team = firebaseDataRef.child("teams");
        team.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshotChildren : dataSnapshot.getChildren()) {
                    Equipo unEquipo = snapshotChildren.getValue(Equipo.class);
                    String key = snapshotChildren.getKey().toString();

                    unEquipo.setId(key);

                        equipoList.add(unEquipo);



                }


                //adapterRanking = new AdapterRanking(getApplicationContext(),this);
                //cargar lista de jugadores

                Collections.sort(equipoList, new Comparator<Equipo>() {
                    @Override
                    public int compare(Equipo o1, Equipo o2) {
                        return o1.getScore().compareTo(o2.getScore());
                    }
                });
                Collections.reverse(equipoList);


                adapterRanking.setEquipoList(equipoList);
                recyclerView.setAdapter(adapterRanking);



                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),VERTICAL,false);
                recyclerView.setLayoutManager(linearLayoutManager);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }




    private void signOut() {
        //mAuth.signOut();
        finish();
    }

    @Override
    public void seleccionaronA(Equipo unEquipo) {
        equipoListener.seleccionaronA(unEquipo);
    }

    public interface EquipoListenerR{
        public void seleccionaronA(Equipo unEquipo);
    }
}
package com.appaloossa.honorgame.View.activity.Capitan;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.appaloossa.honorgame.R;
import com.appaloossa.honorgame.View.activity.Arbitro.ArbitroMainActivity;
import com.appaloossa.honorgame.View.adapter.CapitanAdapter.ViewPagerAdapter;
import com.appaloossa.honorgame.View.fragments.Capitan.desafio_fragments.FragmentDesafios;
import com.appaloossa.honorgame.View.fragments.Capitan.desafio_fragments.FragmentEnEspera;
import com.appaloossa.honorgame.View.fragments.Capitan.desafio_fragments.FragmentRecibido;
import com.appaloossa.honorgame.base.HonorBaseActivity;
import com.appaloossa.honorgame.model.Desafio;
import com.appaloossa.honorgame.model.Equipo;
import com.appaloossa.honorgame.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends HonorBaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        FragmentDesafios.DesafioListenerFrag,
        FragmentEnEspera.DesafioEnEsperaListener{

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private TabLayout tabLayout;

    FragmentDesafios fragmentDesafios;

    /*Typeface poppinsBold = Typeface.createFromAsset(getAssets(),"fonts/Poppins-Bold.ttf");
    Typeface poppinsRegular = Typeface.createFromAsset(getAssets(),"fonts/Poppins-Regular.ttf");
    Typeface poppinsSemiBold = Typeface.createFromAsset(getAssets(),"fonts/Poppins-SemiBold.ttf");
    Typeface poppinsLight = Typeface.createFromAsset(getAssets(),"fonts/Poppins-Light.ttf");
    Typeface poppinsMedium = Typeface.createFromAsset(getAssets(),"fonts/Poppins-Medium.ttf");*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        queTipoDeUsuarioEs(mAuth);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPagerLayout);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        // Add Fragments to adapter one by one
        adapter.addFragment(new FragmentDesafios(), "A desafiar");
        adapter.addFragment(new FragmentRecibido(), "Recibidos");
        adapter.addFragment(new FragmentEnEspera(),"En espera");

        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.setupWithViewPager(viewPager);

        setCustomFont();




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
        } else if (id == R.id.nav_gallery) {

            startActivity(new Intent(MainActivity.this,PartidosActivity.class));


        } else if (id == R.id.nav_slideshow) {
            startActivity(new Intent(MainActivity.this,RankingActivity.class));

        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(MainActivity.this,EquipoActivity.class));

        }  else if (id == R.id.nav_send) {
            signOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void queTipoDeUsuarioEs(FirebaseAuth mAuth){
        FirebaseUser user = mAuth.getCurrentUser();

        mDatabase.child(User.modelName).child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);

                if (user.getUsuarioTipo().equals(1)) {
                    startActivity(new Intent(MainActivity.this, ArbitroMainActivity.class));
                    finish();
                } else if (user.getUsuarioTipo().equals(2)){
                    Toast.makeText(getApplicationContext(),"journalist",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Capitan",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }




    private void signOut() {
        mAuth.signOut();
        finish();
    }

    public void setCustomFont() {

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();

        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);

            int tabChildsCount = vgTab.getChildCount();

            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    //Put your font in assests folder
                    //assign name of the font here (Must be case sensitive)
                    ((TextView) tabViewChild).setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Poppins-Light.ttf"));
                }

            }
        }
    }


    @Override
    public void seleccionaronA(Equipo unDesafio) {
        Toast.makeText(getApplicationContext(),"jnjnkj",Toast.LENGTH_LONG).show();
    }




    @Override
    public void seleccionaronAenEspera(Desafio unDesafio) {

    }
}

package com.appaloossa.honorgame.View.activity.Capitan;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.appaloossa.honorgame.R;
import com.appaloossa.honorgame.model.Equipo;
import com.appaloossa.honorgame.model.Player;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EquipoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private FirebaseAuth mAuth;
    private Button buttonAgregarJugador;
   private Button nextActivity;
   private TextView tvPlayerCount;
   private EditText editTextNombreDeEquipo;
   private EditText editTextNombreJugador;
   private EditText editTextDni;
   private EditText editTextMail;
   private TextView title_numJugador;
   private List<Player> listJugadores = new ArrayList<>();
   private List<Player>playerList = new ArrayList<>();
   private Boolean estaLLeno = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface poppinsBold = Typeface.createFromAsset(this.getAssets(),"fonts/Poppins-Bold.ttf");
        Typeface poppinsRegular = Typeface.createFromAsset(this.getAssets(),"fonts/Poppins-Regular.ttf");
        Typeface poppinsSemiBold = Typeface.createFromAsset(this.getAssets(),"fonts/Poppins-SemiBold.ttf");
        Typeface poppinsLight = Typeface.createFromAsset(this.getAssets(),"fonts/Poppins-Light.ttf");
        Typeface poppinsMedium = Typeface.createFromAsset(this.getAssets(),"fonts/Poppins-Medium.ttf");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_equipo);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_equipo);
        navigationView.setNavigationItemSelectedListener(this);

         buttonAgregarJugador = (Button)findViewById(R.id.bttn_agregarJugador);
         buttonAgregarJugador.setTypeface(poppinsSemiBold);
         nextActivity = (Button)findViewById(R.id.button_siguiente);
         editTextNombreDeEquipo = (EditText)findViewById(R.id.editText_nombreDeEquipo);
         editTextNombreDeEquipo.setTypeface(poppinsRegular);
        editTextNombreJugador = (EditText)findViewById(R.id.editText_nombreYapellido);
        editTextNombreJugador.setTypeface(poppinsRegular);
        tvPlayerCount = (TextView)findViewById(R.id.tv_count_jugadores);
        tvPlayerCount.setTypeface(poppinsRegular);
         editTextDni =(EditText)findViewById(R.id.editText_dni);
         editTextDni.setTypeface(poppinsRegular);
         editTextMail = (EditText)findViewById(R.id.editText_mail);
         editTextMail.setTypeface(poppinsRegular);
         title_numJugador = (TextView)findViewById(R.id.tv_count_jugadores);
         title_numJugador.setTypeface(poppinsSemiBold);
        buttonAgregarJugador.setOnClickListener(this);
        nextActivity.setOnClickListener(this);

        cuantosJugadoresHay();



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_equipo);
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
            startActivity(new Intent(EquipoActivity.this,MainActivity.class));
        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(EquipoActivity.this,PartidosActivity.class));
        } else if (id == R.id.nav_slideshow) {
            startActivity(new Intent(EquipoActivity.this,RankingActivity.class));
        } else if (id == R.id.nav_manage) {
            Toast.makeText(this,"ya estas aca",Toast.LENGTH_LONG).show();
        }
         else if (id == R.id.nav_send) {
            signOut();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_equipo);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }






    private void signOut() {
        //mAuth.signOut();
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == buttonAgregarJugador.getId()) {
            mAuth = FirebaseAuth.getInstance();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = database.getReference();
            DatabaseReference team = databaseReference.child("teams");

            //DatabaseReference newTeamRef = team.push();
            String userid = mAuth.getCurrentUser().getUid();
            // Equipo equipoToUpLoad = new Equipo(0, 0, 0, 0, editTextNombreDeEquipo.getText().toString(), "caba", listJugadores, 0);

            Equipo equipoToUpLoad = new Equipo();
            equipoToUpLoad.setLost(0);
            //equipoToUpLoad.setId(userid);
            //equipoToUpLoad.setRating(0);
            equipoToUpLoad.setScore(0);
            equipoToUpLoad.setTied(0);
            equipoToUpLoad.setWon(0);
            equipoToUpLoad.setName(editTextNombreDeEquipo.getText().toString());


            //equipoToUpLoad.setUbicacion("caba");
            //equipoToUpLoad.setPlayers(listJugadores);
            team.getRef().child(userid).setValue(equipoToUpLoad);

            String nombreDeJugador = editTextNombreJugador.getText().toString();
            Integer Dni = Integer.parseInt(editTextDni.getText().toString());
            String Mail = editTextMail.getText().toString();
            Player player0 = new Player();
            player0.setName(nombreDeJugador);
            player0.setDni(Dni);
            player0.setMail(Mail);
            player0.setGoals(0);
            player0.setRedCards(0);
            player0.setYellowCard(0);
            listJugadores.add(player0);
            Toast.makeText(this, editTextNombreJugador.getText().toString() + " fue agregado", Toast.LENGTH_LONG).show();
            //tvPlayerCount.setText("Jugador1");
            editTextNombreJugador.setText("");
            editTextDni.setText("");
            editTextMail.setText("");

            team.getRef().child(userid).child("players").push().setValue(player0);


            /*if (listJugadores.size() <= 0) {
                tvPlayerCount.setText("Jugador0");
                String nombreDeJugador = editTextNombreJugador.getText().toString();
                Integer Dni = Integer.parseInt(editTextDni.getText().toString());
                String Mail = editTextMail.getText().toString();
                Player player0 = new Player();
                player0.setName(nombreDeJugador);
                player0.setDni(Dni);
                player0.setMail(Mail);
                player0.setGoals(0);
                player0.setRedCards(0);
                player0.setYellowCard(0);
                listJugadores.add(player0);
                Toast.makeText(this, editTextNombreJugador.getText().toString() + " fue agregado", Toast.LENGTH_LONG).show();
                tvPlayerCount.setText("Jugador1");
                editTextNombreJugador.setText("");
                editTextDni.setText("");
                editTextMail.setText("");
            }

            else if (listJugadores.size() == 1){
                tvPlayerCount.setText("Jugador 2");
                String nombreDeJugador = editTextNombreJugador.getText().toString();
                Integer Dni = Integer.parseInt(editTextDni.getText().toString());
                String Mail = editTextMail.getText().toString();
                Player player1 = new Player();
                player1.setName(nombreDeJugador);
                player1.setDni(Dni);
                player1.setMail(Mail);
                player1.setGoals(0);
                player1.setRedCards(0);
                player1.setYellowCard(0);
                listJugadores.add(player1);
                Toast.makeText(this, editTextNombreJugador.getText().toString() + " fue agregado", Toast.LENGTH_LONG).show();
                editTextNombreJugador.setText("");
                editTextDni.setText("");
                editTextMail.setText("");

            } else if (listJugadores.size() == 2){
                tvPlayerCount.setText("Jugador 3");
                String nombreDeJugador = editTextNombreJugador.getText().toString();
                Integer Dni = Integer.parseInt(editTextDni.getText().toString());
                String Mail = editTextMail.getText().toString();
                Player player2 = new Player();
                player2.setName(nombreDeJugador);
                player2.setDni(Dni);
                player2.setMail(Mail);
                player2.setGoals(0);
                player2.setRedCards(0);
                player2.setYellowCard(0);
                listJugadores.add(player2);
                Toast.makeText(this, editTextNombreJugador.getText().toString() + " fue agregado", Toast.LENGTH_LONG).show();
                editTextNombreJugador.setText("");
                editTextDni.setText("");
                editTextMail.setText("");

            } else if (listJugadores.size() == 3){
                tvPlayerCount.setText("Jugador 4");
                String nombreDeJugador = editTextNombreJugador.getText().toString();
                Integer Dni = Integer.parseInt(editTextDni.getText().toString());
                String Mail = editTextMail.getText().toString();
                Player player3 = new Player();
                player3.setName(nombreDeJugador);
                player3.setDni(Dni);
                player3.setMail(Mail);
                player3.setGoals(0);
                player3.setRedCards(0);
                player3.setYellowCard(0);
                listJugadores.add(player3);
                Toast.makeText(this, editTextNombreJugador.getText().toString() + " fue agregado", Toast.LENGTH_LONG).show();
                editTextNombreJugador.setText("");
                editTextDni.setText("");
                editTextMail.setText("");

            } else if (listJugadores.size() == 4){
                tvPlayerCount.setText("Jugador 5");
                String nombreDeJugador = editTextNombreJugador.getText().toString();
                Integer Dni = Integer.parseInt(editTextDni.getText().toString());
                String Mail = editTextMail.getText().toString();
                Player player4 = new Player();
                player4.setName(nombreDeJugador);
                player4.setDni(Dni);
                player4.setMail(Mail);
                player4.setGoals(0);
                player4.setRedCards(0);
                player4.setYellowCard(0);
                listJugadores.add(player4);
                Toast.makeText(this, editTextNombreJugador.getText().toString() + " fue agregado", Toast.LENGTH_LONG).show();
                editTextNombreJugador.setText("");
                editTextDni.setText("");
                editTextMail.setText("");

            }*/
            if (listJugadores.size() >= 5) {
                buttonAgregarJugador.setVisibility(View.GONE);
                /*mAuth = FirebaseAuth.getInstance();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = database.getReference();
                DatabaseReference team = databaseReference.child("teams");

                //DatabaseReference newTeamRef = team.push();
                String userid = mAuth.getCurrentUser().getUid();
               // Equipo equipoToUpLoad = new Equipo(0, 0, 0, 0, editTextNombreDeEquipo.getText().toString(), "caba", listJugadores, 0);

               Equipo equipoToUpLoad = new Equipo();
               equipoToUpLoad.setLost(0);
               //equipoToUpLoad.setId(userid);
               //equipoToUpLoad.setRating(0);
               equipoToUpLoad.setScore(0);
               equipoToUpLoad.setTied(0);
               equipoToUpLoad.setWon(0);
               equipoToUpLoad.setName(editTextNombreDeEquipo.getText().toString());


               //equipoToUpLoad.setUbicacion("caba");
                //equipoToUpLoad.setPlayers(listJugadores);
                team.getRef().child(userid).setValue(equipoToUpLoad);*/





                /*team.child("lost").setValue(0);
                team.child("won").setValue(0);
                team.child("tied").setValue(0);
                team.child("score").setValue(0);
                team.child("name").setValue(editTextNombreDeEquipo.getText().toString());
                team.child("players").setValue(listJugadores);*/







               // Toast.makeText(this,"Equipo Listo",Toast.LENGTH_LONG);
                //startActivity(new Intent(EquipoActivity.this,EquipoArmadoActivity.class));


            }

        }
        if (v.getId() == nextActivity.getId()){
            startActivity(new Intent(EquipoActivity.this,EquipoArmadoActivity.class));
        }
    }
    private void cuantosJugadoresHay() {
        String mAuth;
        mAuth = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference firebaseDataRef = firebaseDatabase.getReference();
        final DatabaseReference team = firebaseDataRef.child("teams").child(mAuth).child("players");

        team.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshotChildren : dataSnapshot.getChildren()) {
                    Player player = snapshotChildren.getValue(Player.class);

                    playerList.add(player);

                }
                if (playerList.size() >= 5){
                    estaLLeno = true;
                }
                if (estaLLeno == true){
                    startActivity(new Intent(EquipoActivity.this, EquipoArmadoActivity.class));
                    finish();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

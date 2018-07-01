package com.appaloossa.honorgame.View.fragments.Capitan.desafio_fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appaloossa.honorgame.R;
import com.appaloossa.honorgame.View.adapter.CapitanAdapter.AdapterDesafiosRecibidos;
import com.appaloossa.honorgame.model.Desafio;
import com.appaloossa.honorgame.model.Equipo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRecibido extends Fragment implements AdapterDesafiosRecibidos.DesafioRecibidoListener {
    private List<Desafio> desafioList = new ArrayList<>();
    private String mAuth;
    private AdapterDesafiosRecibidos.DesafioRecibidoListener desafioListener;
    private RecyclerView recyclerView;
    private AdapterDesafiosRecibidos adaptador;
    private int contador;
    public FragmentRecibido() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recibido, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_recibido);
        cargarDesafios();


        return view;
    }

    private void cargarDesafios(){
        mAuth = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference firebaseDataRef = firebaseDatabase.getReference();
        final DatabaseReference team = firebaseDataRef.child("challenges");
        team.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshotChildren : dataSnapshot.getChildren()) {
                    Desafio unDesafio = snapshotChildren.getValue(Desafio.class);

                    if (unDesafio.getReceiver().equals(mAuth)) {
                        cargarEquipoADesafio(unDesafio);
                        //desafioList.add(unDesafio);
                    }


                }





            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void cargarEquipoADesafio(final Desafio unDesafio){
        contador = 0;
        String mAuth;
        mAuth = unDesafio.getOwner().toString();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference firebaseDataRef = firebaseDatabase.getReference();
        final DatabaseReference team = firebaseDataRef.child("teams");
        team.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Equipo unEquipo = snapshot.getValue(Equipo.class);
                    String key = snapshot.getKey().toString();
                    unEquipo.setId(key);

                    if (unEquipo.getId().equals(unDesafio.getOwner().toString())) {
                        unDesafio.setUnEquipo(unEquipo);
                        desafioList.add(unDesafio);
                        unDesafio.setNumPos(contador);
                        contador = contador + 1;

                    }


                }
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

                recyclerView.setLayoutManager(layoutManager);

                adaptador = new AdapterDesafiosRecibidos(desafioList, getContext(), desafioListener);

                recyclerView.setAdapter(adaptador);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }





    @Override
    public void seleccionaronA(Desafio unDesafio) {

    }
}


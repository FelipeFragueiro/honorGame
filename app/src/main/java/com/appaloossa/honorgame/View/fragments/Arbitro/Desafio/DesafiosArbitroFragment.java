package com.appaloossa.honorgame.View.fragments.Arbitro.Desafio;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appaloossa.honorgame.R;
import com.appaloossa.honorgame.View.adapter.ArbitroAdapter.AdapterNuevosDesafios;
import com.appaloossa.honorgame.model.Desafio;
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
public class DesafiosArbitroFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdapterNuevosDesafios adapter;
    private String mAuth;
    private List<Desafio> desafiosList = new ArrayList<>();
    private int contador;

    public DesafiosArbitroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_desafios_arbitro, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_nuevosDesafios_arbitro);


        cargarDesafios();



        return view;
    }

    public void cargarDesafios(){
        contador = 0;
        mAuth = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference firebaseDataRef = firebaseDatabase.getReference();
        final DatabaseReference team = firebaseDataRef.child("challenges");

        team.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshotChildren : dataSnapshot.getChildren()) {

                    Desafio unDesafio = snapshotChildren.getValue(Desafio.class);
                    //String key = snapshotChildren.getKey().toString();
                    if (unDesafio.getState()==1) {
                        unDesafio.setContador(contador);
                        desafiosList.add(unDesafio);
                        contador = contador + 1;
                    }

                }

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

                recyclerView.setLayoutManager(layoutManager);
                adapter = new AdapterNuevosDesafios(getContext());
                adapter.setProductList(desafiosList);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

}

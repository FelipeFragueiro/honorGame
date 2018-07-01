package com.appaloossa.honorgame.View.fragments.Arbitro.Desafio.Partidos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appaloossa.honorgame.R;
import com.appaloossa.honorgame.View.adapter.ArbitroAdapter.AdapterPartidosTrabajados;
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
public class PartidosPorTrabajarFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdapterPartidosTrabajados adapterPartidosTrabajados;
    private String mAuth;
    private List<Desafio> desafiosList = new ArrayList<>();

    public PartidosPorTrabajarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_partidos_por_trabajar, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView_partidosPorTrabajar_arbitro);
        cargarDesafios();





        return view;
    }

    public void cargarDesafios(){

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
                    desafiosList.add(unDesafio);

                    //Aca para ponerle el numero de defasio lo que podria hacer es poner el primer item que resivo
                    // de firebase al final (voltear toda la lista)y asi numerarlas con un contador.
                    //o algo del estilo.
                }

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                adapterPartidosTrabajados = new AdapterPartidosTrabajados(getContext());
                adapterPartidosTrabajados.setProductList(desafiosList);
                adapterPartidosTrabajados.setHayResumen(2);
                recyclerView.setAdapter(adapterPartidosTrabajados);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


    }

}

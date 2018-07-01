package com.appaloossa.honorgame.View.fragments.Capitan.desafio_fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appaloossa.honorgame.R;
import com.appaloossa.honorgame.View.adapter.CapitanAdapter.AdapterEnEspera;
import com.appaloossa.honorgame.model.Desafio;
import com.appaloossa.honorgame.model.Equipo;
import com.appaloossa.honorgame.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentEnEspera extends Fragment implements AdapterEnEspera.DesafioEnEsperaListener {
    private List<Desafio> desafioList = new ArrayList<>();
    private DesafioEnEsperaListener desafioEnEsperaListener;


    public FragmentEnEspera() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        desafioEnEsperaListener = (FragmentEnEspera.DesafioEnEsperaListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_en_espera, container, false);


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_desafio_enEspera);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        cargarDesafios(desafioList);
        AdapterEnEspera adaptador = new AdapterEnEspera( getContext(), this);
        adaptador.setProductList(desafioList);
        recyclerView.setAdapter(adaptador);



        return view;
    }
    private void cargarDesafios(List<Desafio> unaLista){

        Equipo unEquipo = new Equipo();
        unEquipo.setName("CUBA");
        unEquipo.setRating(2);
        Desafio unDesafio = new Desafio();
        unDesafio.setNumPos(0);
        unDesafio.setUnEquipo(unEquipo);
        unDesafio.setOwnerName(unEquipo.getName());

        Equipo unEquipo2 = new Equipo();
        unEquipo2.setName("pingo");
        unEquipo2.setRating(3);
        Desafio unDesafio2 = new Desafio();
        unDesafio2.setNumPos(1);
        unDesafio2.setUnEquipo(unEquipo2);
        unDesafio2.setOwnerName(unEquipo2.getName());

        Equipo unEquipo3 = new Equipo();
        unEquipo3.setName("los de moron");
        unEquipo3.setRating(1);
        Desafio unDesafio3 = new Desafio();
        unDesafio3.setNumPos(2);
        unDesafio3.setUnEquipo(unEquipo3);
        unDesafio3.setOwnerName(unEquipo3.getName());

        unaLista.add(unDesafio);
        unaLista.add(unDesafio2);
        unaLista.add(unDesafio3);
    }

    @Override
    public void seleccionaronA(Desafio unDesafio) {

    }
    public interface DesafioEnEsperaListener{
        public void seleccionaronAenEspera(Desafio unDesafio);
    }

}

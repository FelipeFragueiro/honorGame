package com.appaloossa.honorgame.View.fragments.Capitan.partidos_fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appaloossa.honorgame.R;
import com.appaloossa.honorgame.View.adapter.CapitanAdapter.AdapterPartidos;
import com.appaloossa.honorgame.model.Desafio;
import com.appaloossa.honorgame.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentJugados extends Fragment implements AdapterPartidos.DesafioListener{
    private List<Desafio> desafioList = new ArrayList<>();
    private AdapterPartidos.DesafioListener desafioListener;


    public FragmentJugados() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_partidos, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_partidos_jugados);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        cargarDesafios(desafioList);
        AdapterPartidos adaptador = new AdapterPartidos(desafioList, getContext(), desafioListener);

        recyclerView.setAdapter(adaptador);


        return view;
    }
    private void cargarDesafios(List<Desafio>unaLista){
        List<Player>unaListaDePlayers = new ArrayList<>();
       /* Player unPlayer = new Player("micho","micho@tito.com",123123,1,2,1);
        Player unPlayer2 = new Player("micho","micho@tito.com",123123,1,2,1);
        Player unPlayer3 = new Player("micho","micho@tito.com",123123,1,2,1);
        Player unPlayer4 = new Player("micho","micho@tito.com",123123,1,2,1);
        Player unPlayer5 = new Player("micho","micho@tito.com",123123,1,2,1);
        unaListaDePlayers.add(unPlayer);
        unaListaDePlayers.add(unPlayer2);
        unaListaDePlayers.add(unPlayer3);
        unaListaDePlayers.add(unPlayer4);
        unaListaDePlayers.add(unPlayer5);*/

        /*Equipo unEquipo = new Equipo("fsdfsdf",6,130,1,12,"El nenefc","Lanus",unaListaDePlayers,3);
        Desafio unDesafio = new Desafio();
        unDesafio.setUnEquipo(unEquipo);
        unDesafio.setOwnerName(unEquipo.getName());

        Equipo unEquipo2 = new Equipo("fsdfsdf",3,103,1,12,"El nenefc","caba",unaListaDePlayers,5);
        Desafio unDesafio2 = new Desafio();
        unDesafio2.setUnEquipo(unEquipo2);
        unDesafio2.setOwnerName(unEquipo2.getName());

        Equipo unEquipo3 = new Equipo("fsdfsdf",3,170,1,12,"El nenefccc","puerto rico",unaListaDePlayers,3);
        Desafio unDesafio3 = new Desafio();
        unDesafio3.setUnEquipo(unEquipo3);
        unDesafio3.setOwnerName(unEquipo3.getName());

        unaLista.add(unDesafio);
        unaLista.add(unDesafio2);
        unaLista.add(unDesafio3);*/
    }

    @Override
    public void seleccionaronA(Desafio unDesafio) {

    }
}

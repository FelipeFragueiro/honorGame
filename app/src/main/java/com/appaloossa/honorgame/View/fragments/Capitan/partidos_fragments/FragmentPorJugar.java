package com.appaloossa.honorgame.View.fragments.Capitan.partidos_fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appaloossa.honorgame.R;
import com.appaloossa.honorgame.View.adapter.CapitanAdapter.AdapterPartidosPorJugar;
import com.appaloossa.honorgame.model.Desafio;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPorJugar extends Fragment implements AdapterPartidosPorJugar.PartidosListener {
    private List<Desafio> desafioList = new ArrayList<>();
    private DesafioPorJugarListener desafioListener;


    public FragmentPorJugar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_por_jugar, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_partidosPorjugar);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        cargarDesafios(desafioList);
        AdapterPartidosPorJugar adaptador = new AdapterPartidosPorJugar(desafioList, getContext(), this);

        recyclerView.setAdapter(adaptador);


        return view;
    }
    private void cargarDesafios(List<Desafio>unaLista){
       /* List<Player>unaListaDePlayers = new ArrayList<>();
        Player unPlayer = new Player("micho","micho@tito.com",123123,1,2,1);
        Player unPlayer2 = new Player("micho","micho@tito.com",123123,1,2,1);
        Player unPlayer3 = new Player("micho","micho@tito.com",123123,1,2,1);
        Player unPlayer4 = new Player("micho","micho@tito.com",123123,1,2,1);
        Player unPlayer5 = new Player("micho","micho@tito.com",123123,1,2,1);
        unaListaDePlayers.add(unPlayer);
        unaListaDePlayers.add(unPlayer2);
        unaListaDePlayers.add(unPlayer3);
        unaListaDePlayers.add(unPlayer4);
        unaListaDePlayers.add(unPlayer5);*/


    }

    @Override
    public void seleccionaronA(Desafio unDesafio) {
        desafioListener.seleccionaronA(unDesafio);
    }

    public interface DesafioPorJugarListener{
        public void seleccionaronA(Desafio unDesafio);
    }
}


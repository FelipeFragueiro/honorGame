package com.appaloossa.honorgame.View.adapter.CapitanAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.appaloossa.honorgame.R;
import com.appaloossa.honorgame.model.Desafio;
import com.appaloossa.honorgame.model.Equipo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ines on 11/4/2018.
 */

public class AdapterRanking extends RecyclerView.Adapter {
    private List<Equipo> equipoList;
    private Context unContext;
    private EquipoListener equipoListener;

    public AdapterRanking(Context unContext, EquipoListener desafioListener){
        this.equipoList = new ArrayList<>();
        this.unContext = unContext;
        this.equipoListener = (EquipoListener) desafioListener;
    }
    public void setEquipoList(List<Equipo> equipolist1) {
        this.equipoList = equipolist1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(unContext);
        View celda = layoutInflater.inflate(R.layout.cell_ranking,parent,false);
        RankingViewHolder rankingViewHolder = new RankingViewHolder(celda);

        return rankingViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Equipo unEquipo = equipoList.get(position);
        RankingViewHolder rankingViewHolder = (RankingViewHolder)holder;
        rankingViewHolder.cargarEquipo(unEquipo);

        View viewDeLaCelda = rankingViewHolder.itemView;
        viewDeLaCelda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equipoListener.seleccionaronA(unEquipo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return equipoList.size();
    }

    private class RankingViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        //TextView textViewDesafio = (TextView) itemView.findViewById(R.id.tv_nombreDeEquipo);
        TextView textViewPuesto = (TextView)itemView.findViewById(R.id.tv_puesto);
        TextView textViewNombreEquipo = (TextView)itemView.findViewById(R.id.tv_nombreEquipo_ranking);
        TextView textViewPuntos = (TextView)itemView.findViewById(R.id.tv_puntajes);


        public RankingViewHolder(View itemView){
            super(itemView);




        }

        public void cargarEquipo(Equipo unEquipo){
            //textViewDesafio.setText(unDesafio.getOwnerName());
            textViewNombreEquipo.setText(unEquipo.getName());
            textViewPuntos.setText(unEquipo.getScore().toString());


        }

        @Override
        public void onClick(View v) {


        }
    }

    public interface EquipoListener {
        public void seleccionaronA(Equipo unEquipo);
    }
}


package com.appaloossa.honorgame.View.adapter.CapitanAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appaloossa.honorgame.R;
import com.appaloossa.honorgame.model.Desafio;

import java.util.List;

/**
 * Created by Ines on 30/3/2018.
 */

public class AdapterPartidos extends RecyclerView.Adapter {


    private List<Desafio> desafioList;
    private Context unContext;
    private DesafioListener desafioListener;

    public AdapterPartidos(List<Desafio> desafioList, Context unContext, AdapterPartidos.DesafioListener desafioListener){
        this.desafioList = desafioList;
        this.unContext = unContext;
        this.desafioListener = (DesafioListener) desafioListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(unContext);
        View celda = layoutInflater.inflate(R.layout.cell_partidos_jugados,parent,false);
        DesafioViewHolder desafioViewHolder = new DesafioViewHolder(celda);

        return desafioViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Desafio unDesafio = desafioList.get(position);
        DesafioViewHolder desafioViewHolder = (DesafioViewHolder)holder;
        desafioViewHolder.cargarDesafio(unDesafio);

        View viewDeLaCelda = desafioViewHolder.itemView;
        viewDeLaCelda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                desafioListener.seleccionaronA(unDesafio);
            }
        });
    }

    @Override
    public int getItemCount() {
        return desafioList.size();
    }

    private class DesafioViewHolder extends  RecyclerView.ViewHolder{
        TextView textViewDesafio = (TextView) itemView.findViewById(R.id.tv_nombre_equipo_jugado);
        TextView resultado = (TextView) itemView.findViewById(R.id.tv_resultado_jugado);


        public DesafioViewHolder(View itemView){
            super(itemView);
        }

        public void cargarDesafio(Desafio unDesafio){
            textViewDesafio.setText(unDesafio.getOwnerName());



        }
    }

    public interface DesafioListener {
        public void seleccionaronA(Desafio unDesafio);
    }
}


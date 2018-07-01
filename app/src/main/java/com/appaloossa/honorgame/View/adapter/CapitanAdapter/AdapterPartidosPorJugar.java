package com.appaloossa.honorgame.View.adapter.CapitanAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.appaloossa.honorgame.R;
import com.appaloossa.honorgame.model.Desafio;

import java.util.List;

import static com.appaloossa.honorgame.R.color.yellow;

/**
 * Created by Ines on 14/4/2018.
 */

public class AdapterPartidosPorJugar extends RecyclerView.Adapter {
    private List<Desafio> desafioList;
    private Context unContext;
    private PartidosListener desafioListener;

    public AdapterPartidosPorJugar(List<Desafio> desafioList, Context unContext, PartidosListener desafioListener){
        this.desafioList = desafioList;
        this.unContext = unContext;
        this.desafioListener = (PartidosListener) desafioListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(unContext);
        View celda = layoutInflater.inflate(R.layout.cell_partidosporjugar,parent,false);
        PartidosViewHolder partidoViewHolder = new PartidosViewHolder(celda);

        return partidoViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Desafio unDesafio = desafioList.get(position);
        PartidosViewHolder partidoViewHolder = (PartidosViewHolder)holder;
        partidoViewHolder.cargarPartido(unDesafio);

        View viewDeLaCelda = partidoViewHolder.itemView;
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

    private class PartidosViewHolder extends  RecyclerView.ViewHolder{
       //
        RatingBar ratingBar = (RatingBar)itemView.findViewById(R.id.ratingbar_partidosporjugar);
        TextView textView = (TextView)itemView.findViewById(R.id.tv_partidos);


        public PartidosViewHolder(View itemView){
            super(itemView);
        }

        @SuppressLint("ResourceAsColor")
        public void cargarPartido(Desafio unDesafio){

            textView.setText(unDesafio.getUnEquipo().getName());
            ratingBar.setRating(unDesafio.getUnEquipo().getRating());
            //ratingBar.setBackgroundColor(yellow);

        }
    }

    public interface PartidosListener {
        public void seleccionaronA(Desafio unDesafio);
    }
}


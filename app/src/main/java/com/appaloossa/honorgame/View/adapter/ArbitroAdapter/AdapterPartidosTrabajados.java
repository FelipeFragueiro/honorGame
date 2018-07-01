package com.appaloossa.honorgame.View.adapter.ArbitroAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.appaloossa.honorgame.R;
import com.appaloossa.honorgame.View.activity.Arbitro.ResumenArbitroActivity;
import com.appaloossa.honorgame.model.Desafio;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ines on 12/5/2018.
 */

public class AdapterPartidosTrabajados extends RecyclerView.Adapter {

    private List<Desafio> desafioList;
    private Context unContext;
    private Integer hayResumen = 0;
    private String challengueKey;

    public AdapterPartidosTrabajados(Context unContext){
        this.desafioList = new ArrayList<>();
        this.unContext = unContext;
    }
    public void setProductList(List<Desafio> desafiolist1) {
        this.desafioList = desafiolist1;
    }
    public void setHayResumen(Integer integer){
        this.hayResumen = integer;
    }
    public void setChallengueKey(String key){
        this.challengueKey = key;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(unContext);
        View celda = layoutInflater.inflate(R.layout.cell_partidos_trabajados, parent,false);
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
            }
        });
    }

    @Override
    public int getItemCount() {
        return desafioList.size();
    }

    private class DesafioViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvNumeroEquipo = (TextView)itemView.findViewById(R.id.tv_numeroDeDesafio_partidosTrabajados_arbitro);
        TextView tvFechaTrabajada = (TextView)itemView.findViewById(R.id.tv_fechaYHora_partidosTrabajados);
        TextView tvHiddenChallengeID = (TextView)itemView.findViewById(R.id.hidden_challengeidText);
        Button bttnVerResumen = (Button)itemView.findViewById(R.id.bttn_verResumen_partidosTrabajados_arbitro);
        Button bttnCrearResumen = (Button)itemView.findViewById(R.id.bttn_crearResumen_partidosTrabajados_arbitro);



        public DesafioViewHolder(View itemView){
            super(itemView);

            if (hayResumen == 1){
                bttnCrearResumen.setVisibility(View.GONE);
                bttnVerResumen.setVisibility(View.VISIBLE);
                bttnVerResumen.setOnClickListener(this);
            }else if (hayResumen == 0){
                bttnVerResumen.setVisibility(View.GONE);
                bttnCrearResumen.setVisibility(View.VISIBLE);
                bttnCrearResumen.setOnClickListener(this);
            }else if (hayResumen == 2){
                bttnVerResumen.setVisibility(View.GONE);
                bttnCrearResumen.setVisibility(View.GONE);
            }
        }

        public void cargarDesafio(Desafio unDesafio){
            //tvNumeroEquipo.setText();
            if (unDesafio.getId() != null) {

                String key = unDesafio.getId().get(unDesafio.getNumPos()).toString();
                tvFechaTrabajada.setText(unDesafio.getDate() + " " + unDesafio.getTime1());
                tvHiddenChallengeID.setText(key);
            }


        }

        @Override
        public void onClick(View v) {

            if (v.getId() == bttnCrearResumen.getId()){
                Toast.makeText(unContext,"crearResumen",Toast.LENGTH_SHORT).show();
                ///unContext.startActivity(new Intent(unContext.getApplicationContext(),ResumenArbitroActivity.class));
                Intent intent = new Intent(unContext, ResumenArbitroActivity.class);
                Bundle b = new Bundle();

                b.putString("key", tvHiddenChallengeID.getText().toString()); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                unContext.startActivity(intent);
            }
            if (v.getId() == bttnVerResumen.getId()){
                Toast.makeText(unContext,"verResumen",Toast.LENGTH_SHORT).show();
            }


            }
        }
    }






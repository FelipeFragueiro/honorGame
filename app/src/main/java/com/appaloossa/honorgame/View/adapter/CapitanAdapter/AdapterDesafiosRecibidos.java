package com.appaloossa.honorgame.View.adapter.CapitanAdapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.appaloossa.honorgame.R;
import com.appaloossa.honorgame.View.activity.Capitan.MainActivity;
import com.appaloossa.honorgame.View.fragments.Capitan.desafio_fragments.FragmentAceptarDesafio;
import com.appaloossa.honorgame.model.Desafio;

import java.util.List;

/**
 * Created by Ines on 7/4/2018.
 */

public class AdapterDesafiosRecibidos extends RecyclerView.Adapter {

    private List<Desafio> desafioList;
    private Context unContext;
    private DesafioRecibidoListener desafioListener;

    public AdapterDesafiosRecibidos(List<Desafio> desafioList, Context unContext, DesafioRecibidoListener desafioListener){
        this.desafioList = desafioList;
        this.unContext = unContext;
        this.desafioListener = (DesafioRecibidoListener) desafioListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(unContext);
        View celda = layoutInflater.inflate(R.layout.cell_desafio_recibido,parent,false);
        DesafioRecibidoViewHolder desafioViewHolder = new DesafioRecibidoViewHolder(celda);

        return desafioViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Desafio unDesafio = desafioList.get(position);
        DesafioRecibidoViewHolder desafioViewHolder = (DesafioRecibidoViewHolder) holder;
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

    private class DesafioRecibidoViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {

        Button buttonAceptar;
        Button buttonRechazar;

        RatingBar ratingBar =(RatingBar)itemView.findViewById(R.id.ratingbar_desafio_recibido);
        TextView textViewDesafio = (TextView) itemView.findViewById(R.id.tv_nombreDeEquipoRecibido);
        TextView textViewVictorias = (TextView) itemView.findViewById(R.id.tv_victorias_recibidos);
        TextView textViewDerrotas = (TextView) itemView.findViewById(R.id.tv_derrotas_recibidos);
        TextView textViewEmpate = (TextView)itemView.findViewById(R.id.tv_empates_recibidos);
        TextView textViewUbicacion = (TextView)itemView.findViewById(R.id.tvUbicacionRecibido);
        TextView textViewFechaAjugar =(TextView)itemView.findViewById(R.id.fechaoculta);
        TextView title_reputacion = (TextView)itemView.findViewById(R.id.title_reputacion_recibido);
        LinearLayout container_item_recibido = (LinearLayout) itemView.findViewById(R.id.container_item_recibido);


        TextView textViewTime1 = (TextView)itemView.findViewById(R.id.time1oculto);
        TextView textViewTime2 = (TextView)itemView.findViewById(R.id.time2oculto);
        TextView textViewTime3 = (TextView)itemView.findViewById(R.id.time3oculto);


        public DesafioRecibidoViewHolder(View itemView){
            super(itemView);

            buttonAceptar = (Button)itemView.findViewById(R.id.button_aceptar_recibido);
            buttonRechazar = (Button)itemView.findViewById(R.id.button_desafiarOrechazar_recibido);

            buttonAceptar.setOnClickListener(this);
            buttonRechazar.setOnClickListener(this);


        }

        public void cargarDesafio(Desafio unDesafio){

            //cargarEquipoADesafio(unDesafio);
            //ratingBar.setRating(unDesafio.getUnEquipo().getRating());

            Typeface poppinsBold = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-Bold.ttf");
            Typeface poppinsRegular = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-Regular.ttf");
            Typeface poppinsSemiBold = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-SemiBold.ttf");
            Typeface poppinsLight = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-Light.ttf");
            Typeface poppinsMedium = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-Medium.ttf");

            buttonAceptar.setTypeface(poppinsRegular);
            buttonRechazar.setTypeface(poppinsRegular);
            textViewUbicacion.setTypeface(poppinsLight);

            title_reputacion.setTypeface(poppinsLight);
            textViewDesafio.setText(unDesafio.getUnEquipo().getName().toString());
            textViewDesafio.setTypeface(poppinsSemiBold);

            textViewFechaAjugar.setText(unDesafio.getDate());
            textViewVictorias.setText((unDesafio.getUnEquipo().getWon().toString() + " V"));
            textViewDerrotas.setText(unDesafio.getUnEquipo().getLost().toString()+ " D");
            textViewEmpate.setText(unDesafio.getUnEquipo().getTied().toString()+ " E");

            textViewTime1.setText(unDesafio.getTime1());
            textViewTime2.setText(unDesafio.getTime2());
            textViewTime3.setText(unDesafio.getTime3());

            if (unDesafio.getNumPos()%2==0  ){
                container_item_recibido.setBackgroundColor(Color.WHITE);
            }else {
                container_item_recibido.setBackgroundColor(Color.parseColor("#81e7e6e6"));
            }



        }

        @Override
        public void onClick(View v) {
            if(v.getId() == buttonAceptar.getId()){
                Bundle args = new Bundle();
                args.putString("nombreDeEquipo", textViewDesafio.getText().toString());
                args.putString("fecha",textViewFechaAjugar.getText().toString());
                args.putString("time1",textViewTime1.getText().toString());
                args.putString("time2",textViewTime2.getText().toString());
                args.putString("time3",textViewTime3.getText().toString());

                //aca para desafiar
                FragmentAceptarDesafio nextFrag= new FragmentAceptarDesafio();
                nextFrag.setArguments(args);
                ((MainActivity)unContext).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.framelayout_desafioRecibido, nextFrag,"findThisFragment")
                        .addToBackStack(null)
                        .commit();


            }
            if (v.getId() == buttonRechazar.getId()){
                Toast.makeText(((MainActivity)unContext),"rechazado",Toast.LENGTH_LONG).show();
            }
        }
    }


    public interface DesafioRecibidoListener {
        public void seleccionaronA(Desafio unDesafio);
    }
}
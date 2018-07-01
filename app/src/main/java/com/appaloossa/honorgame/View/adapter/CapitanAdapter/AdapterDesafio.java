package com.appaloossa.honorgame.View.adapter.CapitanAdapter;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.appaloossa.honorgame.R;
import com.appaloossa.honorgame.View.activity.Capitan.MainActivity;
import com.appaloossa.honorgame.View.fragments.Capitan.desafio_fragments.FragmentConfirmacionDesafio;
import com.appaloossa.honorgame.model.Equipo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ines on 28/3/2018.
 */

public class AdapterDesafio extends RecyclerView.Adapter{

    private List<Equipo> desafioList;
    private Context unContext;
    private DesafioListener desafioListener;

    public AdapterDesafio(Context unContext ,DesafioListener desafioListener){
        this.desafioList = new ArrayList<>();
        this.unContext = unContext;
        this.desafioListener = (DesafioListener) desafioListener;
    }
    public void setProductList(List<Equipo> desafiolist1) {
        this.desafioList = desafiolist1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(unContext);
        View celda = layoutInflater.inflate(R.layout.celda_desafio,parent,false);
        DesafioViewHolder desafioViewHolder = new DesafioViewHolder(celda);

        return desafioViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Equipo unDesafio = desafioList.get(position);
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

    private class DesafioViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewDesafio = (TextView) itemView.findViewById(R.id.tv_nombreDeEquipo);
        Button buttonDesafiar;
        TextView textViewID = (TextView)itemView.findViewById(R.id.tv_id);
        TextView textViewVictorias = (TextView) itemView.findViewById(R.id.tv_victorias);
        TextView textViewDerrotas = (TextView) itemView.findViewById(R.id.tv_derrotas);
        TextView textViewEmpate = (TextView)itemView.findViewById(R.id.tv_empates);
        TextView textViewUbicacion = (TextView)itemView.findViewById(R.id.tvUbicacion);
        //TextView textViewGastado = itemView.findViewById(R.id.TextViewGastado);
        RatingBar ratingBar = (RatingBar)itemView.findViewById(R.id.ratingbar_desafio);
        TextView title_reputation = (TextView)itemView.findViewById(R.id.title_reputation);
        LinearLayout container_item = (LinearLayout)itemView.findViewById(R.id.container_item);

        public DesafioViewHolder(View itemView){
            super(itemView);
            buttonDesafiar = (Button)itemView.findViewById(R.id.button_desafiarOrechazar);
            //buttonDesafiar.setTag(1,itemView);
            buttonDesafiar.setOnClickListener(this);



        }

        public void cargarDesafio(Equipo unDesafio){

            Typeface poppinsBold = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-Bold.ttf");
            Typeface poppinsRegular = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-Regular.ttf");
            Typeface poppinsSemiBold = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-SemiBold.ttf");
            Typeface poppinsLight = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-Light.ttf");
            Typeface poppinsMedium = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-Medium.ttf");


            textViewID.setText(unDesafio.getId());
            textViewDesafio.setText(unDesafio.getName());
            textViewDesafio.setTypeface(poppinsSemiBold);
            textViewUbicacion.setTypeface(poppinsLight);
            title_reputation.setTypeface(poppinsLight);
            textViewVictorias.setText((unDesafio.getWon().toString() + " V"));
            textViewDerrotas.setText(unDesafio.getLost().toString()+ " D");
            textViewEmpate.setText(unDesafio.getTied().toString()+ " E");
            buttonDesafiar.setTypeface(poppinsLight);

            if (unDesafio.getPosition()%2==0  ){
                container_item.setBackgroundColor(Color.WHITE);
            }else {
                container_item.setBackgroundColor(Color.parseColor("#81e7e6e6"));
            }




        }

        @Override
        public void onClick(View v) {

            if (v.getId() == buttonDesafiar.getId()){

                Bundle args = new Bundle();
                args.putString("nombreDeEquipo", textViewDesafio.getText().toString());
                args.putString("id", textViewID.getText().toString());
                //aca para desafiar

                FragmentConfirmacionDesafio nextFrag= new FragmentConfirmacionDesafio();
                nextFrag.setArguments(args);
                ((MainActivity)unContext).getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .replace(R.id.framelayout_confirmacion, nextFrag,"findThisFragment")
                        .addToBackStack(null)
                        .commit();
                notifyDataSetChanged();
            }
        }
    }

    public interface DesafioListener {
        public void seleccionaronA(Equipo unDesafio);
    }
}

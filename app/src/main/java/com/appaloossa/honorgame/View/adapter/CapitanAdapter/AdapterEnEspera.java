package com.appaloossa.honorgame.View.adapter.CapitanAdapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.appaloossa.honorgame.R;
import com.appaloossa.honorgame.model.Desafio;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ines on 9/4/2018.
 */

public class AdapterEnEspera extends RecyclerView.Adapter {

    private List<Desafio> desafioList;
    private Context unContext;
    private DesafioEnEsperaListener desafioListener;

    public AdapterEnEspera(Context unContext, DesafioEnEsperaListener desafioListener){
        this.desafioList = new ArrayList<>();
        this.unContext = unContext;
        this.desafioListener = (DesafioEnEsperaListener) desafioListener;
    }
    public void setProductList(List<Desafio> desafiolist1) {
        this.desafioList = desafiolist1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(unContext);
        View celda = layoutInflater.inflate(R.layout.cell_desafio_enespera,parent,false);
        DesafioEnEsperaViewHolder desafioViewHolder = new DesafioEnEsperaViewHolder(celda);

        return desafioViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Desafio unDesafio = desafioList.get(position);
        DesafioEnEsperaViewHolder desafioViewHolder = (DesafioEnEsperaViewHolder) holder;
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

    private class DesafioEnEsperaViewHolder extends  RecyclerView.ViewHolder  {

        //TextView textViewGastado = itemView.findViewById(R.id.TextViewGastado);
        TextView textViewNombreEquipo = (TextView)itemView.findViewById(R.id.tv_nombreDeEquipo_enEspera);
        RatingBar ratingBar = (RatingBar)itemView.findViewById(R.id.ratingbar_desafio_enEspera);
        TextView textViewFecha = (TextView)itemView.findViewById(R.id.tv_fecha_enEspera);
        TextView textViewUbicacion = (TextView)itemView.findViewById(R.id.tv_Ubicacion_enEspera);
        LinearLayout container_item = (LinearLayout)itemView.findViewById(R.id.container_item);

        public DesafioEnEsperaViewHolder(View itemView){
            super(itemView);
            //buttonDesafiar.setTag(1,itemView);



        }

        public void cargarDesafio(Desafio unDesafio){
            Typeface poppinsBold = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-Bold.ttf");
            Typeface poppinsRegular = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-Regular.ttf");
            Typeface poppinsSemiBold = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-SemiBold.ttf");
            Typeface poppinsLight = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-Light.ttf");
            Typeface poppinsMedium = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-Medium.ttf");

            ratingBar.setRating(unDesafio.getUnEquipo().getRating());
            textViewNombreEquipo.setText(unDesafio.getUnEquipo().getName().toString());
            textViewNombreEquipo.setTypeface(poppinsSemiBold);
            textViewUbicacion.setTypeface(poppinsLight);
            textViewFecha.setTypeface(poppinsRegular);

           // textViewFecha.setText(unDesafio.getDate());
            //textViewUbicacion.setText(unDesafio.getUnEquipo().getUbicacion());
            if (unDesafio.getNumPos()%2==0  ){
                container_item.setBackgroundColor(Color.WHITE);
            }else {
                container_item.setBackgroundColor(Color.parseColor("#81e7e6e6"));
            }



        }


    }

    public interface DesafioEnEsperaListener {
        public void seleccionaronA(Desafio unDesafio);
    }
}




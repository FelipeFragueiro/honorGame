package com.appaloossa.honorgame.View.adapter.ArbitroAdapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appaloossa.honorgame.R;
import com.appaloossa.honorgame.model.Desafio;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ines on 6/5/2018.
 */

public class AdapterConfirmarDesafioArbitro extends RecyclerView.Adapter {
    private List<Desafio> desafioList;
    private Context unContext;

    public AdapterConfirmarDesafioArbitro(Context unContext){
        this.desafioList = new ArrayList<>();
        this.unContext = unContext;
    }
    public void setProductList(List<Desafio> desafiolist1) {

        this.desafioList = desafiolist1;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(unContext);
        View celda = layoutInflater.inflate(R.layout.cell_confirmacion_desafio_arbitro,parent,false);
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
                //desafioListener.seleccionaronA(unDesafio);
            }
        });
    }

    @Override
    public int getItemCount() {
        return desafioList.size();
    }

    private class DesafioViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {

        //TextView textViewGastado = itemView.findViewById(R.id.TextViewGastado);
        TextView textViewNombreEquipo1 = (TextView) itemView.findViewById(R.id.tv_nombreEquipo1_nuevoDesafio_cell);
        TextView textViewNombreEquipo2 = (TextView) itemView.findViewById(R.id.tv_nombreEquipo2_nuevoDesafio_cell);
        TextView title_numeroDesafio = (TextView)itemView.findViewById(R.id.tv_numero_nuevoDesafio_cell);
        TextView tv_ubicacion  = (TextView)itemView.findViewById(R.id.tv_ubicacion_nuevoDesafio_cell);
        TextView descrip = (TextView)itemView.findViewById(R.id.tv_descripcion_arbitro);
        TextView fecha = (TextView)itemView.findViewById(R.id.textView3);
        TextView bttn_hora1 = (Button)itemView.findViewById(R.id.bttn_hora1_confirmacionDesafio_arbitro);
        TextView bttn_hora2 = (Button)itemView.findViewById(R.id.bttn_hora2_confirmacionDesafio_arbitro);
        TextView bttn_confirmar = (Button)itemView.findViewById(R.id.bttn_tomarDesafio_nuevoDesafio_cell);
        RelativeLayout item_container = (RelativeLayout)itemView.findViewById(R.id.item_container);



        public DesafioViewHolder(View itemView){
            super(itemView);
            //buttonDesafiar.setTag(1,itemView);




        }

        public void cargarDesafio(Desafio unDesafio){
            Typeface poppinsBold = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-Bold.ttf");
            Typeface poppinsRegular = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-Regular.ttf");
            Typeface poppinsSemiBold = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-SemiBold.ttf");
            Typeface poppinsLight = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-Light.ttf");
            Typeface poppinsMedium = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-Medium.ttf");

            textViewNombreEquipo1.setText(unDesafio.getOwnerName());
            textViewNombreEquipo2.setText(unDesafio.getReceiverName());

            textViewNombreEquipo1.setTypeface(poppinsSemiBold);
            textViewNombreEquipo2.setTypeface(poppinsSemiBold);
            bttn_confirmar.setTypeface(poppinsLight);
            bttn_hora1.setTypeface(poppinsLight);
            bttn_hora2.setTypeface(poppinsLight);
            title_numeroDesafio.setTypeface(poppinsSemiBold);
            tv_ubicacion.setTypeface(poppinsRegular);
            descrip.setTypeface(poppinsRegular);
            fecha.setTypeface(poppinsRegular);

            if (unDesafio.getContador()%2==0  ){
                item_container.setBackgroundColor(Color.WHITE);
            }else {
                item_container.setBackgroundColor(Color.parseColor("#81e7e6e6"));
            }






        }

        @Override
        public void onClick(View v) {

            /*if (v.getId() == buttonDesafiar.getId()){

                Bundle args = new Bundle();
                args.putString("nombreDeEquipo", textViewDesafio.getText().toString());
                args.putString("id", textViewID.getText().toString());
                //aca para desafiar

                FragmentConfirmacionDesafio nextFrag= new FragmentConfirmacionDesafio();
                nextFrag.setArguments(args);
                ((MainActivity)unContext).getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .replace(R.id.framelayout_confirmacion, nextFrag,"findThisFragment")
                        .addToBackStack(null)
                        .commit();*/


        }
    }
}

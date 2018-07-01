package com.appaloossa.honorgame.View.adapter.ArbitroAdapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.appaloossa.honorgame.R;
import com.appaloossa.honorgame.View.activity.Capitan.MainActivity;
import com.appaloossa.honorgame.View.adapter.CapitanAdapter.AdapterDesafio;
import com.appaloossa.honorgame.View.fragments.Capitan.desafio_fragments.FragmentConfirmacionDesafio;
import com.appaloossa.honorgame.model.Desafio;
import com.appaloossa.honorgame.model.Equipo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ines on 5/5/2018.
 */

public class AdapterNuevosDesafios extends RecyclerView.Adapter {

    private List<Desafio> desafioList;
    private List<Desafio> desafioSnapshotList = new ArrayList<>();
    private Context unContext;

    public AdapterNuevosDesafios(Context unContext){
        this.desafioList = new ArrayList<>();
        this.unContext = unContext;
      }
    public void setProductList(List<Desafio> desafiolist1) {

        this.desafioList = desafiolist1;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(unContext);
        View celda = layoutInflater.inflate(R.layout.cell_nuevosdesafios_arbitro,parent,false);
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
        TextView tvidEquipo1= (TextView)itemView.findViewById(R.id.idEquipo1_secret);
        TextView tvidEquipo2= (TextView)itemView.findViewById(R.id.idEquipo2_secret);
        Button bttnTomarDesafio = (Button) itemView.findViewById(R.id.bttn_tomarDesafio_nuevoDesafio_cell);
        TextView tv_ubicacion= (TextView)itemView.findViewById(R.id.tv_ubicacion_nuevoDesafio_cell);
        TextView tv_numeroDesafio = (TextView)itemView.findViewById(R.id.tv_numero_nuevoDesafio_cell);
        RelativeLayout container_item =  (RelativeLayout)itemView.findViewById(R.id.container_item);



        public DesafioViewHolder(View itemView) {
            super(itemView);

            bttnTomarDesafio.setOnClickListener(this);

        }

        public void cargarDesafio(Desafio unDesafio) {

            Typeface poppinsBold = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-Bold.ttf");
            Typeface poppinsRegular = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-Regular.ttf");
            Typeface poppinsSemiBold = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-SemiBold.ttf");
            Typeface poppinsLight = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-Light.ttf");
            Typeface poppinsMedium = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-Medium.ttf");

            textViewNombreEquipo1.setText(unDesafio.getOwnerName());
            textViewNombreEquipo1.setTypeface(poppinsSemiBold);
            textViewNombreEquipo2.setText(unDesafio.getReceiverName());
            textViewNombreEquipo2.setTypeface(poppinsSemiBold);
            tvidEquipo1.setText(unDesafio.getOwner());
            tv_ubicacion.setTypeface(poppinsRegular);
            tv_numeroDesafio.setTypeface(poppinsSemiBold);
            tvidEquipo2.setText(unDesafio.getReceiver());
            bttnTomarDesafio.setTypeface(poppinsLight);

            if (unDesafio.getContador()%2==0  ){
                container_item.setBackgroundColor(Color.WHITE);
            }else {
                container_item.setBackgroundColor(Color.parseColor("#81e7e6e6"));
            }


        }

        @Override
        public void onClick(View v) {
            final String idEquipo1 = tvidEquipo1.getText().toString();
            final String idEquipo2 = tvidEquipo2.getText().toString();

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference databaseReference = database.getReference();
            if (v.getId() == bttnTomarDesafio.getId()) {

                final DatabaseReference childDB = databaseReference.child("challenges");
                childDB.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshotChildren : dataSnapshot.getChildren()) {

                            Desafio unDesafio = snapshotChildren.getValue(Desafio.class);

                            //String key = snapshotChildren.getKey().toString();
                            if (unDesafio.getOwner().equals(idEquipo1)&& unDesafio.getReceiver().equals(idEquipo2)) {

                                //Toast.makeText(unContext,"es igual",Toast.LENGTH_SHORT).show();
                                childDB.child(snapshotChildren.getKey()).child("state").setValue(2);
                                notifyDataSetChanged();

                            }

                        }
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



            }
        }
    }
}




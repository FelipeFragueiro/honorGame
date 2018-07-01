package com.appaloossa.honorgame.View.adapter.CapitanAdapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.appaloossa.honorgame.R;
import com.appaloossa.honorgame.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ines on 10/4/2018.
 */

public class AdapterJugadores extends RecyclerView.Adapter{

    private List<Player> players;
    private Context unContext;

    public AdapterJugadores(Context unContext){
        this.players = new ArrayList<>();
        this.unContext = unContext;
    }
    public void setPlayersList(List<Player> desafiolist1) {
        this.players = desafiolist1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(unContext);
        View celda = layoutInflater.inflate(R.layout.cell_jugador_equipo,parent,false);
        PlayerViewHolder playerViewHolder = new PlayerViewHolder(celda);

        return playerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Player unJugador = players.get(position);
        PlayerViewHolder  playerViewHolder = (PlayerViewHolder)holder;
        playerViewHolder.cargarJugador(unJugador);

        View viewDeLaCelda = playerViewHolder.itemView;
        viewDeLaCelda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //playerListener.seleccionaronA(unJugador);
            }
        });
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    private class PlayerViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewNombreJugador = (TextView) itemView.findViewById(R.id.tv_nombre_jugador);
        Button tv_verMas = (Button) itemView.findViewById(R.id.bttn_verMas);
        ImageView tv_editar = (ImageView) itemView.findViewById(R.id.bttn_nota);
        ImageView tv_eliminar = (ImageView)itemView.findViewById(R.id.bttn_eliminar);
        RelativeLayout item_container = (RelativeLayout)itemView.findViewById(R.id.item_container);



        public PlayerViewHolder(View itemView){
            super(itemView);
            tv_verMas.setOnClickListener(this);
            tv_editar.setOnClickListener(this);
            tv_eliminar.setOnClickListener(this);



        }

        public void cargarJugador(Player unJugador){
            Typeface poppinsBold = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-Bold.ttf");
            Typeface poppinsRegular = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-Regular.ttf");
            Typeface poppinsSemiBold = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-SemiBold.ttf");
            Typeface poppinsLight = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-Light.ttf");
            Typeface poppinsMedium = Typeface.createFromAsset(unContext.getAssets(),"fonts/Poppins-Medium.ttf");
            textViewNombreJugador.setText(unJugador.getName());
            textViewNombreJugador.setTypeface(poppinsRegular);
            tv_verMas.setTypeface(poppinsLight);

            if (unJugador.getContador()%2==0  ){
                item_container.setBackgroundColor(Color.WHITE);
            }else {
                item_container.setBackgroundColor(Color.parseColor("#81e7e6e6"));
            }



        }

        @Override
        public void onClick(View v) {
                if (v.getId() == tv_eliminar.getId()){
                    Toast.makeText(unContext,"eliminar",Toast.LENGTH_SHORT).show();
                }
            /*if (v.getId() == buttonDesafiar.getId()){

                Bundle args = new Bundle();
                args.putString("nombreDeEquipo", textViewDesafio.getText().toString());
                //aca para desafiar
                FragmentConfirmacionDesafio nextFrag= new FragmentConfirmacionDesafio();
                nextFrag.setArguments(args);
                ((MainActivity)unContext).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.framelayout_confirmacion, nextFrag,"findThisFragment")
                        .addToBackStack(null)
                        .commit();*/
                //para pasar info al otro fragment
                /*YourNewFragment ldf = new YourNewFragment ();
                Bundle args = new Bundle();
                args.putString("YourKey", "YourValue");
                ldf.setArguments(args);


                getFragmentManager().beginTransaction().add(R.id.container, ldf).commit();*/

            }
        }


    public interface PlayerListener {
        public void seleccionaronA(Player unJugador);
    }
}

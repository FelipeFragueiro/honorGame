package com.appaloossa.honorgame.View.fragments.Capitan.desafio_fragments;


import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.appaloossa.honorgame.Controller.RetrofitController;
import com.appaloossa.honorgame.R;
import com.appaloossa.honorgame.Utils.ResultListener;
import com.appaloossa.honorgame.View.adapter.CapitanAdapter.AdapterDesafio;
import com.appaloossa.honorgame.model.CountryYState.Country;
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
 * A simple {@link Fragment} subclass.
 */
public class FragmentDesafios extends Fragment implements AdapterDesafio.DesafioListener  {
    AdapterDesafio adaptador;
    private List<Country> countryList = new ArrayList<>();
    String mAuth;
    private List<Equipo> desafioList = new ArrayList<>();
    private DesafioListenerFrag desafioListener;
    RecyclerView recyclerView;
    private int contador;
    private String mAuth1;
    private Boolean yaestadesafiado = false;

    public FragmentDesafios() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //desafioListener = (DesafioListenerFrag) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_desafios, container, false);

        recyclerView = (RecyclerView) view  .findViewById(R.id.recyclerView_Desafio);

        adaptador = new AdapterDesafio(getContext(),this);

       // updateWithRetrofit();

        cargarDesafios();


        return view;
    }


    public void showPopup(View anchorView) {

        View popupView = getLayoutInflater().inflate(R.layout.popup_layout, null);

        PopupWindow popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        // Example: If you have a TextView inside `popup_layout.xml`
        //TextView tv = (TextView) popupView.findViewById(R.id.tv);

        // tv.setText(....);

        // Initialize more widgets from `popup_layout.xml`


        // If the PopupWindow should be focusable
        popupWindow.setFocusable(true);

        // If you need the PopupWindow to dismiss when when touched outside
        popupWindow.setBackgroundDrawable(new ColorDrawable());

        int location[] = new int[2];

        // Get the View's(the one that was clicked in the Fragment) location
        anchorView.getLocationOnScreen(location);

        // Using location, the PopupWindow will be displayed right under anchorView
        popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY,
                location[0], location[1] + anchorView.getHeight());

    }

    private void cargarDesafios() {
        contador = 0;
        mAuth = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference firebaseDataRef = firebaseDatabase.getReference();
        final DatabaseReference team = firebaseDataRef.child("teams");
        team.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshotChildren : dataSnapshot.getChildren()) {
                    Equipo unEquipo = snapshotChildren.getValue(Equipo.class);
                    String key = snapshotChildren.getKey().toString();
                    esteEquipoEstaDesafiado(key);
                    unEquipo.setId(key);
                    if (!(unEquipo.getId().equals(mAuth) || yaestadesafiado == true)) {

                        desafioList.add(unEquipo);
                        unEquipo.setPosition(contador);
                        contador = contador + 1;

                    }


                }

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                adaptador.setProductList(desafioList);
                recyclerView.setAdapter(adaptador);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }




    @Override
    public void seleccionaronA(Equipo unDesafio) {
        desafioListener.seleccionaronA(unDesafio);
    }

    public void updateWithRetrofit(){
        RetrofitController retrofitController = new RetrofitController();
        ResultListener<List<Country>> listResultListener = new ResultListener<List<Country>>() {
            @Override
            public void finish(List<Country> result) {

                countryList = result;

                //paintsAdapter.setProductList(result);
                //paintsAdapter.notifyDataSetChanged();
            }
        };
        retrofitController.getCountry(listResultListener,getContext());
    }
    public interface DesafioListenerFrag{

        public void seleccionaronA(Equipo unDesafio);

        }


        private void esteEquipoEstaDesafiado(String key){
        mAuth1 = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference firebaseDataRef = firebaseDatabase.getReference();
            final DatabaseReference team = firebaseDataRef.child("challenges");
            team.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshotChildren : dataSnapshot.getChildren()) {
                        Desafio unEquipo = snapshotChildren.getValue(Desafio.class);
                        String key = snapshotChildren.getKey().toString();
                        Toast.makeText(getContext(),key,Toast.LENGTH_LONG).show();
                        if ((unEquipo.getOwner().equals(mAuth1)) && unEquipo.getReceiver().equals(key)) {

                            yaestadesafiado = true;

                        }


                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }


}


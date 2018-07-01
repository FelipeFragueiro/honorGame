package com.appaloossa.honorgame.View.fragments.Capitan.desafio_fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.appaloossa.honorgame.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAceptarDesafio extends Fragment implements View.OnClickListener {

    Button buttonCancelar;
    Button buttonAceptar;
    Button buttonTime1;
    Button buttonTime2;
    Button buttonTime3;
    String NombreEquipoArgs;
    String fechaArgs;
    String time1Args;
    String time2Args;
    String time3Args;

    public FragmentAceptarDesafio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_aceptar_desafio, container, false);

        Typeface poppinsBold = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Poppins-Bold.ttf");
        Typeface poppinsRegular = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Poppins-Regular.ttf");
        Typeface poppinsSemiBold = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Poppins-SemiBold.ttf");
        Typeface poppinsLight = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Poppins-Light.ttf");
        Typeface poppinsMedium = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Poppins-Medium.ttf");

        NombreEquipoArgs = getArguments().getString("nombreDeEquipo");
        fechaArgs = getArguments().getString("fecha");
        time1Args = getArguments().getString("time1");
        time2Args = getArguments().getString("time2");
        time3Args = getArguments().getString("time3");

        TextView tv = (TextView)view.findViewById(R.id.tv_nombreDeEquipo_aDesafiar);
        TextView tv_Descrip = (TextView)view.findViewById(R.id.tv_confirmacion);
        TextView tvfecha = (TextView)view.findViewById(R.id.tv_fechadia);
        TextView diaAJugar = (TextView)view.findViewById(R.id.tv_diaAJugar);
        TextView tv_Horarios = (TextView)view.findViewById(R.id.tv_horarios);
        buttonTime1 =(Button)view.findViewById(R.id.bttn_horario1);
        buttonTime2 =(Button)view.findViewById(R.id.bttn_horario2);
        buttonTime3 =(Button)view.findViewById(R.id.bttn_horario3);
        buttonTime1.setTypeface(poppinsLight);
        buttonTime2.setTypeface(poppinsLight);
        buttonTime3.setTypeface(poppinsLight);

        tv.setText(NombreEquipoArgs);
        tv.setTypeface(poppinsSemiBold);
        tv_Descrip.setTypeface(poppinsLight);
        diaAJugar.setTypeface(poppinsRegular);
        tv_Horarios.setTypeface(poppinsRegular);

        tvfecha.setText(fechaArgs);

        buttonTime1.setText(time1Args);
        buttonTime2.setText(time2Args);
        buttonTime3.setText(time3Args);


        buttonCancelar = (Button)view.findViewById(R.id.bttn_cancelar_recib);
        buttonAceptar = (Button)view.findViewById(R.id.bttn_confirmar_recib);

        buttonCancelar.setOnClickListener(this);
        buttonAceptar.setOnClickListener(this);




        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == buttonAceptar.getId()){
            Toast.makeText(getContext(),"ACEPTADO",Toast.LENGTH_LONG).show();





        }
        if (v.getId() == buttonCancelar.getId()){
            getFragmentManager().popBackStackImmediate();
        }
    }
}

package com.appaloossa.honorgame.View.fragments.Capitan.desafio_fragments;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.appaloossa.honorgame.R;
import com.appaloossa.honorgame.model.Desafio;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentConfirmacionDesafio extends Fragment implements View.OnClickListener {

String value;
String valueId;
Button buttonCancelar;
Button buttonConfirmar;
EditText horario1;
EditText horario2;
EditText horario3;
EditText calendarioFecha;
ImageView imageView;
Calendar calendario;
DatePickerDialog.OnDateSetListener date;

    public FragmentConfirmacionDesafio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirmacion_desafio, container, false);
        //para agarrar la info del fragment anterior
        value = getArguments().getString("nombreDeEquipo");
        valueId = getArguments().getString("id");

        Typeface poppinsBold = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Poppins-Bold.ttf");
        Typeface poppinsRegular = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Poppins-Regular.ttf");
        Typeface poppinsSemiBold = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Poppins-SemiBold.ttf");
        Typeface poppinsLight = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Poppins-Light.ttf");
        Typeface poppinsMedium = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Poppins-Medium.ttf");

        TextView tv = (TextView)view.findViewById(R.id.tv_nombreDeEquipo_aDesafiar);
        TextView title_vs = (TextView)view.findViewById(R.id.title_vs_confirmar);
        TextView tv_descrip = (TextView)view.findViewById(R.id.tv_confirmacion);
        TextView tv_DiaAJugar = (TextView)view.findViewById(R.id.tv_diaAJugar);
        TextView tv_HorariosDisponibles = (TextView)view.findViewById(R.id.horarios_disponibles);
        title_vs.setTypeface(poppinsSemiBold);
        tv_DiaAJugar.setTypeface(poppinsRegular);
        tv_HorariosDisponibles.setTypeface(poppinsRegular);

        tv.setText(value);
        tv.setTypeface(poppinsSemiBold);
        tv_descrip.setTypeface(poppinsLight);
         buttonCancelar = (Button)view.findViewById(R.id.bttn_cancelar);
         buttonConfirmar = (Button)view.findViewById(R.id.bttn_confirmar);
         horario1 = (EditText)view.findViewById(R.id.edittext_hora1);
         horario2 = (EditText)view.findViewById(R.id.edittext_hora2);
         horario3 = (EditText)view.findViewById(R.id.edittext_hora3);
         calendarioFecha = (EditText)view.findViewById(R.id.editText_fecha);
         imageView = (ImageView)view.findViewById(R.id.imageView_calendar);
         calendario = Calendar.getInstance();

        horario1.setInputType(InputType.TYPE_NULL);
        horario2.setInputType(InputType.TYPE_NULL);
        horario3.setInputType(InputType.TYPE_NULL);

        buttonConfirmar.setTypeface(poppinsLight);
        buttonCancelar.setTypeface(poppinsLight);

        buttonCancelar.setOnClickListener(this);
        buttonConfirmar.setOnClickListener(this);
        horario1.setOnClickListener(this);
        horario2.setOnClickListener(this);
        horario3.setOnClickListener(this);
        imageView.setOnClickListener(this);

         date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendario.set(Calendar.YEAR, year);
                calendario.set(Calendar.MONTH, month);
                calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };



        return view;
    }
    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        calendarioFecha.setText(sdf.format(calendario.getTime()));





    }



    @Override
    public void onClick(View v) {
        if(v.getId() == buttonCancelar.getId()) {
            getFragmentManager().popBackStackImmediate();
        }

        if (v.getId() == horario1.getId()){
            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                    horario1.setText(hourOfDay + ":" + minutes);
                }
            }, 0, 0, false);
            timePickerDialog.show();

        }
        if (v.getId() == horario2.getId()){
            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                    horario2.setText(hourOfDay + ":" + minutes);
                }
            }, 0, 0, false);
            timePickerDialog.show();
        }
        if (v.getId() == horario3.getId()){
            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                    horario3.setText(hourOfDay + ":" + minutes);
                }
            }, 0, 0, false);
            timePickerDialog.show();
        }
        if (v.getId() == imageView.getId()){
            new DatePickerDialog(getContext(), date, calendario
                    .get(Calendar.YEAR), calendario.get(Calendar.MONTH),
                    calendario.get(Calendar.DAY_OF_MONTH)).show();
            Toast.makeText(getContext(),calendarioFecha.getText().toString(),Toast.LENGTH_LONG).show();
        }

        if (v.getId() == buttonConfirmar.getId()){
            if (horario1.getText().toString() != null){
                Date currentTime = Calendar.getInstance().getTime();

                String mAuth = FirebaseAuth.getInstance().getCurrentUser().getUid();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = database.getReference();
                DatabaseReference team = databaseReference.child("challenges");
                DatabaseReference newChallengueRef = team.push();
                Desafio desafioToUpLoad = new Desafio();
                desafioToUpLoad.setOwner(mAuth);
                desafioToUpLoad.setOwnerName(value);
                desafioToUpLoad.setCreated(currentTime.toString());
                desafioToUpLoad.setDate(calendarioFecha.getText().toString());
                desafioToUpLoad.setTime1(horario1.getText().toString());
                desafioToUpLoad.setTime2(horario2.getText().toString());
                desafioToUpLoad.setTime3(horario3.getText().toString());
                desafioToUpLoad.setReceiver(valueId);
                desafioToUpLoad.setReceiverName(value);
                desafioToUpLoad.setPlace("palermo");
                desafioToUpLoad.setState(1);
                Toast.makeText(getContext(),"DESAFIADO",Toast.LENGTH_LONG).show();
                newChallengueRef.setValue(desafioToUpLoad);
                getFragmentManager().popBackStackImmediate();


            }else {
                Toast.makeText(getContext(),"falta horario.",Toast.LENGTH_LONG).show();

            }
        }
    }
}

package com.appaloossa.honorgame.Controller;

import android.content.Context;
import android.widget.Toast;

import com.appaloossa.honorgame.DAO.RetrofitDAO;
import com.appaloossa.honorgame.Utils.ResultListener;
import com.appaloossa.honorgame.model.CountryYState.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ines on 2/5/2018.
 */

public class RetrofitController {
    private List<Country> listCountryForUri2Test = new ArrayList<>();
    List<Country>listCountryForUri = new ArrayList<>();
    private List<Country>countryList = new ArrayList<>();

    public void getCountry(final ResultListener<List<Country>> resultListener, final Context aContext) {


        if (RetrofitDAO.isNetworkingOnline(aContext)) {


            RetrofitDAO retrofitDAO = new RetrofitDAO();
            retrofitDAO.askForCountryes(new ResultListener<List<Country>>() {
                @Override
                public void finish(final List<Country> resultado) {

                    for ( final Country aCountry : resultado) {
                        Toast.makeText(aContext,aCountry.toString(),Toast.LENGTH_LONG).show();


                        listCountryForUri.add(aCountry);


                    }

                    resultListener.finish(listCountryForUri);
                }
            });
        } else {


        }
    }
}

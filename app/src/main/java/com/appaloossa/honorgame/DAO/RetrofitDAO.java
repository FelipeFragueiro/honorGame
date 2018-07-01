package com.appaloossa.honorgame.DAO;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.appaloossa.honorgame.Utils.ApiService;
import com.appaloossa.honorgame.Utils.ResultListener;
import com.appaloossa.honorgame.model.Containers.CountryContainer;
import com.appaloossa.honorgame.model.CountryYState.Country;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ines on 2/5/2018.
 */

public class RetrofitDAO {
    static final String baseUrl = "https://api.mercadolibre.com/countries/AR/";
    private static ApiService API_SERVICE;
    private Retrofit retrofit;
    private List<Country> countryList;

    public RetrofitDAO(){
        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        API_SERVICE = retrofit.create(ApiService.class);
    }


    public static boolean isNetworkingOnline(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeConnection = cm.getActiveNetworkInfo();
        Boolean isOnline = (activeConnection != null) && activeConnection.isConnected();
        return isOnline;
    }


    public void askForCountryes(final ResultListener<List<Country>> resultListener){
        ApiService apiService = retrofit.create(ApiService.class);
        final Call<CountryContainer> paintsContainerCall = apiService.getCountryes();
        paintsContainerCall.enqueue(new Callback<CountryContainer>() {
            @Override
            public void onResponse(Call<CountryContainer> call, Response<CountryContainer> response) {

                if(response.isSuccessful()){

                    List<Country>countryList = response.body().getResults();
                    resultListener.finish(countryList);
                }
            }

            @Override
            public void onFailure(Call<CountryContainer> call, Throwable t) {
            }
        });
    }


}

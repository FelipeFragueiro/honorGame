package com.appaloossa.honorgame.Utils;

import com.appaloossa.honorgame.model.Containers.CountryContainer;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Ines on 1/5/2018.
 */

public interface ApiService {
    @GET("/AR")
    Call<CountryContainer> getCountryes();

}

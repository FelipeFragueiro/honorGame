package com.appaloossa.honorgame.model.Containers;

import com.appaloossa.honorgame.model.CountryYState.Country;

import java.util.List;

/**
 * Created by Ines on 1/5/2018.
 */

public class CountryContainer {

    private List<Country> countryList ;

    public List<Country> getResults() {

        return countryList;

    }

    public void setPaints(List<Country> countryList) {
        this.countryList = countryList;
    }


}

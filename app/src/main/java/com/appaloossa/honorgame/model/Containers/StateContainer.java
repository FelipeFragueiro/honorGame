package com.appaloossa.honorgame.model.Containers;

import com.appaloossa.honorgame.model.CountryYState.Country;
import com.appaloossa.honorgame.model.CountryYState.State;

import java.util.List;

/**
 * Created by Ines on 1/5/2018.
 */

public class StateContainer {
    private List<State> states;

    public List<State> getResults() {

        return states;

    }

    public void setPaints(List<State> stateList) {
        this.states = stateList;
    }
}

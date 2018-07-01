package com.appaloossa.honorgame.Utils;

import com.appaloossa.honorgame.model.Equipo;

import java.util.Comparator;

/**
 * Created by Ines on 11/4/2018.
 */

public class ComparadorEquipos implements Comparator<Equipo> {


    @Override
    public int compare(Equipo o1, Equipo o2) {
        return o1.getScore().compareTo(o2.getScore());
    }
}

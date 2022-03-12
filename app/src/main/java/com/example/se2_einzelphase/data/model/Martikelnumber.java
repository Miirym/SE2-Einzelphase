package com.example.se2_einzelphase.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class Martikelnumber {

    private int matnr;

    public Martikelnumber(int matnr) {
        this.matnr = matnr;
    }

    public int getMatnr() {
        return matnr;
    }

}
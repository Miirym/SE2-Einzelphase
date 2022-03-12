package com.example.se2_einzelphase.ui.login;

/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView {
    private int matnr;
    //... other data fields that may be accessible to the UI

    LoggedInUserView(int matnr) {
        this.matnr = matnr;
    }

    int getMatnr() {
        return matnr;
    }
}
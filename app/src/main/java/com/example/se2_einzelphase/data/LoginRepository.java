package com.example.se2_einzelphase.data;

import com.example.se2_einzelphase.data.model.Martikelnumber;

/**
 * Class that requests authentication and matnr information from the remote data source and
 * maintains an in-memory cache of login status and matnr credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private AppDataSource appDataSource;

    // If matnr credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private Martikelnumber matnr = null;

    // private constructor : singleton access
    private LoginRepository(AppDataSource appDataSource) {
        this.appDataSource = appDataSource;
    }

    public static LoginRepository getInstance(AppDataSource appDataSource) {
        if (instance == null) {
            instance = new LoginRepository(appDataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return matnr != null;
    }

    public void logout() {
        matnr = null;
    }

    private void setLoggedInUser(Martikelnumber matnr) {
        this.matnr = matnr;
        // If matnr credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public AppResult<Martikelnumber> login(int matnr) {
        // handle login
        AppResult<Martikelnumber> result = appDataSource.login(matnr);
        if (result instanceof AppResult.Success) {
            setLoggedInUser(((AppResult.Success<Martikelnumber>) result).getData());
        }
        return result;
    }
}
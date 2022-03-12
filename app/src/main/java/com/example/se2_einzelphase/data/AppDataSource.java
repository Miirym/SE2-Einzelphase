package com.example.se2_einzelphase.data;

import com.example.se2_einzelphase.data.model.Martikelnumber;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class AppDataSource {


    public AppResult<Martikelnumber> login(int matnr) {

        try {
            // TODO: handle loggedInUser authentication
            Martikelnumber fakeUser =
                    new Martikelnumber(
                            matnr);
            return new AppResult.Success<>(fakeUser);
        } catch (Exception e) {
            return new AppResult.Error(new IOException("Error logging in", e));
        }
    }

}
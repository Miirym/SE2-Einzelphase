package com.example.se2_einzelphase.data;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class AppDataSource extends AsyncTask<String, String, AppResult<Integer>> {

    private TextView output;

    public AppDataSource(TextView output){
        this.output = output;
    }
    /*public AppResult<Martikelnumber> login(int matnr) {

        try {
            // TODO: handle loggedInUser authentication
            Martikelnumber fakeUser =
                    new Martikelnumber(
                            matnr);
            return new AppResult.Success<>(fakeUser);
        } catch (Exception e) {
            return new AppResult.Error(new IOException("Error logging in", e));
        }
    }*/


    @Override
    protected AppResult<Integer> doInBackground(String... matnr) {

        try {
            // TODO: handle loggedInUser authentication

            Integer a = 98765433;
            return new AppResult.Success<>(a);
        } catch (Exception e) {
            return new AppResult.Error(new IOException("Error logging in", e));
        }

    }

    @Override
    protected void onPostExecute(AppResult<Integer> martikelnumberAppResult) {
        output.setText(martikelnumberAppResult.toString());
    }
}
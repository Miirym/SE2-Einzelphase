package com.example.se2_einzelphase.ui.login;

import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.se2_einzelphase.data.AppDataSource;
import com.example.se2_einzelphase.R;

/**
 * Main class for processing in- and output
 */
public class AppViewModel extends androidx.lifecycle.ViewModel {

    private MutableLiveData<FormState> FormState = new MutableLiveData<>();

    LiveData<FormState> getFormState() {
        return FormState;
    }

    //Triggers sending of matnr to server
    public void send(String matnr, TextView output) {
        //Use AsyncTask to send data to server
        AppDataSource source = new AppDataSource(output);
        source.execute(matnr);
    }

    //Calculates checksum
    public String calculate(int matnr) {
        String checksum = "Quersumme gerade";
        int[] digits = new int[10];

        //Split integer into digits
        int a = 0;
        while (matnr > 0) {
            digits[a] = matnr % 10;
            matnr = matnr / 10;
            a++;
        }

        //Calculate checksum
        int sum = 0;
        for (int i = 0; i < digits.length; i++) {
            if (i % 2 == 0) {
                sum += digits[i];
            } else {
                sum -= digits[i];
            }
        }

        //Check if uneven
        if (sum % 2 != 0) {
            checksum = "Quersumme ungerade";
        }

        return checksum;
    }

    // Validate input on change
    public void inputDataChanged(String matnr) {
        if (!isMatnrValid(matnr)) {
            FormState.setValue(new FormState(R.string.invalid_matnr));
        } else {
            FormState.setValue(new FormState(true));
        }
    }

    // Matnr. validation check
    private boolean isMatnrValid(String matnr) {
        return matnr != null && matnr.trim().length() == 8;
    }

}
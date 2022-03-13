package com.example.se2_einzelphase.ui.login;

import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.se2_einzelphase.data.AppDataSource;
import com.example.se2_einzelphase.R;


public class AppViewModel extends androidx.lifecycle.ViewModel {

    private MutableLiveData<FormState> FormState = new MutableLiveData<>();

    LiveData<FormState> getFormState() {
        return FormState;
    }


    public void send(int matnr, TextView output) {

        String a = String.valueOf(matnr);

       AppDataSource source = new AppDataSource(output);
       source.execute(a);

        // can be launched in a separate asynchronous job
        /*AppResult result = loginRepository.login(matnr);
        if (result instanceof AppResult.Success) {
            Martikelnumber data = ((AppResult.Success<Martikelnumber>) result).getData();
            Result.setValue(new Result(new LoggedInUserView(data.getMatnr())));
        } else {
            Result.setValue(new Result(R.string.login_failed));
        }*/
    }

    /*@Override
    public void processFinish(AppResult<Martikelnumber> output) {
        if (output instanceof AppResult.Success) {
            Martikelnumber data = ((AppResult.Success<Martikelnumber>) output).getData();
            Result.setValue(new Result(new LoggedInUserView(data.getMatnr())));
        } else {
            Result.setValue(new Result(R.string.login_failed));
        }
    }*/


    //Calculates checksum
    public String calculate(int matnr) {
        String checksum = "gerade";
        int[] digits = new int[10];

        //Split integer into digits
        int a = 0;
        while (matnr > 0) {
            digits[a]=  matnr % 10;
            matnr = matnr / 10;
            a++;
        }

        //Calculate checksum
        int sum = 0;
        for(int i = 0; i<digits.length; i++){
            if(i%2 == 0){
               sum += digits[i];
            } else {
               sum -= digits[i];
            }
        }

        //Check if uneven
        if (sum%2 != 0 ){
            checksum = "ungerade";
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
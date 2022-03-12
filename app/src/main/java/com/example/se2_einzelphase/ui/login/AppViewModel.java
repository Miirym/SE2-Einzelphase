package com.example.se2_einzelphase.ui.login;

import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.se2_einzelphase.data.AppResult;
import com.example.se2_einzelphase.data.AppDataSource;
import com.example.se2_einzelphase.data.LoginRepository;
import com.example.se2_einzelphase.data.model.Martikelnumber;
import com.example.se2_einzelphase.R;


public class AppViewModel extends androidx.lifecycle.ViewModel {

    private MutableLiveData<FormState> FormState = new MutableLiveData<>();
    private MutableLiveData<Result> Result = new MutableLiveData<>();
    private LoginRepository loginRepository;

    AppViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<FormState> getFormState() {
        return FormState;
    }

    LiveData<Result> getLoginResult() {
        return Result;
    }

    public void send(int matnr) {

        // can be launched in a separate asynchronous job
        AppResult result = loginRepository.login(matnr);
        if (result instanceof AppResult.Success) {
            Martikelnumber data = ((AppResult.Success<Martikelnumber>) result).getData();
            Result.setValue(new Result(new LoggedInUserView(data.getMatnr())));
        } else {
            Result.setValue(new Result(R.string.login_failed));
        }
    }

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
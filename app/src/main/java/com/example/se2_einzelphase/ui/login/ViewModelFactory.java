package com.example.se2_einzelphase.ui.login;

import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.example.se2_einzelphase.data.AppDataSource;
import com.example.se2_einzelphase.data.LoginRepository;

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends androidx.lifecycle.ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AppViewModel.class)) {
            return (T) new AppViewModel(LoginRepository.getInstance(new AppDataSource()));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
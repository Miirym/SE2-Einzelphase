package com.example.se2_einzelphase.ui.login;

import androidx.lifecycle.Observer;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.se2_einzelphase.databinding.ActivityLoginBinding;

public class AppActivity extends AppCompatActivity {

    private AppViewModel AppViewModel;
    private ActivityLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppViewModel = new AppViewModel();

        final EditText matnr = binding.matnr;
        TextView output = binding.textoutput;
        final Button sendButton = binding.send;
        final Button calcButton = binding.calculate;

        AppViewModel.getFormState().observe(this, new Observer<FormState>() {
            @Override
            public void onChanged(@Nullable FormState FormState) {
                if (FormState == null) {
                    return;
                }
                sendButton.setEnabled(FormState.isDataValid());
                calcButton.setEnabled(FormState.isDataValid());
                if (FormState.getMatnrError() != null) {
                    matnr.setError(getString(FormState.getMatnrError()));
                }
            }
        });

        //Add TextWatcher to trigger input validation function
        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                AppViewModel.inputDataChanged(matnr.getText().toString());
            }
        };
        matnr.addTextChangedListener(afterTextChangedListener);

        //Use Event listeners for buttons on click
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // loadingProgressBar.setVisibility(View.VISIBLE);
                AppViewModel.send(Integer.parseInt(matnr.getText().toString()), output);

               // output.setText("Asdf");

                /*String asd = matnr.getText().toString();
                asd = asd.replace("1","9");
                matnr.setText(asd);*/
            }
        });
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              output.setText(  AppViewModel.calculate(Integer.parseInt(matnr.getText().toString())) );
            }
        });
    }


}
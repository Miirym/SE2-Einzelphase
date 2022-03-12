package com.example.se2_einzelphase.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.se2_einzelphase.R;
import com.example.se2_einzelphase.databinding.ActivityLoginBinding;

public class AppActivity extends AppCompatActivity {

    private AppViewModel AppViewModel;
    private ActivityLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppViewModel = new ViewModelProvider(this, new ViewModelFactory())
                .get(AppViewModel.class);

        final EditText matnr = binding.matnr;
        TextView output = binding.textoutput;
        final Button sendButton = binding.send;
        final Button calcButton = binding.calculate;
        final ProgressBar loadingProgressBar = binding.loading;

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

        AppViewModel.getLoginResult().observe(this, new Observer<Result>() {
            @Override
            public void onChanged(@Nullable Result result) {
                if (result == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (result.getError() != null) {
                    showLoginFailed(result.getError());
                }
                if (result.getSuccess() != null) {
                    updateUiWithUser(result.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
              //  finish();
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

        //Don't need this for now (triggers function from keyboard enter)
        /* matnr.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    AppViewModel.send(Integer.parseInt(matnr.getText().toString()));
                }
                return false;
            }
        }); */

        //Use Event listeners for buttons on click
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // loadingProgressBar.setVisibility(View.VISIBLE);
                AppViewModel.send(Integer.parseInt(matnr.getText().toString()));

                output.setText("Asdf");

                String asd = matnr.getText().toString();
                asd = asd.replace("1","9");
                matnr.setText(asd);
            }
        });
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   loadingProgressBar.setVisibility(View.VISIBLE);
              output.setText(  AppViewModel.calculate(Integer.parseInt(matnr.getText().toString())) );
            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getMatnr();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
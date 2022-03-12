package com.example.se2_einzelphase.ui.login;

import androidx.annotation.Nullable;

/**
 * Data validation state of the login form.
 */
class FormState {
    @Nullable
    private Integer matnrError;
    @Nullable
    private boolean isDataValid;

    FormState(@Nullable Integer matnrError) {
        this.matnrError = matnrError;
        this.isDataValid = false;
    }

    FormState(boolean isDataValid) {
        this.matnrError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    Integer getMatnrError() {
        return matnrError;
    }

    boolean isDataValid() {
        return isDataValid;
    }
}
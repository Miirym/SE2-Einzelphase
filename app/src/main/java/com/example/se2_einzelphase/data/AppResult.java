package com.example.se2_einzelphase.data;

/**
 * Holds result success w/ data or an error exception
 */
public class AppResult<T> {
    // hide the private constructor to limit subclass types (Success, Error)
    private AppResult() {
    }

    @Override
    public String toString() {
        if (this instanceof AppResult.Success) {
            AppResult.Success success = (AppResult.Success) this;
            return success.getData().toString();
        } else if (this instanceof AppResult.Error) {
            AppResult.Error error = (AppResult.Error) this;
            return "Error[exception=" + error.getError().toString() + "]";
        }
        return "";
    }

    // Success sub-class
    public final static class Success<T> extends AppResult {
        private T data;

        public Success(T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }
    }

    // Error sub-class
    public final static class Error extends AppResult {
        private Exception error;

        public Error(Exception error) {
            this.error = error;
        }

        public Exception getError() {
            return this.error;
        }
    }
}
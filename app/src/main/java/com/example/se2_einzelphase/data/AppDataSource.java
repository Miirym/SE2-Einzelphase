package com.example.se2_einzelphase.data;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * Sends/Receives data to/from Server
 */
public class AppDataSource extends AsyncTask<String, String, AppResult<String>> {

    private TextView output;

    public AppDataSource(TextView output) {
        this.output = output;
    }

    @Override
    protected AppResult<String> doInBackground(String... matnr) {

        String param = matnr[0];

        String hostname = "se2-isys.aau.at";
        int port = 53212;

        try (
            //Create new socket
            Socket socket = new Socket(hostname, port)) {

            //Send data to server
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(param);

            //Get data from Server
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String answer = reader.readLine();

            return new AppResult.Success<>(answer);
        } catch (Exception e) {
            return new AppResult.Error(new IOException("Server error", e));
        }

    }

    @Override
    protected void onPostExecute(AppResult<String> result) {
        output.setText(result.toString());
    }
}
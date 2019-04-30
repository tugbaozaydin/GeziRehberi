package com.tugbaozaydin.myapplication.Helpers;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by TugbaOzaydin on 11.03.2019.
 */

public class HttpHandler {
    public HttpHandler() {
    }

    public String makeServiceCall(String requestUrl) {
        String response = null;

        try{
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStream in = new BufferedInputStream(connection.getInputStream());
            response = convertStreamToString(in);


        }catch(MalformedURLException e){
            Log.e("ERROR","MalformedURLException:" +e.getMessage());

        }catch(IOException e){
            Log.e("ERROR","IOException:" +e.getMessage());

        }catch(Exception e){
            Log.e("ERROR","Exception:" +e.getMessage());

        }

        return response;
    }
    private  String convertStreamToString(InputStream is){
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try{
            while (((line = reader.readLine()) != null )){
                sb.append(line).append("\n");
            }

        }catch (IOException e){
            Log.e("ERROR","convertStreamToString:" +e.getMessage());

        }finally {
            try {
                is.close();
            }catch (Exception e){
                Log.e("ERROR","convertStreamToString:" +e.getMessage());

            }

        }
        return sb.toString();

    }
}

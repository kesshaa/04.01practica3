package Practica3LarionovDogsImage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        String BASE_URL = "https://dog.ceo/api/breeds/image/random";



        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            System.out.println(responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject jsonResponse = new JSONObject(response.toString());
            if (jsonResponse.has("message")) {
                String imageUrl = jsonResponse.getString("message");
                System.out.println(imageUrl);
            } else {
                System.err.println("JSON response does not contain a 'message' field.");
            };


        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package currencyconverterfx;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
//
import java.util.Scanner;

/**
 *
 * @author Rohit
 */
public class MoneyConverterAPI {
    private static final String API_KEY = "ENTER_YOUR_UNIQUE_KEY";
    private static final String USER_AGENT_ID = "Java/"+System.getProperty("java.version");
    
    
    static double rate(String from, String to) throws IOException {
        String queryPath = "https://free.currconv.com/api/v7/convert?q="
                + from + "_" 
                + to
                + "&compact=ultra&apiKey=" + API_KEY;
        URL queryURL = new URL(queryPath);
        HttpURLConnection connection 
                = (HttpURLConnection) queryURL.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT_ID);
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) { // 200 is HTTP status OK
            InputStream stream 
                    = (InputStream) connection.getContent();
            Scanner scanner = new Scanner(stream);
            String quote = scanner.nextLine();
            String number = quote.substring(quote.indexOf(':') + 1,
                    quote.indexOf('}'));
            return Double.parseDouble(number);
        } else {
            String excMsg = "Query " + queryPath 
                    + " returned status " + responseCode;
            throw new RuntimeException(excMsg);
        }
    }
}

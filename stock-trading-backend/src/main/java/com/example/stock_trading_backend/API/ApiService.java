//package com.example.stock_trading_backend.API;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.coyote.Request;
//import org.springframework.stereotype.Service;
//import org.springframework.stereotype.Service;
//import org.apache.hc.client5.http.fluent.Request;
//
//import java.util.Map;
//
//@Service
//public class AlphaVantageService {
//
//    private static final String API_KEY = "YOUR_API_KEY"; // Replace with your API key
//    private static final String BASE_URL = "https://www.alphavantage.co/query";
//
//    public Map<String, Object> getStockData(String symbol) throws Exception {
//        // Construct the URL
//        String url = BASE_URL + "?function=TIME_SERIES_INTRADAY&symbol=" + symbol +
//                "&interval=1min&apikey=" + API_KEY;
//
//        // Send the HTTP GET request
//        String response = Request.get(url)
//                .execute()
//                .returnContent()
//                .asString();
//
//        // Parse the JSON response
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.readValue(response, Map.class);
//    }
//}
//

//package com.example.stock_trading_backend.Services;
//
////import org.apache.hc.client5.http.fluent.Request;
//
//import java.util.Map;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.stereotype.Service;
//
//@Service
// class StockDataService {
//
//    private static final String API_KEY = "YOUR_API_KEY";
//    private static final String BASE_URL = "https://finnhub.io/api/v1";
//
//    public Map<String, Object> getStockQuote(String symbol) throws Exception {
//        // Construct the API URL
//        String url = BASE_URL + "/quote?symbol=" + symbol + "&token=" + API_KEY;
//
//        // Make the HTTP GET request
//        String response = Request.get(url)
//                .execute()
//                .returnContent()
//                .asString();
//
//
//
//        // Parse the JSON response
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.readValue(response, Map.class);
//    }
//}

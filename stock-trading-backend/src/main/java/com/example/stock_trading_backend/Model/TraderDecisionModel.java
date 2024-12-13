package com.example.stock_trading_backend.Model;

import lombok.Data;

@Data
public class TraderDecisionModel {

    // General Company Information
    private String companyName;
    private String tickerSymbol;
    private String sector;
    private double marketPrice;

    // Fundamental Analysis Data
    private double earningsPerShare; // EPS
    private double growthRate; // Annualized growth rate (as percentage)
    private double totalLiabilities; // Total debt
    private double shareholdersEquity; // Equity
    private double revenue; // Current revenue
    private double previousRevenue; // Revenue from last period
    private double cashFlow; // Cash flow for DCF calculation
    private double discountRate; // Used for intrinsic value calculation
    private double totalAssets; // Total assets of the company

    // Technical Analysis Data
    private double highPrice; // High price for the period
    private double lowPrice;  // Low price for the period
    private double closePrice; // Close price for the period
    private double averageGain; // Average gain for RSI calculation
    private double averageLoss; // Average loss for RSI calculation

    // Macro and Sector Data
    private double inflationRate; // Current inflation rate
    private double interestRate; // Current interest rate
    private double gdpGrowthRate; // Current GDP growth rate
    private double sectorGrowthRate; // Growth rate of the company's sector

    // Other Metrics for Specific Traders
    private double totalAddressableMarket; // TAM (Cathie Wood)
    private double riskPerTrade; // Risk per trade (Paul Tudor Jones)
    private double stopLoss; // Stop loss value for a position (Paul Tudor Jones)
    private double volatility; // Risk metric (Ray Dalio)
    private double expenseRatio; // Expense ratio for index funds (John Bogle)
}



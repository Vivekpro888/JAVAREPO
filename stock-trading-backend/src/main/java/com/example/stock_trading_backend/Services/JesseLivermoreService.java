package com.example.stock_trading_backend.Services;


import com.example.stock_trading_backend.Interface.TraderService;
import com.example.stock_trading_backend.Model.Decision;
import com.example.stock_trading_backend.Model.TraderDecisionModel;
import com.example.stock_trading_backend.Repository.TraderDecisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JesseLivermoreService implements TraderService {
    @Autowired
    private final TraderDecisionRepository traderDecisionRepository;

    public JesseLivermoreService(TraderDecisionRepository traderDecisionRepository) {
        this.traderDecisionRepository = traderDecisionRepository;
    }

    @Override
    public Decision analyze(TraderDecisionModel model) {
        // Calculate Pivot Points
        double pivotPoint = (model.getHighPrice() + model.getLowPrice() + model.getClosePrice()) / 3;

        // Calculate Simple Moving Average (SMA)
        double sma = (model.getHighPrice() + model.getLowPrice() + model.getClosePrice()) / 3;

        // Momentum-based Decision
        String action = "HOLD";
        String reason = "The market is in a neutral zone, no clear trend.";

        // If the stock price is above the Pivot Point and the SMA, recommend BUY
        if (model.getMarketPrice() > pivotPoint && model.getMarketPrice() > sma) {
            action = "BUY";
            reason = "The market price is showing upward momentum.";
        }
        // If the stock price is below the Pivot Point and the SMA, recommend SELL
        else if (model.getMarketPrice() < pivotPoint && model.getMarketPrice() < sma) {
            action = "SELL";
            reason = "The market is showing downward momentum, stock price is falling.";
        }

        Decision decision=new Decision();
        decision.setCompanyName(model.getCompanyName());
        decision.setTickerSymbol(model.getTickerSymbol());
        decision.setTraderName("Jesse Livermore");
        decision.setAction(action);
        decision.setReason(reason);


        return traderDecisionRepository.save(decision);
    }
}


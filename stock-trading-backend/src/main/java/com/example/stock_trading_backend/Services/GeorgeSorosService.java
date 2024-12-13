package com.example.stock_trading_backend.Services;

import com.example.stock_trading_backend.Interface.TraderService;
import com.example.stock_trading_backend.Model.Decision;
import com.example.stock_trading_backend.Model.TraderDecisionModel;
import com.example.stock_trading_backend.Repository.TraderDecisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeorgeSorosService implements TraderService {
    @Autowired
    private final TraderDecisionRepository traderDecisionRepository;

    public GeorgeSorosService(TraderDecisionRepository traderDecisionRepository) {
        this.traderDecisionRepository = traderDecisionRepository;
    }


    @Override
    public Decision analyze(TraderDecisionModel model) {
        double positionSize = model.getMarketPrice() * model.getRiskPerTrade() / model.getStopLoss();

        String action = "HOLD";
        String reason = "Position size is within acceptable range.";

        // If the position size suggests a favorable risk-to-reward, recommend BUY
        if (positionSize < 0.1) {
            action = "BUY";
            reason = "The market inefficiency is ripe for exploitation.";
        }

        // If risk is too high, recommend SELL
        else if (positionSize > 0.2) {
            action = "SELL";
            reason = "Position size is too high; reduce exposure.";
        }

        Decision decision=new Decision();
        decision.setCompanyName(model.getCompanyName());
        decision.setTickerSymbol(model.getTickerSymbol());
        decision.setTraderName("George Soros");
        decision.setAction(action);
        decision.setReason(reason);
        return traderDecisionRepository.save(decision);
    }
}


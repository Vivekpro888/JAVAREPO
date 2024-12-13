package com.example.stock_trading_backend.Services;

import com.example.stock_trading_backend.Interface.TraderService;
import com.example.stock_trading_backend.Model.Decision;
import com.example.stock_trading_backend.Model.TraderDecisionModel;
import com.example.stock_trading_backend.Repository.TraderDecisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaulTudorJonesService implements TraderService {
    private final TraderDecisionRepository traderDecisionRepository;

    public PaulTudorJonesService(TraderDecisionRepository traderDecisionRepository) {
        this.traderDecisionRepository = traderDecisionRepository;
    }

    @Override
    public Decision analyze(TraderDecisionModel model) {

        double riskPerUnit = model.getMarketPrice() * model.getRiskPerTrade();

        String action = "HOLD";
        String reason = "Risk per unit is acceptable.";

        // If risk is low and stop loss is within acceptable range, consider BUY
        if (riskPerUnit < 0.02 && model.getStopLoss() > 0.5) {
            action = "BUY";
            reason = "Risk-to-reward ratio is favorable.";
        }
        // If risk is too high, suggest SELL
        else if (riskPerUnit > 0.05) {
            action = "SELL";
            reason = "High risk, reduce exposure.";
        }

        Decision decision = new Decision();
        decision.setCompanyName(model.getCompanyName());
        decision.setTickerSymbol(model.getTickerSymbol());
        decision.setTraderName("Paul Tudor Jones");
        decision.setAction(action);
        decision.setReason(reason);

        return traderDecisionRepository.save(decision);
    }
}


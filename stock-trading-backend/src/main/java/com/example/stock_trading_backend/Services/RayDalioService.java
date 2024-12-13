package com.example.stock_trading_backend.Services;

import com.example.stock_trading_backend.Interface.TraderService;
import com.example.stock_trading_backend.Model.Decision;
import com.example.stock_trading_backend.Model.TraderDecisionModel;
import com.example.stock_trading_backend.Repository.TraderDecisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RayDalioService implements TraderService {

    @Autowired
    private final TraderDecisionRepository traderDecisionRepository;

    public RayDalioService(TraderDecisionRepository traderDecisionRepository) {
        this.traderDecisionRepository = traderDecisionRepository;
    }


    @Override
    public Decision analyze(TraderDecisionModel model) {
        double riskParity = model.getVolatility() * model.getMarketPrice();

        String action = "HOLD";
        String reason = "Risk parity is not favorable for a significant change.";

        // If risk parity indicates a favorable opportunity, recommend BUY
        if (riskParity < 0.01) {
            action = "BUY";
            reason = "The risk-to-reward ratio is favorable in the current market conditions.";
        }

        // If market conditions indicate high risk, recommend SELL
        else if (riskParity > 0.05) {
            action = "SELL";
            reason = "Market volatility suggests higher risk, consider reducing exposure.";
        }



        // Create and save the decision
        Decision decision = new Decision();
        decision.setCompanyName(model.getCompanyName());
        decision.setTickerSymbol(model.getTickerSymbol());
        decision.setTraderName("Benjamin Graham");
        decision.setAction(action);
        decision.setReason(reason);


        return traderDecisionRepository.save(decision);
    }
}


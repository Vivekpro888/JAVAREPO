package com.example.stock_trading_backend.Services;

import com.example.stock_trading_backend.Interface.TraderService;
import com.example.stock_trading_backend.Model.Decision;
import com.example.stock_trading_backend.Model.TraderDecisionModel;
import com.example.stock_trading_backend.Repository.TraderDecisionRepository;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BenjaminGrahamService implements TraderService {
    @Autowired
    private final TraderDecisionRepository traderDecisionRepository;

    public BenjaminGrahamService(TraderDecisionRepository traderDecisionRepository) {
        this.traderDecisionRepository = traderDecisionRepository;
    }

    @Override
    public Decision analyze(TraderDecisionModel model) {
        double intrinsicValue = model.getEarningsPerShare() * (8.5 + 2 * model.getGrowthRate());
        double marginOfSafety = intrinsicValue - model.getMarketPrice();

        String action = "HOLD";
        String reason = "Company is not sufficiently undervalued.";

        // If margin of safety is high enough, recommend BUY
        if (marginOfSafety > 15) {
            action = "BUY";
            reason = "The company is significantly undervalued with a strong margin of safety.";
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


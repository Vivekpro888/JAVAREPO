package com.example.stock_trading_backend.Services;

import com.example.stock_trading_backend.Interface.TraderService;
import com.example.stock_trading_backend.Model.Decision;
import com.example.stock_trading_backend.Model.TraderDecisionModel;
import com.example.stock_trading_backend.Repository.TraderDecisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarrenBuffettService implements TraderService {

    private final TraderDecisionRepository traderDecisionRepository;

    @Autowired
    public WarrenBuffettService(TraderDecisionRepository traderDecisionRepository) {
        this.traderDecisionRepository = traderDecisionRepository;
    }

    @Override
    public Decision analyze(TraderDecisionModel model) {
        double intrinsicValue = (model.getEarningsPerShare() * (8.5 + 2 * model.getGrowthRate()));
        double marginOfSafety = intrinsicValue - model.getMarketPrice();

        String action = "HOLD";
        String reason = "Company is not undervalued enough for buying.";

        // If margin of safety is large, recommend BUY
        if (marginOfSafety > 10) {
            action = "BUY";
            reason = "The company is undervalued with a significant margin of safety.";
        }

        // If market price is significantly higher than intrinsic value, recommend SELL
        else if (model.getMarketPrice() > intrinsicValue * 1.2) {
            action = "SELL";
            reason = "The company is overpriced compared to its intrinsic value.";
        }

        // Create Decision entity to save in database
        Decision decision = new Decision();
        decision.setTraderName("Warren Buffett");
        decision.setCompanyName(model.getCompanyName());
        decision.setTickerSymbol(model.getTickerSymbol());
        decision.setAction(action);
        decision.setReason(reason);

        // Save the decision to the database
        return traderDecisionRepository.save(decision);
    }
}

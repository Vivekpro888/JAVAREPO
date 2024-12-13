package com.example.stock_trading_backend.Services;

import com.example.stock_trading_backend.Interface.TraderService;
import com.example.stock_trading_backend.Model.Decision;
import com.example.stock_trading_backend.Model.TraderDecisionModel;
import com.example.stock_trading_backend.Repository.TraderDecisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeterLynchService implements TraderService {

    private final TraderDecisionRepository traderDecisionRepository;

    @Autowired
    public PeterLynchService(TraderDecisionRepository traderDecisionRepository) {
        this.traderDecisionRepository = traderDecisionRepository;
    }
    @Override
    public Decision analyze(TraderDecisionModel model) {
        double pegRatio = model.getEarningsPerShare() / model.getGrowthRate();

        String action = "HOLD";
        String reason = "PEG ratio is not attractive for buying.";

        // If PEG ratio is less than 1, recommend BUY
        if (pegRatio < 1) {
            action = "BUY";
            reason = "The company has high growth potential with an attractive PEG ratio.";
        }

        // If PEG ratio is too high, recommend SELL
        else if (pegRatio > 2) {
            action = "SELL";
            reason = "The company’s growth potential is priced too high.";
        }
// Create and save the decision
        Decision decision = new Decision();
        decision.setCompanyName(model.getCompanyName());
        decision.setTickerSymbol(model.getTickerSymbol());
        decision.setTraderName("Peter Lynch");
        decision.setAction(action);
        decision.setReason(reason);

        return traderDecisionRepository.save(decision);
    }
}


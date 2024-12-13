package com.example.stock_trading_backend.Services;

import com.example.stock_trading_backend.Interface.TraderService;
import com.example.stock_trading_backend.Model.Decision;
import com.example.stock_trading_backend.Model.TraderDecisionModel;
import com.example.stock_trading_backend.Repository.TraderDecisionRepository;
import org.springframework.stereotype.Service;

@Service
public class JohnBogleService implements TraderService {

    private final TraderDecisionRepository traderDecisionRepository;

    public JohnBogleService(TraderDecisionRepository traderDecisionRepository) {
        this.traderDecisionRepository = traderDecisionRepository;
    }

    @Override
    public Decision analyze(TraderDecisionModel model) {
        double expenseRatio = model.getExpenseRatio();

        String action = "HOLD";
        String reason = "Expense ratio is reasonable for index funds.";

        // If the expense ratio is low, recommend BUY (in the context of index funds)
        if (expenseRatio < 0.1) {
            action = "BUY";
            reason = "The fund has a low expense ratio and aligns with passive investing principles.";
        }


        Decision decision = new Decision();
        decision.setCompanyName(model.getCompanyName());
        decision.setTickerSymbol(model.getTickerSymbol());
        decision.setTraderName("John Bogle");
        decision.setAction(action);
        decision.setReason(reason);
        return traderDecisionRepository.save(decision);
    }
}


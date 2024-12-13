package com.example.stock_trading_backend.Services;

import com.example.stock_trading_backend.Interface.TraderService;
import com.example.stock_trading_backend.Model.Decision;
import com.example.stock_trading_backend.Model.TraderDecisionModel;
import com.example.stock_trading_backend.Repository.TraderDecisionRepository;
import org.springframework.stereotype.Service;

@Service
public class CathieWoodService implements TraderService {
    private final TraderDecisionRepository traderDecisionRepository;

    public CathieWoodService(TraderDecisionRepository traderDecisionRepository) {
        this.traderDecisionRepository = traderDecisionRepository;
    }


    @Override
    public Decision analyze(TraderDecisionModel model) {
        double marketGrowthPotential = model.getTotalAddressableMarket() * model.getSectorGrowthRate();

        String action = "HOLD";
        String reason = "The company is not positioned to capitalize on the sector's growth potential.";

        // If the market growth potential is high, recommend BUY
        if (marketGrowthPotential > 1000) {
            action = "BUY";
            reason = "The company is well-positioned to capitalize on innovation and growth.";
        }

        Decision decision=new Decision();
        decision.setCompanyName(model.getCompanyName());
        decision.setTickerSymbol(model.getTickerSymbol());
        decision.setReason(reason);
        decision.setTraderName("Cathie Wood");
        decision.setAction(action);

        return traderDecisionRepository.save(decision);
    }
}


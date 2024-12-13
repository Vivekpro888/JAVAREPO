package com.example.stock_trading_backend.Interface;

import com.example.stock_trading_backend.Model.Decision;
import com.example.stock_trading_backend.Model.TraderDecisionModel;

public interface TraderService {
    Decision analyze(TraderDecisionModel model);
}


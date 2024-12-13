package com.example.stock_trading_backend.Controller;


import com.example.stock_trading_backend.Model.TraderDecisionModel;
import com.example.stock_trading_backend.Model.Decision;
import com.example.stock_trading_backend.Services.WarrenBuffettService;
import com.example.stock_trading_backend.Services.PeterLynchService;
import com.example.stock_trading_backend.Services.BenjaminGrahamService;
import com.example.stock_trading_backend.Services.RayDalioService;
import com.example.stock_trading_backend.Services.GeorgeSorosService;
import com.example.stock_trading_backend.Services.JesseLivermoreService;
import com.example.stock_trading_backend.Services.CathieWoodService;
import com.example.stock_trading_backend.Services.JohnBogleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/decision")
public class TradingDecisionController {

    @Autowired
    private WarrenBuffettService warrenBuffettService;

    @Autowired
    private PeterLynchService peterLynchService;

    @Autowired
    private BenjaminGrahamService benjaminGrahamService;

    @Autowired
    private RayDalioService rayDalioService;

    @Autowired
    private GeorgeSorosService georgeSorosService;

    @Autowired
    private JesseLivermoreService jesseLivermoreService;

    @Autowired
    private CathieWoodService cathieWoodService;

    @Autowired
    private JohnBogleService johnBogleService;

    // Single endpoint for all trader decisions
    @PostMapping("/all")
    public boolean getAllTraderDecisions(@RequestBody TraderDecisionModel model) {
        List<Decision> decisions = new ArrayList<>();

        // Collect decisions from each trader
        decisions.add(warrenBuffettService.analyze(model));
        decisions.add(peterLynchService.analyze(model));
        decisions.add(benjaminGrahamService.analyze(model));
        decisions.add(rayDalioService.analyze(model));
        decisions.add(georgeSorosService.analyze(model));
        decisions.add(jesseLivermoreService.analyze(model));
        decisions.add(cathieWoodService.analyze(model));
        decisions.add(johnBogleService.analyze(model));

        return true; // Return all decisions as a list
    }

    @PostMapping("/warrenDuffect")
    public Decision OnlyWarrenbuffed(@RequestBody TraderDecisionModel model){
        return  warrenBuffettService.analyze(model);
    }
}


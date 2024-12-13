package com.example.stock_trading_backend.Repository;

import com.example.stock_trading_backend.Model.Decision;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraderDecisionRepository extends JpaRepository<Decision, Long> {
    // You can add custom query methods here if needed
}


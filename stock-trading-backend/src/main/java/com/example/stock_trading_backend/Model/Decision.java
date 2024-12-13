package com.example.stock_trading_backend.Model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "DesicionT")
public class Decision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String tickerSymbol;
    private String traderName;
    private String action;
    private String reason;

    // Getters and Setters
}



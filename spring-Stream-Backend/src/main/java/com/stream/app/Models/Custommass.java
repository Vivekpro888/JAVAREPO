package com.stream.app.Models;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
@Builder
public class Custommass {

   private String msg;
    private  boolean scc=false;
}

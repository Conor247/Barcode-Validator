package com.conor.barcodevalidator.domain.service.strategy;

import org.springframework.stereotype.Service;

@Service
public class ValidatePrefix implements ValidationStrategyInterface {

    @Override
    public boolean validateS10(String prefix) {
        return prefix.matches("^[A-Z]{2}$");
    }
}

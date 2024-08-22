package com.conor.barcodevalidator.domain.service.strategy;

import org.springframework.stereotype.Service;

@Service
public class ValidateCountryCode implements ValidationStrategyInterface {

    @Override
    public boolean validateS10(String countryCode) {
        return countryCode.equals("GB");
    }
}

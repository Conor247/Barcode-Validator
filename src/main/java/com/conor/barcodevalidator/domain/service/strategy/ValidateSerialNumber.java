package com.conor.barcodevalidator.domain.service.strategy;

import org.springframework.stereotype.Service;

@Service
public class ValidateSerialNumber implements ValidationStrategyInterface {

    @Override
    public boolean validateS10(String serialNum) {
        return serialNum.matches("^\\d{8}$");
    }
}

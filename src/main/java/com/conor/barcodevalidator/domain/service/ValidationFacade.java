package com.conor.barcodevalidator.domain.service;

import com.conor.barcodevalidator.domain.service.strategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ValidationFacade {
    private final ValidationStrategyInterface validatePrefix;
    private final ValidationStrategyInterface validateSerialNumber;
    private final ValidationStrategyInterface validateCheckDigit;
    private final ValidationStrategyInterface validateCountryCode;

    @Autowired
    public ValidationFacade(ValidationStrategyInterface validatePrefix,
                            ValidationStrategyInterface validateSerialNumber,
                            ValidationStrategyInterface validateCheckDigit,
                            ValidationStrategyInterface validateCountryCode) {
        this.validatePrefix = validatePrefix;
        this.validateSerialNumber = validateSerialNumber;
        this.validateCheckDigit = validateCheckDigit;
        this.validateCountryCode = validateCountryCode;
    }

    public boolean validateS10Barcode(String barcode) throws IOException {
        try {
            if (barcode == null || barcode.length() != 13) {
                throw new IllegalArgumentException("The barcode string must be exactly 13 characters long.");
            }

            String prefix = barcode.substring(0, 2);
            String serial = barcode.substring(2, 10);
            String serialCheckDigit = barcode.substring(2, barcode.length() - 2);
            String countryCode = barcode.substring(barcode.length() - 2);

            return validatePrefix.validateS10(prefix) &&
                    validateSerialNumber.validateS10(serial) &&
                    validateCheckDigit.validateS10(serialCheckDigit) &&
                    validateCountryCode.validateS10(countryCode);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}


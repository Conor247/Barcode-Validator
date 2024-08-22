package com.conor.barcodevalidator.domain.service;

import com.conor.barcodevalidator.domain.service.strategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ValidationFacade implements ValidationFacadeInterface {
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
        String prefix = barcode.substring(0, 2);
        String serial = barcode.substring(2, 10);
        String serialCheckDigit = barcode.substring(2, barcode.length() - 2);
        String countryCode = barcode.substring(barcode.length() - 2);

        return validatePrefix.validateS10(prefix) &&
               validateSerialNumber.validateS10(serial) &&
               validateCheckDigit.validateS10(serialCheckDigit) &&
               validateCountryCode.validateS10(countryCode);
    }
}

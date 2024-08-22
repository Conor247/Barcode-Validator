package com.conor.barcodevalidator.domain.service.strategy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ValidateCountryCodeTest {

    private final ValidateCountryCode validateCountryCode = new ValidateCountryCode();

    @Test
    void validateS10CountryCodeTest() {
        assertTrue(validateCountryCode.validateS10("GB"));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "A1",
            "1A",
            "ZZZ",
            "U",
            "22",
            "  ",
            "",
            "IE"
    })
    void validateS10PrefixFailsTest(String prefix) {
        assertFalse(validateCountryCode.validateS10(prefix));
    }
}
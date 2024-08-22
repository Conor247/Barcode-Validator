package com.conor.barcodevalidator.domain.service.strategy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ValidateSerialNumberTest {

    private final ValidateSerialNumber validateSerialNumber = new ValidateSerialNumber();

    @ParameterizedTest
    @ValueSource(strings = {
            "87452909",
            "00000009",
            "00000000",
            "99999999"
    })
    void validateS10SerialNumberTest(String serialNumber) {
        assertTrue(validateSerialNumber.validateS10(serialNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "8745290",
            "000000091",
            "9",
            "A1234567",
            "CDERGBNH",
            "QWERTYS",
            "        ",
            ""
    })
    void validateS10SerialNumberFailsTest(String serialNumber) {
        assertFalse(validateSerialNumber.validateS10(serialNumber));
    }
}
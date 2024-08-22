package com.conor.barcodevalidator.domain.service;

import com.conor.barcodevalidator.domain.service.strategy.ValidationStrategyInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidationFacadeTest {

    @InjectMocks
    private ValidationFacade validationFacade;

    @Mock
    private ValidationStrategyInterface validate;

    @Test
    void validateS10BarcodeTest() throws IOException {
        when(validate.validateS10(anyString())).thenReturn(true);
        assertTrue(validationFacade.validateS10Barcode("AB473124829GB"));
    }

    @Test
    void validateS10BarcodeFailsTest() throws IOException {
        when(validate.validateS10(anyString())).thenReturn(false);
        assertFalse(validationFacade.validateS10Barcode("AB473124829IE"));
    }
}
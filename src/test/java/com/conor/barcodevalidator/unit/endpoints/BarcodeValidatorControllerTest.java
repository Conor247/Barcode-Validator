package com.conor.barcodevalidator.unit.endpoints;

import com.conor.barcodevalidator.domain.service.ValidationFacade;
import com.conor.barcodevalidator.endpoints.BarcodeValidatorController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BarcodeValidatorControllerTest {

    @Mock
    ValidationFacade validationFacade;
    @InjectMocks
    BarcodeValidatorController barcodeValidatorController;
    @Test
    void validateControllerTest() throws IOException {
        when(validationFacade.validateS10Barcode(anyString())).thenReturn(true);
        assertTrue(barcodeValidatorController.validate("AB473124829GB"));
    }

    @Test
    void validateControllerFailsTest() throws IOException {
        when(validationFacade.validateS10Barcode(anyString())).thenReturn(false);
        assertFalse(barcodeValidatorController.validate("AB473124829IE"));
    }
}
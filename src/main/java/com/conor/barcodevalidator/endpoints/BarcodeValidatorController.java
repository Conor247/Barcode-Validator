package com.conor.barcodevalidator.endpoints;

import com.conor.barcodevalidator.domain.service.ValidationFacade;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class BarcodeValidatorController {

    private final ValidationFacade validationFacade;

    public BarcodeValidatorController(ValidationFacade validationFacade) {
        this.validationFacade = validationFacade;
    }

    @PostMapping(path = "/validate")
    public boolean validate(@RequestHeader("barcode") String barcode) throws IOException {
        return validationFacade.validateS10Barcode(barcode);
    }

}

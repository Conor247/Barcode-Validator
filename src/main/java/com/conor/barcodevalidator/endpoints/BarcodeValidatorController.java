package com.conor.barcodevalidator.endpoints;

import com.conor.barcodevalidator.domain.service.ValidationFacadeInterface;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class BarcodeValidatorController {

    private final ValidationFacadeInterface validationFacade;

    public BarcodeValidatorController(ValidationFacadeInterface validationFacade) {
        this.validationFacade = validationFacade;
    }

    @PostMapping(path = "/validate")
    public boolean validate(@RequestHeader("barcode") String barcode) throws IOException {
        return validationFacade.validateS10Barcode(barcode);
    }

}

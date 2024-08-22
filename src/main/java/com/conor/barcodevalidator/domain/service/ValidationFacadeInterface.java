package com.conor.barcodevalidator.domain.service;

import java.io.IOException;

public interface ValidationFacadeInterface {

    boolean validateS10Barcode(String barcode) throws IOException;
}

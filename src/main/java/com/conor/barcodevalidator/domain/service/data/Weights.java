package com.conor.barcodevalidator.domain.service.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Weights {
    @JsonProperty("S10")
    List<Integer> s10;
}

package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor

public class PaintingSpecs {

    private final Integer alturaCm;
    private final Integer largoCm;
    private final String medium;
    private final String supportMaterial;

}

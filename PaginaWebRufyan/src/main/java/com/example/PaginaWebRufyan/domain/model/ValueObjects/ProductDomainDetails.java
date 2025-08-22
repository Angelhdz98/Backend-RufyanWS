package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class ProductDomainDetails {
    public abstract void validate();
}

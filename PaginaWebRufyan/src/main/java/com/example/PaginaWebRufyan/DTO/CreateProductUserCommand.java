package com.example.PaginaWebRufyan.DTO;

import com.example.PaginaWebRufyan.domain.model.ValueObjects.StockManagerBase;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateProductUserCommand {
    private final String name;

}

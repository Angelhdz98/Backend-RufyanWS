package com.example.PaginaWebRufyan.Buys.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRegisterNew {
    private Integer productId;
    private Integer userId;
    private Map<String, String> details;
}

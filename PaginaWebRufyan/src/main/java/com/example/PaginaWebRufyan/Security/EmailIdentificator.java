package com.example.PaginaWebRufyan.Security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class EmailIdentificator implements UserIdentificable{
    @Getter
    private String email;


    @Override
    public String getIdentificator() {
        return getEmail();
    }
}

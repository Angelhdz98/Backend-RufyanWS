package com.example.PaginaWebRufyan.Security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class UsernameIdentificator implements UserIdentificable{
    @Getter
   private String username;


    @Override
    public String getIdentificator() {
        return getUsername();
    }
    
}

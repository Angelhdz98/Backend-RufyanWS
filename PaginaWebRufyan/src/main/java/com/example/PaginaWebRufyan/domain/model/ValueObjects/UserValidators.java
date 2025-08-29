package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@AllArgsConstructor
@Component
public class UserValidators {
    private boolean isEnabled;
    private boolean accountNoExpired;
    private boolean accountNoLocked;
    private boolean credentialNoExpired;

    public Boolean isLoggeable(){
        return isEnabled && accountNoExpired && accountNoLocked && credentialNoExpired;
    }
}

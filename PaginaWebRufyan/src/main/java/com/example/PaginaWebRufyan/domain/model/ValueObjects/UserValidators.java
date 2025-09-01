package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Embeddable
public class UserValidators {
    private boolean isEnabled;
    private boolean accountNoExpired;
    private boolean accountNoLocked;
    private boolean credentialNoExpired;

    public Boolean isLoggeable(){
        return isEnabled && accountNoExpired && accountNoLocked && credentialNoExpired;
    }
}

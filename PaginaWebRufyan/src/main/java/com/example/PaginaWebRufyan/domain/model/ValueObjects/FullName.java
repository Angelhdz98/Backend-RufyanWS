package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Builder
@AllArgsConstructor
@Component
public class FullName {
    @NotNull
    private final String firstName;
    private final String secondName;
    @NotNull
    private final String firstLastname;
    private final String secondLastname;

    public String getFullName(){
        StringBuilder nameCrafter = new StringBuilder(this.firstName);
        nameCrafter.append(" ") ;
        if(!secondName.isBlank()) nameCrafter.append(secondName);
        nameCrafter.append(firstLastname);
        if(!secondLastname.isBlank()) nameCrafter.append(secondLastname);

        return nameCrafter.toString();

    }




}

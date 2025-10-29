package com.example.PaginaWebRufyan.domain.model.ValueObjects;
import java.util.regex.*;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Embeddable
public class FullName {
    public static Integer MIN_NAME_LENGTH=2;
    @NotNull
    private final String firstName;
    private final String secondName;
    @NotNull
    private final String firstLastname;
    private final String secondLastname;
    protected FullName(){

        secondLastname = "";
        firstLastname = "";
        secondName = "";
        firstName = "";
    }

    public FullName(String firstName, String secondName, String firstLastname, String secondLastname) {

        String regex = "^[A-Za-záéíóúÁÉÍÓÚñÑ\s]+$";
        if(firstName.length()< MIN_NAME_LENGTH|| !firstName.matches(regex)  ) throw  new IllegalArgumentException("El primer nombre no puede ser menor a "+MIN_NAME_LENGTH+" caracteres y solo puede contener letras");
        this.firstName = firstName;
        if(!secondName.isBlank() && (secondName.length()< MIN_NAME_LENGTH|| !secondName.matches(regex))  ) throw  new IllegalArgumentException("El segundo nombre no puede ser menor a "+MIN_NAME_LENGTH+" caracteres  y solo puede contener letras o debe estar vacio ");
        this.secondName = secondName;
        if(firstLastname.length()< MIN_NAME_LENGTH|| !firstLastname.matches(regex)  ) throw  new IllegalArgumentException("El primer apelllido no puede ser menor a "+MIN_NAME_LENGTH+" caracteres y solo puede contener letras");
        this.firstLastname = firstLastname;

        if(!secondLastname.isBlank() && (secondLastname.length()< MIN_NAME_LENGTH|| !secondLastname.matches(regex))  ) throw  new IllegalArgumentException("El segundo apellido no puede ser menor a "+MIN_NAME_LENGTH+" caracteres  y solo puede contener letras o debe estar vacio ");
        this.secondLastname = secondLastname;
    }

    public String getFullName(){
        StringBuilder nameCrafter = new StringBuilder(this.firstName);
        nameCrafter.append(" ") ;
        if(!secondName.isBlank()){
            nameCrafter.append(secondName).append(" ");

        }
        nameCrafter.append(firstLastname).append(" ");
        if(!secondLastname.isBlank()) nameCrafter.append(secondLastname);

        return nameCrafter.toString();

    }




}

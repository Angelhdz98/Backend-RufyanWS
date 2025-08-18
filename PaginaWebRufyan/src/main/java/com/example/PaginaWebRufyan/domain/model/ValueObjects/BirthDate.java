package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Getter
@Component
public class BirthDate {
    @JsonFormat(pattern = "yyyy-M-d")
    private final LocalDate birthDate;

    public  BirthDate(LocalDate birthDate) {
        if(birthDate.isAfter(LocalDate.now()
        )) throw new IllegalArgumentException("Se prohibe la especulación de arte a viajeros del tiempo");
        //if(birthDate.equals(null))throw new IllegalArgumentException("La fecha de cumpleaños no puede ser nula");

        this.birthDate = birthDate;

    }


    public boolean isAdult(){
return getAge()>=18;
    }

    public Integer getAge(){
       // return (int) ChronoUnit.YEARS.between(birthDate ,LocalDate.now()); // no es precisa
        return Period.between(birthDate,LocalDate.now()).getYears();
    }

}

package com.example.PaginaWebRufyan.DomainTest.PriceManagerTest;


import com.example.PaginaWebRufyan.adapter.out.SinglePriceManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.assertj.core.api.Assertions;

import java.math.BigDecimal;


@ExtendWith(MockitoExtension.class)
public class SinglePriceManagerTest {

    @Test
    @DisplayName("Test para crear un SinglePriceManager ")
    public void shouldCreateSinglePriceManager(){
        SinglePriceManager singlePriceManager = new SinglePriceManager(SinglePriceManager.MIN_PRICE);

        Assertions.assertThat(singlePriceManager).isInstanceOf(SinglePriceManager.class);


    }
     @Test
     @DisplayName("Test para arrojar una excepción en caso de que se quierar registrar un SinglePriceManager con un precio menor al establecido")
    public void shouldThrowErrorCreatingSinglePriceManager(){

        assertThrows(IllegalArgumentException.class, ()->{
    new SinglePriceManager(SinglePriceManager.MIN_PRICE.subtract(new BigDecimal("0.01")));
        });

    }
}

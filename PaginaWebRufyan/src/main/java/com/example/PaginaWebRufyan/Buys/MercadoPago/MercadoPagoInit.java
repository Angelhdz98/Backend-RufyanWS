package com.example.PaginaWebRufyan.Buys.MercadoPago;

import com.mercadopago.MercadoPagoConfig;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MercadoPagoInit {

    @Value("${mercado_pago.access_token}")
    private String accessToken;

    @PostConstruct
    public void initMercadoPago(){
         MercadoPagoConfig.setAccessToken(accessToken);
         System.out.println(" Mercado pago ha sido inicializado");
    }

}

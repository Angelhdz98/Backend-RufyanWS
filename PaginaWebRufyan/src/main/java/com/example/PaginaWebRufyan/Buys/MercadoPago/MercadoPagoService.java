package com.example.PaginaWebRufyan.Buys.MercadoPago;

import com.example.PaginaWebRufyan.Buys.Entity.CartItem;
import com.example.PaginaWebRufyan.Buys.Entity.ShoppingCartEntity;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.beans.factory.annotation.Value;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

public class MercadoPagoService {

    @Value("${spring.MercadoPago.backUrl.success}")
    private String success;
    @Value("${spring.MercadoPago.backUrl.pending}")
    private String pending;
    @Value("${spring.MercadoPago.backUrl.failure}")
    private String failure;
    @Value("${spring.MercadoPago.expiration.dayForExpiration}")
    private String expirtarionDays;





    private PreferenceItemRequest createPreference(CartItem cartItem){
        return PreferenceItemRequest.builder()
                .id(String.valueOf(cartItem.getId()))
                //.categoryId(cartItem.getProduct().getCategory())
                .currencyId("MXN")
                .quantity(cartItem.getCartItemDetails().getQuantity())
                .description(cartItem.getProduct().getDescription())
                .pictureUrl(cartItem.getProduct().getImage().stream().findFirst().orElseThrow().getUrl())
                .title(cartItem.getProduct().getName())
                .unitPrice(cartItem.getTotalPrice())
                .build();
    }

    private List<PreferenceItemRequest> createPreferenceItemRequestList(ShoppingCartEntity cart){
        System.out.println("cart: "+cart);
        List<PreferenceItemRequest> itemRequests =  cart.getItemList().stream().map(this::createPreference).collect(Collectors.toList());
        //System.out.println("Items: "+itemRequests);
        return itemRequests;
    }

    // al llevarse a cabo el request también se creará una order que al verificarse el pago se cambiará su estado

    public Preference createRequest(ShoppingCartEntity cart) throws MPException, MPApiException {

        PreferenceClient client = new PreferenceClient();
        List<PreferenceItemRequest>
                preferenceItemRequestList = createPreferenceItemRequestList(cart);

        PreferenceBackUrlsRequest backUrlsRequest = PreferenceBackUrlsRequest.builder()
                .success(success)
                .pending(pending)
                .failure(failure)
                .build();

        // el usuario solo cuenta con 3 días para pagar una obra

            PreferenceRequest order = PreferenceRequest
                    .builder()
                .backUrls(backUrlsRequest)
                .items(preferenceItemRequestList)
                .expirationDateFrom(OffsetDateTime.now(ZoneOffset.UTC))
                .expirationDateTo(OffsetDateTime.now().plusDays(/*Long.getLong(expirtarionDays)*/ 3L))
                .build();



          return client.create(order);

    }
}

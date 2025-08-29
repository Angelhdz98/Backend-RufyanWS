package com.example.PaginaWebRufyan.Buys.Service;

import com.example.PaginaWebRufyan.Buys.DTO.PurchaseDTO;
import com.example.PaginaWebRufyan.Buys.Enums.OrderStatusEnum;
import com.example.PaginaWebRufyan.Buys.Entity.PurchaseOrder;
import com.example.PaginaWebRufyan.Buys.Entity.ShoppingCart;
import com.example.PaginaWebRufyan.Buys.Repository.PurchasesRepository;
import com.example.PaginaWebRufyan.Buys.MercadoPago.MercadoPagoService;
import com.example.PaginaWebRufyan.Buys.Repository.OrderStatusRepository;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PurchasesService {
    @Autowired
    private PurchasesRepository purchasesRepo;
    @Autowired
    private MercadoPagoService mercadoPagoService;
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    public List<PurchaseDTO> retrieveAllPurchases(){

        return(purchasesRepo.findAll().stream().map(PurchaseDTO::new).collect(Collectors.toList()));
    }
    public List<PurchaseOrder> retrievePaidPurchases(){
        return null;
    }
    public List<PurchaseOrder> retrievePendingPurchases(){
       return null;
    }
    public List<PurchaseOrder> retrieveCancelledPurchases(){
        return null;
    }
    public PurchaseOrder cancelOrder(Integer orderId){
        return null;
    }

    //all orders
    @Transactional
    public PurchaseOrder createOrder(ShoppingCart cart){
        Preference newPreference;
        try {
           newPreference = mercadoPagoService.createRequest(cart);
        } catch (MPException e) {
            throw new RuntimeException(e);
        } catch (MPApiException e) {
            System.out.println("Status code: " + e.getStatusCode());
            System.out.println("Message: " + e.getApiResponse().getContent());
            throw new RuntimeException(e);
        }

       PurchaseOrder purchase= new PurchaseOrder(cart);
        purchase.setPaymentId(newPreference.getId());
        purchase.setOrderStatus(orderStatusRepository.findByOrderStatusEnum(OrderStatusEnum.PENDING).get());
        purchase.setUser(cart.getUser());

        return purchasesRepo.save(purchase);
    }

    public PurchaseOrder confirmPaymentOrder(Map<String,Object> payload){

        PaymentClient paymentClient = new PaymentClient();
        Payment payment;
        Long paymentIdLong = (Long)payload.get("user_id");
        System.out.println(paymentIdLong);
        String paymentId = String.valueOf(paymentIdLong);
        try {
            payment = paymentClient.get(paymentIdLong);
            System.out.println(payment);
        } catch (MPException e) {
            throw new RuntimeException(e);
        } catch (MPApiException e) {
            throw new RuntimeException(e);
        }
        // PurchaseOrder foundPurchaseOrder =  purchasesRepo.findPurchaseOrderByPaymentId(paymentId).orElseThrow(()->new ResourceNotFoundException("Not found by payment_id: " +paymentId));
        //foundPurchaseOrder.setOrderStatus(new OrderStatus(1,"Pago confirmado ", OrderStatusEnum.PAID));



        return null;

    }


}

package com.example.PaginaWebRufyan.Buys.Controller;

import com.example.PaginaWebRufyan.Buys.DTO.PurchaseDTO;
import com.example.PaginaWebRufyan.Buys.Entity.PurchaseOrder;
import com.example.PaginaWebRufyan.Buys.Entity.ShoppingCart;
import com.example.PaginaWebRufyan.Buys.Service.PurchasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PurchasesController {
    @Autowired
    private PurchasesService purchasesService;

    //This function creates the purchase order and saveUser the request for MP

    @GetMapping("purchases")
    public ResponseEntity<List<PurchaseDTO>> showAllPurchases(){
     return ResponseEntity.ok(purchasesService.retrieveAllPurchases());
    }
    @PostMapping("purchases/MP-notification")
    public ResponseEntity<PurchaseOrder> receiveMPNotification(@RequestBody Map<String,Object> payload){
        System.out.println("Notificación recibida con map: "+ payload.toString());

        String type = (String) payload.get("type");

        if("payment".equals(type)){
            System.out.println("payment");
            purchasesService.confirmPaymentOrder(payload);
        }
        else{
            System.out.println(type);
        }

        return ResponseEntity.ok().build();

    }

    @PostMapping
    public ResponseEntity<PurchaseOrder> createPurchase(ShoppingCart cart){

        return ResponseEntity.ok(purchasesService.createOrder(cart));

    }

   /* @PostMapping("/Purchases-confirm")
    public ResponseEntity<PurchaseOrder> confirmPurchase(String paymentId){
        return ResponseEntity.ok(purchasesService.confirmPaymentOrder(paymentId));

    }
    */


    @DeleteMapping("/Purchases/{id}")
    public ResponseEntity<Void> deletePurchase(@PathVariable Integer purchaseOrder){

        return ResponseEntity.ok().build();
    }

    // this has to work with partial and complete devolution
    @PutMapping("/Purchases/{id}")
    public ResponseEntity<PurchaseOrder> updateOrder(){
        return ResponseEntity.ok(null
        );
    }

}

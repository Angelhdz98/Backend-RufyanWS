package com.example.PaginaWebRufyan.serviceTest;

import com.example.PaginaWebRufyan.Buys.DTO.PurchaseDTO;
import com.example.PaginaWebRufyan.Buys.Entity.OrderStatus;
import com.example.PaginaWebRufyan.Buys.Enums.OrderStatusEnum;
import com.example.PaginaWebRufyan.Buys.Entity.PurchaseOrder;
import com.example.PaginaWebRufyan.Buys.Repository.PurchasesRepository;
import com.example.PaginaWebRufyan.Buys.Service.PurchasesService;
import com.example.PaginaWebRufyan.Products.Entity.Painting;
import com.example.PaginaWebRufyan.adapter.out.persistence.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class OrdersServiceTest {

@InjectMocks
    PurchasesService purchasesService;

@Mock
    private PurchasesRepository purchasesRepository;



    Product painting1 = Painting.builder().orderItems(Set.of())..build()


    private PurchaseOrder purchasePaid = new PurchaseOrder();
    private PurchaseOrder recentlyPurchasePaid = new PurchaseOrder();
    private PurchaseOrder purchasePending =new PurchaseOrder();
    private OrderStatus pendingStatus = new OrderStatus("Compra pendiente de pago ",OrderStatusEnum.PENDING );

    private OrderStatus paidStatus = new OrderStatus("Compra pagada", OrderStatusEnum.PAID);
    private OrderStatus cancelledStatus = new OrderStatus("Compra cancelada",OrderStatusEnum.CANCELLED);
    @BeforeEach
    void setUp(){
        purchasePaid = PurchaseOrder.builder()
                .orderStatus(paidStatus)
                .createdAt(LocalDate.now().minusDays(31))
                .build();
        recentlyPurchasePaid = purchasePaid.clone();
        //recentlyPurchasePaid.setCreatedAt(LocalDate.now().minusDays(5));

        purchasePending = PurchaseOrder.builder()
                .orderStatus(pendingStatus)
                .build();



    }
    @Test
    @DisplayName("Test para mostrar todas las compras realizadas ")
    void showAllPurchasesTest(){

        given(purchasesRepository.findAll()).willReturn(List.of(purchaseCancelled, purchasePaid, purchasePending, recentlyPurchasePaid));
        List<PurchaseDTO> allPurchases= purchasesService.retrieveAllPurchases();
        assertThat(allPurchases.get(1)).isInstanceOf(PurchaseOrder.class);


    }
    @Test
    @DisplayName("Test para mostrar la compras pagadas  ")
    void retrievePaidPurchasesTest(){
        PurchaseOrder paidclone1 = purchasePaid.clone();

    }
    @Test
    @DisplayName("Test para mostrar la compras pendientes  ")
    void retrievePendingPurchasesTest(){

    }
    @Test
    @DisplayName("Test para mostrar la compras canceladas  ")
    void retrieveCancelledPurchasesTest(){

    }

    @Test
    @DisplayName("Test para cancelar una orden de manera exitosa")
    void cancelAnOrderTest(){

    }
    @Test
    @DisplayName("Test para intentar cancelar una orden cuando ya ha pasado más del tiempo de entrega  ")
    void tryToCancelAnOrder(){

    }

}







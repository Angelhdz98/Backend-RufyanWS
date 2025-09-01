package com.example.PaginaWebRufyan.Service.ShoppingCartServiceAdapter;

import com.example.PaginaWebRufyan.Products.Entity.Painting;
import com.example.PaginaWebRufyan.adapter.in.ShoppingCartController.CartItemCommand;
import com.example.PaginaWebRufyan.domain.model.*;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.CartItemDetails;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ClothingItemDetails;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.PaintingItemDetails;
import com.example.PaginaWebRufyan.domain.port.in.ShoppingCartUseCase.AddCartItemUseCase;
import com.example.PaginaWebRufyan.domain.port.out.CartItemMapper;
import com.example.PaginaWebRufyan.domain.port.out.ProductRepositoryPort;
import com.example.PaginaWebRufyan.domain.port.out.ShoppingCartRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class AddCartItemService implements AddCartItemUseCase {

    private final ShoppingCartRepositoryPort shoppingCartRepositoryPort;
    private final ProductRepositoryPort productRepositoryPort;

    public AddCartItemService(ShoppingCartRepositoryPort shoppingCartRepositoryPort, ProductRepositoryPort productRepositoryPort) {
        this.shoppingCartRepositoryPort = shoppingCartRepositoryPort;
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public ShoppingCartDomain addCartItemUseCase(CartItemCommand cartItemCommand) {
        ShoppingCartDomain shoppingCartDomain = shoppingCartRepositoryPort.retrieveShoppingCart(cartItemCommand.userId());


        CartItemDetails cartItemDetails=  cartItemCommand.cartItemDetails();
        ProductDomain productDomain = productRepositoryPort.retrieveProductById(cartItemCommand.productId());

        CartItemDomain cartItemDomain = new CartItemDomain(0L,productDomain,cartItemCommand.cartItemDetails());
        //Before  adding the item it will remove the item with the same CartItemDetails
        shoppingCartDomain.getItems().removeIf((CartItemDomain item)-> switch (productDomain.getProductType()) {
              case PAINTING -> {
                  assert cartItemDetails instanceof PaintingItemDetails;
                  PaintingItemDetails paintingItemDetails = (PaintingItemDetails) cartItemDetails;
                  PaintingDomain paintingDomain = (PaintingDomain) productDomain;
                  PaintingItemDetails paintingItemDetail = (PaintingItemDetails) item.getDetails();
                  yield (item.getProduct().equals(paintingDomain) && paintingItemDetails.getIsOriginalSelected().getIsOriginalSelected().equals(paintingItemDetail.getIsOriginalSelected().getIsOriginalSelected()));
              }
              case CLOTHING -> {
                  assert cartItemDetails instanceof ClothingItemDetails;
                  ClothingItemDetails clothingNewItemDetails =  (ClothingItemDetails) cartItemDetails;
                  BodyClothingDomain bodyClothingDomain = (BodyClothingDomain) productDomain;
                  ClothingItemDetails clothingActualItemDetails = (ClothingItemDetails) item.getDetails();
                  yield (item.getProduct().equals(bodyClothingDomain) && clothingActualItemDetails.getClothingSizeEnum().equals(clothingNewItemDetails.getClothingSizeEnum()) &&  clothingActualItemDetails.getColor().equals(clothingNewItemDetails.getColor()) );
              }
              case PRINT,   CUP -> false;
          });

        shoppingCartDomain.addItem(cartItemDomain);
        return shoppingCartRepositoryPort.updateShoppingCart(cartItemCommand.userId(), shoppingCartDomain);
    }




}

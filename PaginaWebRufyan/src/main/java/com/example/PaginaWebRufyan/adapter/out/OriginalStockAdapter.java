

package com.example.PaginaWebRufyan.adapter.out;

import jakarta.persistence.Embeddable;
import lombok.*;

@Setter
@Getter
@Embeddable
@ToString
public class OriginalStockAdapter implements StockManager {
    private Integer stockCopies;
    private final Integer copiesMade;
    private Boolean isOriginalAvailable;
    // agregar una obra al carrito no la apartará
    //private Boolean isInCart = false;


    public OriginalStockAdapter(Integer stockCopies, Integer copiesMade, Boolean isOriginalAvailable){
        this.stockCopies = stockCopies;
        this.copiesMade = copiesMade;
        this.isOriginalAvailable= isOriginalAvailable;

    }

    public OriginalStockAdapter(Integer stockCopies, Integer copiesMade){
        this.stockCopies = stockCopies;
        this.copiesMade = copiesMade;
        this.isOriginalAvailable= false;
         }



         /*
    @Override
    public void decreaseStock(Product product, Map<String, String> additionalFeatures) {
        if (Boolean.valueOf(additionalFeatures.get("isOriginalSelected"))   && isOriginalAvailable && !isInCart ){
            setIsOriginalAvailable(false);
        }
        else {

            int quantity =1;
            if(additionalFeatures.containsKey("quantity")){
                quantity= Integer.parseInt(additionalFeatures.get("quantity"));
            }

            if(stockCopies> quantity){
            setStockCopies(getStockCopies()-quantity);
            } else throw  new NoStockException("User ask for: "+ quantity+ " but there are just: "+ stockCopies);

        }
    }

    @Override
    public void increaseStock(Product product, Map<String, String> additionalFeatures) {
        if (Boolean.valueOf(additionalFeatures.get("isOriginalSelected"))   ){
            setIsOriginalAvailable(true);
        }
        else {
            setStockCopies(getStockCopies()+Integer.parseInt(additionalFeatures.get("quantity")));
        }
    }

    @Override
    public Object getStockInfo() {
        Map<String, Object> infoResponse = new HashMap<>();
        infoResponse.put("isOriginalAvailable",isOriginalAvailable);
        infoResponse.put("stockCopies",stockCopies);
       //String message =  String.valueOf("is original available: "+ String.valueOf(isOriginalAvailable) + "/n" +"Is in cart: "+ String.valueOf(isInCart)+"/n"+ "Available copies: " + stockCopies  );
       //infoResponse.put("ResponseMessage", message);

       return infoResponse;

    }

    @Override
    public Map<String, Object> getStockMap() {
       Object response = getStockInfo();
       Map<String,Object>  mapResponse;
       if(response instanceof Map<?,?>) {
           mapResponse = (Map<String, Object>) response;
           return mapResponse;
       }else throw new RuntimeException("Get stockInfo does not return a Map type");

    }
    */


}

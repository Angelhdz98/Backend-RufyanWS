package com.example.PaginaWebRufyan.Products.Entity;
import com.example.PaginaWebRufyan.adapter.out.OriginalStockAdapter;
import com.example.PaginaWebRufyan.adapter.out.PaintingPriceManager;
import com.example.PaginaWebRufyan.adapter.out.persistence.Product;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.PaintingSpecs;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@DiscriminatorValue("PAINTING")
@Entity
public class Painting extends Product {



	@Builder.Default
	private PaintingPriceManager priceManager= new PaintingPriceManager();
	@Builder.Default
	private OriginalStockAdapter stockManager = new OriginalStockAdapter(1,1,false) ;

	 public static final Integer minHeightCm = 15;
	 public static final Integer minLargeCm= 15;
	 public static final BigDecimal minPrice = new BigDecimal(750);
	 public static final BigDecimal minPricePerCopy= new BigDecimal(300);

	//original ownew se manejará con una clase aparte
	/*@ManyToOne(cascade = CascadeType.REFRESH)
	private UserEntity originalOwner;
	 */


/*
	public static void checkConsistentData(ProductUpdateRegisterDTO productUpdateRegisterDTO) {
		String message ="Problems with product: /n";
		Map<String, Object> priceManage = productUpdateRegisterDTO.getPriceManage();
		Map<String, Object> stockManage = productUpdateRegisterDTO.getStock();
		if( stockManage!=null&&
				priceManage!=null&&
				stockManage.containsKey("stockCopies") &&
				stockManage.containsKey("isOriginalAvailable") &&
				stockManage.containsKey("copiesMade")&&
				stockManage.containsKey("isInCart")&&
				priceManage.containsKey("pricePerCopy") &&
			priceManage.containsKey("pricePerOriginal"))

		{
			Integer stockCopies =(Integer) stockManage.get("stockCopies");
			Integer copiesMade = (Integer) stockManage.get("copiesMade");
			if(stockCopies>copiesMade){
				throw  new InconsitentDataException("No puede haber más obras en stock de las que se hicieron");
			}
			Boolean isOriginalAvailable = (Boolean) stockManage.get("isOriginalAvailable");
			Boolean isInCart = (Boolean) stockManage.get("isInCart");
			BigDecimal pricePerCopy = (BigDecimal) priceManage.get("pricePerCopy");
			BigDecimal pricePerOriginal=(BigDecimal) priceManage.get("pricePerOriginal");

			if(pricePerOriginal.compareTo(Painting.minPrice)<0 || pricePerCopy.compareTo(Painting.minPricePerCopy)<0){
				throw new InconsitentDataException("Los precios son más bajos de los minimos establescidos \n original: "+ Painting.minPrice + "\n copia: "+ Painting.minPricePerCopy);
			}


		} else{
			throw new InputMismatchException("El producto no tiene no los datos necesarios para iniciar components PriceManager y StockManager");
		}
	}

 */

}
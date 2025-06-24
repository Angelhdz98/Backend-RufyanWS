package com.example.PaginaWebRufyan.Entity;
import com.example.PaginaWebRufyan.Components.OriginalStock;
import com.example.PaginaWebRufyan.Components.PaintingPriceManager;
import com.example.PaginaWebRufyan.DTO.PaintingRegisterDTO;
import com.example.PaginaWebRufyan.DTO.ProductUpdateRegisterDTO;
import com.example.PaginaWebRufyan.Exceptions.InconsitentDataException;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.assertj.core.internal.BooleanArrays;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@DiscriminatorValue("PAINTING")
@Entity
public class Painting extends Product {
	
	private Integer alturaCm;
	private Integer largoCm;
	private String medium;
	private String supportMaterial;


	@Builder.Default
	private PaintingPriceManager priceManager= new PaintingPriceManager();
	@Builder.Default
	private OriginalStock stockManager = new OriginalStock(1,1,false) ;

	 public static final Integer minHeightCm = 15;
	 public static final Integer minLargeCm= 15;
	 public static final BigDecimal minPrice = new BigDecimal(750);
	 public static final BigDecimal minPricePerCopy= new BigDecimal(300);

	@ManyToOne(cascade = CascadeType.REFRESH)
	private UserEntity originalOwner;



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
}
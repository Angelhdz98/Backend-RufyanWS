package com.example.PaginaWebRufyan.Controller;

//import java.net.URI;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.PaginaWebRufyan.DTO.PaintingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.PaginaWebRufyan.Entity.Image;
import com.example.PaginaWebRufyan.Entity.Painting;
import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.Service.ImageService;
import com.example.PaginaWebRufyan.Service.PaintingService;
import com.example.PaginaWebRufyan.Service.ProductsCategoryService;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
@Validated
@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@PreAuthorize("permitAll()")
public class PaintingController {
	@Autowired
	private PaintingService paintingService;
	@Autowired
	private ImageService imageService;
	@Autowired 
	private ProductsCategoryService categoryService;
	
	Painting examplePainting = Painting.builder().build();
	
	final int alturaMin = Painting.minHeightCm;
	
	final int largoMin = Painting.minLargeCm;
	
	final BigDecimal originalPriceMin = Painting.minPrice;
	
	final BigDecimal copyPriceMin = Painting.minPricePerCopy;
	
	// @Value("${file.upload-dir}")
	 //private  String uploadDir;
	
	@GetMapping("/paintings")
	@PreAuthorize("permitAll()")
	public ResponseEntity<List<PaintingDTO>>  retrieveAllPaintings(){
		List<PaintingDTO> allPaintings =  paintingService.findAll();
		return ResponseEntity.ok(allPaintings);
	}
	
	@GetMapping("/paintings/favorites")
	public ResponseEntity<List<PaintingDTO>> retrieveFavoritePaintings(){
		 return ResponseEntity.ok(paintingService.findFavoritePaintings());
	}
	
	/*
	 * HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		return new  ResponseEntity<>(paintingService.findFavoritePaintings(), headers, HttpStatus.OK);
	 */
	@GetMapping("/paintings/{id}")
	public ResponseEntity<PaintingDTO> retrievePainting(@PathVariable @Positive(message = "El id debe de ser un numero positivo") int id) {
			    return ResponseEntity.ok(paintingService.retrievePaintingById(id));
		
	}
	/*
	//This method is Replaced with ProductFactory
	@Transactional
	@PostMapping("/paintings/create")
	public ResponseEntity<Painting> uploadPainting( @RequestParam("altura_cm") @NotNull @Min(20) Integer altura_cm,
													@RequestParam("available_copies")@NotNull @Min(0) Integer available_copies,
													@RequestParam("copies_made") @NotNull @Min(0) Integer copies_made,
													@RequestParam("description") @NotNull @NotBlank @Size(max = 255, min = 10) String description,
													@RequestParam("favorite") @NotBlank @Pattern(regexp = "^(true|false)$") String favorite,
													@RequestParam("largo_cm") @NotNull @Min(14) Integer largo_cm,
													@RequestParam("medium") @NotNull String medium,
													@RequestParam("name") @NotNull @NotBlank @Size(min = 3, max =127) String name,
													@RequestParam("price") @NotNull @Min(500) Integer price,
													@RequestParam("price_copy")@NotNull @Min(200) Integer price_copy,
													@RequestParam("image")@NotNull @Size(min=1, max= 6) List<MultipartFile> imageFiles,
													@RequestParam("support_material") @NotNull @NotBlank @Size(min=3, max= 63) String support_material, 
													@RequestParam("category") @NotNull @NotBlank @Size(min=3, max=63) String category){
		
		
				
		List<Image> images = imageService.processImages(imageFiles); 
			
		
		Painting inputPainting = Painting.builder() 
				.alturaCm(altura_cm)
				.availableCopies(available_copies)
				.category(categoryService.retrieveCategoryByName(category).orElseThrow(()->new ResourceNotFoundException("No se encontró la categoría: " +category))) 
				.copiesMade(copies_made)
				.description(description) 
				.isFavorite(Boolean.valueOf(favorite))
				.image(images) 
				.largoCm(largo_cm)
				.medium(medium) 
				.name(name) 
				.price(price) 
				.pricePerCopy(price_copy)
				.supportMaterial(support_material).build();
		
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("Cache-Control","no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		
		
		return new ResponseEntity<>(paintingService.save(inputPainting), headers, HttpStatus.OK);		
		
		}
		*/
	/*
	 * 
	 */

	/*
	@Transactional
	@PutMapping("/paintings/{id}")
	public ResponseEntity<Painting> updatePainting(	@PathVariable Integer id, 
													@RequestParam Integer altura_cm,
													@RequestParam Integer available_copies,
													@RequestParam String category,
													@RequestParam Integer copies_made,
													@RequestParam String description,
													@RequestParam Boolean favorite,
													@RequestParam Integer largo_cm,
													@RequestParam String medium,
													@RequestParam String name,
													@RequestParam Integer price,
													@RequestParam Integer price_copy,
													@RequestParam(value="imageFiles", required = false) List<MultipartFile> imageFiles,
													@RequestParam String support_material){
		Optional<Painting> paintingToUpdate= paintingService.findById(id); 
		if (paintingToUpdate.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			
			List<Image> allImages = paintingToUpdate.get().getImage();
			if (imageFiles!=null && !imageFiles.isEmpty()){
				List<Image> procesedImages=imageService.processImages(imageFiles);
				allImages.addAll(procesedImages);
			}
			
			
			Painting painting = Painting.builder() 
					.alturaCm(altura_cm)
					.availableCopies(available_copies)
					.category(categoryService.retrieveCategoryByName(category)
							.orElseThrow(()->new ResourceNotFoundException("No se encontró la categoría: "+category))) 
					.copiesMade(copies_made)
					.description(description) 
					.isFavorite(favorite)
					.image(allImages) 
					.largoCm(largo_cm)
					.medium(medium) 
					.name(name) 
					.price(price) 
					.pricePerCopy(price_copy)
					.supportMaterial(support_material).build();
			
			 Painting updatedPainting = paintingService.updatePaintingById(id, painting);
			 
			return ResponseEntity.ok(updatedPainting);	
		}
		
	}

	 */

	@Transactional
	@DeleteMapping("/paintings/{paintingId}/{imageId}")
	public ResponseEntity<PaintingDTO> deleteImageFromPainting(@PathVariable Integer paintingId, @PathVariable Integer imageId){

	imageService.deleteImageById(imageId);
	return ResponseEntity.ok(paintingService.retrievePaintingById(paintingId));
	}
	@Transactional
	@DeleteMapping("/paintings/{id}")
	public ResponseEntity<Void> deletePaintingById(@PathVariable Integer id ) {
		paintingService.deletePaintingByid(id);
		return ResponseEntity.ok().build();


	}
	
	
}
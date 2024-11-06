package com.example.PaginaWebRufyan.Controller;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
//import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.PaginaWebRufyan.Entity.Image;
import com.example.PaginaWebRufyan.Entity.Painting;
import com.example.PaginaWebRufyan.Entity.ProductsCategory;
import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.Service.ImageService;
import com.example.PaginaWebRufyan.Service.PaintingService;
import com.example.PaginaWebRufyan.Service.ProductsCategoryService;
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
	
	
	 private final String UPLOAD_DIR = "src/main/resources/static";
	
	@GetMapping("/paintings")
	@PreAuthorize("permitAll()")
	public List<Painting> retrieveAllPaintings(){
		return paintingService.findAll(); 
	}
	
	@GetMapping("/paintings/favorites")
	public ResponseEntity<List<Painting>> retrieveFavoritePaintings(){
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
	public Painting retrievePainting(@PathVariable int id) {
		Optional<Painting> painting= paintingService.findById(id);
		return painting.get();
	}
	@PostMapping("/paintings/create")
	public ResponseEntity<Painting> uploadPainting( @RequestParam("altura_cm") Integer altura_cm,
													@RequestParam("available_copies") Integer available_copies,
													@RequestParam("copies_made") Integer copies_made,
													@RequestParam("description") String description,
													@RequestParam("favorite") String favorite,
													@RequestParam("largo_cm") Integer largo_cm,
													@RequestParam("medium") String medium,
													@RequestParam("name") String name,
													@RequestParam("price") Integer price,
													@RequestParam("price_copy") Integer price_copy,
													@RequestParam("image") List<MultipartFile> imageFiles,
													@RequestParam("support_material") String support_material, 
													@RequestParam("category") String category){
		
		
				
		List<Image> images = imageFiles.stream().map((file)->{
			try {
				// Primero se guarda el archivo en el sistema de archivo
				String fileName =System.currentTimeMillis()+ "_"+ file.getOriginalFilename();
				Path filePath = Paths.get(UPLOAD_DIR +"/UploadedImages/UploadedPaintingImages");
				if(!Files.exists(filePath)) {
					Files.createDirectories(filePath);
				}
				Path savedFilePath = filePath.resolve(fileName);
				Files.copy(file.getInputStream(),savedFilePath, StandardCopyOption.REPLACE_EXISTING);
				//Files.write(filePath, file.getBytes());
				// Agregamos el path del archivo a la image
				Image image = Image.builder() 
								   .url("http://localhost:8080/UploadedImages/UploadedPaintingImages/"+fileName) 
								  .productName(file.getOriginalFilename()).build();
				
				return imageService.saveImage(image);
			}
			catch(IOException e) {
				throw new RuntimeException();
			}
			
		}).collect(Collectors.toList());
		

		
		Painting inputPainting = Painting.builder() 
				.altura_cm(altura_cm) 
				.available_copies(available_copies) 
				.category(categoryService.retrieveCategoryByName(category).orElseThrow(()->new ResourceNotFoundException("No se encontró la categoría: " +category))) 
				.copies_made(copies_made) 
				.description(description) 
				.favorite(Boolean.valueOf(favorite)) 
				.image(images) 
				.largo_cm(largo_cm) 
				.medium(medium) 
				.name(name) 
				.price(price) 
				.price_copy(price_copy) 
				.support_material(support_material).build();
		
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("Cache-Control","no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		
		return new ResponseEntity<>(paintingService.save(inputPainting), headers, HttpStatus.OK);		
		
		}
		
	/*
	 * 
	 */
		
	
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
				List<Image> images = imageFiles.stream().map((file)->{
				try {
					// Primero se guarda el archivo en el sistema de archivo
					String fileName =System.currentTimeMillis()+ "_"+ file.getOriginalFilename();
					Path filePath = Paths.get(UPLOAD_DIR +"/UploadedImages/UploadedPaintingImages");
					if(!Files.exists(filePath)) {
						
						Files.createDirectories(filePath);
					}
					Path savedFilePath = filePath.resolve(fileName);
					
					Files.copy(file.getInputStream(),savedFilePath, StandardCopyOption.REPLACE_EXISTING);
					//Files.write(filePath, file.getBytes());
					// Agregamos el path del archivo a la image
					Image image = Image.builder() 
									   .url("http://localhost:8080/UploadedImages/UploadedPaintingImages/"+fileName) 
									  .productName(file.getOriginalFilename()).build();
					
					return imageService.saveImage(image);
				}
				catch(IOException e) {
					throw new RuntimeException();
				}
				
			}).collect(Collectors.toList());
			
			allImages.addAll(images);
			}
			
			
			
			
			
			Painting painting = Painting.builder() 
					.altura_cm(altura_cm) 
					.available_copies(available_copies) 
					.category(categoryService.retrieveCategoryByName(category)
							.orElseThrow(()->new ResourceNotFoundException("No se encontró la categoría: "+category))) 
					.copies_made(copies_made) 
					.description(description) 
					.favorite(Boolean.valueOf(favorite)) 
					.image(allImages) 
					.largo_cm(largo_cm) 
					.medium(medium) 
					.name(name) 
					.price(price) 
					.price_copy(price_copy) 
					.support_material(support_material).build();
			
			 Painting updatedPainting = paintingService.updatePaintingById(id, painting);
			 
			return ResponseEntity.ok(updatedPainting);	
		}
		
	}
	
	@DeleteMapping("/paintings/{paintingId}/{imageId}")
	public ResponseEntity<Painting> deleteImageFromPainting(@PathVariable Integer paintingId, @PathVariable Integer imageId){
			Optional<Painting> optionalOwnerPainting = paintingService.findById(paintingId);
			if(optionalOwnerPainting.isPresent()) {
				Painting ownerPainting = optionalOwnerPainting.get();
				ownerPainting.setImage(ownerPainting.getImage().stream()
						.filter(image->!image.getId().equals(imageId))
						.collect(Collectors.toList()));
				
				paintingService.updatePaintingById(paintingId, ownerPainting);
				paintingService.deleteImage(imageId);
				
				return ResponseEntity.ok(ownerPainting);
			}
			else return ResponseEntity.badRequest().build();
		
		
	}
	
	@DeleteMapping("/paintings/{id}")
	public void deletePaintingById(@PathVariable Integer id ) {
		Optional<Painting> painting = paintingService.findById(id);
		if(painting.isPresent()) {
			painting.get().getImage().forEach(image ->{
				imageService.deleteImageById(image.getId());
			});	
		}
		
		paintingService.deletePaintingByid(id);
	}
	
	
}
package com.example.PaginaWebRufyan.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.PaginaWebRufyan.Entity.Image;
import com.example.PaginaWebRufyan.Entity.Painting;
import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.Repository.ImageRepository;
import com.example.PaginaWebRufyan.Repository.PaintingRepository;

import jakarta.transaction.Transactional;

@Service
public class PaintingService {

	@Autowired
	private PaintingRepository paintingRepository;
	@Autowired
	private ImageRepository imageRepository;
	  private final String UPLOAD_DIR = "C://Users//PP//Documents//Proyectos_Programación//Backends//Back_end_rufyan//PaginaWebRufyan//PaginaWebRufyan//src//main//resources//static";
//			  "C:/Users/PP/Documents/Proyectos Programación/Backends/Back-end rufyan/PaginaWebRufyan/PaginaWebRufyan/src/main/resources/static/";
//
	  
	
	public List<Painting> findAll(){
		return paintingRepository.findAll();
	}
	
	public Optional<Painting> findById(Integer id){
		return paintingRepository.findById(id);
	}
	public Optional<Painting> findPaintingByName(String name){
		return paintingRepository.findByName(name);
	}

	public List<Painting> findFavoritePaintings(){
		Painting examplePainting = new Painting();
		examplePainting.setFavorite(true);
		Example<Painting> example= Example.of(examplePainting);
		return paintingRepository.findAll(example);
	}
	
	@Transactional
	public Painting updatePaintingById(Integer id, Painting paintingData) {
		Optional<Painting>optionalPainting = paintingRepository.findById(id);
		if(optionalPainting.isPresent()) {
			 paintingData.setId(id);
			Painting painting= optionalPainting.get();
			
			painting.setAltura_cm(paintingData.getAltura_cm());
			painting.setAvailable_copies(paintingData.getAvailable_copies());
			painting.setCategory(paintingData.getCategory());
			painting.setCopies_made(paintingData.getCopies_made());
			painting.setCreation_date(paintingData.getCreation_date());
			painting.setDescription(paintingData.getDescription());
			painting.setFavorite(paintingData.getFavorite());
			painting.setId(paintingData.getId());
			//painting.setImage(paintingData.getImage());
			painting.setLargo_cm(paintingData.getLargo_cm());
			painting.setMedium(paintingData.getMedium());
			painting.setName(paintingData.getName());
			painting.setPrice(paintingData.getPrice());
			painting.setSupport_material(paintingData.getSupport_material());
			painting.setAdittionalFeatures(paintingData.getAdittionalFeatures());
			painting.setPrice_copy(paintingData.getPrice_copy());
			painting.setStyle(paintingData.getStyle());
			painting.setSupport_material(paintingData.getSupport_material());
			
			//			painting.setCopyBuyers(paintingData.getCopyBuyers());
			//painting.setOriginalOwner(paintingData.getOriginalOwner());
			
			
			paintingData.getImage().forEach(image->{
				
				/* I want to check images if exist
				Example<Image> imageExample = Example.of(image);
				Optional<Image> imageToCheck = imageRepository.findOne( imageExample);
				if(imageToCheck.isEmpty()) {
					imageRepository.save(image);
				}
					*/
				
				imageRepository.save(image);
			});
			return paintingRepository.save(paintingData);
		}
		else {
			throw new ResourceNotFoundException("Painting no found with id: "+id);
		}
	}
	
	
@Transactional
	public Painting save(Painting painting) {

		return paintingRepository.save(painting);
	}

	public Painting saveWithImages(Painting painting, List<MultipartFile> imageFiles ) throws IOException {
		
		List<Image> images = imageFiles.stream().map((file)->{
			try {
				// Primero se guarda el archivo en el sistema de archivo
				String fileName = file.getOriginalFilename() + "_"+ System.currentTimeMillis();
				Path filePath = Paths.get(UPLOAD_DIR);
				if(!Files.exists(filePath)) {
					Files.createDirectories(filePath);
				}
				Path savedFilePath = filePath.resolve(fileName);
				Files.copy(file.getInputStream(),savedFilePath, StandardCopyOption.REPLACE_EXISTING);
				//Files.write(filePath, file.getBytes());
				// Agregamos el path del archivo a la image
				Image image = Image.builder() 
								   .url(savedFilePath.toString()) 
								  .productName(file.getOriginalFilename()).build();
				return imageRepository.save(image);
			}
			catch(IOException e) {
				throw new RuntimeException("Failed to store file"+ file.getOriginalFilename());
			}
			
		}).collect(Collectors.toList());
		
		painting.setImage(images);
		
		return paintingRepository.save(painting);
		
		
	}

	public void deletePaintingByid(Integer id) {
		paintingRepository.deleteById(id);
	}

	public void deleteImage(Integer imageId) {
		// TODO Auto-generated method stub
		imageRepository.deleteById(imageId);
		
	
		
		
	}
	
}
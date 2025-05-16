package com.example.PaginaWebRufyan.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.PaginaWebRufyan.Entity.Painting;
import com.example.PaginaWebRufyan.Exceptions.AlreadyExistIdenticatorException;
import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.Repository.ImageRepository;
import com.example.PaginaWebRufyan.Repository.PaintingRepository;

@Service
public class PaintingService {

	@Autowired
	private PaintingRepository paintingRepository;
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private ImageService imageService;
	  
//			  "C:/Users/PP/Documents/Proyectos Programación/Backends/Back-end rufyan/PaginaWebRufyan/PaginaWebRufyan/src/main/resources/static/";
//
	
	
	
	public Optional<Painting> findById(Integer id){
		return paintingRepository.findById(id);
	}
	public Optional<Painting> findPaintingByName(String name){
		return paintingRepository.findByName(name);
	}
	
	public List <Painting> findPaintingsSortedAndPaged(String searchTerm){
		
		List<Painting> paintingList = List.of();
		return paintingList;
	}

	public List<Painting> findFavoritePaintings(){
		Painting examplePainting = new Painting();
		examplePainting.setIsFavorite(true);
		Example<Painting> example= Example.of(examplePainting);
		return paintingRepository.findAll(example);
	}
	
	
	public Painting updatePaintingById(Integer id, Painting paintingData) {
		Optional<Painting>optionalPainting = paintingRepository.findById(id);
		if(optionalPainting.isPresent()) {
			 paintingData.setId(id);
			Painting painting= optionalPainting.get();
			
			painting.setAlturaCm(paintingData.getAlturaCm());
			painting.setAvailableCopies(paintingData.getAvailableCopies());
			painting.setCategory(paintingData.getCategory());
			painting.setCopiesMade(paintingData.getCopiesMade());
			painting.setCreationDate(paintingData.getCreationDate());
			painting.setDescription(paintingData.getDescription());
			painting.setIsFavorite(paintingData.getIsFavorite());
			painting.setId(paintingData.getId());
			painting.setImage(paintingData.getImage());
			painting.setLargoCm(paintingData.getLargoCm());
			painting.setMedium(paintingData.getMedium());
			painting.setName(paintingData.getName());
			painting.setPrice(paintingData.getPrice());
			painting.setSupportMaterial(paintingData.getSupportMaterial());
			painting.setAdditionalFeatures(paintingData.getAdditionalFeatures());
			painting.setPricePerCopy(paintingData.getPricePerCopy());
			painting.setStyle(paintingData.getStyle());
			painting.setSupportMaterial(paintingData.getSupportMaterial());
			
			//			painting.setCopyBuyers(paintingData.getCopyBuyers());
			//painting.setOriginalOwner(paintingData.getOriginalOwner());
			/* I want to check images if exist
			Example<Image> imageExample = Example.of(image);
			Optional<Image> imageToCheck = imageRepository.findOne( imageExample);
			if(imageToCheck.isEmpty()) {
				imageRepository.save(image);
			}
				*/
			/*
			if(paintingData.getImage() ==null || !paintingData.getImage().isEmpty() ) {
				paintingData.getImage().forEach(image->{
				
				
				
				imageRepository.save(image);
			});
			}
			*/
			
			return paintingRepository.save(paintingData);
		}
		else {
			throw new ResourceNotFoundException("Painting no found with id: "+id);
		}
	}
	
	


	public Painting save(Painting painting) throws AlreadyExistIdenticatorException {
		if(paintingRepository.existsByName(painting.getName())) {
			throw new AlreadyExistIdenticatorException("El nombre ya existe en otra obra");
		}
		return paintingRepository.save(painting);
	}


// this function is not used, controller does the the repository. save 
	/*
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
*/

	
	public void deletePaintingByid(Integer id) {
		paintingRepository.deleteById(id);
	}

	public void deleteImage(Integer imageId) {
		// TODO Auto-generated method stub
		imageRepository.deleteById(imageId);
		
	
		
		
	}
	public List<Painting> findAll() {
		// TODO Auto-generated method stub
		return paintingRepository.findAll();
	}

}
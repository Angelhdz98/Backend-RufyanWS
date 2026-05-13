package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.Image.Image;
import com.example.PaginaWebRufyan.Products.Repository.ProductsRepository;
import com.example.PaginaWebRufyan.adapter.out.persistence.Product;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ImageDomain;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ImageMapper {

    private final ProductsRepository productsRepository;

    public ImageMapper(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public  ImageDomain toDomain(Image image){
    return new ImageDomain(image.getId(), image.getProductName(), image.getUrl());
    }

    public Image toEntity(ImageDomain imageDomain, Product product){
        return new Image(imageDomain.id(), product, imageDomain.productName(), imageDomain.url());
    }

    public Image toEntity(ImageDomain imageDomain, Long productId){
        Optional<Product> optionalProductById = productsRepository.findById(productId);
        if(optionalProductById.isEmpty()){
            throw new ResourceNotFoundException("No existe el producto con el id:"+ productId);
        }
    return new Image(imageDomain.id(), optionalProductById.get(), imageDomain.productName(), imageDomain.url()  );
    }

}

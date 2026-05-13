package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import com.example.PaginaWebRufyan.domain.model.BodyClothingDomainDetails;
import com.example.PaginaWebRufyan.domain.model.PaintingDomainDetails;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "productTypeEnum"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PaintingDomainDetails.class, name = "PAINTING"),
        @JsonSubTypes.Type(value = BodyClothingDomainDetails.class, name = "CLOTHING")
})
public abstract class ProductDomainDetails {
    public abstract void validate();
}

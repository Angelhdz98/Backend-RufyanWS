package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "pricingType",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PaintingPricingDTO.class, name = "ORIGINAL"),
        @JsonSubTypes.Type(value = SinglePricingDTO.class, name = "SINGLE"),
        @JsonSubTypes.Type(value = SinglePricingDTO.class, name = "SIMPLE"),
})
public interface ProductPricingDTO {
}

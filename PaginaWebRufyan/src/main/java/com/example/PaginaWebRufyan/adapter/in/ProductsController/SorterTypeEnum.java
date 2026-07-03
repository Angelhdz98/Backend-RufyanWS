package com.example.PaginaWebRufyan.adapter.in.ProductsController;

public enum SorterTypeEnum {
    CREATION_DATE("creationDate"),
    PRICE("price"),
    NAME("name");
    private final String value;
    SorterTypeEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
    // MeTodo auxiliar para converir desde string
    public static SorterTypeEnum fromValue(String value) {
        for (SorterTypeEnum type : SorterTypeEnum.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Valor inválido: " + value);
    }
}
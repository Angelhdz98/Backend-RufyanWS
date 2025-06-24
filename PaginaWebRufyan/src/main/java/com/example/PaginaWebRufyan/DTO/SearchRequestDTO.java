package com.example.PaginaWebRufyan.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SearchRequestDTO {
String searchTerm;
Integer itemsPerPage;
Integer pageNumber;
String sortBy;
}

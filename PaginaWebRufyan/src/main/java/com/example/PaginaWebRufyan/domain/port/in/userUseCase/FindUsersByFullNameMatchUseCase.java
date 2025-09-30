package com.example.PaginaWebRufyan.domain.port.in.userUseCase;

import com.example.PaginaWebRufyan.domain.model.UserDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindUsersByFullNameMatchUseCase {

    Page<UserDomain> findUsersByFullNameMatch(String namePart, Pageable pageable);
}

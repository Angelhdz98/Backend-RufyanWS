package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.adapter.out.persistence.LikeEntity;
import com.example.PaginaWebRufyan.domain.model.LikeDomain;

public class LikeMapper {

    public static LikeEntity mapToEntity(LikeDomain domain){
        return new LikeEntity(domain.getId(), domain.getUserId(), domain.getProductId());
    }

    public static LikeDomain mapToDomain(LikeEntity entity){
        return new LikeDomain(entity.getId(), entity.getUserId(), entity.getProductId());
    }


}

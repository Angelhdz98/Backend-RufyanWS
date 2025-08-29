package com.example.PaginaWebRufyan.Returns.Controller;

import com.example.PaginaWebRufyan.Returns.DTO.ReturnRequestDTO;
import com.example.PaginaWebRufyan.Returns.ReturnOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReturnController {
    @PostMapping
    ResponseEntity<ReturnOrder> createReturnRequest(@RequestBody ReturnRequestDTO returnRequestDTO){
        return ResponseEntity.ok().build();
    }
    @DeleteMapping
    ResponseEntity<Void> deleteRequest(){

        return ResponseEntity.ok().build();

    }

}

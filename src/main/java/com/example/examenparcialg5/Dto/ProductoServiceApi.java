package com.example.examenparcialg5.Dto;


import com.example.examenparcialg5.Entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductoServiceApi {

    Page<Producto> getAll(Pageable pageable);
    Page<Producto> getEver(String search, Pageable pageable);




}
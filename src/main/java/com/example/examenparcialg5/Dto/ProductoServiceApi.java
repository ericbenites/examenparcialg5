package com.example.examenparcialg5.Dto;


import com.example.examenparcialg5.Entity.Fotos;
import com.example.examenparcialg5.Entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ProductoServiceApi {

    Page<Producto> getAll(Pageable pageable);
    Page<Producto> getEver(String search, Pageable pageable);
    void saveImage(MultipartFile imageFile, Fotos foto) throws Exception;




}
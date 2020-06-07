package com.example.examenparcialg5.Dao;

import com.example.examenparcialg5.Entity.Fotos;
import org.springframework.web.multipart.MultipartFile;


public interface IFotoDao {
    void saveFotoImage(Fotos foto , MultipartFile imageFile) throws Exception;
    void save(Fotos foto);

}

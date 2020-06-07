package com.example.examenparcialg5.Dao;

import com.example.examenparcialg5.Entity.Fotos;
import com.example.examenparcialg5.Repository.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FotoDao implements IFotoDao {
    @Autowired
    FotoRepository fotoRepository;

    @Override
    public void saveFotoImage(Fotos foto, MultipartFile imageFile) throws Exception {
        Path currentPath = Paths.get(".");
        Path absolutePath = currentPath.toAbsolutePath();
        foto.setPath(absolutePath + "/src/main/resources/static/img/productoFotos");
        byte[] bytes = imageFile.getBytes();
        Path path = Paths.get(foto.getPath() + imageFile.getOriginalFilename());
        Files.write(path, bytes);
    }

    @Override
    public void save(Fotos foto) {
        fotoRepository.save(foto);

    }
}

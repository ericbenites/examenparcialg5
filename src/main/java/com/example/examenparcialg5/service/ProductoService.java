package com.example.examenparcialg5.service;




import com.example.examenparcialg5.Dto.ProductoServiceApi;
import com.example.examenparcialg5.Entity.Fotos;
import com.example.examenparcialg5.Entity.Producto;
import com.example.examenparcialg5.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ProductoService implements ProductoServiceApi {



    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Page<Producto> getAll(Pageable pageable) {
        return productoRepository.findAll( pageable);
    }

    @Override
    public Page<Producto> getEver(String search, Pageable pageable) {
        return productoRepository.obtenerFiltroProducto(search, pageable);
    }

    @Override
    public void saveImage(MultipartFile imageFile, Fotos foto) throws Exception {
        Path currentPath = Paths.get(".");
        Path absolutePath = currentPath.toAbsolutePath();
        foto.setPath(absolutePath + "/src/main/resources/static/img/productoFotos/");
        byte[] bytes = imageFile.getBytes();
        Path path = Paths.get(foto.getPath() + imageFile.getOriginalFilename());
        Files.write(path, bytes);
    }





}

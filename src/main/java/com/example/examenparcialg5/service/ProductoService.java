package com.example.examenparcialg5.service;




import com.example.examenparcialg5.Dao.FotoDao;
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

import com.example.examenparcialg5.Dao.IFotoDao;
@Component
public class ProductoService implements ProductoServiceApi {

    FotoDao fotoDao;


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
        fotoDao.saveFotoImage(foto , imageFile);
        fotoDao.save(foto);
    }





}

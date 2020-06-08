package com.example.examenparcialg5.Repository;


import com.example.examenparcialg5.Entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Integer> {


    @Query(value = "SELECT * FROM parcialsw2.producto\n" +
            "where nombreproducto like'1?%' or codigoproducto like '1?%'",
            countQuery = "SELECT count(*) FROM producto);"
            , nativeQuery = true)
    Page<Producto> obtenerFiltroProducto(String search, Pageable pageable);



}

package com.example.examenparcialg5.Repository;

import com.example.examenparcialg5.Entity.Pedidos;
import com.example.examenparcialg5.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository <Pedidos, Integer> {

    @Query(value = "SELECT sum(totalpagado) as totalfacturado FROM parcialsw2.pedidos;" , nativeQuery = true)
    int  obtenertotalfacturado();

    //falto agregar la columna de cantidad por produc


}

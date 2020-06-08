package com.example.examenparcialg5.Controllers;

import com.example.examenparcialg5.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Calendar;

@Controller
public class EstadisticaController {

    @Autowired
    PedidoRepository pedidoRepository;

    public String estadisticas (){

        long cantidadcompras = pedidoRepository.count();
        int totalfacturado = pedidoRepository.obtenertotalfacturado();



        return "gestor/estadistica";
    }

}

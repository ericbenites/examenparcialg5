package com.example.examenparcialg5.Controllers;


import com.example.examenparcialg5.Repository.UsuarioRepository;

import com.example.examenparcialg5.Entity.Producto;
import com.example.examenparcialg5.Entity.Usuario;
import com.example.examenparcialg5.Repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Controller
@RequestMapping("/usuario")
public class UsuarioController {


    @Autowired
    UsuarioRepository usuarioRepository;


@Autowired
    ProductoRepository productoRepository;
    @GetMapping("/anadirCarrito")
    public String anadirCarrito(@RequestParam("id") int idProducto, HttpSession session){
        ArrayList<Producto> productoCarrito = (ArrayList<Producto>) session.getAttribute("productosCarritoDeCompras");
        Optional<Producto> producto = productoRepository.findById(idProducto);
        productoCarrito.add(producto.get());
        session.setAttribute("productoCarritoDeCompras",productoCarrito);
        return "redirect:/producto";

    }
    @GetMapping("/comprar")
    public String preCompraJuegos(HttpSession session){
        return "user/compra";
    }





}

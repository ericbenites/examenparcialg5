package com.example.examenparcialg5.Controllers;


import com.example.examenparcialg5.Repository.UsuarioRepository;

import com.example.examenparcialg5.Entity.Producto;
import com.example.examenparcialg5.Entity.Usuario;
import com.example.examenparcialg5.Repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;




@Controller
@RequestMapping("/usuario")
public class UsuarioController {


    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping(value = {"", "/"})
    public String nuevo (@ModelAttribute("usuario") Usuario usuario){


        return "usuario/nuevo";
    }

    @GetMapping("/gestores")
    public String listagestores (Model model){
        int rol_idrol = 2;
        model.addAttribute("listaGestores", usuarioRepository.obtenerGestores(rol_idrol));
        return "usuario/lista";
    }

    //ya que no se puede importar javax.validation.* no se puede agregar el @Valid

    @PostMapping("/registrar")
    public String registrarUsuario(@ModelAttribute("usuario") Usuario usuario, Model model,
                                   RedirectAttributes attr) {

        if (usuario.getIdusuario() == 0){
            attr.addFlashAttribute("msg1", "Usuario creado exitosamente");
            usuarioRepository.save(usuario);
            return "redirect:/producto";
        }else {
            attr.addFlashAttribute("msg1", "Usuario actualizado exitosamente");
            usuarioRepository.save(usuario);
            return "redirect:/producto";
        }

    }

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


    @GetMapping("/borrar")
    public String borrar (@RequestParam("idusuario") int id, RedirectAttributes attr ){

        Optional<Usuario> borrado = usuarioRepository.findById(id);

        if (borrado.isPresent()){
            usuarioRepository.deleteById(id);
            attr.addFlashAttribute("msg1", "Borrado exitosamente");
        }

        return "producto/listar";
    }



}

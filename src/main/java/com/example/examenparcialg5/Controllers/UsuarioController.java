package com.example.examenparcialg5.Controllers;



import com.example.examenparcialg5.Entity.Usuario;
import com.example.examenparcialg5.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.examenparcialg5.Entity.Producto;
import com.example.examenparcialg5.Repository.ProductoRepository;
import com.example.examenparcialg5.Repository.UsuarioRepository;
import com.example.examenparcialg5.Entity.Producto;
import com.example.examenparcialg5.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {


    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping(value = {"", "/"})
    public String nuevo (){
        return "usuario/nuevo";
    }

    @PostMapping("/registrar")
    public String registrarUsuario() {

        return "usuario/lista";
    }
    @GetMapping("/lista")
    public String listarUsuarios(Model model){
        model.addAttribute("listaUsuarios", usuarioRepository.findAll());
        return "Usuario/lista";

    }


    @GetMapping("/editar")
    public String editarUsuario(@ModelAttribute("usuario") Usuario usuario, Model model, @RequestParam("id") int id, RedirectAttributes redirectAttributes){
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if(optionalUsuario.isPresent()){
            usuario = optionalUsuario.get();
            model.addAttribute("usuario", usuario);
            return "Usuario/form";
        } else {
            return"redirect: /usuario/listar";
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



}

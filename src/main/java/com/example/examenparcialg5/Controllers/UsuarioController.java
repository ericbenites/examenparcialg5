package com.example.examenparcialg5.Controllers;

import com.example.examenparcialg5.Entity.Usuario;
import com.example.examenparcialg5.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String registrarUsuario(){

//        usuarioRepository.registrarusuario();

        return "algunavista";
    }



}

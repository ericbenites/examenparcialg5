package com.example.examenparcialg5.Controllers;

import com.example.examenparcialg5.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping(value = {" ", "/"})
    public String nuevo (){

        return "usuario/nuevo";
    }


    @PostMapping("/registrar")
    public String registrarUsuario(){

//        usuarioRepository.registrarusuario();

        return "algunavista";
    }


}

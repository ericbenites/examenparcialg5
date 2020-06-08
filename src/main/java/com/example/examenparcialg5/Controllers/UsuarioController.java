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
            return"redirect: /usuario/lista";
        }
    }






}

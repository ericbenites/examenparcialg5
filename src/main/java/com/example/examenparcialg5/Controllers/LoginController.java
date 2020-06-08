package com.example.examenparcialg5.Controllers;


import com.example.examenparcialg5.Entity.Producto;
import com.example.examenparcialg5.Entity.Usuario;
import com.example.examenparcialg5.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class LoginController {

    @Autowired
    public LoginController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @GetMapping("/loginForm")
    public String loginForm() {
        return "login/login";
    }


    @GetMapping("/redirectByRol")
    public String redirectByRol(Authentication authentication, HttpSession session) {
        String rol = "";
        for (GrantedAuthority role : authentication.getAuthorities()) {
            rol = role.getAuthority();
            break;
        }
        String username = authentication.getName();
        Usuario usuario = usuarioRepository.findByCorreo(username);
        session.setAttribute("usuario", usuario);
        ArrayList<Producto> productosCarritoDeCompras = new ArrayList<>();
        session.setAttribute("productosCarritoDeCompras",productosCarritoDeCompras);
        Double totalPagar = null;
        session.setAttribute("totalPagar", totalPagar);

        return "redirect:/producto";


    }

}

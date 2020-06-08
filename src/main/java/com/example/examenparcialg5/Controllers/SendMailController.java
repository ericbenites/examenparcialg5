package com.example.examenparcialg5.Controllers;


import com.example.examenparcialg5.Entity.Usuario;
import com.example.examenparcialg5.Repository.UsuarioRepository;
import com.example.examenparcialg5.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;
import java.util.Random;

@Controller
@RequestMapping("/email")
public class SendMailController {

    @Autowired
    private MailService mailService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping(value = {"", "/"})
    public String index(){

        return "email/olvidarContrasena";
    }

    @PostMapping("/sendMail")
    public String sendMail(@RequestParam("email") String email, RedirectAttributes attr){

        Usuario usuario = usuarioRepository.findByCorreo(email);


        if (usuario != null){

            int id = usuario.getIdusuario();
            Optional<Usuario> user = usuarioRepository.findById(id);

            Usuario user2 = user.get();

            String caracteres = "ABCDEFGHIJKLMNPQRSTUVWXYZ";
            String randomString = "";
            int largoCaracteres = 8;

            String caracteres2 = "1234567890";
            String randomnumeros = "";
            int largoCaracteres2 = 2;

            Random random1 = new Random();
            Random random2 = new Random();

            char[] text = new char[largoCaracteres];

            for (int i =0; i < largoCaracteres; i++){
                text[i] = caracteres.charAt(random1.nextInt(caracteres.length()));
            }
            for (int i = 0; i < text.length; i++){
                randomString += text[i];
            }

            char[] text2 = new char[largoCaracteres2];

            for (int i =0; i < largoCaracteres2; i++){
                text2[i] = caracteres2.charAt(random2.nextInt(caracteres2.length()));
            }
            for (int i = 0; i < text2.length; i++){
                randomnumeros += text2[i];
            }

            String randompass = randomString + randomnumeros;

            String message = "Esta es tu nueva contraseña: " + randompass;
            String subject = "recuperar contraseña";
            mailService.sendMail("mijailmontalvo46@gmail.com",email,subject,message);

            String passw = encriptar(randompass);

            user2.setContrasena(passw);
            user2.setConfirmarcontrasena(passw);
            usuarioRepository.save(user2);

            attr.addFlashAttribute("msg", "Se le ha enviado a su correo electrónico su\n" +
                    "nueva contraseña");
            return "redirect:/email";
        }

        attr.addFlashAttribute("msg", "Debe registrarse primero");

        return "redirect:/email";
    }


    public String encriptar(String pww) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        pww = bCryptPasswordEncoder.encode(pww);
        return pww;
    }
}
package com.example.examenparcialg5.Repository;


import com.example.examenparcialg5.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    public Usuario findByCorreo(String correo);

    @Procedure(name="registrarusuario")
    String registrarusuario(@Param("idusuario") int idusuario,
                            @Param("nombre") String nombre,
                            @Param("apellido") String apellido,
                            @Param("dni") int dni,
                            @Param("correo") String correo,
                            @Param("contrasena") String contrasena,
                            @Param("confirmarcontrasena") String confirmarcontrasena,
                            @Param("enable") boolean enable,
                            @Param("rol") int rol);


}

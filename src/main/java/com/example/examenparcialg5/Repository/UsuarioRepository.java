package com.example.examenparcialg5.Repository;


import com.example.examenparcialg5.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    public Usuario findByCorreo(String correo);
}

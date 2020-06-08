package com.example.examenparcialg5.Entity;




import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
//import javax.validation.constraints.*;



@Entity
@Table(name = "usuario")
@Setter
@Getter
@NamedStoredProcedureQueries({@NamedStoredProcedureQuery(name = "registrarusuario",
        procedureName = "registrarusuario",
        parameters = {@StoredProcedureParameter(mode = ParameterMode.IN, name = "idusuario", type = int.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "nombre", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "apellido", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "dni", type = int.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "correo", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "contrasena", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "confirmarcontrasena", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "enable", type = boolean.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "rol", type = int.class)})})

public class Usuario implements Serializable {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idusuario;
    //    @NotBlank(message = "El nombre no debe ser vacío")
//    @Pattern(regexp="[a-zA-Z ]{2,40}",message = "Solo aceptan letras")
    private String nombre;
//    @NotBlank(message = "El nombre no debe ser vacío")
//    @Pattern(regexp="[a-zA-Z ]{2,40}",message = "Solo aceptan letras")
    private String apellido;
    //@NotBlank(message = "El DNI no debe ser vacío")
    //@Pattern(regexp = "[0-9]{8}" ,message = "Solo se aceptan numeros")
    private String dni;
//    @NotBlank(message = "el correo no debe ser vacio")
//@Pattern(regexp="^([a-zA-Z0-9\\-\\.\\_]+)'+'(\\@)([a-zA-Z0-9\\-\\.]+)'+'(\\.)([a-zA-Z]{2,4})$")
 //@Email(message = "ingrese un correo válido")
    private String correo;

    private String contrasena;
    private String confirmarcontrasena;
    @Column(nullable = true)
    private boolean enable;
    @ManyToOne
    @JoinColumn(name = "rol_idrol")
    private Rol rol;




    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getConfirmarcontrasena() {
        return confirmarcontrasena;
    }

    public void setConfirmarcontrasena(String confirmarcontrasena) {
        this.confirmarcontrasena = confirmarcontrasena;
    }
}

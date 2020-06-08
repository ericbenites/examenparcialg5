package com.example.examenparcialg5.Entity;



import javax.persistence.*;

@Entity
@Table(name = "fotos")
public class Fotos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfotos")
    private int idfotos ;

    private String path;
    private String filename;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "producto_idproducto")
    private Producto producto;

    public int getIdfotos() {
        return idfotos;
    }

    public void setIdfotos(int idfotos) {
        this.idfotos = idfotos;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}

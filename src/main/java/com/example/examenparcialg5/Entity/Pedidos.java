package com.example.examenparcialg5.Entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "pedidos")
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpedidos")
    private int idPedidos;

    private String codigopedido;

    private Double totalpagado;

    private Date fechacompra;

    @ManyToMany
    @JoinTable(
            name = "producto_has_pedidos",
            joinColumns = @JoinColumn(name = "pedidos_idpedidos"),
            inverseJoinColumns = @JoinColumn(name = "producto_idproducto"))
    private List<Producto> listaProductos;
    public int getIdPedidos() {
        return idPedidos;
    }

    public void setIdPedidos(int idPedidos) {
        this.idPedidos = idPedidos;
    }

    public String getCodigopedido() {
        return codigopedido;
    }

    public void setCodigopedido(String codigopedido) {
        this.codigopedido = codigopedido;
    }

    public Double getTotalpagado() {
        return totalpagado;
    }

    public void setTotalpagado(Double totalpagado) {
        this.totalpagado = totalpagado;
    }

    public Date getFechacompra() {
        return fechacompra;
    }

    public void setFechacompra(Date fechacompra) {
        this.fechacompra = fechacompra;
    }


}

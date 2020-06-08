package com.example.examenparcialg5.Controllers;

import com.example.examenparcialg5.Entity.Pedidos;
import com.example.examenparcialg5.Entity.Producto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("/pago")
public class PagoController {


    @GetMapping(value = {"","/"})
    public String ventanaPago(){return "checkout";}

    @PostMapping
    public String validarTarjeta(@RequestParam("tarjeta") String tarjeta, Model model, RedirectAttributes attr, HttpSession session, Pedidos pedidos){


        int s1 = 0, s2 = 0;

        char digitover = tarjeta.charAt(15);
        String cadena1 = tarjeta.substring(0,16);

        int digitoverificador = Integer.parseInt(String.valueOf(digitover));

        String reversa = new StringBuffer(cadena1).reverse().toString();
        int []impares = new int[15];
        for(int i = 0 ;i < reversa.length();i++){
            int digito = Character.digit(reversa.charAt(i), 15);

            if(i % 2 == 0){
                s2 = 2 * digito;
                if(s2>9){
                    s2-=9;
                    impares[i] = s2;
                    s2-=s2;
                }

            }else{
                    s1 += digito;
                }
            }
        int ab = 0;

        for (int i = 0; i < 15; i=i+2 ) {
            ab = impares[i] + ab;
        }
        int suma = s1 + ab;

        int verif = (10 - (suma%10))%10;

        if (verif == digitoverificador){
            Calendar fecha = Calendar.getInstance();
            int año = fecha.get(Calendar.YEAR);
            int mes = fecha.get(Calendar.MONTH) + 1;
            int dia = fecha.get(Calendar.DAY_OF_MONTH);


            ArrayList<Producto> productoCarrito = (ArrayList<Producto>) session.getAttribute("productosCarritoDeCompras");
            int incre = 1 ;
            String stringIncre = String.valueOf(incre);
            String stringAño = String.valueOf(año);
            String stringMes = String.valueOf(mes);
            String stringDia = String.valueOf(dia);
            pedidos.setCodigopedido("PE"+ stringDia + stringMes + stringAño + " " );
            // Pensaba en tener el incre en sesión y cada vez que haya un cambio en el carrito de 0 a 1 (desde la primea vez) se incremente 1 el incre  l
            pedidos.setListaProductos(productoCarrito);
            pedidos.setFechacompra(new Date());

            attr.addFlashAttribute("msg1", "Compra exitosa");

        }


        return "index";
    }

}

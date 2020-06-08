package com.example.examenparcialg5.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pago")
public class PagoController {


    @GetMapping(value = {"","/"})
    public String ventanaPago(){return "checkout/tarjeta";}

    @PostMapping
    public String validarTarjeta(@RequestParam("tarjeta") String tarjeta, Model model, RedirectAttributes attr){


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


            attr.addFlashAttribute("msg1", "Compra exitosa");

        }


        return "index";
    }

}

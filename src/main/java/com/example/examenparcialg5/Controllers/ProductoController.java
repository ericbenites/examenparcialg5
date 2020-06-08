package com.example.examenparcialg5.Controllers;


import com.example.examenparcialg5.Dto.ProductoServiceApi;
import com.example.examenparcialg5.Entity.Fotos;
import com.example.examenparcialg5.Entity.Producto;
import com.example.examenparcialg5.Repository.FotoRepository;
import com.example.examenparcialg5.Repository.ProductoRepository;
import com.example.examenparcialg5.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    FotoRepository fotoRepository;

    @Autowired
    ProductoServiceApi productoServiceApi;
    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    ProductoService productoService;

    Logger log = LoggerFactory.getLogger(this.getClass());
    @GetMapping(value = {"", "/"})
    public ModelAndView listaProducto(@RequestParam Map<String, Object> params) {
        ModelAndView modelAndView = new ModelAndView();

        Fotos foto = new Fotos();
        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;

        PageRequest pageRequest = PageRequest.of(page, 7);

        Page<Producto> pageProduct = productoServiceApi.getAll(pageRequest);

        int totalPage = pageProduct.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            modelAndView.addObject("pages", pages);
        }

        modelAndView.addObject("listaProductos", pageProduct.getContent());
        modelAndView.addObject("listaFotos",fotoRepository.findAll()) ;
        modelAndView.addObject("current", page + 1);
        modelAndView.addObject("next", page + 2);
        modelAndView.addObject("prev", page);
        modelAndView.addObject("last", totalPage);

        modelAndView.setViewName("index");
        return modelAndView;
    }


    @GetMapping("/nuevo")
    public String nuevoProductoFrm(Model model, @ModelAttribute("producto") Producto producto) {
        return "producto/editFrm";
    }

    @PostMapping(value = "/guardar")
    public String guardarProducto(@RequestParam("imageFile") MultipartFile  imageFile ,Fotos foto , Producto producto, RedirectAttributes attr) {



        if (producto.getIdproducto() == 0) {
              productoRepository.save(producto);



              foto.setPath("/img/productoFotos/");
              foto.setFilename(imageFile.getOriginalFilename());
              if (!(foto.getFilename().endsWith(".png") || foto.getFilename().endsWith(".jpeg") ||foto.getFilename().endsWith(".jpg"))){
                  attr.addFlashAttribute("msg1", "Only PNG , JPG and JPEG images are allowed");

                    return "redirect:/producto/nuevo";}


                  foto.setProducto(producto);
            fotoRepository.save(foto);
            attr.addFlashAttribute("msg", "Producto creado exitosamente");




            return "redirect:/producto";


        } else {
            attr.addFlashAttribute("msg", "Producto actualizado exitosamente");
            productoRepository.save(producto);
            return "redirect:/producto";
        }



    }

    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("imageFile") MultipartFile imageFile) {
        String returnValue = "redirect:/producto";

        Fotos foto = new Fotos();
        foto.setFilename(imageFile.getOriginalFilename());
        foto.setPath("/img/productoFotos/");

        try {
            productoService.saveImage(imageFile, foto);
        } catch (Exception e) {

            e.printStackTrace();
        }

        return returnValue;
    }

}


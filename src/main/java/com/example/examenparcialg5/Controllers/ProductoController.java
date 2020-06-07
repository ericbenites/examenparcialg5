package com.example.examenparcialg5.Controllers;


import com.example.examenparcialg5.Dto.ProductoServiceApi;
import com.example.examenparcialg5.Entity.Fotos;
import com.example.examenparcialg5.Entity.Producto;
import com.example.examenparcialg5.Repository.ProductoRepository;
import com.example.examenparcialg5.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.print.Pageable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    ProductoServiceApi productoServiceApi;
    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    ProductoService productoService;

    Logger log = LoggerFactory.getLogger(this.getClass());
    @GetMapping(value = {"", "/"})
    public String listaProducto(@RequestParam Map<String, Object> params, Model model) {



        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;

        PageRequest pageRequest = PageRequest.of(page, 7);

        Page<Producto> pageProduct = productoServiceApi.getAll(pageRequest);

        int totalPage = pageProduct.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }

        model.addAttribute("listaProductos", pageProduct.getContent());
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);

        return "producto/listar";
    }


    @GetMapping("/nuevo")
    public String nuevoProductoFrm(Model model, @ModelAttribute("producto") Producto producto) {
        return "producto/editFrm";
    }

    @PostMapping(value = "/guardar")
    public ModelAndView guardarProducto(@RequestParam("imageFile") MultipartFile  imageFile , Producto producto, RedirectAttributes attr) throws IOException {
        ModelAndView modelAndView = new ModelAndView();


        if (producto.getIdproducto() == 0) {
          try {
              productoRepository.save(producto);
          } catch (Exception e) {

              log.error("No se puede guardar el producto", e);
              e.printStackTrace();
              modelAndView.setViewName("error");
              return modelAndView;
          }

              Fotos foto = new Fotos();
              foto.setPath("/img/productoFotos/");
              foto.setFilename(imageFile.getOriginalFilename());
              if (!(imageFile.getOriginalFilename().endsWith(".png") || imageFile.getOriginalFilename().endsWith(".jpg") || imageFile.getOriginalFilename().endsWith(".jpeg"))){
                  attr.addFlashAttribute("msg1", "Only PNG , JPG and JPEG images are allowed");
                    modelAndView.setViewName("producto/editFrm");
                    return modelAndView;}

              File f = new File("C://TEMP//" + imageFile.getOriginalFilename());
              f.createNewFile();
              FileOutputStream fout = new FileOutputStream(f);
              fout.write(imageFile.getBytes());
              fout.close();
              BufferedImage image = ImageIO.read(f);

              int height = image.getHeight();
              int width = image.getWidth();
              if(width > 135 || height>135) {
                  attr.addFlashAttribute("msg2", "No son válidas imágenes mayor que 135 x 135 px");
                  modelAndView.setViewName("producto/editFrm");
                  return modelAndView;
              }
                  foto.setProducto(producto);
            attr.addFlashAttribute("msg", "Producto creado exitosamente");
              modelAndView.setViewName("producto/listar");
              try {
                  productoService.saveImage(imageFile,foto);
              } catch (Exception e) {
                  e.printStackTrace();
                  log.error("Error guardando foto" , e);
                  modelAndView.setViewName("error");
                  return modelAndView;
              }
            modelAndView.addObject("foto", foto);
            modelAndView.addObject("producto", producto);
            return modelAndView;


        } else {
            attr.addFlashAttribute("msg", "Producto actualizado exitosamente");
            productoRepository.save(producto);
            modelAndView.setViewName("producto/listar");
        }
       return modelAndView;


    }

    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("imageFile") MultipartFile imageFile) {
        String returnValue = "producto/editFrm";

        Fotos foto = new Fotos();
        foto.setFilename(imageFile.getOriginalFilename());
        foto.setPath("/photo/");

        try {
            productoService.saveImage(imageFile, foto);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error("Error saving photo", e);
            returnValue = "error";
        }

        return returnValue;
    }

}


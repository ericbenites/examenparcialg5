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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PedidosController {

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
    public ModelAndView listaPedidos(@RequestParam Map<String, Object> params) {
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

        modelAndView.addObject("listaPedidos", pageProduct.getContent());
        modelAndView.addObject("current", page + 1);
        modelAndView.addObject("next", page + 2);
        modelAndView.addObject("prev", page);
        modelAndView.addObject("last", totalPage);

        modelAndView.setViewName("producto/index");
        return modelAndView;
    }

}

package com.example.demo.controller;

import com.example.demo.entity.Producto;
import com.example.demo.repository.ProductoRepository;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller //Anotacion que le indica a Spring que esta clase es un controlador
public class ProductoController {

    private ProductoRepository productoRepository;

    public ProductoController(ProductoRepository repository) {
        this.productoRepository = repository;
    }

    // GET http://localhost/productos, con la anotacion GetMapping indicamos que la ruta /productos
        // usara el metodo findAll mediante el metodo GET
        @GetMapping("/productos")
        public String findALL(Banner.Mode model){
        List<Producto> productos = this.productoRepository.findAll();
        //model.addAttribute("productos", productos);
        return "producto-list";
        }
        @GetMapping("/productos/add")
        public String add(){
            List<Producto> productos = new ArrayList<Producto>();
            Producto p1 = new Producto(null, "Producto 1", 12, 45.1);
            Producto p2 = new Producto(null, "Producto 2", 19, 34.3);
            Producto p3 = new Producto(null, "Producto 3", 15, 12.5);
            Producto p4 = new Producto(null, "Producto 4", 18, 48.9);

            productos.add(p1);
            productos.add(p2);
            productos.add(p3);
            productos.add(p4);

            // añadimos los productos en la DB
            this.productoRepository.saveAll(productos);

            // redirigimos al controlador /productos
            return "redirect:/productos";
        }
}

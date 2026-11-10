package com.afcldev.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.afcldev.apirest.apirest.Repositories.ProductoRepository;
import com.afcldev.apirest.apirest.Entities.Producto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping("/productos") 
public class ProductoController {

    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @GetMapping
    public List<Producto> getAllProductos(){
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto obtenerProductiPorId(@PathVariable Long id){
        return productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));
    }

    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto detallesProduto){
         Producto producto = productoRepository.findById(id)
         .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));

         producto.setNombre(detallesProduto.getNombre());
         producto.setPrecio(detallesProduto.getPrecio());

         return productoRepository.save(producto);
    }
    
    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id){
        Producto producto = productoRepository.findById(id)
         .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));

         productoRepository.delete(producto);
         return "El producto con el ID: " + id + " fue eliminado correctamente";
        }
}

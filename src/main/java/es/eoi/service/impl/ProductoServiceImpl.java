package es.eoi.service.impl;


import es.eoi.model.Producto;
import es.eoi.repository.ProductoRepository;
import es.eoi.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    @Override
    public List<Producto> getAll() {
        return productoRepository.findAll();
    }

    @Override
    public Producto getById(Long idProducto) {
        Optional<Producto> productoOptional = productoRepository.findById(idProducto);
        Producto productovacio = new Producto();
        if (((Optional<?>) productoOptional).isPresent())
            return productoOptional.get();
        else
            return productovacio;
    }

    @Override
    @Transactional(readOnly = false)
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    @Transactional(readOnly = false)
    public Producto update(Producto producto) {
        return productoRepository.saveAndFlush(producto);
    }

    @Override
    public void updateStock(Long idProducto, Long cantidad) {
        Producto producto = this.getById(idProducto);
        producto.setCantidad(producto.getCantidad() - cantidad);
        this.update(producto);
    }
}

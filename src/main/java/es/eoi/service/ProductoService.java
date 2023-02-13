package es.eoi.service;


import es.eoi.model.Producto;

import java.util.List;

public interface ProductoService {

    public List<Producto> getAll();

    public Producto getById(Long idProducto);

    public Producto save(Producto producto);

    public Producto update(Producto producto);

    public void updateStock(Long idProducto, Long cantidad);
}

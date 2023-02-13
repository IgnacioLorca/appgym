package es.eoi.service.impl;


import es.eoi.model.Compra;
import es.eoi.model.DetalleCompra;
import es.eoi.model.Producto;
import es.eoi.repository.CompraRepository;
import es.eoi.repository.DetalleCompraRepository;
import es.eoi.service.CompraService;
import es.eoi.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CompraServiceImpl implements CompraService {

    @Autowired
    CompraRepository compraRepository;

    @Autowired
    DetalleCompraRepository detalleCompraRepository;

    @Autowired
    ProductoService productoService;

    @Override
    public List<Compra> getAll() {
        return compraRepository.findAll();
    }

    @Override
    public Compra getById(Long idCompra) {
        Optional<Compra> compraOptional = compraRepository.findById(idCompra);
        Compra compravacio = new Compra();
        if (((Optional<?>) compraOptional).isPresent())
            return compraOptional.get();
        else
        return compravacio;
    }

    @Override
    @Transactional(readOnly = false)
    public Compra save(Compra compra) {

        compra.setFecha(new Date());

        // Calcular el total de los productos
        compra.setTotal(
                compra.getListaDetalleCompra()
                        .stream()
                        .mapToDouble(detalle -> {
                            // Calcula precio del producto * cantidad
                            Producto producto = productoService.getById(detalle.getIdDetalle());
                            detalle.setPrecio(producto.getPrecio());
                            detalle.setTotal(producto.getPrecio() * detalle.getCantidad());

                            return detalle.getTotal();
                        })
                        .sum()
        );

        // Actualizar stock de cada producto
        compra.getListaDetalleCompra()
                .forEach(detalle -> productoService.updateStock(detalle.getProducto().getIdProducto(), detalle.getCantidad()));

        compraRepository.save(compra);

        compra.getListaDetalleCompra().forEach(detalleCompra -> {
            detalleCompra.setCompra(compra);
            detalleCompraRepository.save(detalleCompra);
        });
        return compra;
    }
}

package es.eoi.service;


import es.eoi.model.Compra;

import java.util.List;

public interface CompraService {

    public List<Compra> getAll();

    public Compra getById(Long idCompra);

    public Compra save(Compra compra);
}

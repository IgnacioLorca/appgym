package es.eoi.service;

import es.eoi.model.TipoProducto;
import es.eoi.repository.TipoProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoProductoSrvc extends AbstractBusinessServiceE<TipoProducto, Integer, TipoProductoRepository>{
    private final TipoProductoRepository tipoProductoRepository;

    public TipoProductoSrvc(TipoProductoRepository tipoProductoRepository) {
        super(tipoProductoRepository);
        this.tipoProductoRepository = tipoProductoRepository;
    }

    public List<TipoProducto> getTipoProducto() {
        return tipoProductoRepository.findAll();
    }
}

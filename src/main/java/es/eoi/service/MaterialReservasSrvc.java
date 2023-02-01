package es.eoi.service;

import es.eoi.model.MaterialReservas;
import es.eoi.repository.MaterialReservasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialReservasSrvc extends AbstractBusinessServiceE<MaterialReservas, Integer, MaterialReservasRepository>{
    private final MaterialReservasRepository materialReservasRepository;

    public MaterialReservasSrvc(MaterialReservasRepository materialReservasRepository) {
        super(materialReservasRepository);
        this.materialReservasRepository = materialReservasRepository;
    }

    public List<MaterialReservas> getMaterialReservas() {
        return materialReservasRepository.findAll();
    }
}

package es.eoi.service;

import es.eoi.model.DatosBiometricos;
import es.eoi.repository.DatosBiometricosRepostitory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatosBiometricosSrvc extends AbstractBusinessServiceE<DatosBiometricos, Integer, DatosBiometricosRepostitory>{
    private final DatosBiometricosRepostitory datosBiometricosRepostitory;

    public DatosBiometricosSrvc(DatosBiometricosRepostitory datosBiometricosRepostitory) {
        super(datosBiometricosRepostitory);
        this.datosBiometricosRepostitory = datosBiometricosRepostitory;
    }

    public List<DatosBiometricos> getDatos() {
        return datosBiometricosRepostitory.findAll();
    }
}

package es.eoi.service;

import es.eoi.dto.DatosBiometricosDto;
import es.eoi.model.DatosBiometricos;
import es.eoi.repository.DatosBiometricosRepostitory;
import es.eoi.service.mapper.DatosBiometricosMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatosBiometricosSrvc extends AbstractBusinessService<DatosBiometricos, Integer, DatosBiometricosDto, DatosBiometricosRepostitory, DatosBiometricosMapper> {

    public DatosBiometricosSrvc(DatosBiometricosRepostitory repo, DatosBiometricosMapper serviceMapper) {
        super(repo, serviceMapper);
    }

    @Autowired
    private DatosBiometricosRepostitory datosBiometricosRepostitory;

    public List<DatosBiometricos> getDatos() {
        return datosBiometricosRepostitory.findAll();
    }
}

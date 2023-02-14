package es.eoi.service.mapper;



import es.eoi.dto.DatosBiometricosDto;
import es.eoi.model.DatosBiometricos;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DatosBiometricosMapper extends AbstractServiceMapper<DatosBiometricos, DatosBiometricosDto> {

    @Override
    public DatosBiometricosDto toDto(DatosBiometricos entidad){
        final DatosBiometricosDto dto = new DatosBiometricosDto();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(entidad,dto);
        return dto;
    }

    @Override
    public DatosBiometricos toEntity(DatosBiometricosDto dto) throws Exception {
        final DatosBiometricos entidad = new DatosBiometricos();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(dto,entidad);
        return entidad;
    }

    public DatosBiometricosMapper(){}

}

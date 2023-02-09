package es.eoi.service.mapper;

import es.eoi.dto.CatalogoDto;
import es.eoi.model.Catalogo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CatalogoMapper extends AbstractServiceMapper<Catalogo, CatalogoDto> {

    @Override
    public CatalogoDto toDto(Catalogo entidad) {
        final CatalogoDto dto = new CatalogoDto();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(entidad,dto);
        return dto;
    }

    @Override
    public Catalogo toEntity(CatalogoDto dto) throws Exception {
        final Catalogo entidad = new Catalogo();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(dto,entidad);
        return entidad;
    }

    public CatalogoMapper(){}
}

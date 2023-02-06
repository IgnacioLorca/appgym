package es.eoi.service.mapper;

import es.eoi.dto.RoleDto;
import es.eoi.model.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceMapper extends AbstractServiceMapper<Role, RoleDto> {

    public Role toEntity(RoleDto dto) {
        final Role entidad = new Role();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(entidad,dto);
        return entidad;
    }

    public RoleDto toDto(Role entidad) {
        final RoleDto dto = new RoleDto();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(entidad,dto);
        return dto;
    }

}

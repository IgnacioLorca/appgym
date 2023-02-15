package es.eoi.service.mapper;

import es.eoi.dto.ClienteDto;
import es.eoi.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ClienteMapper extends AbstractServiceMapper<Cliente, ClienteDto>{

        @Override
        public ClienteDto toDto(Cliente entidad){
            final ClienteDto dto = new ClienteDto();
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.map(entidad,dto);
            return dto;
        }

        @Override
        public Cliente toEntity(ClienteDto dto)  {
            final Cliente entidad = new Cliente();
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.map(dto,entidad);
            return entidad;
        }


        public ClienteMapper(){}


}

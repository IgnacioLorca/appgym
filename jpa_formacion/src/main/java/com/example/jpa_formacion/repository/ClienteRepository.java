package com.example.jpa_formacion.repository;

import com.example.jpa_formacion.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;





public interface ClienteRepository extends JpaRepository<Cliente,Long>{
    //List<Cliente> findBynombreCliente(String nombre);

}

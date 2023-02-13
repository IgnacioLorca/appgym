package es.eoi.repository;


import es.eoi.model.DatosBiometricos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatosBiometricosRepostitory extends JpaRepository<DatosBiometricos, Integer> {
}

package es.eoi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public class AbstractBusinessServiceE<E, ID, REPO extends JpaRepository<E,ID>> {

    private final REPO repo;

    protected AbstractBusinessServiceE(REPO repo) {
        this.repo = repo;
    }

    // Listar Entidades paginadas
    public List<E> buscarTodos(){
        return this.repo.findAll();
    }
    public Set<E> buscarTodosSet(){
        Set<E> dtos = new HashSet<E>(this.repo.findAll());
        return  dtos;
    }
    public Page<E> buscarTodos(Pageable pageable){
        return  repo.findAll(pageable);
    }

    // Buscar por id
    public Optional<E> encuentraPorId(ID id){
        return this.repo.findById(id);
    }

    // Guardar
    public E guardar(E e) throws Exception {
        E entidadGuardada =  repo.save(e);
        return entidadGuardada;
    }

    public void guardar(List<E> es) throws Exception {
        Iterator<E> it = es.iterator();
        while(it.hasNext()){
            E e = it.next();
            repo.save(e);
        }
    }

    // Eliminar un registro
    public void eliminarPorId(ID id){
        this.repo.deleteById(id);
    }

    // Obtener el repo
    public REPO getRepo(){return  repo;}

}

package es.eoi.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public abstract class AbstractBusinessServiceE <E, ID, REPO extends JpaRepository<E,ID>> {
    private final REPO repo;

    public AbstractBusinessServiceE(REPO repo) {
        this.repo = repo;
    }

    public List<E> buscarTodos(){
        return  this.repo.findAll();
    }

    public Set<E> buscarTodosSet(){
        Set<E> dtos = new HashSet<E>(this.repo.findAll());
        return  dtos;
    }

    public Page<E> buscarTodos(Pageable pageable){
        return  repo.findAll(pageable);
    }

    //Buscar por id
    public Optional<E> encuentraPorId(ID id){

        return this.repo.findById(id);
    }

    //Guardar
    public E guardar(E e) throws Exception {

        //Guardo el la base de datos
        E entidadGuardada =  repo.save(e);
        //Traducir la entidad a DTO para devolver el DTO
        return entidadGuardada;
    }
    public void  guardar(List<E> es) throws Exception {
        Iterator<E> it = es.iterator();

        // mientras al iterador queda proximo juego
        while(it.hasNext()){
            //Obtenemos la password de a entidad
            //Datos del usuario
            E e = it.next();
            repo.save(e);
        }
    }
    //eliminar un registro
    public void eliminarPorId(ID id){
        this.repo.deleteById(id);
    }
    //Obtener el mapper
    //Obtener el repo
    public REPO getRepo(){return  repo;}
}

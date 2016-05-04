package hello.api.ruta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase implementadora de los métodos de acceso a la base de datos
 * Utilizamos RutaRepository para trabajar con los métodos
 * Created by manuelgm on 04/05/2016.
 */
@Service
@Transactional
public class RutaService {

    /**
     * Incluimos el repositorio para trabajar con los métodos definidos
     * (En principio solo tiene los crud básicos)
     */

    private RutaRepository repository;
    @Autowired
    public RutaService(RutaRepository repository) {
        this.repository = repository;
    }

    /**
     * MÉTODOS DEL REPOSITORIO (RutaRepository)
     * ¿Por qué reimplementamos los métodos por default que tiene RutaRepository?
     * Para que en la aplicación se trabaje a través RutaService siempre. Entonces cuando se instancie
     * RutaService tendrá tanto sus propios métodos indicados findBy... y también los default
     * del RutaRepository.
     */

    public Iterable<Ruta> findAll(){
        return repository.findAll();
    }

    public Iterable<Ruta> save(Ruta ruta){

        return repository.save((List) ruta);
    }

    public Ruta findOne(int id){
        return repository.findOne(id);
    }

    public void delete(int id){
        repository.delete(id);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    /**
     * MÉTODOS DEL SERVICIO (RutaService)
     */

    public Ruta findByRuta(int id) {
        return null;
    }

    public Iterable<Ruta> findByNivel(int nivel) {
        return null;
    }

    public Iterable<Ruta> findByValoracion(int valoracion) {
        return null;
    }

    public Iterable<Ruta> findByPoblacion(int poblacion) {
        return null;
    }

    public Iterable<Ruta> findByCodPostal(int codPostal) {
        return null;
    }

    public Iterable<Ruta> findByUsuario(String email) {
        return null;
    }
}

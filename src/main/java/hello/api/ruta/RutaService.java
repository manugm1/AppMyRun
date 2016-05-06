package hello.api.ruta;

import com.sun.org.apache.xerces.internal.xinclude.XIncludeTextReader;
import hello.api.poblacion.Poblacion;
import hello.api.poblacion.PoblacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    private PoblacionRepository poblacionRepository;

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

    public Ruta save(Ruta ruta){ return repository.save(ruta); }

    public Ruta findOne(int id){ return repository.findOne(id); }

    public void delete(int id){
        repository.delete(id);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    /**
     * MÉTODOS DEL SERVICIO (RutaService)
     */

    /**
     * Rutas por nivel (dificultad)
     * @param nivel
     * @return
     */
    public Iterable<Ruta> findByNivel(int nivel) {
        ArrayList<Ruta> aux = new ArrayList<Ruta>();
        Iterable<Ruta> rutas = repository.findAll();

        for(Ruta ruta: rutas){
            if(ruta.getDificultad() == nivel)
                aux.add(ruta);
        }
        return aux;
    }

    /**
     * Rutas por valoración
     * @param valoracion
     * @return Iterable<Ruta>
     */
    public Iterable<Ruta> findByValoracion(int valoracion) {
        ArrayList<Ruta> aux = new ArrayList<Ruta>();
        Iterable<Ruta> rutas = repository.findAll();

        for(Ruta ruta: rutas){
            if(ruta.getPopularidad() == valoracion)
                aux.add(ruta);
        }
        return aux;
    }

    /**
     * Rutas por población (es el código postal)
     * @param codPostal
     * @return Iterable<Ruta>
     */
    public Iterable<Ruta> findByCodPostal(String codPostal) {
        ArrayList<Ruta> aux = new ArrayList<Ruta>();
        Iterable<Ruta> rutas = repository.findAll();

        for(Ruta ruta: rutas){
            if(ruta.getFk_poblacion().equals(codPostal))
                aux.add(ruta);
        }
        return aux;
    }

    /**
     * Rutas por población (nombre de la población)
     * @param poblacion
     * @return Iterable<Ruta>
     */
    public Iterable<Ruta> findByPoblacion(String poblacion) {
        ArrayList<Ruta> aux = new ArrayList<Ruta>();
        Iterable<Poblacion> poblaciones = poblacionRepository.findAll();
        Iterable<Ruta> rutasCodPostales;

        //Recorremos todas las poblaciones
        for(Poblacion poblac: poblaciones){
            if(poblac.getPoblacion().equals(poblacion)){
                //Buscamos ahora la ruta asociada al código postal recorrido
                //Las recorremos
                rutasCodPostales = findByCodPostal(poblac.getCodPostal());
                for(Ruta ruta: rutasCodPostales){
                    aux.add(ruta);
                }
            }

        }
        return aux;
    }

    /**
     * Rutas por usuario
     * @param email
     * @return Iterable<Ruta>
     */
    public Iterable<Ruta> findByUsuario(String email) {
        ArrayList<Ruta> aux = new ArrayList<Ruta>();
        Iterable<Ruta> rutas = repository.findAll();

        for(Ruta ruta: rutas){
            if(ruta.getFk_usuario().equals(email))
                aux.add(ruta);
        }
        return aux;
    }
}

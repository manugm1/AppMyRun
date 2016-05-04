package hello.api.ruta;

import org.springframework.data.repository.CrudRepository;

/**
 * Es lo que antes teníamos como clase Dao
 * Created by manuelgm on 04/05/2016.
 */
public interface RutaRepository extends CrudRepository<Ruta, Integer> {
    //CrudRepository<Ruta, Integer> (primer parámetro-> tipo de objeto; segundo parámetro->tipo clave primaria)
    //Incluye métodos save, delete, deleteAll, findOne y findAll.
}
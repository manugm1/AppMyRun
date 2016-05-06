package hello.api.poblacion;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by manuelgm on 06/05/2016.
 */
public interface PoblacionRepository extends CrudRepository<Poblacion, String> {
    //CrudRepository<Ruta, Integer> (primer parámetro-> tipo de objeto; segundo parámetro->tipo clave primaria)
    //Incluye métodos save, delete, deleteAll, findOne y findAll.
}

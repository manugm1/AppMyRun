package hello.api.estadisticas;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by wilad on 21/05/2016.
 */
public interface EstadisticaCiudadDao  extends CrudRepository< EstadisticaCodPostal, String> {
}

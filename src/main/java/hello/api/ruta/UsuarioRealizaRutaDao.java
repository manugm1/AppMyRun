package hello.api.ruta;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by Javier on 18/05/2016.
 */

@Transactional
public interface UsuarioRealizaRutaDao extends CrudRepository<UsuarioRealizaRuta, UsuarioRealizaRutaPK>
{

}

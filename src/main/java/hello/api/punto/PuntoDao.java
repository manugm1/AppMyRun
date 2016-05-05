package hello.api.punto;


import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;


@Transactional
public interface PuntoDao extends CrudRepository<Punto, Integer> {




}


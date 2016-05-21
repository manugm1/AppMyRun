package hello.api.opinion;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by wilad on 19/05/2016.
 */
@Transactional
public interface OpinionDao extends CrudRepository< Opinion, OpinionPK> {
}

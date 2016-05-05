package hello.api.usuario; /**
 * Created by Javier on 21/04/2016.
 */
import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

/**
 * A DAO for the entity backend.hello.api.usuario.User is simply created by extending the CrudRepository
 * interface provided by spring. The following methods are some of the ones
 * available from such interface: save, delete, deleteAll, findOne and findAll.
 * The magic is that such methods must not be implemented, and moreover it is
 * possible create new query methods working only by defining their signature!
 *
 * @author netgloo
 */
@Transactional
public interface UserRepository extends CrudRepository<User, String> {

    /**
     * Return the user having the passed email or null if no user is found.
     *
     * @param email the user email.
     */


} // class backend.hello.api.usuario.UserRepository
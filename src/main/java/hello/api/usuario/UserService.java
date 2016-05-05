package hello.api.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase implementadora de los métodos de acceso a la base de datos
 * Utilizamos UserRepository para trabajar con los métodos
 * Created by AdelZB on 05/05/2016.
 */

@Service
@Transactional
public class UserService {

    private UserRepository repository;
    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * MÉTODOS DEL REPOSITORIO (UserRepository)
     */

    public Iterable<User> findAll(){
        return repository.findAll();
    }

    public User save(User user){

        return repository.save(user);
    }

    public User findOne(String id){
        return repository.findOne(id);
    }

    public void delete(String id){
        repository.delete(id);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public Boolean exists(String id) {return repository.exists(id);}




    /**
     * MÉTODOS DEL SERVICIO (UserService)
     */

    public Boolean verifyUser (String email, String password){

        Boolean existe=false;

        if(exists(email)){
            User usuaux=findOne(email);
            if(password.equals(usuaux.getPassword()))
                existe=true;

        }

        return existe;
    }

    public Boolean activeUser (String email, String password){
        Boolean activo=false;

        activo=verifyUser(email,password) && findOne(email).getActivo()==1;

        return activo;
    }

}

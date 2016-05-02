package hello;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.ResponseBody;
@RestController
public class UserController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(2,"Bienvenido "+ name);
    }


    @RequestMapping("/registroUsuario")
    @ResponseBody
    public Mensaje create(String email, String pass) {
        User user = null;
        Mensaje mens=new Mensaje(400, "Error en los parámetros");
        ArrayList<User> usuarios=new ArrayList<User>();
        boolean existe=false;
        try {

            Iterable<User> users=userDao.findAll();
            usuarios=(ArrayList<User>)(users);
            for(int i=0;i< usuarios.size();i++)
            {
                if(email.equals(usuarios.get(i).getEmail()))
                {
                    existe=true;
                }
            }

            if(!existe) {
                user = new User(email, pass);
                user.setActivo(1);
                userDao.save(user);
                mens.setCodigo(200);
                mens.setInfo("Registro correcto");
                System.out.println("No existe");
            }
            else
            {
                mens.setCodigo(401);
                mens.setInfo("Usuario existente");
                System.out.println("Existe");
            }

        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return mens;
    }



    @Autowired
    private UserDao userDao;
}

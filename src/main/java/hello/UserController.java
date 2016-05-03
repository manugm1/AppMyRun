package hello;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

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
    public Mensaje create(String email, String password, String nombre, String apellido1, String apellido2, Timestamp fecNacimiento, Integer sexo) {
        User user = null;
        Mensaje mens=new Mensaje(400, "Error en los parámetros");
        Date auxFecha=new Date();

        //SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
        /*try{
            auxFecha.formatFecha.parse(fecNacimiento);
        }
        catch(ParseException ex){
           return mens;
        }*/
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
                user = new User(email,password, nombre, apellido1,apellido2, fecNacimiento, sexo);
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
            System.out.println("Salta excepción");
            System.out.println(ex.getMessage());

        }
        return mens;
    }



    @Autowired
    private UserDao userDao;
}

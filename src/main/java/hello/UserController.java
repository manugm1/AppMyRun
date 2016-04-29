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


    @RequestMapping("/create")
    @ResponseBody
    public Iterable<User> create(String email, String name) {
        User user = null;
        ArrayList<User> usuarios=new ArrayList<User>();
        try {
            user = new User(email, name);
            Iterable<User> users=userDao.findAll();
            usuarios=(ArrayList<User>)(users);
            userDao.save(user);

        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return usuarios;
    }

    @Autowired
    private UserDao userDao;
}

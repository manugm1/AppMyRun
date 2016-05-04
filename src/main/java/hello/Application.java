package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application {

    /**
     * Ruta raíz
     * @return String
     */
    @RequestMapping("/")
    public String init() {
        return "Servidor iniciado";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

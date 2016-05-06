package hello.api.ruta;

import hello.api.usuario.UserService;
import hello.lib.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by manuelgm on 04/05/2016.
 */
@RestController
@RequestMapping(value = "/api/ruta")
public class RutaController {

    /**
     * Incluimos RutaService para acceder a los métodos del Objeto en cuestión
     * que a su vez accederá a la base de datos
     */
    @Autowired
    private RutaService service;

    @Autowired
    private UserService userService;

    /** FUNCIONA
     * A continuación definimos los métodos cara al exterior (accesibles)
     * Pueden ser simples CRUD o una mezcla que requiera más operaciones
     * Para dichas operaciones tenemos al objeto RutaService.
     */
    @RequestMapping(value = "/crearRuta", method = RequestMethod.POST)
    public Mensaje crear(String email, String password, String nombre, int dificultad, String descripcion, String poblacion, int tipo){
        Mensaje mens=new Mensaje(401, "Error en los parámetros");
        boolean existe;

        try{
            //Paso 1 - Verificar si el usuario es válido
            existe = userService.verifyUser(email, password);
            if(existe){
                //Paso 2 - Creamos registro en la bbdd
                //De momento no validamos
                Ruta ruta = new Ruta();
                ruta.setNombre(nombre);
                ruta.setDificultad(dificultad);
                ruta.setDescripcion(descripcion);
                ruta.setFk_poblacion(poblacion);
                ruta.setTipo(tipo);
                ruta.setFk_usuario(email);

                //Paso 3 - Invocamos al service
                service.save(ruta);
                //En este caso devolvemos la id
                mens=new Mensaje(200, ruta.getId());
            }
            else{
                mens.setCodigo(401);
                mens.setInfo("El usuario o contraseña no es correcto");
            }
        }catch(Exception ex) {}

        return mens;
    }

    /**
     * FUNCIONA (FALTA DEVOLVER PUNTOS - PREGUNTAR A CRISTIAN OBJETO AUTO CARGADO)
     * @param id
     * @return Mensaje
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Mensaje devolver(@PathVariable("id") int id){
        Mensaje mens=new Mensaje(401, "Error en los parámetros");

        try{
            //Buscamos la ruta por su id
            mens = new Mensaje(200, service.findOne(id));
        }catch(Exception ex) {}

        return mens;
    }

    /**
     * FUNCIONA
     * @param id
     * @return Mensaje
     */
    @RequestMapping(value = "/devolverRutaSimple/{id}", method = RequestMethod.GET)
    public Mensaje devolverSimple(@PathVariable("id") int id){
        Mensaje mens=new Mensaje(401, "Error en los parámetros");

        try{
            //Buscamos la ruta por su id
            //ruta.setPuntos(null);
            mens = new Mensaje(200, service.findOne(id));

        }catch(Exception ex) {}

        return mens;
    }

    /**
     * FUNCIONA (FALTA DEVOLVER PUNTOS - PREGUNTAR A CRISTIAN OBJETO AUTO CARGADO)
     * @return Mensaje
     */
    @RequestMapping(value = "/devolverRutas", method = RequestMethod.GET)
    public Mensaje devolverTodas(){
        Mensaje mens=new Mensaje(401, "Error en los parámetros");

        try{

            //Devolvemos todas
            mens = new Mensaje(200, service.findAll());

        }catch(Exception ex) {}

        return mens;
    }

    @RequestMapping(value = "/devolverRutasPorNivel/{param}", method = RequestMethod.GET)
    public Mensaje devolverRutasPorNivel(@PathVariable("param") int param){
        Mensaje mens=new Mensaje(401, "Error en los parámetros");

        try{

            //Buscamos las rutas por su nivel
            mens = new Mensaje(200, service.findByNivel(param));

        }catch(Exception ex) {}

        return mens;
    }

    @RequestMapping(value = "/devolverRutasPorValoracion/{param}", method = RequestMethod.GET)
    public Mensaje devolverRutasPorValoracion(@PathVariable("param") int param){
        Mensaje mens=new Mensaje(401, "Error en los parámetros");

        try{

            //Buscamos la ruta por su valoración
            mens = new Mensaje(200, service.findByValoracion(param));

        }catch(Exception ex) {}

        return mens;
    }

    @RequestMapping(value = "/devolverRutasPorPoblacion/{param}", method = RequestMethod.GET)
    public Mensaje devolverRutasPorPoblacion(@PathVariable("param") String param){
        Mensaje mens=new Mensaje(401, "Error en los parámetros");

        try{

            //Paso 2 - Buscamos la ruta por su población
            mens = new Mensaje(200, service.findByPoblacion(param));

        }catch(Exception ex) {}

        return mens;
    }

    @RequestMapping(value = "/devolverRutasPorCodPostal/{param}", method = RequestMethod.GET)
    public Mensaje devolverRutasPorCodPostal(@PathVariable("param") String param){
        Mensaje mens=new Mensaje(401, "Error en los parámetros");

        try{

            //Buscamos la ruta por su código postal
            mens = new Mensaje(200, service.findByCodPostal(param));

        }catch(Exception ex) {}

        return mens;
    }

    @RequestMapping(value = "/devolverRutasPorUsuario/{param}", method = RequestMethod.GET)
    public Mensaje devolverRutasPorUsuario(@PathVariable("param") String param){
        Mensaje mens=new Mensaje(401, "Error en los parámetros");

        try{

            //Buscamos la ruta por su usuario
            mens = new Mensaje(200, service.findByUsuario(param));

        }catch(Exception ex) {}

        return mens;
    }
}

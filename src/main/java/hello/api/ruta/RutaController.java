package hello.api.ruta;

import hello.api.usuario.UserService;
import hello.lib.Mensaje;
import hello.lib.Validaciones;
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
            //Paso 1 - Verificar formato campos (de momento solo el email)
            Validaciones validar = new Validaciones();
            if(validar.validaEmail(email)){
                //Paso 2 - Verificar si el usuario y password existe y está activo
                existe = userService.activeUser(email, password);
                if(existe){
                    //Paso 3 - Creamos registro en la bbdd
                    Ruta ruta = new Ruta();
                    ruta.setNombre(nombre);
                    ruta.setDificultad(dificultad);
                    ruta.setDescripcion(descripcion);
                    ruta.setFk_poblacion(poblacion);
                    ruta.setTipo(tipo);
                    ruta.setFk_usuario(email);

                    //Paso 4 - Invocamos al service
                    service.save(ruta);
                    //En este caso devolvemos la id
                    mens=new Mensaje(200, ruta.getId());
                }
                else{
                    mens.setCodigo(401);
                    mens.setInfo("El usuario o contraseña no es correcto");
                }
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
    @RequestMapping(value = "/devolverRutaCompleta", method = RequestMethod.GET)
    public Mensaje devolver(int id){
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
    @RequestMapping(value = "/devolverRutaSimple", method = RequestMethod.GET)
    public Mensaje devolverSimple(int id){
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

    @RequestMapping(value = "/devolverRutasPorNivel", method = RequestMethod.GET)
    public Mensaje devolverRutasPorNivel(int nivel){
        Mensaje mens=new Mensaje(401, "Error en los parámetros");

        try{

            //Buscamos las rutas por su nivel
            mens = new Mensaje(200, service.findByNivel(nivel));

        }catch(Exception ex) {}

        return mens;
    }

    @RequestMapping(value = "/devolverRutasPorValoracion", method = RequestMethod.GET)
    public Mensaje devolverRutasPorValoracion(int valoracion){
        Mensaje mens=new Mensaje(401, "Error en los parámetros");

        try{

            //Buscamos la ruta por su valoración
            mens = new Mensaje(200, service.findByValoracion(valoracion));

        }catch(Exception ex) {}

        return mens;
    }

    @RequestMapping(value = "/devolverRutasPorPoblacion", method = RequestMethod.GET)
    public Mensaje devolverRutasPorPoblacion(String poblacion){
        Mensaje mens=new Mensaje(401, "Error en los parámetros");

        try{

            //Paso 2 - Buscamos la ruta por su población
            mens = new Mensaje(200, service.findByPoblacion(poblacion));

        }catch(Exception ex) {}

        return mens;
    }

    @RequestMapping(value = "/devolverRutasPorCodPostal", method = RequestMethod.GET)
    public Mensaje devolverRutasPorCodPostal(String codPostal){
        Mensaje mens=new Mensaje(401, "Error en los parámetros");

        try{

            //Buscamos la ruta por su código postal
            mens = new Mensaje(200, service.findByCodPostal(codPostal));

        }catch(Exception ex) {}

        return mens;
    }

    @RequestMapping(value = "/devolverRutasPorUsuario", method = RequestMethod.GET)
    public Mensaje devolverRutasPorUsuario(String usuario){
        Mensaje mens=new Mensaje(401, "Error en los parámetros");

        try{

            //Buscamos la ruta por su usuario
            mens = new Mensaje(200, service.findByUsuario(usuario));

        }catch(Exception ex) {}

        return mens;
    }
}

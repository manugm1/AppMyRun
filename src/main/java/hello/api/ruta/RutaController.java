package hello.api.ruta;

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

    /** HAY QUE PROBARLO
     * A continuación definimos los métodos cara al exterior (accesibles)
     * Pueden ser simples CRUD o una mezcla que requiera más operaciones
     * Para dichas operaciones tenemos al objeto RutaService.
     */
    @RequestMapping(value = "/crearRuta", method = RequestMethod.POST)
    public Mensaje crear(String email, String password, String nombre, int dificultad, String descripcion, int tipo){
        Mensaje mens=new Mensaje(401, "Error en los parámetros");

        //Paso 1 - Verificar si el usuario es válido
        //De momento no implementado (usuario debe tener UsuarioService para invocarse desde aquí)

        try{
            //Paso 2 - Creamos registro en la bbdd
            //De momento no validamos
            Ruta ruta = new Ruta();
            ruta.setNombre(nombre);
            ruta.setDificultad(dificultad);
            ruta.setDescripcion(descripcion);
            ruta.setTipo(tipo);
            ruta.setFk_usuario(email);

            //Paso 3 - Invocamos al service
            mens=new Mensaje(200, service.save(ruta));

        }catch(Exception ex) {

        }

        return mens;
    }

    /**
     * FUNCIONA
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Mensaje devolver(@PathVariable("id") int id){
        Mensaje mens=new Mensaje(401, "Error en los parámetros");

        //Paso 1 - Verificar si el usuario es válido
        //De momento no implementado (usuario debe tener UsuarioService para invocarse desde aquí)

        try{
            //Paso 2 - Buscamos la ruta por su id
            Ruta ruta = service.findOne(id);
            mens = new Mensaje(200, (Object) ruta);
        }catch(Exception ex) {

        }

        return mens;
    }

    /**
     * FUNCIONA
     * @param id
     * @return
     */
    @RequestMapping(value = "/devolverRutaSimple/{id}", method = RequestMethod.GET)
    public Mensaje devolverSimple(@PathVariable("id") int id){
        Mensaje mens=new Mensaje(401, "Error en los parámetros");

        //Paso 1 - Verificar si el usuario es válido
        //De momento no implementado (usuario debe tener UsuarioService para invocarse desde aquí)

        try{
            //Paso 2 - Buscamos la ruta por su id

            Ruta ruta = service.findOne(id);
            //ruta.puntos = null;
            mens = new Mensaje(200, (Object) ruta);

        }catch(Exception ex) {

        }

        return mens;
    }

    @RequestMapping(value = "/devolverRutas", method = RequestMethod.GET)
    public Mensaje devolverTodas(){
        Mensaje mens=new Mensaje(401, "Error en los parámetros");

        //Paso 1 - Verificar si el usuario es válido
        //De momento no implementado (usuario debe tener UsuarioService para invocarse desde aquí)

        try{

            //Paso 2 - Buscamos la ruta por su id
            Iterable<Ruta> rutas = service.findAll();
            mens = new Mensaje(200, rutas);

        }catch(Exception ex) {

        }

        return mens;
    }

    @RequestMapping(value = "/devolverRutasPorNivel/{param}", method = RequestMethod.GET)
    public Mensaje devolverRutasPorNivel(@PathVariable("param") int param){
        Mensaje mens=new Mensaje(401, "Error en los parámetros");

        //Paso 1 - Verificar si el usuario es válido
        //De momento no implementado (usuario debe tener UsuarioService para invocarse desde aquí)

        try{

            //Paso 2 - Buscamos la ruta por su id
            Iterable<Ruta> rutas = service.findByNivel(param);
            mens = new Mensaje(200, rutas.toString());

        }catch(Exception ex) {

        }

        return mens;
    }

    @RequestMapping(value = "/devolverRutasPorValoracion/{param}", method = RequestMethod.GET)
    public Mensaje devolverRutasPorValoracion(@PathVariable("param") int param){
        Mensaje mens=new Mensaje(401, "Error en los parámetros");

        //Paso 1 - Verificar si el usuario es válido
        //De momento no implementado (usuario debe tener UsuarioService para invocarse desde aquí)

        try{

            //Paso 2 - Buscamos la ruta por su id
            Iterable<Ruta> rutas = service.findByValoracion(param);
            mens = new Mensaje(200, rutas.toString());

        }catch(Exception ex) {

        }

        return mens;
    }

    @RequestMapping(value = "/devolverRutasPorPoblacion/{param}", method = RequestMethod.GET)
    public Mensaje devolverRutasPorPoblacion(@PathVariable("param") int param){
        Mensaje mens=new Mensaje(401, "Error en los parámetros");

        //Paso 1 - Verificar si el usuario es válido
        //De momento no implementado (usuario debe tener UsuarioService para invocarse desde aquí)

        try{

            //Paso 2 - Buscamos la ruta por su id
            Iterable<Ruta> rutas = service.findByPoblacion(param);
            mens = new Mensaje(200, rutas.toString());

        }catch(Exception ex) {

        }

        return mens;
    }

    @RequestMapping(value = "/devolverRutasPorCodPostal/{param}", method = RequestMethod.GET)
    public Mensaje devolverRutasPorCodPostal(@PathVariable("param") int param){
        Mensaje mens=new Mensaje(401, "Error en los parámetros");

        //Paso 1 - Verificar si el usuario es válido
        //De momento no implementado (usuario debe tener UsuarioService para invocarse desde aquí)

        try{

            //Paso 2 - Buscamos la ruta por su id
            Iterable<Ruta> rutas = service.findByCodPostal(param);
            mens = new Mensaje(200, rutas.toString());

        }catch(Exception ex) {

        }

        return mens;
    }

    @RequestMapping(value = "/devolverRutasPorUsuario/{param}", method = RequestMethod.GET)
    public Mensaje devolverRutasPorUsuario(@PathVariable("param") String param){
        Mensaje mens=new Mensaje(401, "Error en los parámetros");

        //Paso 1 - Verificar si el usuario es válido
        //De momento no implementado (usuario debe tener UsuarioService para invocarse desde aquí)

        try{

            //Paso 2 - Buscamos la ruta por su id
            Iterable<Ruta> rutas = service.findByUsuario(param);
            mens = new Mensaje(200, rutas.toString());

        }catch(Exception ex) {

        }

        return mens;
    }
}

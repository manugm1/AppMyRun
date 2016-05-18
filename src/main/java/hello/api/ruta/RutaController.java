package hello.api.ruta;

import hello.api.exceptions.BadRequestException;
import hello.api.exceptions.OKException;
import hello.api.exceptions.UnauthorizedException;
import hello.api.punto.Punto;
import hello.api.punto.PuntoDao;
import hello.api.punto.PuntoRuta;
import hello.api.punto.PuntoRutaDao;
import hello.api.usuario.UserService;
import hello.lib.Mensaje;
import hello.lib.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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

    @Autowired
    private PuntoDao puntoDao;

    @Autowired
    private PuntoRutaDao puntoRutaDao;

    /** FUNCIONA
     * A continuación definimos los métodos cara al exterior (accesibles)
     * Pueden ser simples CRUD o una mezcla que requiera más operaciones
     * Para dichas operaciones tenemos al objeto RutaService.
     */
    @RequestMapping(value = "/crearRuta", method = RequestMethod.POST)
    @ResponseBody
    public Integer crear(String email, String password, String nombre, Integer dificultad, String descripcion, String poblacion, Integer tipo) throws UnauthorizedException, BadRequestException{
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
                    return ruta.getId();
                }
                else{
                    throw new UnauthorizedException("El usuario o contraseña no es correcto.");
                }
            }
            else{
                throw new BadRequestException("Error en los parámetros del usuario.");
            }

        }//Aquí capturamos y enviamos
        catch(UnauthorizedException ex){
            throw new UnauthorizedException(ex.getMessage());
        }
        catch(BadRequestException ex){
            throw new BadRequestException(ex.getMessage());
        }
        catch(Exception ex){
            throw new BadRequestException("Error en los parámetros.");
        }
    }

    /**
     * FUNCIONA (FALTA DEVOLVER PUNTOS - PREGUNTAR A CRISTIAN OBJETO AUTO CARGADO)
     * @param idRuta
     * @return Mensaje
     */

    @RequestMapping(value = "/devolverRutaCompleta", method = RequestMethod.GET)
    @ResponseBody
    public Ruta devolver(Integer idRuta) throws BadRequestException{

        try{
            //Buscamos la ruta por su id
            Ruta ruta = service.findOne(idRuta);

            //Buscamos los puntos que tiene (ESTO DEBERÍA SER AUTOCARGADO AL HACER EL findOne)
            ArrayList<PuntoRuta> pr = (ArrayList) puntoRutaDao.findAll();
            ArrayList<Punto> puntos = new ArrayList<Punto>();

            for (int i = 0; i < pr.size(); i++) {
                int idr = pr.get(i).getPk().getRuta().getId();
                int idp = pr.get(i).getPk().getPunto().getId();

                if (idr == idRuta) {
                    Float coordx = puntoDao.findOne(idp).getCoordx();
                    Float coordy = puntoDao.findOne(idp).getCoordy();
                    String nombre = puntoDao.findOne(idp).getNombre();
                    String foto = puntoDao.findOne(idp).getFoto();
                    String descripcion = puntoDao.findOne(idp).getDescripcion();
                    Punto punto = new Punto(coordx, coordy, nombre, foto, descripcion);
                    punto.setId(idp);
                    puntos.add(punto);
                }
            }
            //Asignamos los puntos de la ruta a la ruta
            ruta.setPuntos(puntos);

            return ruta;
        }
        catch(Exception ex) {
            throw new BadRequestException("Error en los parámetros.");
        }
    }

    /**
     * FUNCIONA
     * @param idRuta
     * @return Mensaje
     */
    @RequestMapping(value = "/devolverRutaSimple", method = RequestMethod.GET)
    @ResponseBody
    public Ruta devolverSimple(Integer idRuta) throws BadRequestException{
        try {
            //Buscamos la ruta por su id
            return service.findOne(idRuta);

        } catch(Exception ex) {
            throw new BadRequestException("Error en los parámetros.");
        }
    }

    /**
     * FUNCIONA (FALTA DEVOLVER PUNTOS - PREGUNTAR A CRISTIAN OBJETO AUTO CARGADO)
     * @return Mensaje
     */
    @RequestMapping(value = "/devolverRutas", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Ruta> devolverTodas() throws BadRequestException{
        try{

            //Devolvemos todas
            return service.findAll();

        }catch(Exception ex) {
            throw new BadRequestException("Error en los parámetros.");
        }
    }

    @RequestMapping(value = "/devolverRutasPorNivel", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Ruta> devolverRutasPorNivel(Integer nivel) throws BadRequestException{
        try{
            //Buscamos las rutas por su nivel
            return service.findByNivel(nivel);

        }catch(Exception ex) {
            throw new BadRequestException("Error en los parámetros.");
        }
    }

    @RequestMapping(value = "/devolverRutasPorValoracion", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Ruta> devolverRutasPorValoracion(Integer valoracion) throws BadRequestException{
        try{
            //Buscamos la ruta por su valoración
            return service.findByValoracion(valoracion);

        }catch(Exception ex) {
            throw new BadRequestException("Error en los parámetros.");
        }
    }

    @RequestMapping(value = "/devolverRutasPorPoblacion", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Ruta> devolverRutasPorPoblacion(String poblacion) throws BadRequestException{
        try{
            //Paso 2 - Buscamos la ruta por su población
            return service.findByPoblacion(poblacion);

        }catch(Exception ex) {
            throw new BadRequestException("Error en los parámetros.");
        }
    }

    @RequestMapping(value = "/devolverRutasPorCodPostal", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Ruta> devolverRutasPorCodPostal(String codPostal) throws BadRequestException{
        try{
            //Buscamos la ruta por su código postal
            return service.findByCodPostal(codPostal);

        }catch(Exception ex) {
            throw new BadRequestException("Error en los parámetros.");
        }
    }

    @RequestMapping(value = "/devolverRutasPorUsuario", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Ruta> devolverRutasPorUsuario(String usuario) throws BadRequestException{
        try{
            //Buscamos la ruta por su usuario
            return service.findByUsuario(usuario);

        }catch(Exception ex) {
            throw new BadRequestException("Error en los parámetros.");
        }
    }
}

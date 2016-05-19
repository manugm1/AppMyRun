package hello.api.punto;

import hello.api.exceptions.BadRequestException;
import hello.api.exceptions.OKException;
import hello.api.exceptions.UnauthorizedException;
import hello.api.ruta.Ruta;
import hello.api.ruta.RutaService;
import hello.api.usuario.User;
import hello.api.usuario.UserService;
import hello.lib.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


/**
 * Created by Javier on 04/05/2016.
 */
@RestController
@RequestMapping("/api/punto")
public class PuntoController
{

    @RequestMapping(value = "/asignarPuntos",method = POST)
    @ResponseBody
    public void asignar(Integer idRuta, String nombre, String descripcion, String foto, Float coordx, Float coordy, String email, String password) throws UnauthorizedException, BadRequestException, OKException {
        try {
            User usuario = userDao.findOne(email);
            if (usuario.getPassword().equals(password)) {

                Ruta ruta = rutaService.findOne(idRuta);
                if (ruta.getFk_usuario().equals(email)) {
                    Punto punto = new Punto(coordx, coordy, nombre, foto, descripcion);
                    puntoDao.save(punto);

                    RutaHasPunto rutahaspunto = new RutaHasPunto();
                    rutahaspunto.setPunto(punto);
                    rutahaspunto.setRuta(ruta);

                    PuntoRuta pr = new PuntoRuta();
                    pr.setPk(rutahaspunto);

                    puntoRutaDao.save(pr);

                    throw new OKException("Punto introducido correctamente.");
                } else {
                    throw new UnauthorizedException("El usuario no es dueño de la ruta.");
                }
            } else {
                throw new BadRequestException("Error en los parámetros del usuario.");
            }
        }
        //Aquí capturamos y enviamos
        catch(OKException ex){
            throw new OKException(ex.getMessage());
        }
        catch(UnauthorizedException ex){
            throw new UnauthorizedException(ex.getMessage());
        }
        catch(BadRequestException ex){
            throw new BadRequestException(ex.getMessage());
        }
        //Para cualquier otra excepción, supondremos que se debe a error de cliente producidos en los parámetros en vez de mostrar
        //el ex.getMessage() ya que la excepción podría darse por ejemplo si se viola la integridad en los
        //objetos many to many. Pero esto también es un error provocado en la petición, así nos aseguramos
        //siempre que el error en la invocación es provocado por el cliente.
        //Se ignoran errores propios de servidor como el acceso a la base de datos, etc.
        catch(Exception ex){
            throw new BadRequestException("Error en los parámetros.");
        }
    }

    @RequestMapping(value = "/devolverPuntos")
    @ResponseBody
    public ArrayList<Punto> devolver(Integer idRuta) throws BadRequestException
    {
        try {
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
            return puntos;

        }catch(Exception ex){
            throw new BadRequestException("Error en los parámetros.");
        }
    }


    @RequestMapping(value = "/crearPunto",method = POST)
    @ResponseBody
    public void crearPunto(String nombre, String descripcion, String foto, Float coordx, Float coordy) throws BadRequestException, OKException {
        try {

                    Punto punto = new Punto(coordx, coordy, nombre, foto, descripcion);
                    puntoDao.save(punto);

                    throw new OKException("Punto introducido correctamente.");

        }
        //Aquí capturamos y enviamos
        catch(OKException ex){
            throw new OKException(ex.getMessage());
        }
        //Para cualquier otra excepción, supondremos que se debe a error de cliente producidos en los parámetros en vez de mostrar
        //el ex.getMessage() ya que la excepción podría darse por ejemplo si se viola la integridad en los
        //objetos many to many. Pero esto también es un error provocado en la petición, así nos aseguramos
        //siempre que el error en la invocación es provocado por el cliente.
        //Se ignoran errores propios de servidor como el acceso a la base de datos, etc.
        catch(Exception ex){
            throw new BadRequestException("Error en los parámetros.");
        }
    }


    @RequestMapping(value = "/asignarPuntoSimple",method = POST)
    @ResponseBody
    public void asignarSimple(Integer idRuta, Integer idPunto, String email, String password) throws UnauthorizedException, BadRequestException, OKException {
        try {
            User usuario = userDao.findOne(email);
            if (usuario.getPassword().equals(password)) {

                Ruta ruta = rutaService.findOne(idRuta);
                if (ruta.getFk_usuario().equals(email)) {
                    Punto punto = puntoDao.findOne(idPunto);

                    RutaHasPunto rutahaspunto = new RutaHasPunto();
                    rutahaspunto.setPunto(punto);
                    rutahaspunto.setRuta(ruta);

                    PuntoRuta pr = new PuntoRuta();
                    pr.setPk(rutahaspunto);

                    puntoRutaDao.save(pr);

                    throw new OKException("Punto relacionado correctamente.");
                } else {
                    throw new UnauthorizedException("El usuario no es dueño de la ruta.");
                }
            } else {
                throw new BadRequestException("Error en los parámetros del usuario.");
            }
        }
        //Aquí capturamos y enviamos
        catch(OKException ex){
            throw new OKException(ex.getMessage());
        }
        catch(UnauthorizedException ex){
            throw new UnauthorizedException(ex.getMessage());
        }
        catch(BadRequestException ex){
            throw new BadRequestException(ex.getMessage());
        }
        //Para cualquier otra excepción, supondremos que se debe a error de cliente producidos en los parámetros en vez de mostrar
        //el ex.getMessage() ya que la excepción podría darse por ejemplo si se viola la integridad en los
        //objetos many to many. Pero esto también es un error provocado en la petición, así nos aseguramos
        //siempre que el error en la invocación es provocado por el cliente.
        //Se ignoran errores propios de servidor como el acceso a la base de datos, etc.
        catch(Exception ex){
            throw new BadRequestException("Error en los parámetros.");
        }
    }

    @Autowired
    private UserService userDao;

    @Autowired
    private PuntoDao puntoDao;

    @Autowired
    private PuntoRutaDao puntoRutaDao;

    @Autowired
    private RutaService rutaService;

}

package hello.api.punto;

import hello.api.ruta.Ruta;
import hello.api.ruta.RutaService;
import hello.api.usuario.User;
import hello.api.usuario.UserDao;
import hello.lib.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    public Mensaje asignar(Integer idRuta, String nombre, String descripcion, String foto, Float coordx, Float coordy, String email, String password)
    {
        Mensaje mens = new Mensaje(400, "Error en los parámetros");


        User usuario= userDao.findOne(email);

        try {
            if (usuario.getPassword().equals(password)) {

                Ruta ruta = rutaService.findOne(idRuta);
                if(ruta.getFk_usuario().equals(email))
                {
                    Punto punto = new Punto(coordx, coordy, nombre, foto, descripcion);
                    puntoDao.save(punto);


                    RutaHasPunto rutahaspunto = new RutaHasPunto();
                    rutahaspunto.setPunto(punto);
                    rutahaspunto.setRuta(ruta);

                    PuntoRuta pr = new PuntoRuta();
                    pr.setPk(rutahaspunto);

                    puntoRutaDao.save(pr);


                    mens.setInfo("Punto introducido correctamente");
                    mens.setCodigo(200);
                }
                else
                {
                    mens.setInfo("El usuario no es dueño de la Ruta");
                    mens.setCodigo(402);
                }
            }
            else
            {
                mens.setInfo("Usuario incorrecto");
                mens.setCodigo(401);
            }
        }catch (Exception ex)
        {
            System.out.println("Salta excepcion");
        }


        return mens;
    }

    @Autowired
    private UserDao userDao;

    @Autowired
    private PuntoDao puntoDao;

    @Autowired
    private PuntoRutaDao puntoRutaDao;

    @Autowired
    private RutaService rutaService;

}

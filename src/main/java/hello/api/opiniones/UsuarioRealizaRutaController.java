package hello.api.opiniones;

import hello.api.exceptions.DataBaseException;
import hello.api.exceptions.UnauthorizedException;
import hello.api.punto.PuntoDao;
import hello.api.ruta.Ruta;
import hello.api.ruta.RutaRepository;
import hello.api.usuario.UserService;
import hello.lib.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Javier on 18/05/2016.
 */

@RestController
@RequestMapping("/api/ruta")
public class UsuarioRealizaRutaController
{

    @RequestMapping(value = "/asignarRutaUsuario", method = POST)
    @ResponseBody
    public Mensaje asignarRuta(int idRuta, String email, int idPunto, String foto, String coment) throws DataBaseException
    {
        Mensaje mens=new Mensaje(400, "Fallo en parámetros");

        try {


            UsuarioRealizaRuta urr = new UsuarioRealizaRuta();

            UsuarioRealizaRutaPK pk = new UsuarioRealizaRutaPK();
            Ruta ruta = rutaRep.findOne(idRuta);
            pk.setRuta(ruta);
            pk.setRuta2(ruta);

            pk.setPunto(puntoDao.findOne(idPunto));
            pk.setUsuario(userDao.findOne(email));

            urr.setComentario(coment);
            urr.setFoto(foto);
            urr.setPk(pk);

            urrDao.save(urr);
            mens.setCodigo(200);
            mens.setInfo("Relacionado ruta con usuario en punto con foto");

        } catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw new DataBaseException("Fallo al conectar con la BD: "+e.getMessage());
        }

        return mens;
    }

    @Autowired
    private UserService userDao;

    @Autowired
    private UsuarioRealizaRutaDao urrDao;

    @Autowired
    private RutaRepository rutaRep;

    @Autowired
    private PuntoDao puntoDao;
}

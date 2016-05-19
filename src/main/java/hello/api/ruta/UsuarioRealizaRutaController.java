package hello.api.ruta;

import hello.api.exceptions.DataBaseException;
import hello.api.punto.PuntoDao;
import hello.api.usuario.User;
import hello.api.usuario.UserService;
import hello.lib.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;

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
            java.util.Date date= new java.util.Date();
            urr.setTiempo(new Timestamp(date.getTime()));
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



    @RequestMapping(value = "/rutasRecorridasPorUser")
    @ResponseBody
    public ArrayList<Ruta> dameRutasRecorridas(String email, String pass)
    {
        User usuario= userDao.findOne(email);
        ArrayList<Ruta> rutas=new ArrayList<Ruta>();

        if(userDao.verifyUser(email, pass))
        {
           ArrayList<UsuarioRealizaRuta> urrList= (ArrayList)urrDao.findAll();

            for(int i=0;i<urrList.size();i++)
            {

                if(email.equals(urrList.get(i).getPk().getUsuario().getEmail()))
                {
                    Ruta ruta=new Ruta();
                    Ruta aux=urrList.get(i).getPk().getRuta();

                    ruta.setDescripcion(aux.getDescripcion());
                    ruta.setDificultad(aux.getDificultad());
                    ruta.setFk_poblacion(aux.getFk_poblacion());
                    ruta.setFk_usuario(aux.getFk_usuario());
                    ruta.setId(aux.getId());
                    ruta.setNombre(aux.getNombre());
                    ruta.setTipo(aux.getTipo());
                    ruta.setPopularidad(aux.getPopularidad());



                    if(!rutas.contains(ruta)) {
                        rutas.add(ruta);
                    }

                }
            }
        }

        return rutas;

    }


    @RequestMapping(value = "/dameUsuarioRealizaRuta")
    @ResponseBody
    public ArrayList<URRSimple> dameUsuarioRealizaRuta()
    {

            ArrayList<UsuarioRealizaRuta> aux= (ArrayList)urrDao.findAll();
            ArrayList<URRSimple> urrList=new ArrayList<URRSimple>();

            for(int i=0;i<aux.size();i++)
            {
                URRSimple urr=new URRSimple();
                UsuarioRealizaRuta urrAux=aux.get(i);

                urr.setEmail(urrAux.getPk().getUsuario().getEmail());
                urr.setIdPunto(urrAux.getPk().getPunto().getId());
                urr.setIdRuta(urrAux.getPk().getRuta().getId());
                urr.setFoto(urrAux.getFoto());
                urr.setComentario(urrAux.getComentario());
                urr.setTiempo(urrAux.getTiempo());
                urrList.add(urr);
            }
           return urrList;

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

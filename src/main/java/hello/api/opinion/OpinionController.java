package hello.api.opinion;

import hello.api.exceptions.BadRequestException;
import hello.api.exceptions.DataBaseException;
import hello.api.exceptions.OKException;
import hello.api.ruta.*;
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
 * Created by wilad on 19/05/2016.
 */

@RestController
@RequestMapping("/api/opinion")
public class OpinionController {

    @RequestMapping(value = "/opinionRuta", method = POST)
    @ResponseBody
    public void opinionRuta(int idRuta, String email, int puntuacion, int nivel, String passwordUsuario, String comentario) throws DataBaseException, BadRequestException, OKException {


        try {

            if(userDao.verifyUser(email, passwordUsuario)) {

                Opinion op = new Opinion();

                OpinionPK pk = new OpinionPK();
                Ruta ruta = rutaRepo.findOne(idRuta);
                pk.setRuta(ruta);


                pk.setUsuario(userDao.findOne(email));

                op.setComentario(comentario);
                op.setPuntuacion(puntuacion);
                op.setNivel(nivel);

                op.setPk(pk);


                opDao.save(op);
                throw new OKException("Creada opinión correctamente");

            }else
            {
                throw new BadRequestException("Error en los parámetros del usuario.");
            }

        }catch(OKException e)
        {
            System.out.println(e.getMessage());
            throw new OKException(e.getMessage());
        } catch(BadRequestException e)
        {
            System.out.println(e.getMessage());
            throw new BadRequestException(e.getMessage());
        } catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw new DataBaseException("Fallo al conectar con la BD: "+e.getMessage());
        }


    }

    @RequestMapping(value = "/devolverOpinionesRuta")
    @ResponseBody
    public ArrayList<OpinionSimple> devolverOpinionesRuta(int idRuta)
    {
        Ruta ruta = rutaRepo.findOne(idRuta);
        ArrayList<OpinionSimple> opinionesRuta =new ArrayList<OpinionSimple>();
        ArrayList<Opinion> ops= (ArrayList)opDao.findAll();

            for(int i=0;i<ops.size();i++)
            {

                if(ruta.equals(ops.get(i).getPk().getRuta()))
                {
                    Opinion aux=ops.get(i);
                    OpinionSimple op =new OpinionSimple();
                    
                    op.setEmail(aux.getPk().getUsuario().getEmail());
                    op.setIdRuta(aux.getPk().getRuta().getId());
                    op.setId(aux.getPk().getId());
                    op.setComentario(aux.getComentario());
                    op.setNivel(aux.getNivel());
                    op.setPuntuacion(aux.getPuntuacion());
                    opinionesRuta.add(op);
                }
            }


        return opinionesRuta;

    }

    @Autowired
    private UserService userDao;

    @Autowired
    private OpinionDao opDao;

    @Autowired
    private RutaRepository rutaRepo;

}

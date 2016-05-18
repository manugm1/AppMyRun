package hello.api.usuario;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import hello.api.exceptions.BadRequestException;
import hello.api.exceptions.OKException;
import hello.api.exceptions.UnauthorizedException;
import hello.lib.Mensaje;
import hello.lib.Validaciones;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/usuario")
public class UserController {

    @Autowired
    private UserService service;


    @RequestMapping(value="/registroUsuario",method=RequestMethod.POST )
    @ResponseBody
    public void create(String email, String password, String nombre, String apellido1, String apellido2, Timestamp fecNacimiento, Integer sexo, Integer tipo)
    throws UnauthorizedException, BadRequestException, OKException{
        try {
            User user = null;
            boolean existe;
            Validaciones val=new Validaciones();
            if (val.validaEmail(email)) {

                existe = service.exists(email);

                if (!existe) {
                    user = new User(email, password, nombre, apellido1, apellido2, fecNacimiento, sexo);
                    user.setActivo(1);
                    if (tipo != null)
                        user.setTipo(tipo);
                    service.save(user);
                    throw new OKException("Registro correcto.");
                } else {
                    throw new UnauthorizedException("Usuario existente.");
                }
            } else {
                throw new BadRequestException("Error en los parámetros del usuario.");
            }
        }//Aquí capturamos y enviamos
        catch(OKException ex){
            throw new OKException(ex.getMessage());
        }
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

    @RequestMapping(value="/loginUsuario",method=RequestMethod.POST)
    @ResponseBody
    public void login(String email, String password) throws BadRequestException, OKException {
        try {
            boolean existe = service.verifyUser(email, password);

            if (existe) {
                if (service.activeUser(email,password)) {
                    throw new OKException("Login correcto.");
                }
                else{
                    User user=service.findOne(email);
                    user.setActivo(1);
                    service.save(user);
                    throw new OKException("Usuario inactivo vuelve a activarse.");
                }
            } else {
                throw new BadRequestException("Error en los parámetros del usuario.");
            }
        }catch(OKException ex){
            throw new OKException(ex.getMessage());
        }
        catch(BadRequestException ex){
            throw new BadRequestException(ex.getMessage());
        }
        catch(Exception ex){
            throw new BadRequestException("Error en los parámetros.");
        }
    }

    @RequestMapping(value="/modificaUsuario",method=RequestMethod.PUT )
    @ResponseBody
    public void modify(String email, String passactual, String nombre, String apellido1, String apellido2, Timestamp fecNacimiento, Integer sexo, String nick, Float peso, Integer estatura, String codpostal,Integer nivel , String passnew)
    throws UnauthorizedException, BadRequestException, OKException{

        try {
            boolean existe;
            existe=service.verifyUser(email,passactual);

            if(existe) {
                if(service.activeUser(email,passactual)){
                        User user=service.findOne(email);
                        if (passnew != null && !(passnew.equals(passactual)))
                            user.setPassword(passnew);
                        if (nombre != null)
                            user.setNombre(nombre);
                        if (apellido1 != null)
                            user.setApellido1(apellido1);
                        if (apellido2 != null)
                            user.setApellido2(apellido2);
                        if (fecNacimiento != null)
                            user.setFecNacimiento(fecNacimiento);
                        if (sexo != null)
                            user.setSexo(sexo);
                        if (nick != null)
                            user.setNick(nick);
                        if (peso != null)
                            user.setPeso(peso);
                        if (estatura != null)
                            user.setEstatura(estatura);
                        if (codpostal != null)
                            user.setCodPostal(codpostal);
                        if (nivel != null)
                            user.setNivel(nivel);
                        service.save(user);
                        throw new OKException("Modificaciones realizadas correctamente.");
                }
                else{
                    throw new UnauthorizedException("Imposible modificar usuario inactivo.");
                }
            }
            else
            {
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
        catch(Exception ex){
            throw new BadRequestException("Error en los parámetros.");
        }
    }

    @RequestMapping(value="/darBaja",method=RequestMethod.PUT)
    @ResponseBody
    public void baja(String email, String password) throws BadRequestException, OKException{
        try {
            boolean existe = service.verifyUser(email, password);

            if (existe) {
                User user=service.findOne(email);
                user.setActivo(0);
                service.save(user);
                throw new OKException("Usuario deshabilitado correctamente.");
            } else {
                throw new BadRequestException("Error en los parámetros del usuario.");
            }
        }catch(OKException ex){
            throw new OKException(ex.getMessage());
        }
        catch(BadRequestException ex){
            throw new BadRequestException(ex.getMessage());
        }
        catch(Exception ex){
            throw new BadRequestException("Error en los parámetros.");
        }
    }


}

package hello.api.usuario;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

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

    public Mensaje create(String email, String password, String nombre, String apellido1, String apellido2, Timestamp fecNacimiento, Integer sexo) {

        User user = null;
        Mensaje mens=new Mensaje(401, "Error en los parámetros");
        boolean existe,valido;
        Validaciones val=new Validaciones();

        if(val.validaEmail(email)) {

            try {
                existe = service.exists(email);

                if (!existe) {
                    user = new User(email, password, nombre, apellido1, apellido2, fecNacimiento, sexo);
                    user.setActivo(1);
                    service.save(user);
                    mens.setCodigo(200);
                    mens.setInfo("Registro correcto");
                } else {
                    mens.setCodigo(401);
                    mens.setInfo("Usuario existente");
                }

            } catch (Exception ex) {
                System.out.println("Salta excepción");
                System.out.println(ex.getMessage());

            }
        }
        else{
            mens.setCodigo(401);
            mens.setInfo("Error. Email no valido");
        }
        return mens;
    }

    @RequestMapping(value="/loginUsuario",method=RequestMethod.POST)

    public Mensaje login(String email, String password){

        Mensaje mens=new Mensaje(401, "Error en los parámetros");
        try {
            boolean existe = service.verifyUser(email, password);

            if (existe) {
                if (service.activeUser(email,password)) {
                    mens.setCodigo(200);
                    mens.setInfo("Login correcto");
                }
                else{
                    User user=service.findOne(email);
                    user.setActivo(1);
                    service.save(user);
                    mens.setCodigo(200);
                    mens.setInfo("Usuario inactivo vuelve a activarse");
                }
            } else {
                mens.setCodigo(401);
                mens.setInfo("El usuario o contraseña no es correcto");
            }
        }catch (Exception ex){
            return mens;
        }

        return mens;

    }

    @RequestMapping(value="/modificaUsuario",method=RequestMethod.PUT )

    public Mensaje modify(String email, String passactual, String nombre, String apellido1, String apellido2, Timestamp fecNacimiento, Integer sexo, String nick, Float peso, Integer estatura, String codpostal,Integer nivel , String passnew) {

        Mensaje mens=new Mensaje(401, "Error en los parámetros");
        boolean existe;

        try {
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
                        mens.setCodigo(200);
                        mens.setInfo("Modificaciones realizadas correctamente");

                }
                else{
                    mens.setCodigo(401);
                    mens.setInfo("Imposible modificar usuario inactivo");
                }
            }
            else
            {
                mens.setCodigo(401);
                mens.setInfo("El usuario o contraseña no es correcto");
            }

        }
        catch (Exception ex) {
            return mens;

        }
        return mens;
    }

    @RequestMapping(value="/darBaja",method=RequestMethod.PUT)

    public Mensaje baja(String email, String password){
        Mensaje mens=new Mensaje(401, "Error en los parámetros");
        try {
            boolean existe = service.verifyUser(email, password);

            if (existe) {
                User user=service.findOne(email);
                user.setActivo(0);
                service.save(user);
                mens.setCodigo(200);
                mens.setInfo("Usuario deshabilitado correctamente");
            } else {
                mens.setCodigo(401);
                mens.setInfo("El usuario o contraseña no es correcto");
            }
        }catch (Exception ex){
            return mens;
        }

        return mens;

    }


}

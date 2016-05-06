package hello.lib;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by AdelZB on 06/05/2016.
 */
public class Validaciones {

    public Validaciones(){ }

    public Boolean validaEmail(String email){

        Boolean valido;
        Pattern pattern= Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather=pattern.matcher(email);

        valido=mather.find();

        return valido;
    }



}

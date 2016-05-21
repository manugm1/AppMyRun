package hello.api.estadisticas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by wilad on 21/05/2016.
 */

@RestController
@RequestMapping("/api/estadisticas")
public class EstadisticaEdadController {

    @RequestMapping(value = "/devolverEstadisticasEdad")
    @ResponseBody
    public ArrayList<EstadisticaEdad> devolverEstadisticasEdadTodas()
    {

        ArrayList<EstadisticaEdad> estadisticas = new ArrayList<>();
        ArrayList<EstadisticaEdad> ops= (ArrayList)estDao.findAll();

        for(int i=0;i<ops.size();i++)
        {
            EstadisticaEdad puesEsta = new EstadisticaEdad(ops.get(i).getEdad1() , ops.get(i).getEdad2(), ops.get(i).getEdad3(), ops.get(i).getEdad4(), ops.get(i).getEdad5(), ops.get(i).getEdad6(), ops.get(i).getEdad7());

            estadisticas.add(puesEsta);

        }


        return estadisticas;

    }

    @Autowired
    private EstadisticaEdadDao estDao;

}
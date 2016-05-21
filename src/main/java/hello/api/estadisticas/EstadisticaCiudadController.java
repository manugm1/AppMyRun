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
public class EstadisticaCiudadController {

    @RequestMapping(value = "/devolverEstadisticasCiudad")
    @ResponseBody
    public ArrayList<EstadisticaCiudad> devolverEstadisticasCiudadTodas()
    {

        ArrayList<EstadisticaCiudad> estadisticasCity = new ArrayList<>();
        ArrayList<EstadisticaCiudad> ops= (ArrayList)estDao.findAll();

        for(int i=0;i<ops.size();i++)
        {
            EstadisticaCiudad puesEsta = new EstadisticaCiudad(ops.get(i).getCiudad(), ops.get(i).getCantidad());
            //puesEsta = ops.get(i);
                estadisticasCity.add(puesEsta);

        }


        return estadisticasCity;

    }

    @RequestMapping(value = "/devolverEstadisticasCiudadEligiendo")
    @ResponseBody
    public EstadisticaCiudad devolverEstadisticasCiudadUna(String ciudad)
    {

        EstadisticaCiudad devolver = new EstadisticaCiudad();
        ArrayList<EstadisticaCiudad> ops= (ArrayList)estDao.findAll();

        for(int i=0;i<ops.size();i++)
        {
            if(ciudad.equals(ops.get(i).getCiudad())) {
                devolver=ops.get(i);
            }

        }


        return devolver;

    }



    @Autowired
    private EstadisticaCiudadDao estDao;

}

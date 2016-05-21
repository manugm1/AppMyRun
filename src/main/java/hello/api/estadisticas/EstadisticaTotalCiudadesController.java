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
public class EstadisticaTotalCiudadesController {

    @RequestMapping(value = "/devolverEstadisticasTotalCiudad")
    @ResponseBody
    public ArrayList<EstadisticaTotalCiudades> devolverEstadisticasTotalCiudadesTodas()
    {

        ArrayList<EstadisticaTotalCiudades> estadisticas = new ArrayList<>();
        ArrayList<EstadisticaTotalCiudades> ops= (ArrayList)estDao.findAll();

        for(int i=0;i<ops.size();i++)
        {
            EstadisticaTotalCiudades puesEsta = new EstadisticaTotalCiudades(ops.get(i).getTotal());

            estadisticas.add(puesEsta);

        }


        return estadisticas;

    }

    @Autowired
    private EstadisticaTotalCiudadesDao estDao;
}

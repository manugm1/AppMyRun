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
public class EstadisticaTotalRutasController {

    @RequestMapping(value = "/devolverEstadisticasTotalRutas")
    @ResponseBody
    public ArrayList<EstadisticaTotalRutas> devolverEstadisticasTotalRutasTodas()
    {

        ArrayList<EstadisticaTotalRutas> estadisticas = new ArrayList<>();
        ArrayList<EstadisticaTotalRutas> ops= (ArrayList)estDao.findAll();

        for(int i=0;i<ops.size();i++)
        {
            EstadisticaTotalRutas puesEsta = new EstadisticaTotalRutas(ops.get(i).getTotal());

            estadisticas.add(puesEsta);

        }


        return estadisticas;

    }

    @Autowired
    private EstadisticaTotalRutasDao estDao;
}

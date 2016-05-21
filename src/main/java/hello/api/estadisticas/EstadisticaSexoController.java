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
public class EstadisticaSexoController {

    @RequestMapping(value = "/devolverEstadisticasSexo")
    @ResponseBody
    public ArrayList<EstadisticaSexo> devolverEstadisticaSexoTodas()
    {

        ArrayList<EstadisticaSexo> estadisticas= new ArrayList<>();
        ArrayList<EstadisticaSexo> ops= (ArrayList)estDao.findAll();

        for(int i=0;i<ops.size();i++)
        {
            EstadisticaSexo puesEsta = new EstadisticaSexo(ops.get(i).getPorcenhombres());

            estadisticas.add(puesEsta);

        }


        return estadisticas;

    }

    @Autowired
    private EstadisticaSexoDao estDao;

}
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
public class EstadisticaTotalUsuariosController {

    @RequestMapping(value = "/devolverEstadisticasTotalUsuarios")
    @ResponseBody
    public ArrayList<EstadisticaTotalUsuarios> devolverEstadisticasTotalUsuariosTodas()
    {

        ArrayList<EstadisticaTotalUsuarios> estadisticas = new ArrayList<>();
        ArrayList<EstadisticaTotalUsuarios> ops= (ArrayList)estDao.findAll();

        for(int i=0;i<ops.size();i++)
        {
            EstadisticaTotalUsuarios puesEsta = new EstadisticaTotalUsuarios(ops.get(i).getTotal());

            estadisticas.add(puesEsta);

        }


        return estadisticas;

    }

    @Autowired
    private EstadisticaTotalUsuariosDao estDao;
}

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
public class EstadisticaCodPostalController {

        @RequestMapping(value = "/devolverEstadisticasCopPostal")
        @ResponseBody
        public ArrayList<EstadisticaCodPostal> devolverEstadisticasCodigoPostalTodas()
        {

            ArrayList<EstadisticaCodPostal> estadisticas = new ArrayList<>();
            ArrayList<EstadisticaCodPostal> ops= (ArrayList)estDao.findAll();

            for(int i=0;i<ops.size();i++)
            {
                EstadisticaCodPostal puesEsta = new EstadisticaCodPostal(ops.get(i).getCodpostal(), ops.get(i).getCantidad());
                //puesEsta = ops.get(i);
                estadisticas.add(puesEsta);

            }


            return estadisticas;

        }

        @RequestMapping(value = "/devolverEstadisticasCiudadEligiendoCodigo")
        @ResponseBody
        public EstadisticaCodPostal devolverEstadisticasConCodigo(String codigo)
        {

            EstadisticaCodPostal devolver = new EstadisticaCodPostal();
            ArrayList<EstadisticaCodPostal> ops= (ArrayList)estDao.findAll();

            for(int i=0;i<ops.size();i++)
            {
                if(codigo.equals(ops.get(i).getCodpostal())) {
                    devolver=ops.get(i);
                }

            }


            return devolver;

        }



        @Autowired
        private EstadisticaCodPostalDao estDao;

    }


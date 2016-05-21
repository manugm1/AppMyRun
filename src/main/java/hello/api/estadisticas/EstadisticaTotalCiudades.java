package hello.api.estadisticas;

/**
 * Created by wilad on 21/05/2016.
 */

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@Embeddable
@Entity
@Table(name = "totalciudades")
public class EstadisticaTotalCiudades {
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @NotNull
    @Id
    private Integer total;


    public EstadisticaTotalCiudades(Integer total) {
        this.total = total;
    }
    public EstadisticaTotalCiudades() {

    }
    //ESTE METODO DEVUELVE LA HORA DEL SISTEMA ACTUAL COMO UN TIMESTAMP
    public Timestamp getUltfecha() {
        java.util.Date date= new java.util.Date();
        return (new Timestamp(date.getTime()));
    }

    @NotNull
    private Timestamp ultfecha;
}

package hello.api.estadisticas;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Created by wilad on 21/05/2016.
 */


@Embeddable
@Entity
@Table(name = "totalrutas")
public class EstadisticaTotalRutas {
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @NotNull
    @Id
    private Integer total;


    public EstadisticaTotalRutas(Integer total) {
        this.total = total;
    }
    public EstadisticaTotalRutas() {

    }
    //ESTE METODO DEVUELVE LA HORA DEL SISTEMA ACTUAL COMO UN TIMESTAMP
    public Timestamp getUltfecha() {
        java.util.Date date= new java.util.Date();
        return (new Timestamp(date.getTime()));
    }

    @NotNull
    private Timestamp ultfecha;
}

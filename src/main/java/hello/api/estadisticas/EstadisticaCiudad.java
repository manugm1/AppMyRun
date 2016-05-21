package hello.api.estadisticas;

/**
 * Created by wilad on 21/05/2016.
 */

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


/**
 * Created by wilad on 19/05/2016.
 */
@Embeddable
@Entity
@Table(name = "estadisticaciudad")

public class EstadisticaCiudad {

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @NotNull
    @Id
    private String ciudad;

    public EstadisticaCiudad(String ciudad,Integer cantidad) {
        this.ciudad = ciudad;
        this.cantidad = cantidad;
    }
    public EstadisticaCiudad() {

    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @NotNull
    private Integer cantidad;

    //ESTE METODO DEVUELVE LA HORA DEL SISTEMA ACTUAL COMO UN TIMESTAMP
    public Timestamp getUltfecha() {
        java.util.Date date= new java.util.Date();
        return (new Timestamp(date.getTime()));
    }

    @NotNull
    private Timestamp ultfecha;


}

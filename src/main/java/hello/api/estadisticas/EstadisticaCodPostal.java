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
@Table(name = "estadisticacodpostal")
public class EstadisticaCodPostal {


    @NotNull
    @Id
    private String codpostal;
    @NotNull
    private Integer cantidad;
    @NotNull
    private Timestamp ultfecha;

    public String getCodpostal() {
        return codpostal;
    }

    public void setCodpostal(String codpostal) {
        this.codpostal = codpostal;
    }

        public EstadisticaCodPostal(String codpostal,Integer cantidad) {
            this.codpostal = codpostal;
            this.cantidad = cantidad;
        }
        public EstadisticaCodPostal() {

        }

        public Integer getCantidad() {
            return cantidad;
        }

        public void setCantidad(Integer cantidad) {
            this.cantidad = cantidad;
        }

        //ESTE METODO DEVUELVE LA HORA DEL SISTEMA ACTUAL COMO UN TIMESTAMP
        public Timestamp getUltfecha() {
            java.util.Date date= new java.util.Date();
            return (new Timestamp(date.getTime()));
        }

}

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
@Table(name = "estadisticaedad")
public class EstadisticaEdad {


    public Integer getEdad1() {
        return edad1;
    }

    public void setEdad1(Integer edad1) {
        this.edad1 = edad1;
    }

    public Integer getEdad2() {
        return edad2;
    }

    public void setEdad2(Integer edad2) {
        this.edad2 = edad2;
    }

    public Integer getEdad4() {
        return edad4;
    }

    public void setEdad4(Integer edad4) {
        this.edad4 = edad4;
    }

    public Integer getEdad3() {
        return edad3;
    }

    public void setEdad3(Integer edad3) {
        this.edad3 = edad3;
    }

    public Integer getEdad5() {
        return edad5;
    }

    public void setEdad5(Integer edad5) {
        this.edad5 = edad5;
    }

    public Integer getEdad6() {
        return edad6;
    }

    public void setEdad6(Integer edad6) {
        this.edad6 = edad6;
    }

    public Integer getEdad7() {
        return edad7;
    }

    public void setEdad7(Integer edad7) {
        this.edad7 = edad7;
    }

    @NotNull
    @Id
    private Integer edad1;
    @NotNull
    private Integer edad2;
    @NotNull
    private Integer edad3;
    @NotNull
    private Integer edad4;
    @NotNull
    private Integer edad5;
    @NotNull
    private Integer edad6;
    @NotNull
    private Integer edad7;
    @NotNull
    private Timestamp ultfecha;


    public EstadisticaEdad(Integer edad1, Integer edad2,Integer edad3,Integer edad4,Integer edad5,Integer edad6,Integer edad7) {
        this.edad1 = edad1;
        this.edad1 = edad2;
        this.edad1 = edad3;
        this.edad1 = edad4;
        this.edad1 = edad5;
        this.edad1 = edad6;
        this.edad1 = edad7;
    }
    public EstadisticaEdad() {

    }
    //ESTE METODO DEVUELVE LA HORA DEL SISTEMA ACTUAL COMO UN TIMESTAMP
    public Timestamp getUltfecha() {
        java.util.Date date= new java.util.Date();
        return (new Timestamp(date.getTime()));
    }

}
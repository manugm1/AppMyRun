package hello.api.poblacion;

import javax.persistence.*;

/**
 * Clase modelo de poblacion
 * Created by manuelgm on 06/05/2016.
 */
@Entity
@Table(name = "poblacion")
public class Poblacion {
    @Id
    @Column(name = "cod_postal")
    private String codPostal;
    private String poblacion;
    private String provincia;
    private String pais;


    public Poblacion(){

    }

    public Poblacion(String codPostal, String poblacion, String provincia, String pais){
        this.codPostal = codPostal;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.pais = pais;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}

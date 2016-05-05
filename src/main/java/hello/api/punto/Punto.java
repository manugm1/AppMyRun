package hello.api.punto;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Embeddable
@Entity
@Table(name = "punto")
public class Punto {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotNull
    private Float coordx;
    @NotNull
    private Float coordy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getCoordx() {
        return coordx;
    }

    public void setCoordx(Float coordx) {
        this.coordx = coordx;
    }

    public Punto()
    {

    }

    public Punto(Float coordx, Float coordy, String nombre, String foto, String descripcion) {

        this.coordx = coordx;
        this.coordy = coordy;
        this.nombre = nombre;
        this.foto = foto;
        this.descripcion = descripcion;
    }

    public Float getCoordy() {
        return coordy;
    }

    public void setCoordy(Float coordy) {
        this.coordy = coordy;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @NotNull
    private String nombre;
    private String foto;
    private String descripcion;

}

package hello.api.opinion;


import hello.api.ruta.UsuarioRealizaRutaPK;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by wilad on 19/05/2016.
 */
@Embeddable
@Entity
@Table(name = "opinion")
public class Opinion {

    private OpinionPK pk = new OpinionPK();

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    private Integer puntuacion;

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    private Integer nivel;

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    private String comentario;

    @EmbeddedId
    public OpinionPK getPk()
    {
        return pk;
    }

    public void setPk(final OpinionPK  pk)
    {
        this.pk = pk;
    }

}

package hello.api.opinion;

import hello.api.punto.Punto;
import hello.api.ruta.Ruta;
import hello.api.usuario.User;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by wilad on 19/05/2016.
 */
@Embeddable
public class OpinionPK implements Serializable {

    private Ruta ruta;

    private User usuario;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="idruta")
    public Ruta getRuta()
    {
        return ruta;
    }

    public void setRuta(final Ruta ruta)
    {
        this.ruta = ruta;
    }



    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="email")
    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(final User usuario) {
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public OpinionPK()
    {

    }


}

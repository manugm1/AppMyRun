package hello.api.ruta;

import hello.api.punto.Punto;
import hello.api.ruta.Ruta;
import hello.api.usuario.User;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by Javier on 18/05/2016.
 */
@Embeddable
public class UsuarioRealizaRutaPK  implements Serializable
{

    private Ruta ruta;
    private Punto punto;

    private Ruta ruta2;
    private User usuario;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ruta_has_punto_ruta_id")
    public Ruta getRuta()
    {
        return ruta;
    }

    public void setRuta(final Ruta ruta)
    {
        this.ruta = ruta;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ruta_has_punto_punto_id")
    public Punto getPunto() {
        return punto;
    }

    public void setPunto(final Punto punto) {
        this.punto = punto;
    }



    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Usuario_email")
    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(final User usuario) {
        this.usuario = usuario;
    }

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ruta_id")
    public Ruta getRuta2()
    {
        return ruta2;
    }

    public void setRuta2(final Ruta ruta2)
    {
        this.ruta2 = ruta2;
    }


    public UsuarioRealizaRutaPK()
    {

    }

}

package hello.api.ruta;



import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by Javier on 18/05/2016.
 */


@Entity
@Table(name = "usuario_realiza_ruta")
public class UsuarioRealizaRuta
{
    private UsuarioRealizaRutaPK pk = new UsuarioRealizaRutaPK();
    private Timestamp tiempo;
    private String foto;
    private String comentario;


    @EmbeddedId
    public UsuarioRealizaRutaPK getPk()
    {
        return pk;
    }

    public void setPk(final UsuarioRealizaRutaPK  pk)
    {
        this.pk = pk;
    }

    public Timestamp getTiempo() {
        return tiempo;
    }

    public void setTiempo(Timestamp tiempo) {
        this.tiempo = tiempo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}

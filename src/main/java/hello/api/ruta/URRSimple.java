package hello.api.ruta;


import java.sql.Timestamp;

/**
 * Created by Javier on 19/05/2016.
 */
public class URRSimple
{
    String foto;
    String comentario;
    Timestamp tiempo;
    Integer idRuta;
    Integer idPunto;
    String email;

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

    public Timestamp getTiempo() {
        return tiempo;
    }

    public void setTiempo(Timestamp tiempo) {
        this.tiempo = tiempo;
    }

    public Integer getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Integer idRuta) {
        this.idRuta = idRuta;
    }

    public Integer getIdPunto() {
        return idPunto;
    }

    public void setIdPunto(Integer idPunto) {
        this.idPunto = idPunto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

package hello.api.punto;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "ruta_has_punto")
public class PuntoRuta
{
    private RutaHasPunto pk = new RutaHasPunto();

    @EmbeddedId
    public RutaHasPunto  getPk()
    {
    return pk;
    }

    public void setPk(final RutaHasPunto  pk)
    {
    this.pk = pk;
    }

    public PuntoRuta()
    {

    }
}

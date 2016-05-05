package hello.api.punto;

import hello.api.ruta.Ruta;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by Javier on 05/05/2016.
 */

@Embeddable
public class RutaHasPunto implements Serializable
{


        private Ruta ruta;
        private Punto punto;

        @ManyToOne(fetch= FetchType.LAZY)
        @JoinColumn(name="ruta_id")
        public Ruta getRuta()
        {
            return ruta;
        }

        public void setRuta(final Ruta ruta)
        {
            this.ruta = ruta;
        }

        @ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="punto_id")
        public Punto getPunto() {
            return punto;
        }

        public void setPunto(final Punto punto) {
            this.punto = punto;
        }

    public RutaHasPunto()
    {

    }

}

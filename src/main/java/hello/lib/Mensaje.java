package hello.lib;

/**
 * Created by AdelZB on 02/05/2016.
 */
public class Mensaje {
    private Integer codigo;
    private String info;

    public Mensaje(Integer codigo, String info) {
        this.codigo = codigo;
        this.info = info;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

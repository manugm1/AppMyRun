package hello.lib;

/**
 * Created by AdelZB on 02/05/2016.
 */
public class Mensaje {
    private Integer codigo;
    private Object info;

    public Mensaje(Integer codigo, Object info){
        this.codigo = codigo;
        this.info = info;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

}

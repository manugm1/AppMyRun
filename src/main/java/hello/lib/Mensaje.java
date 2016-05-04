package hello.lib;

/**
 * Created by AdelZB on 02/05/2016.
 */
public class Mensaje {
    private Integer codigo;
    private String info;
    private Object infoObj;

    public Mensaje(Integer codigo, String info) {
        this.codigo = codigo;
        this.info = info;
    }

    public Mensaje(Integer codigo, Object info){
        this.codigo = codigo;
        this.infoObj = info;
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

    public Object getInfoObj() {
        return infoObj;
    }

    public void setInfoObj(Object infoObj) {
        this.infoObj = infoObj;
    }
}

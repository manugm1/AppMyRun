package hello.api.ruta;

import hello.api.punto.Punto;

import javax.persistence.*;
import java.util.List;

/**
 * Clase modelo de ruta
 * Created by manuelgm on 04/05/2016.
 */
@Entity
@Table(name = "ruta")
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombre;
    private int dificultad;
    private int popularidad;
    private String descripcion;
    private int tipo;
    private String fk_usuario;




    public Ruta(){

    }

    public Ruta(int id, String nombre, int dificultad, int popularidad, String descripcion, int tipo, String fk_usuario) {
        this.id = id;
        this.nombre = nombre;
        this.dificultad = dificultad;
        this.popularidad = popularidad;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.fk_usuario = fk_usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public int getPopularidad() {
        return popularidad;
    }

    public void setPopularidad(int popularidad) {
        this.popularidad = popularidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getFk_usuario() {
        return fk_usuario;
    }

    public void setFk_usuario(String fk_usuario) {
        this.fk_usuario = fk_usuario;
    }

}

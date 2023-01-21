package es.cifpcm.vidicdaliborkamiali.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "provincias")
public class Provincia {
    @Id
    @Column(name = "id_provincia", nullable = false)
    private Short id;

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
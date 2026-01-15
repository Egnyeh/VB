package ies.sequeros.infraestructure.entities;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "categoria")
public class CategoriaJPA {
    @Id
    private UUID id;

    private String nombre;



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    }




package ies.sequeros.infraestructure.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "producto")
public class ProductoJPA {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "precio", nullable = false)
    private BigDecimal precio;

    // Con el lazy no cargamos automáticamente la categoría al cargar el producto
    // Optional = false porque un producto siempre debe tener una categoría
    // JoinColumn para definir la columna de la clave foránea
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaJPA categoria;

    public ProductoJPA() {}

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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public CategoriaJPA getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaJPA categoria) {
        this.categoria = categoria;
    }
}


package ies.sequeros.infraestructure.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "categoria")
public class CategoriaJPA {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    // Esta lista contendrá los productos asociados a esta categoría
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductoJPA> productos = new ArrayList<>();

    public CategoriaJPA() {}

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

    public List<ProductoJPA> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoJPA> productos) {
        this.productos = productos;
    }
 // Métodos auxiliares
    public void addProducto(ProductoJPA producto) {
        producto.setCategoria(this);
        this.productos.add(producto);
    }

    public void removeProducto(ProductoJPA producto) {
        this.productos.remove(producto);
        producto.setCategoria(null);
    }
}

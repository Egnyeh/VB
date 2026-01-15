package ies.sequeros.infraestructure.mappers;

import ies.sequeros.domain.models.Producto;
import ies.sequeros.infraestructure.entities.ProductoJPA;

public class ProductMapper {
    private ProductMapper() {}

    public static Producto toProducto(ProductoJPA jpa) {
        Producto item = new Producto(
                jpa.getId(),
                jpa.getNombre(),
                jpa.getDescripcion(),
                jpa.getCategoriaId(),
                jpa.getPrecio(),
                jpa.getActivo()
        );
        return item;
    }

    public static ProductoJPA toJpa(Producto domain) {
        ProductoJPA jpa = new ProductoJPA();
        jpa.setId(domain.getId());
        jpa.setNombre(domain.getNombre());
        jpa.setDescripcion(domain.getDescripcion());
        jpa.setCategoriaId(domain.getCategoriaId());
        jpa.setPrecio(domain.getPrecio());
        jpa.setActivo(domain.getActivo());
        return jpa;
    }
}

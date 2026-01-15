CREATE DATABASE IF NOT EXISTS vegaburguer;

-- Conectar a la base de datos
\c vegaburguer;

CREATE TABLE IF NOT EXISTS categoria (
    id UUID PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    activo BOOLEAN DEFAULT false
    );

CREATE TABLE IF NOT EXISTS producto (
    id UUID PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    categoria_id UUID NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    activo BOOLEAN DEFAULT false,
    CONSTRAINT fk_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(id)
    );

-- Datos de prueba generados con IA
INSERT INTO categoria (id, nombre, descripcion, activo) VALUES
                                                            ('550e8400-e29b-41d4-a716-446655440001', 'Hamburguesas', 'Hamburguesas de la casa', true),
                                                            ('550e8400-e29b-41d4-a716-446655440002', 'Bebidas', 'Refrescos y bebidas', true);

INSERT INTO producto (id, nombre, descripcion, categoria_id, precio, activo) VALUES
                                                                                 ('650e8400-e29b-41d4-a716-446655440001', 'Hamburguesa Clásica', 'Carne, lechuga, tomate', '550e8400-e29b-41d4-a716-446655440001', 8.50, true),
                                                                                 ('650e8400-e29b-41d4-a716-446655440002', 'Coca Cola', 'Refresco 330ml', '550e8400-e29b-41d4-a716-446655440002', 2.50, true);
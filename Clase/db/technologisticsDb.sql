-- Creación de la base de datos
CREATE DATABASE TechLogistics;
USE TechLogistics;

-- Tabla Clientes
CREATE TABLE Clientes (
    cliente_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    direccion VARCHAR(200),
    ciudad VARCHAR(50),
    codigo_postal VARCHAR(20)
);

-- Tabla Productos
CREATE TABLE Productos (
    producto_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    peso DECIMAL(10,2) COMMENT 'Peso en kg',
    dimensiones VARCHAR(50) COMMENT 'Formato: Largo x Ancho x Alto en cm',
    precio_unitario DECIMAL(10,2) NOT NULL
);

-- Tabla Transportistas
CREATE TABLE Transportistas (
    transportista_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    vehiculo_tipo VARCHAR(50) NOT NULL,
    capacidad_carga DECIMAL(10,2) COMMENT 'Capacidad en kg'
);

-- Tabla Rutas
CREATE TABLE Rutas (
    ruta_id INT AUTO_INCREMENT PRIMARY KEY,
    origen VARCHAR(100) NOT NULL,
    destino VARCHAR(100) NOT NULL,
    distancia_km DECIMAL(10,2) NOT NULL,
    tiempo_estimado_horas DECIMAL(5,2) NOT NULL
);

-- Tabla Estados_Envio
CREATE TABLE Estados_Envio (
    estado_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(200)
);

-- Tabla Pedidos
CREATE TABLE Pedidos (
    pedido_id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    fecha_pedido DATETIME DEFAULT CURRENT_TIMESTAMP,
    fecha_entrega_estimada DATE,
    total DECIMAL(12,2) NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES Clientes(cliente_id)
);

-- Tabla Pedidos_Productos
CREATE TABLE Pedidos_Productos (
    pedido_producto_id INT AUTO_INCREMENT PRIMARY KEY,
    pedido_id INT NOT NULL,
    producto_id INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (pedido_id) REFERENCES Pedidos(pedido_id),
    FOREIGN KEY (producto_id) REFERENCES Productos(producto_id)
);

-- Tabla Envios
CREATE TABLE Envios (
    envio_id INT AUTO_INCREMENT PRIMARY KEY,
    pedido_id INT NOT NULL,
    transportista_id INT NOT NULL,
    ruta_id INT NOT NULL,
    estado_id INT NOT NULL,
    fecha_inicio DATETIME DEFAULT CURRENT_TIMESTAMP,
    fecha_estimada_entrega DATETIME,
    fecha_real_entrega DATETIME,
    codigo_seguimiento VARCHAR(50) UNIQUE NOT NULL,
    FOREIGN KEY (pedido_id) REFERENCES Pedidos(pedido_id),
    FOREIGN KEY (transportista_id) REFERENCES Transportistas(transportista_id),
    FOREIGN KEY (ruta_id) REFERENCES Rutas(ruta_id),
    FOREIGN KEY (estado_id) REFERENCES Estados_Envio(estado_id)
);

-- Tabla Ubicaciones_Envio
CREATE TABLE Ubicaciones_Envio (
    ubicacion_id INT AUTO_INCREMENT PRIMARY KEY,
    envio_id INT NOT NULL,
    latitud DECIMAL(10,8) NOT NULL,
    longitud DECIMAL(11,8) NOT NULL,
    fecha_hora DATETIME DEFAULT CURRENT_TIMESTAMP,
    observaciones TEXT,
    FOREIGN KEY (envio_id) REFERENCES Envios(envio_id)
);
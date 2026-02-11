/**-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS TecnoStore;
USE TecnoStore;

-- Tabla Marca
CREATE TABLE Marca (
    idMarca INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(255)
);

-- Tabla Celular
CREATE TABLE Celular (
    idCelular INT AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(100) NOT NULL,
    OS VARCHAR(50),
    stock INT NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    gama ENUM('BAJA','MEDIA','ALTA') NOT NULL,
    idMarca INT NOT NULL,
    FOREIGN KEY (idMarca) REFERENCES Marca(idMarca) ON DELETE CASCADE
);

-- Tabla Cliente
CREATE TABLE Cliente (
    idCliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    identificacion VARCHAR(50) UNIQUE,
    correo VARCHAR(100),
    telefono VARCHAR(20)
);

-- Tabla Venta
CREATE TABLE Venta (
    idVenta INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2) NOT NULL,
    idCliente INT NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente) ON DELETE CASCADE
);

-- Tabla DetalleVenta
CREATE TABLE DetalleVenta (
    idDetalleVenta INT AUTO_INCREMENT PRIMARY KEY,
    idVenta INT NOT NULL,
    idCelular INT NOT NULL,
    cantidad INT NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (idVenta) REFERENCES Venta(idVenta) ON DELETE CASCADE,
    FOREIGN KEY (idCelular) REFERENCES Celular(idCelular) ON DELETE CASCADE
);

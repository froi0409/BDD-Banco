CREATE SCHEMA BANCO;
USE BANCO;

CREATE TABLE IF NOT EXISTS GERENTE (
  codigo VARCHAR(45) NOT NULL,
  nombre VARCHAR(90) NOT NULL,
  turno VARCHAR(50) NOT NULL,
  dpi VARCHAR(20) NOT NULL,
  direccion VARCHAR(90) NOT NULL,
  sexo VARCHAR(10) NOT NULL,
  password VARCHAR(90) NOT NULL,
  CONSTRAINT PK_GERENTE PRIMARY KEY(codigo)
);

CREATE TABLE IF NOT EXISTS CAJERO (
  codigo VARCHAR(45) NOT NULL,
  nombre VARCHAR(90) NOT NULL,
  turno VARCHAR(50) NOT NULL,
  dpi VARCHAR(20) NOT NULL,
  direccion VARCHAR(90) NOT NULL,
  sexo VARCHAR(10) NOT NULL,
  password VARCHAR(90) NOT NULL,
  CONSTRAINT PK_CAJERO PRIMARY KEY(codigo)
);

CREATE TABLE IF NOT EXISTS CLIENTE (
  dpi VARCHAR(25) NOT NULL,
  codigo VARCHAR(45) NOT NULL,
  nombre VARCHAR(90) NOT NULL,
  birth DATE NOT NULL,
  direccion VARCHAR(90) NOT NULL,
  sexo VARCHAR(10) NOT NULL,
  dpi_pdf VARCHAR(500) NOT NULL,
  password VARCHAR(90) NOT NULL,
  CONSTRAINT PK_CLIENTE PRIMARY KEY(dpi)
);

CREATE TABLE IF NOT EXISTS CUENTA (
  codigo VARCHAR(45) NOT NULL,
  fecha DATE NOT NULL,
  credito DECIMAL(15,2) NOT NULL,
  cliente VARCHAR(45) NOT NULL,
  CONSTRAINT PK_CUENTA PRIMARY KEY(codigo),
  CONSTRAINT FK_CUENTA_CLIENTE FOREIGN KEY(cliente) REFERENCES CLIENTE(dpi)
);

CREATE TABLE IF NOT EXISTS CUENTA_ASOCIADA (
  cliente VARCHAR(25) NOT NULL,
  cuenta VARCHAR(45) NOT NULL,
  estado VARCHAR(50) NOT NULL,
  intentos INT NOT NULL,
  CONSTRAINT PK_CUENTA_ASOCIADA PRIMARY KEY(cliente,cuenta),
  CONSTRAINT FK_CUENTA_ASOCIADA_CLIENTE FOREIGN KEY(cliente) REFERENCES CLIENTE(dpi),
  CONSTRAINT FK_CUENTA_ASOCIADA_CUENTA FOREIGN KEY(cuenta) REFERENCES CUENTA(codigo)
);

CREATE TABLE IF NOT EXISTS TRANSACCION (
  codigo VARCHAR(45) NOT NULL,
  cuenta_destino VARCHAR(45) NOT NULL,
  cuenta_origen VARCHAR(45),
  fecha DATE NOT NULL,
  hora TIME NOT NULL,
  tipo VARCHAR(45) NOT NULL,
  monto DECIMAL(11,2) NOT NULL,
  cajero VARCHAR(45),
  CONSTRAINT PK_TRANSACCION PRIMARY KEY(codigo),
  CONSTRAINT FK_TRANSACCION_CUENTA_ORIGEN FOREIGN KEY(cuenta_origen) REFERENCES CUENTA(codigo),
  CONSTRAINT FK_TRANSACCION_CUENTA_DESTINO FOREIGN KEY(cuenta_destino) REFERENCES CUENTA(codigo),
  CONSTRAINT FK_TRANSACCION_CAJERO FOREIGN KEY(cajero) REFERENCES CAJERO(codigo)
);

CREATE TABLE IF NOT EXISTS CAMBIO_GERENTE (
  codigo VARCHAR(35) NOT NULL,
  fecha DATE NOT NULL,
  campo VARCHAR(80) NOT NULL,
  dato_antes VARCHAR(100) NOT NULL,
  dato_ahora VARCHAR(100) NOT NULL,
  gerente VARCHAR(45) NOT NULL,
  CONSTRAINT PK_CAMBIO_GERENTE PRIMARY KEY(codigo),
  CONSTRAINT FK_CAMBIO_GERENTE_GERENTE FOREIGN KEY(gerente) REFERENCES GERENTE(codigo)
);

CREATE TABLE IF NOT EXISTS CAMBIO_CLIENTE (
  codigo VARCHAR(45) NOT NULL,
  fecha DATE NOT NULL,
  campo VARCHAR(80) NOT NULL,
  dato_antes VARCHAR(100) NOT NULL,
  dato_ahora VARCHAR(100) NOT NULL,
  gerente VARCHAR(45) NOT NULL,
  cliente VARCHAR(35) NOT NULL,
  CONSTRAINT PK_CAMBIO_CLIENTE PRIMARY KEY(codigo),
  CONSTRAINT FK_CAMBIO_CLIENTE_GERENTE FOREIGN KEY(gerente) REFERENCES GERENTE(codigo),
  CONSTRAINT FK_CAMBIO_CLIENTE_CLIENTE FOREIGN KEY(cliente) REFERENCES CLIENTE(dpi)
);

CREATE TABLE IF NOT EXISTS CAMBIO_CAJERO (
  codigo VARCHAR(45) NOT NULL,
  fecha DATE NOT NULL,
  campo VARCHAR(80) NOT NULL,
  dato_antes VARCHAR(100) NOT NULL,
  dato_ahora VARCHAR(100) NOT NULL,
  gerente VARCHAR(45) NOT NULL,
  cajero VARCHAR(35) NOT NULL,
  CONSTRAINT PK_CAMBIO_CAJERO PRIMARY KEY(codigo),
  CONSTRAINT FK_CAMBIO_CAJERO_GERENTE FOREIGN KEY(gerente) REFERENCES GERENTE(codigo),
  CONSTRAINT FK_CAMBIO_CAJERO_CAJERO FOREIGN KEY(cajero) REFERENCES CAJERO(codigo)
);

CREATE TABLE IF NOT EXISTS LIMITES_CONSULTA(
  limite_1 DECIMAL(15,2) NOT NULL,
  limite_2 DECIMAL(15,2) NOT NULL,
  CONSTRAINT PK_LIMITES_CONSULTA PRIMARY KEY(limite_1, limite_2)
);

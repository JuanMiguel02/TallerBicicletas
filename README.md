
# Sistema de Gestión Taller de Bicicletas

> >  Proyecto desarrollado por Juan Miguel Henao, Jerónimo Delgado y Juan Camilo Agudelo.


Aplicación que simula el funcionamiento de un taller de reparación de bicicletas.  
Permite gestionar clientes, mecánicos, bicicletas y órdenes de reparación mediante una interfaz gráfica intuitiva.

---

## 🖥️ Interfaz Gráfica

El sistema cuenta con interfaz desarrollada en **JavaFX**, incluyendo:

- Pantalla de login
- Panel principal con estadísticas
- Gestión de clientes
- Gestión de mecánicos
- Gestión de bicicletas
- Gestión de órdenes de reparación
- Actualización dinámica de datos

---

## 🔐 Credenciales de Acceso

| Usuario | Contraseña |
|----------|------------|
| taller   | 12345      |

---

## Ejecución

Clonar el repositorio.
```
bash 
https://github.com/JuanMiguel02/TallerBicicletas/tree/main
```

1. Abrir el proyecto en **IntelliJ IDEA** o cualquier IDE compatible con Maven y JavaFX.
2. Cargar las dependencias de `Maven`
3. Compilar y ejecutar el `Launcher` principal.
4. Se crearán dats de ejemplo al ejecutar

---


## ⚙️ Características Principales

### 1 Gestión de Clientes

- Registro de clientes
- Validación para evitar duplicados
- Actualización de información
- Eliminación de clientes
- Asociación de bicicletas

---

### 2️ Gestión de Mecánicos

- Registro de mecánicos con especialidad
- Generación automática de código interno  
  Ejemplo:MEC-001
  MEC-002
---

### 3 Gestión de Bicicletas

- Registro de bicicletas 
- Cada bicicleta tiene un dueño
- Eliminación y actualización de información
---

### 4 Gestión de Ordenes

- Registro de órdenes de reparación
- Filtros por fecha
- Eliminación de información
- Resumen mensual de órdenes de reparación completadas
---

## 🧪 Pruebas Unitarias

El proyecto incluye pruebas con **JUnit 5** para validar:

- Registro correcto de entidades
- Prevención de duplicados
- Generación secuencial de códigos internos
- Cambio de estado de órdenes
- Generación de identificadores únicos
- Manejo de excepciones

---

## 🏗️ Arquitectura MVC

### Entidades principales

- `Taller` (Patrón Singleton)
- `Cliente`
- `Mecanico`
- `Bicicleta`
- `OrdenReparacion`

### Enumeraciones

- `TipoBicicleta`
- `EspecialidadMecanico`
- `MotivoReparacion`
- `EstadoOrden`

---

## 🛠️ Tecnologías Utilizadas

- Java
- JavaFX
- JUnit 5
- Programación Orientada a Objetos
- Patrón Singleton

---


## 🚀 Posibles Mejoras Futuras

- Persistencia con base de datos
- Sistema de facturación
- Exportación a PDF/Excel
- Control de roles avanzado
- Historial de reparaciones por cliente

---

## 📌 Proyecto Académico

Desarrollado como práctica de:

- Programación Orientada a Objetos
- Arquitectura tipo MVC
- Desarrollo de interfaces gráficas
- Pruebas unitarias
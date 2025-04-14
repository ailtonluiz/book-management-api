git # 📚 BookManager - Sistema de Gestión de Libros

![Java](https://img.shields.io/badge/Java-21-red.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.4-brightgreen.svg)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)
![Postman](https://img.shields.io/badge/Postman-API-orange.svg)
![License](https://img.shields.io/badge/license-MIT-green.svg)
![Version](https://img.shields.io/badge/version-1.0.0-blue.svg)

## 🌟 Descripción

BookManager es un sistema robusto y escalable para la gestión integral de bibliotecas, desarrollado con las últimas tecnologías del ecosistema Java. Este proyecto fue creado con el objetivo de demostrar mis conocimientos en desarrollo de software, aplicando buenas prácticas, arquitectura limpia y patrones de diseño modernos.
## ✨ Características Destacadas

- 🏗️ **Arquitectura Limpia**: Implementación de Clean Architecture con separación clara de responsabilidades
- 🔄 **API RESTful**: Diseño de API REST siguiendo las mejores prácticas y estándares HTTP
- 📊 **Persistencia de Datos**: Integración con MySQL utilizando JPA/Hibernate
- 🔍 **Documentación Automática**: API documentada con OpenAPI/Swagger
- 🛡️ **Validación Robusta**: Validación de datos de entrada con Bean Validation
- 🔄 **Migraciones Automáticas**: Control de versiones de base de datos con Flyway
- 🧪 **Código Limpio**: Implementación de principios SOLID y patrones de diseño

## 🚀 Tecnologías Utilizadas

### Backend
- **Java 21**: Aprovechando las últimas características del lenguaje
- **Spring Boot 3.4.4**: Framework moderno para aplicaciones empresariales
- **Spring Data JPA**: Persistencia de datos con JPA/Hibernate
- **Spring Validation**: Validación de datos robusta
- **SpringDoc OpenAPI**: Documentación automática de API
- **ModelMapper**: Mapeo de objetos eficiente
- **Lombok**: Reducción de código boilerplate
- **MySQL 8.0**: Base de datos relacional robusta
- **Flyway**: Migraciones de base de datos versionadas
- **Maven**: Gestión de dependencias y build

### Herramientas de Desarrollo
- **Postman**: Pruebas y documentación de API
- **Git**: Control de versiones
- **IntelliJ IDEA**: IDE para desarrollo

## 📋 Prerrequisitos

- Java 21 o superior
- Maven 3.6 o superior
- MySQL 8.0 o superior
- Git

## 🔧 Instalación y Ejecución

1. **Clonar el repositorio**:
```bash
git clone https://github.com/ailtonluiz/book-management-api.git bookmanager
cd bookmanager
```

2. **Configurar la base de datos**:
   - Crear una base de datos MySQL: `bookmanager`
   - Configurar credenciales en `application.properties`

3. **Compilar el proyecto**:
```bash
mvn clean install
```

4. **Ejecutar la aplicación**:
```bash
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`

## 📚 Documentación de la API

La documentación completa de la API está disponible a través de:

1. **Swagger UI**:
```
http://localhost:8080/swagger-ui/index.html#/
```

2. **Postman Collection**:
   - Importar la colección `BookManager.postman_collection.json` en Postman
   - Configurar el ambiente com variables:
     - `baseUrl`: http://localhost:8080
   - Ejecutar las pruebas automatizadas para verificar la API

## 🏗️ Arquitectura del Proyecto

```
src/main/java/com/ailtonluiz/bookmanager/
├── api/                    # Capa de API
│   ├── controller/        # Controladores REST
│   ├── model/            # DTOs y modelos de API
│   └── exception/        # Manejo de excepciones
├── domain/                # Capa de dominio
│   ├── model/            # Entidades del dominio
│   ├── repository/       # Interfaces de repositorio
│   ├── service/          # Lógica de negocio
│   └── exception/        # Excepciones de dominio
└── infrastructure/        # Capa de infraestructura
    └── repository/       # Implementaciones de repositorio
```

## 📝 Funcionalidades Principales

### 📚 Gestión de Libros
- Registro completo de libros con metadatos
- Sistema de búsqueda avanzada
- Control de estado (activo/inactivo)
- Gestión de ediciones y versiones

### 👤 Gestión de Autores
- Perfiles completos de autores
- Asociación con nacionalidades
- Historial de publicaciones
- Control de estado y relevancia

### 📑 Gestión de Géneros
- Categorización de libros
- Jerarquía de géneros
- Estadísticas por género
- Control de popularidad

### 🌍 Gestión de Nacionalidades
- Base de datos de nacionalidades
- Asociación con autores
- Estadísticas geográficas
- Control de relevancia cultural

## 🔐 Seguridad y Validación

- ✅ Validación robusta de datos de entrada
- 🛡️ Manejo de excepciones personalizado
- 🔒 Prevención de eliminación de entidades en uso
- 📊 Control de estado y auditoría
- 🔍 Validación de integridad referencial

## 🛠️ Configuraciones Avanzadas

El archivo `application.properties` incluye configuraciones para:
- Conexión a base de datos MySQL
- Migraciones automáticas con Flyway
- Mapeo de objetos con ModelMapper
- Documentación OpenAPI/Swagger
- Configuraciones de rendimiento y caché

### Configuración de Postman
- Colección de endpoints organizados por recursos
- Variables de ambiente para diferentes entornos
- Pruebas automatizadas para validación de API
- Ejemplos de solicitudes y respuestas

## 📦 Dependencias Principales

```xml
<dependencies>
    <!-- Spring Boot Starters -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    <!-- API Documentation -->
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.3.0</version>
    </dependency>

    <!-- Utils -->
    <dependency>
        <groupId>org.modelmapper</groupId>
        <artifactId>modelmapper</artifactId>
        <version>3.1.1</version>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

    <!-- Database -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.flywaydb</groupId>
        <artifactId>flyway-core</artifactId>
    </dependency>
    <dependency>
        <groupId>org.flywaydb</groupId>
        <artifactId>flyway-mysql</artifactId>
    </dependency>
</dependencies>
```

## 🚀 Próximas Funcionalidades (v2.0.0)

### 📚 Sistema de Préstamos
- Gestión de múltiples ejemplares por libro
- Control de disponibilidad en tiempo real
- Sistema de reservas anticipadas
- Historial de préstamos por lector

### 👥 Gestión de Lectores
- Registro completo de lectores
- Historial de préstamos
- Sistema de calificaciones
- Preferencias de lectura

### 📧 Sistema de Notificaciones
- Notificaciones por email automáticas
- Recordatorios de devolución
- Alertas de disponibilidad
- Boletines personalizados

### 📊 Control y Estadísticas
- Dashboard de préstamos
- Reportes de popularidad
- Análisis de tendencias
- Métricas de uso

---

⌨️ con ❤️ por [ailtonluiz](https://github.com/ailtonluiz) 😊 
# 📞 Call Management System

Sistema de gestión de extensiones SIP para centrales telefónicas VoIP.

## Descripción

Este proyecto es una aplicación backend desarrollada con **Spring Boot 4.0.4** para la gestión integral de extensiones telefónicas en entornos VoIP. Permite administrar usuarios, extensiones SIP, roles y registros de llamadas de manera eficiente y escalable.

## 🚀 Características

- Gestión de usuarios con roles y estados
- Administración de extensiones SIP
- Registro y seguimiento de llamadas telefónicas
- Integración con Asterisk (VoIP)
- API RESTful (en desarrollo)
- Persistencia con MySQL

## 🛠️ Tecnologías

| Tecnología | Versión |
|------------|---------|
| Java | 21 |
| Spring Boot | 4.0.4 |
| Maven | 3.9+ |
| MySQL | 8.0+ |
| Lombok | - |
| Asterisk Java | 3.41.0 |

## 📋 Requisitos Previos

- Java 21 instalado
- Maven 3.9+ instalado
- MySQL 8.0+ (o Docker)

## ⚡ Inicio Rápido

```bash
# 1. Clonar el repositorio
git clone <url-repositorio>
cd call

# 2. Iniciar MySQL con Docker
docker compose up -d

# 3. Compilar el proyecto
./mvnw clean install

# 4. Ejecutar la aplicación
./mvnw spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080`

## 📁 Estructura del Proyecto

```
call/
├── src/main/java/com/telecom/call/
│   ├── CallApplication.java       # Punto de entrada
│   ├── Model/                     # Entidades JPA
│   ├── DTO/                       # Data Transfer Objects
│   │   ├── request/               # DTOs de entrada
│   │   └── response/              # DTOs de salida
│   ├── Repository/                # Repositorios JPA
│   ├── Mapper/                    # Convertidores Entity <-> DTO
│   ├── Service/                   # Lógica de negocio
│   ├── Enums/                     # Enumeraciones
│   └── Controller/                # Endpoints API REST
├── src/main/resources/
│   └── application.yaml           # Configuración
├── src/test/                      # Tests
├── pom.xml                        # Dependencias Maven
├── compose.yaml                   # Docker Compose
└── README.md                      # Este archivo
```

## 📚 Documentación

- [Arquitectura del Sistema](./ARCHITECTURE.md)
- [Guía de Despliegue](./DEPLOY.md)
- [Contribución](./CONTRIBUTING.md)
- [Changelog](./CHANGELOG.md)

## 🔧 Configuración

La configuración se encuentra en `src/main/resources/application.yaml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/telecom
    username: angel
    password: angel1052
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
server:
  port: 8080
```

## 📄 Licencia

Este proyecto está bajo la licencia MIT.

## 👤 Autor

Proyecto desarrollado para gestión de telecomunicaciones empresariales.
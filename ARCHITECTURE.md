# 🏗️ Arquitectura del Sistema

## Visión General

Call Management System sigue una arquitectura de **capas (Layered Architecture)** basada en Spring Boot, implementando el patrón **Repository** para acceso a datos.

```
┌─────────────────────────────────────────┐
│           Presentation Layer            │
│         (Controllers - REST API)         │
└────────────────┬────────────────────────┘
                 │
┌────────────────▼────────────────────────┐
│            Business Layer               │
│    (Services + DTOs + Mappers)          │
└────────────────┬────────────────────────┘
                 │
┌────────────────▼────────────────────────┐
│             Data Layer                  │
│       (Repositories + Entities)         │
└────────────────┬────────────────────────┘
                 │
┌────────────────▼────────────────────────┐
│          Infrastructure                 │
│     (MySQL + Asterisk + Spring)         │
└─────────────────────────────────────────┘
```

## Componentes Principales

### 1. Model (Entidades)

| Entidad | Descripción | Relaciones |
|---------|-------------|------------|
| `User` | Usuario del sistema | 1:1 Extension, N:1 Rol, 1:N PhoneCall |
| `Extension` | Extensión SIP | 1:1 User, 1:N PhoneCall |
| `PhoneCall` | Registro de llamada | N:1 User, N:1 Extension |
| `Rol` | Rol del usuario | 1:N User |

### 2. DTOs (Data Transfer Objects)

Separación entre:
- **Request DTOs**: Para recibir datos del cliente
- **Response DTOs**: Para enviar respuestas al cliente

```
DTO/
├── request/
│   ├── UserCreateRequestDto.java
│   ├── ExtensionRequestDto.java
│   ├── PhoneCallRequestDto.java
│   └── RolRequestDto.java
└── response/
    ├── UserResponseDto.java
    ├── ExtensionResponseDto.java
    ├── PhoneCallResponseDto.java
    └── RolResponseDto.java
```

### 3. Mappers

Convierte entre entidades y DTOs:
- `UserMapper`
- `ExtensionMapper`
- `PhoneCallMapper`
- `RolMapper`

### 4. Repositories

Interfaces que extienden `JpaRepository` para operaciones CRUD.

### 5. Services

Lógica de negocio, implementada en:
- `UserService` (ejemplo)

## Enumeraciones

| Enum | Valores |
|------|---------|
| `ContextType` | INTERNAL, EXTERNAL, CONFERENCE, IVR, FROMTRUK |
| `StatusType` | ACTIVE, INACTIVE |
| `ExtensionType` | SIP |
| `RolType` | (por definir) |

## Flujo de Datos

```
HTTP Request
    │
    ▼
Controller (receives Request DTO)
    │
    ▼
Service (business logic)
    │
    ▼
Repository (Entity ↔ DB)
    │
    ▼
Service (returns Response DTO)
    │
    ▼
Controller (JSON response)
```

## Integración con Asterisk

El proyecto incluye `asterisk-java 3.41.0` para integración con centrales VoIP Asterisk. Esta integración permite:

- Gestión de extensiones SIP
- Monitoreo de llamadas
- Control de llamadas entrantes/salientes

## Base de Datos

### Diagrama Entidad-Relación

```
┌─────────────┐       ┌─────────────┐       ┌─────────────┐
│    user     │       │  extension  │       │  phone_call │
├─────────────┤       ├─────────────┤       ├─────────────┤
│ id_user PK  │──1:1──│user_id FK   │       │user_id FK   │
│ name_user   │       │id_ext PK    │       │ext_id FK    │
│ email       │       │number       │       │call_id PK   │
│ password    │       │password     │       │origin       │
│ status_user │       │status       │       │destination  │
│ rol_id FK   │       │host         │       │context      │
│ creation_dt │       │creation_dt  │       │start        │
└─────────────┘       │context_type │       │duration     │
        │             │display_name │       │billsec      │
        │             └─────────────┘       │channel      │
        │                                     └─────────────┘
        ▼
┌─────────────┐
│     rol     │
├─────────────┤
│ rol_id PK   │
│ rol_name    │
└─────────────┘
```

## Configuración de Seguridad

*(Planeado)* - Spring Security con JWT para autenticación.

## Patrones de Diseño Utilizados

1. **Repository Pattern**: Abstracción del acceso a datos
2. **DTO Pattern**: Separación entre capas
3. **Mapper Pattern**: Conversión entre objetos
4. **Service Layer**: Lógica de negocio centralizada

## Convenciones de Código

- Paquete base: `com.telecom.call`
- Nombres de clases en PascalCase
- Entidades con `@Entity`, `@Table`
- DTOs inmutables (lombok `@Builder`)
- Servicios con `@Service`
- Repositorios con `@Repository`

## Extensiones Futuras

- Agregar Spring Security + JWT
- Implementar controllers REST completos
- Tests unitarios y de integración
- Documentación con Swagger/OpenAPI
- WebSocket para notificaciones en tiempo real
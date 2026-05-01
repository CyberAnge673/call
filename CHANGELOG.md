# 📜 Changelog

Todos los cambios notables de este proyecto se documentarán en este archivo.

El formato se basa en [Keep a Changelog](https://keepachangelog.com/es-ES/1.0.0/),
y este proyecto adhiere a [Semantic Versioning](https://semver.org/lang/es/).

---

## [0.0.1-SNAPSHOT] - 2025-XX-XX

### Added
- Estructura base del proyecto Spring Boot
- Entidades JPA: User, Extension, PhoneCall, Rol
- DTOs completos (request y response)
- Repositorios con JpaRepository
- Mappers para conversión Entity ↔ DTO
- Enum types: ContextType, StatusType, ExtensionType, RolType
- Docker Compose para MySQL
- Integración con asterisk-java 3.41.0

### In Progress
- UserService básico (solo lectura)
- Controllers REST (pendiente)
- Spring Security + JWT (pendiente)
- Tests unitarios (pendiente)
- Documentación API con Swagger (pendiente)

### Known Issues
- Solo existe UserService, faltantes: ExtensionService, PhoneCallService, RolService
- No hay Controllers REST implementados
- Sin autenticación/seguridad
- Sin tests automatizados

---

## Estructura de Versiones

### Versiones Maior (MAJOR)
- Cambios incompatibles en la API

### Versiones Minor (MINOR)
- Nuevas funcionalidades compatibles

### Versiones Patch (PATCH)
- Correcciones de bugs compatibles

---

## Tipos de Cambios

- `Added`: Nuevas funcionalidades
- `Changed`: Cambios en funcionalidad existente
- `Deprecated`: Funcionalidades que se eliminarán en futuras versiones
- `Removed`: Funcionalidades eliminadas
- `Fixed`: Correcciones de bugs
- `Security`: Actualizaciones de seguridad

---

## Roadmap Sugerido

### v0.1.0 - API REST Completa
- [ ] UserController (CRUD completo)
- [ ] ExtensionController (CRUD completo)
- [ ] PhoneCallController (CRUD completo)
- [ ] RolController (CRUD completo)

### v0.2.0 - Seguridad
- [ ] Spring Security config
- [ ] JWT authentication
- [ ] Roles y permisos

### v0.3.0 - Funcionalidades Avanzadas
- [ ] Filtros y búsquedas en llamadas
- [ ] Reportes de llamadas
- [ ] Notificaciones (WebSocket)

### v1.0.0 - Release
- [ ] Tests覆盖率 > 80%
- [ ] Documentación completa
- [ ] Despliegue en producción

---

## Notas de Migration

### Para actualizar a futuras versiones

Se agregarán aquí las instrucciones de migración cuando sea necesario.
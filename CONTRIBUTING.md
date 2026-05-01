# 🤝 Guía de Contribución

¡Gracias por tu interés en contribuir al proyecto!

## 📋 Código de Conducta

- Ser respetuoso y profesional
- Aceptar críticas constructivas de manera positiva
- Enfocarse en lo que es mejor para la comunidad y el proyecto

---

## 🚦 Formas de Contribuir

1. **Reportar bugs** - Crear issue en GitHub
2. **Sugerir funcionalidades** - Proponer mejoras
3. **Desarrollar código** - Pull requests
4. **Mejorar documentación** - README, guías, comentarios
5. **Revisar código** - Code review

---

## 🛠️ Entorno de Desarrollo

### Requisitos

- Java 21
- Maven 3.9+
- IDE recomendado: IntelliJ IDEA o VS Code
- Docker (para MySQL)

### Configuración Inicial

```bash
# 1. Fork del repositorio

# 2. Clonar tu fork
git clone https://github.com/TU_USUARIO/call.git
cd call

# 3. Agregar upstream
git remote add upstream https://github.com/original/call.git

# 4. Crear rama para tu feature
git checkout -b feature/nombre-feature
```

---

## 📝 Convenciones de Código

### Estructura de Paquetes

```
com.telecom.call/
├── Model/          # Entidades JPA
├── DTO/            # Data Transfer Objects
│   ├── request/    # DTOs de entrada
│   └── response/   # DTOs de salida
├── Repository/     # Interfaces JpaRepository
├── Service/        # Lógica de negocio
├── Controller/     # Endpoints REST
├── Mapper/         # Convertidores
├── Enums/          # Enumeraciones
└── Config/         # Configuración
```

### Nombrado

| Elemento | Convención | Ejemplo |
|----------|------------|---------|
| Clases | PascalCase | `UserService.java` |
| Métodos | camelCase | `getUserById()` |
| Variables | camelCase | `userId` |
| Constantes | UPPER_SNAKE_CASE | `MAX_ATTEMPTS` |
| Paquetes | lowercase | `com.telecom.call.model` |

### Estándares de Código

- Usar **Lombok** para reducir boilerplate
- **Inmutabilidad** en DTOs con `@Builder`
- **Inyección de dependencias** por constructor (preferido) o `@Autowired`
- **Excepciones** propias para errores de negocio
- **Logs** con SLF4J (`@Slf4j`)

### Ejemplo de Service

```java
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    public List<UserResponseDto> getAllUsers() {
        return userRepo.findAll()
            .stream()
            .map(userMapper::toResponse)
            .toList();
    }
}
```

---

## 🔀 Flujo de Trabajo Git

### 1. Sincronizar con upstream

```bash
git fetch upstream
git checkout main
git merge upstream/main
```

### 2. Crear rama

```bash
git checkout -b feature/nueva-funcionalidad
```

### 3. Desarrollar

- Escribir código
- Agregar tests
- Actualizar documentación si es necesario

### 4. Commits

```bash
git add .
git commit -m "feat: agregar nuevo endpoint para usuarios"
```

**Tipos de commit**:
- `feat`: Nueva funcionalidad
- `fix`: Corrección de bug
- `docs`: Documentación
- `refactor`: Refactorización
- `test`: Tests
- `chore`: Mantenimiento

### 5. Push y Pull Request

```bash
git push origin feature/nueva-funcionalidad
```

Luego crear PR en GitHub describiendo:
- ¿Qué hace el cambio?
- ¿Por qué es necesario?
- ¿Cómo se probó?

---

## ✅ Proceso de Pull Request

1. **Revisión**: Otro desarrollador revisará el código
2. **Pruebas**: Verificar que los tests pasen
3. **Discusión**: Ajustar según feedback
4. **Merge**: Un mantenedor hará el merge

### Requisitos para Merge

- [ ] Código sigue las convenciones
- [ ] Tests pasan
- [ ] Sin conflictos con main
- [ ] Documentación actualizada (si aplica)

---

## 🐛 Reportar Bugs

Para reportar un bug, crea un issue con:

1. **Título claro**: Descripción breve del problema
2. **Descripción**:
   - Pasos para reproducir
   - Comportamiento esperado
   - Comportamiento actual
3. **Capturas** (si aplica)
4. **Entorno**: SO, Java version, etc.

---

## 💡 Sugerir Funcionalidades

Para sugerir una funcionalidad:

1. Crear issue con标签 `enhancement`
2. Describir la funcionalidad
3. Explicar por qué sería útil
4. Opcional: Proponer implementación

---

## 📚 Recursos Útiles

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Lombok](https://projectlombok.org/)
- [Asterisk Java](https://asterisk-java.org/)

---

## ❓ Preguntas

Si tienes dudas:
- Crear issue con标签 `question`
- Revisar documentación existente
- Buscar en issues anteriores
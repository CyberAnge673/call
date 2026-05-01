# 📊 Estructura de Base de Datos

## Diagrama de Entidades

```
┌─────────────────────────────────────────────────────────────────────────┐
│                              USER                                       │
├─────────────────────────────────────────────────────────────────────────┤
│ id_user         BIGINT (PK)     AUTO_INCREMENT                         │
│ name_user       VARCHAR(255)    NULL                                    │
│ email           VARCHAR(255)    NOT NULL, UNIQUE                       │
│ password        VARCHAR(255)    NOT NULL                               │
│ creation_date   DATETIME        NOT NULL                               │
│ status_user     VARCHAR(50)     NOT NULL (ACTIVE/INACTIVE)             │
│ rol_id          BIGINT (FK)     NULL → rol.rol_id                      │
└─────────────────────────────────────────────────────────────────────────┘
                                    │
                                    │ (Many-to-One)
                                    ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                                ROL                                      │
├─────────────────────────────────────────────────────────────────────────┤
│ rol_id          BIGINT (PK)     AUTO_INCREMENT                         │
│ rol_name        VARCHAR(100)    NOT NULL, UNIQUE                       │
└─────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────┐
│                             EXTENSION                                   │
├─────────────────────────────────────────────────────────────────────────┤
│ id_extension    BIGINT (PK)     AUTO_INCREMENT                         │
│ number          VARCHAR(30)     NOT NULL, UNIQUE                       │
│ password_secret VARCHAR(255)    NULL                                   │
│ status          VARCHAR(50)     NOT NULL (ACTIVE/INACTIVE)             │
│ host            VARCHAR(255)    NULL                                   │
│ creation_date   DATETIME        NOT NULL                               │
│ extension_type  VARCHAR(50)     NOT NULL (SIP)                        │
│ context_type    VARCHAR(50)     NOT NULL                               │
│ display_name    VARCHAR(255)    NULL                                   │
│ last_register   DATETIME        NULL                                   │
│ user_id         BIGINT (FK)     NULL → user.id_user                    │
└─────────────────────────────────────────────────────────────────────────┘
                                    │
                                    │ (One-to-One)
                                    ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                            PHONE_CALL                                   │
├─────────────────────────────────────────────────────────────────────────┤
│ call_id         BIGINT (PK)     AUTO_INCREMENT                         │
│ origin          VARCHAR(50)    NOT NULL                               │
│ destination     VARCHAR(50)    NOT NULL                               │
│ context         VARCHAR(50)    NOT NULL (INTERNAL/EXTERNAL/etc)       │
│ start           DATETIME        NOT NULL                               │
│ call_duration   INT             NULL                                   │
│ billsec         INT             NULL                                   │
│ channel         VARCHAR(255)    NOT NULL                               │
│ user_id         BIGINT (FK)     NULL → user.id_user                    │
│ extension_id    BIGINT (FK)     NULL → extension.id_extension         │
└─────────────────────────────────────────────────────────────────────────┘
```

---

## Tablas Detalladas

### 1. user

| Campo | Tipo | Restricciones | Descripción |
|-------|------|---------------|-------------|
| `id_user` | BIGINT | PK, AI | ID único del usuario |
| `name_user` | VARCHAR(255) | Nulo | Nombre del usuario |
| `email` | VARCHAR(255) | NOT NULL, UNIQUE | Correo electrónico |
| `password` | VARCHAR(255) | NOT NULL | Contraseña (hasheada) |
| `creation_date` | DATETIME | NOT NULL | Fecha de creación |
| `status_user` | VARCHAR(50) | NOT NULL | Estado: ACTIVE/INACTIVE |
| `rol_id` | BIGINT | FK → rol.rol_id | Rol del usuario |

### 2. rol

| Campo | Tipo | Restricciones | Descripción |
|-------|------|---------------|-------------|
| `rol_id` | BIGINT | PK, AI | ID único del rol |
| `rol_name` | VARCHAR(100) | NOT NULL, UNIQUE | Nombre del rol |

### 3. extension

| Campo | Tipo | Restricciones | Descripción |
|-------|------|---------------|-------------|
| `id_extension` | BIGINT | PK, AI | ID único de extensión |
| `number` | VARCHAR(30) | NOT NULL, UNIQUE | Número de extensión SIP |
| `password_secret` | VARCHAR(255) | Nulo | Contraseña SIP |
| `status` | VARCHAR(50) | NOT NULL | Estado de la extensión |
| `host` | VARCHAR(255) | Nulo | Dirección IP del dispositivo |
| `creation_date` | DATETIME | NOT NULL | Fecha de creación |
| `extension_type` | VARCHAR(50) | NOT NULL | Tipo: SIP |
| `context_type` | VARCHAR(50) | NOT NULL | Contexto VoIP |
| `display_name` | VARCHAR(255) | Nulo | Nombre para mostrar |
| `last_register` | DATETIME | Nulo |Último registro SIP |
| `user_id` | BIGINT | FK → user.id_user | Usuario asociado |

### 4. phone_call

| Campo | Tipo | Restricciones | Descripción |
|-------|------|---------------|-------------|
| `call_id` | BIGINT | PK, AI | ID único de llamada |
| `origin` | VARCHAR(50) | NOT NULL | Origen de la llamada |
| `destination` | VARCHAR(50) | NOT NULL | Destino de la llamada |
| `context` | VARCHAR(50) | NOT NULL | Tipo de contexto |
| `start` | DATETIME | NOT NULL | Fecha/hora de inicio |
| `call_duration` | INT | Nulo | Duración total (seg) |
| `billsec` | INT | Nulo | Tiempo facturable (seg) |
| `channel` | VARCHAR(255) | NOT NULL | Canal Asterisk |
| `user_id` | BIGINT | FK → user.id_user | Usuario relacionado |
| `extension_id` | BIGINT | FK → extension.id_extension | Extensión relacionada |

---

## Relaciones

| Relación | Tipo | Descripción |
|----------|------|-------------|
| User → Rol | Many-to-One | Un usuario tiene un rol |
| User → Extension | One-to-One | Un usuario tiene una extensión |
| User → PhoneCall | One-to-Many | Un usuario puede tener varias llamadas |
| Extension → PhoneCall | One-to-Many | Una extensión puede tener varias llamadas |
| PhoneCall → User | Many-to-One | Una llamada pertenece a un usuario |
| PhoneCall → Extension | Many-to-One | Una llamada usa una extensión |

---

## Índices

| Tabla | Índice | Columnas | Tipo |
|-------|--------|----------|------|
| user | PRIMARY | id_user | PRIMARY |
| user | UNIQUE | email | UNIQUE |
| extension | PRIMARY | id_extension | PRIMARY |
| extension | UNIQUE | number | UNIQUE |
| phone_call | PRIMARY | call_id | PRIMARY |
| rol | PRIMARY | rol_id | PRIMARY |
| rol | UNIQUE | rol_name | UNIQUE |

---

## Enum Values

### status_user (user)
- `ACTIVE`
- `INACTIVE`

### status (extension)
- `ACTIVE`
- `INACTIVE`

### extension_type (extension)
- `SIP`

### context (phone_call)
- `INTERNAL`
- `EXTERNAL`
- `CONFERENCE`
- `IVR`
- `FROMTRUK`

---

## Consultas Útiles

### Obtener usuarios con sus extensiones

```sql
SELECT u.name_user, u.email, e.number as extension_number, e.status
FROM user u
LEFT JOIN extension e ON u.id_user = e.user_id;
```

### Obtener llamadas de un usuario

```sql
SELECT pc.*, e.number as extension
FROM phone_call pc
JOIN extension e ON pc.extension_id = e.id_extension
WHERE pc.user_id = 1;
```

### Contador de llamadas por extensión

```sql
SELECT e.number, COUNT(pc.call_id) as total_calls
FROM extension e
LEFT JOIN phone_call pc ON e.id_extension = pc.extension_id
GROUP BY e.number;
```
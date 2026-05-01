# 📦 Guía de Despliegue

## Entornos

| Entorno | Descripción |
|---------|-------------|
| Desarrollo | Ejecución local con MySQL en Docker |
| Producción | Despliegue en servidor con MySQL externo |

---

## 🚀 Despliegue Local (Desarrollo)

### Prerrequisitos

- Java 21+
- Maven 3.9+
- Docker y Docker Compose

### Pasos

#### 1. Configurar MySQL con Docker

```bash
# Iniciar contenedor MySQL
docker compose up -d

# Verificar que esté ejecutándose
docker compose ps
```

El contenedor creará:
- **Base de datos**: `telecom`
- **Usuario**: `angel`
- **Contraseña**: `angel1052`
- **Puerto**: `3306`

#### 2. Compilar el Proyecto

```bash
# Compilar sin ejecutar tests
./mvnw clean install -DskipTests

# Compilar con tests
./mvnw clean install
```

#### 3. Ejecutar la Aplicación

```bash
# Opción 1: Usar Maven
./mvnw spring-boot:run

# Opción 2: Ejecutar JAR directamente
java -jar target/call-0.0.1-SNAPSHOT.jar
```

#### 4. Verificar Ejecución

```bash
# Ver logs de la aplicación
docker logs -f call-app

# Probar endpoint (si está implementado)
curl http://localhost:8080/api/users
```

---

## 🖥️ Despliegue en Producción

### Prerrequisitos del Servidor

- Ubuntu 20.04+ o similar
- Java 21 (OpenJDK o Amazon Corretto)
- MySQL 8.0+ externo
- Nginx (opcional, como reverse proxy)

### Pasos

#### 1. Preparar el Servidor

```bash
# Actualizar sistema
sudo apt update && sudo apt upgrade -y

# Instalar Java 21
sudo apt install openjdk-21-jdk -y

# Verificar instalación
java -version
```

#### 2. Configurar MySQL en Producción

```bash
# Instalar MySQL
sudo apt install mysql-server -y

# Crear base de datos y usuario
sudo mysql
```

```sql
CREATE DATABASE telecom CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'telecom_user'@'localhost' IDENTIFIED BY 'password_segura';
GRANT ALL PRIVILEGES ON telecom.* TO 'telecom_user'@'localhost';
FLUSH PRIVILEGES;
EXIT;
```

#### 3. Configurar Variables de Entorno

```bash
# Crear archivo de environment
sudo nano /etc/environment
```

Agregar:
```
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/telecom
SPRING_DATASOURCE_USERNAME=telecom_user
SPRING_DATASOURCE_PASSWORD=password_segura
SPRING_JPA_HIBERNATE_DDL_AUTO=validate
```

#### 4. Compilar para Producción

```bash
# Compilar JAR optimizado
./mvnw clean package -DskipTests -Pprod

# El JAR se generará en: target/call-0.0.1-SNAPSHOT.jar
```

#### 5. Crear Servicio systemd

```bash
sudo nano /etc/systemd/system/call.service
```

```ini
[Unit]
Description=Call Management System
After=network.target mysql.service

[Service]
Type=simple
User=www-data
WorkingDirectory=/opt/call
ExecStart=/usr/bin/java -jar /opt/call/call-0.0.1-SNAPSHOT.jar
SuccessExitStatus=143

[Install]
WantedBy=multi-user.target
```

```bash
# Habilitar y iniciar servicio
sudo systemctl daemon-reload
sudo systemctl enable call
sudo systemctl start call

# Verificar estado
sudo systemctl status call
```

#### 6. Configurar Nginx (Opcional)

```bash
sudo nano /etc/nginx/sites-available/call
```

```nginx
server {
    listen 80;
    server_name tu-dominio.com;

    location / {
        proxy_pass http://localhost:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
    }
}
```

```bash
# Habilitar sitio
sudo ln -s /etc/nginx/sites-available/call /etc/nginx/sites-enabled/
sudo nginx -t
sudo systemctl reload nginx
```

---

## 🔧 Configuración por Entorno

### application.yaml

```yaml
spring:
  datasource:
    url: ${SPRING_DATASOURCE_URL:jdbc:mysql://localhost:3306/telecom}
    username: ${SPRING_DATASOURCE_USERNAME:root}
    password: ${SPRING_DATASOURCE_PASSWORD:angel1052}
  jpa:
    hibernate:
      ddl-auto: ${SPRING_JPA_HIBERNATE_DDL_AUTO:update}
    show-sql: ${SPRING_JPA_SHOW_SQL:false}
  docker:
    compose:
      enabled: ${SPRING_DOCKER_COMPOSE_ENABLED:true}

server:
  port: ${SERVER_PORT:8080}
```

### Variables de Entorno Recomendadas

| Variable | Desarrollo | Producción |
|----------|------------|------------|
| `SPRING_JPA_HIBERNATE_DDL_AUTO` | `update` | `validate` |
| `SPRING_JPA_SHOW_SQL` | `true` | `false` |
| `SPRING_DOCKER_COMPOSE_ENABLED` | `true` | `false` |

---

## 📊 Monitoreo

### Logs

```bash
# Ver logs de la aplicación
sudo journalctl -u call -f

# Ver logs de MySQL
sudo tail -f /var/log/mysql/error.log
```

### Health Check

```bash
# Endpoint de salud (si está implementado)
curl http://localhost:8080/actuator/health
```

---

## 🔄 Actualización

```bash
# 1. Detener servicio
sudo systemctl stop call

# 2. Realizar backup
mysqldump -u telecom_user -p telecom > backup_$(date +%Y%m%d).sql

# 3. Compilar nueva versión
./mvnw clean package -DskipTests

# 4. Reemplazar JAR
sudo cp target/call-0.0.1-SNAPSHOT.jar /opt/call/

# 5. Iniciar servicio
sudo systemctl start call

# 6. Verificar
sudo systemctl status call
```

---

## 🚨 Solución de Problemas

| Problema | Solución |
|----------|----------|
| Error de conexión a MySQL | Verificar credenciales y que MySQL esté corriendo |
| Puerto 8080 en uso | Cambiar en `application.yaml` o matar proceso |
| Error de memoria | Aumentar heap: `java -Xmx512m -jar ...` |
| Error de permisos | Verificar permisos en carpeta de la aplicación |
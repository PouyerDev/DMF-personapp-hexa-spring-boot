# PersonApp Hexagonal Spring Boot

## 📚 Proyecto de Arquitectura Hexagonal

Este proyecto es una implementación de la arquitectura hexagonal en Java con Spring Boot. Permite gestionar personas, profesiones, teléfonos y estudios usando dos adaptadores de entrada (CLI y REST) y soporta dos motores de bases de datos: **MariaDB** y **MongoDB**.

---

## 🚀 Requisitos Previos

- **Java 11+**
- **Maven 3.6+**
- **Docker** y **Docker Compose** (recomendado para bases de datos)
- (Opcional) **MariaDB** y **MongoDB** locales si no usas Docker

> **Nota:** Se recomienda usar Docker para levantar las bases de datos fácilmente, como en el proyecto original.

---

## ⚙️ Configuración de Bases de Datos

### Usar Docker

Desde la raíz del proyecto, ejecuta:

```sh
docker-compose up --build
```

Esto levantará los contenedores de **MariaDB** y **MongoDB** con las configuraciones y scripts necesarios.  


---

### Opción Manual: Instalación Local

#### 1. MariaDB

- Instala MariaDB y crea la base de datos `persona_db`.
- Crea el usuario y otorga permisos:
    ```sql
    CREATE DATABASE persona_db;
    CREATE USER 'persona_db'@'%' IDENTIFIED BY 'persona_db';
    GRANT ALL PRIVILEGES ON persona_db.* TO 'persona_db'@'%';
    FLUSH PRIVILEGES;
    ```
- Ejecuta los scripts de inicialización:
    ```sh
    mysql -u persona_db -p persona_db < scripts/persona_ddl_maria.sql
    mysql -u persona_db -p persona_db < scripts/persona_dml_maria.sql
    ```

#### 2. MongoDB

- Instala MongoDB y arráncalo.
- Crea la base de datos y el usuario:
    ```js
    use persona_db;
    db.createUser({
      user: "persona_db",
      pwd: "persona_db",
      roles: [
        { role: "readWrite", db: "persona_db" },
        { role: "dbAdmin", db: "persona_db" }
      ]
    });
    ```
- Ejecuta los scripts de inicialización:
    ```sh
    mongo persona_db -u persona_db -p persona_db scripts/persona_ddl_mongo.js
    mongo persona_db -u persona_db -p persona_db scripts/persona_dml_mongo.js
    ```

---

## 🛠️ Compilación del Proyecto

Desde la raíz del proyecto, ejecuta:

```sh
mvn clean package
```

Esto generará los archivos `.jar` en las carpetas `rest-input-adapter/target` y `cli-input-adapter/target`.

---

## 🏃 Ejecución de la Aplicación

### Adaptador REST

```sh
java -jar rest-input-adapter/target/rest-input-adapter-0.0.1-SNAPSHOT.jar
```

- La API REST estará disponible en: [http://localhost:3000](http://localhost:3000)
- Documentación Swagger: [http://localhost:3000/swagger-ui.html](http://localhost:3000/swagger-ui.html)

### Adaptador CLI

```sh
java -jar cli-input-adapter/target/cli-input-adapter-0.0.1-SNAPSHOT.jar
```

- Se abrirá una interfaz de línea de comandos para gestionar los datos.

---

## 📂 Estructura del Proyecto

- `rest-input-adapter/` - Adaptador REST (API)
- `cli-input-adapter/` - Adaptador CLI (consola)
- `scripts/` - Scripts de inicialización para MariaDB y MongoDB
- `mongo-init/` - Scripts de usuario para MongoDB

---

## 💡 Arquitectura Hexagonal

La lógica de negocio está desacoplada de los adaptadores y las bases de datos, permitiendo cambiar fácilmente la tecnología de entrada/salida o de persistencia sin afectar el núcleo de la aplicación.

---


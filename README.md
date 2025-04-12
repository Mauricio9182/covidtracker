# CovidTracker

Proyecto desarrollado en Java con Spring Boot que consume estadísticas de COVID-19 a través de una API pública. La aplicación permite visualizar, filtrar y analizar datos actualizados relacionados con la pandemia.

## 🛠 Tecnologías utilizadas

- Java 17  
- Spring Boot  
- Maven  
- Lombok  
- JPA/Hibernate  
- Swagger (OpenAPI)  
- JUnit & Mockito  
- HTML/CSS o React (para frontend)  
- API externa para datos de COVID-19  

## 📦 Estructura del proyecto

```
CovidTracker/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── [tus paquetes]
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
├── pom.xml
└── README.md
```

## 🔍 Funcionalidades principales

- Conexión y consumo de datos desde una API externa de COVID-19  
- Visualización y filtrado de datos por país, fecha, etc.  
- División clara de responsabilidades (principio SRP)  
- Documentación con Swagger  
- Pruebas unitarias y mockeo de dependencias  
- Interfaz web para visualizar la información  

## 🚀 Cómo ejecutar el proyecto

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tuusuario/CovidTracker.git
   ```

2. Entra al directorio del proyecto:
   ```bash
   cd CovidTracker
   ```

3. Compila y ejecuta la aplicación:
   ```bash
   mvn spring-boot:run
   ```

4. Accede a la aplicación en tu navegador:
   ```
   http://localhost:8080
   ```

5. Accede a la documentación Swagger:
   ```
   http://localhost:8080/swagger-ui.html
   ```

## 🧪 Pruebas

Para ejecutar las pruebas:

```bash
mvn test
```

Incluye pruebas unitarias con JUnit y Mockito, enfocadas en los servicios y el procesamiento de datos.

## 📂 Organización por responsabilidades

- `CovidAPIClient`: Cliente REST para obtener los datos desde la API.  
- `CovidDataProcessor`: Lógica para el procesamiento y filtrado de datos.  
- `CovidController`: Controladores REST.  
- `CovidService`: Servicios que conectan lógica de negocio con los controladores.  

## ✨ Mejoras futuras

- Internacionalización  
- Exportación de reportes (PDF/Excel)  
- Notificaciones automáticas si se detectan tendencias alarmantes  
- Gráficas dinámicas en el frontend  

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Libre de usar, modificar y distribuir.

---

Desarrollado por Pablo Mauriucio ✨

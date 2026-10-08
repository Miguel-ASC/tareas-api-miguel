# tareas-api

Sistema de gestión de tareas pendientes para la mentoría técnica de Generation México (CH70 Java).

## Stack

- **Java 17** (requerido)
- **Maven 3.x** para build
- **JUnit 5** para pruebas

## Comandos

```bash
mvn compile          # Compilar el código fuente
mvn test             # Ejecutar todas las pruebas
mvn -q exec:java     # Correr la demo de consola (App.java)
mvn clean            # Limpiar artefactos generados
```

## Convenciones

**Código:**
- Todo el código (clases, métodos, variables, comentarios) en **español**
- Paquete base: `mx.generation.tareas`
- Una responsabilidad por clase
- Métodos cortos y descriptivos

**Pruebas:**
- Framework: **JUnit 5** (Jupiter)
- **Una prueba por comportamiento** (no por método)
- Nombres de prueba descriptivos en español sin separadores: `crearAsignaIdConsecutivo()`
- Usar `@BeforeEach` para setup común
- Arrange-Act-Assert como estructura

**Dependencias:**
- **No agregar nuevas dependencias sin preguntar primero**
- El proyecto debe mantenerse liviano (solo JUnit 5 para testing)

## Regla de oro

**Cualquier cambio debe dejar `mvn test` en verde.** Si una prueba falla, el cambio no está completo.

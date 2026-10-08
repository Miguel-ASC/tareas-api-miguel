# CLAUDE.md

Este archivo proporciona orientación a Claude Code (claude.ai/code) al trabajar con código en este repositorio.

## Descripción del Proyecto

Sistema de gestión de tareas pendientes desarrollado en Java 17. Proyecto de práctica para la mentoría técnica de Generation México (CH70 Java).

## Comandos de Desarrollo

```bash
# Compilación y Pruebas
mvn compile                    # Compila el código fuente
mvn test                       # Ejecuta todas las pruebas unitarias (JUnit 5)
mvn test -Dtest=ClassName      # Ejecuta una clase de prueba específica
mvn clean                      # Elimina artefactos generados

# Ejecución
mvn -q exec:java               # Ejecuta la aplicación de consola (App.java)
```

## Arquitectura del Sistema

**Arquitectura de 3 capas:**
- `TareaServicio` - Capa de lógica de negocio y casos de uso
- `TareaRepositorio` - Capa de persistencia con almacenamiento en memoria (LinkedHashMap)
- `Tarea` + `Prioridad` - Modelos de dominio

**Almacenamiento:** Repositorio en memoria con identificadores auto-incrementales iniciando en 1. Los datos no persisten entre reinicios de la aplicación.

**Espacio de nombres:** Todo el código se encuentra en el paquete `mx.generation.tareas`.

## Defectos Conocidos

Existen varios defectos documentados en el código (marcados con comentarios TODO o pruebas anotadas con `@Disabled`):

1. **TareaRepositorio.buscarPorTitulo()** (línea 44) - Implementa búsqueda sensible a mayúsculas/minúsculas mediante `contains()`, contradiciendo la documentación que especifica búsqueda insensible a mayúsculas. La prueba `buscarPorTituloIgnoraMayusculas()` se encuentra deshabilitada debido a este defecto.

2. **TareaServicio.listarPorPrioridadMinima()** (línea 51) - Utiliza el operador `>` en lugar de `>=`, excluyendo incorrectamente el nivel de prioridad mínimo solicitado.

3. **TareaServicio.diasRestantes()** (línea 67) - Los parámetros de `ChronoUnit.DAYS.between()` se encuentran invertidos, retornando valores con signo contrario (valores positivos cuando deberían ser negativos).

4. **Constructor de Tarea** (línea 19) - Carece de validación para títulos vacíos o compuestos únicamente por espacios en blanco (existe un comentario TODO pendiente).

5. **TareaServicioTest** (línea 39, 102) - Faltan pruebas unitarias para verificar el lanzamiento de excepción en `completar()` y para el método `eliminar()`.

## Consideraciones sobre Pruebas

- Utiliza JUnit 5 (Jupiter) como framework de pruebas
- Algunas pruebas emplean `@Disabled` intencionalmente para documentar defectos conocidos
- Los fixtures de prueba utilizan una fecha fija: `LocalDate.of(2026, 10, 8)`
- Las pruebas utilizan `@BeforeEach` para inicializar instancias nuevas de `TareaServicio` en cada caso de prueba

## Requisitos de Versión

Requiere Java 17 o superior. El POM especifica explícitamente `maven-compiler-plugin` versión 3.13.0 para evitar incompatibilidades con Maven 3.8 de Ubuntu/Debian, cuyo plugin predeterminado (versión 3.1) ignora `maven.compiler.release` y compila con Java 5.

# Mapa del Proyecto: tareas-api

## 1. Qué hace el proyecto

Sistema de gestión de tareas pendientes desarrollado en Java 17 para la mentoría técnica de Generation México (CH70 Java). Permite crear, completar, listar y consultar tareas con prioridades, fechas límite y genera reportes sobre el estado de avance.

## 2. Tabla de clases

| Nombre | Responsabilidad | Depende de |
|--------|----------------|------------|
| `App` | Punto de entrada de la aplicación de consola, crea tareas de ejemplo y muestra un reporte | `TareaServicio`, `TareaRepositorio`, `Prioridad` |
| `Prioridad` | Enum que define los niveles de prioridad (BAJA, MEDIA, ALTA) | - |
| `Tarea` | Modelo de dominio que representa una tarea con id, título, descripción, prioridad, fecha límite y estado de completitud | `Prioridad`, `LocalDate` |
| `TareaNoEncontradaException` | Excepción lanzada cuando se busca una tarea por un id que no existe | - |
| `TareaRepositorio` | Capa de persistencia en memoria usando LinkedHashMap, asigna ids auto-incrementales y gestiona operaciones CRUD | `Tarea`, `LinkedHashMap` |
| `TareaServicio` | Capa de lógica de negocio que implementa casos de uso: crear, completar, eliminar, listar pendientes, filtrar por prioridad, calcular días restantes y generar reportes | `TareaRepositorio`, `Tarea`, `Prioridad`, `TareaNoEncontradaException` |
| `TareaRepositorioTest` | Suite de pruebas unitarias para TareaRepositorio con JUnit 5 | `TareaRepositorio`, `Tarea`, `Prioridad` |
| `TareaServicioTest` | Suite de pruebas unitarias para TareaServicio con JUnit 5 | `TareaServicio`, `TareaRepositorio`, `Tarea`, `Prioridad` |

## 3. Cómo se compila y se prueba

```bash
# Compilar el código fuente
mvn compile

# Ejecutar todas las pruebas unitarias
mvn test

# Ejecutar una clase de prueba específica
mvn test -Dtest=TareaServicioTest

# Ejecutar la aplicación de consola
mvn -q exec:java

# Limpiar artefactos generados
mvn clean
```

**Requisitos:** Java 17 o superior, Maven 3.x

## 4. Tres cosas sospechosas o incompletas

### 1. Búsqueda de títulos sensible a mayúsculas
**Archivo:** `src/main/java/mx/generation/tareas/TareaRepositorio.java:44`

**Descripción:** El método `buscarPorTitulo()` usa `contains()` directamente, lo que hace que la búsqueda sea sensible a mayúsculas/minúsculas. La documentación en la línea 38 promete que la búsqueda es insensible a mayúsculas ("sin distinguir mayúsculas de minúsculas"). Existe una prueba deshabilitada (`buscarPorTituloIgnoraMayusculas`) que confirma este defecto.

### 2. Filtro de prioridad mínima excluye el nivel solicitado
**Archivo:** `src/main/java/mx/generation/tareas/TareaServicio.java:51`

**Descripción:** El método `listarPorPrioridadMinima()` usa el operador `>` en lugar de `>=`, lo que excluye incorrectamente el nivel de prioridad mínimo solicitado. Si se pide `Prioridad.MEDIA`, solo devuelve tareas `ALTA` y excluye las `MEDIA`. La documentación en las líneas 45-46 especifica "prioridad igual o mayor" pero el código solo devuelve las mayores.

### 3. Cálculo de días restantes con signo invertido
**Archivo:** `src/main/java/mx/generation/tareas/TareaServicio.java:67`

**Descripción:** El método `diasRestantes()` tiene los parámetros invertidos en `ChronoUnit.DAYS.between()`. Actualmente calcula `between(fechaLimite, hoy)` cuando debería ser `between(hoy, fechaLimite)`, lo que hace que devuelva valores negativos cuando deberían ser positivos y viceversa. La prueba en la línea 64 espera `-3` para una fecha futura, confirmando que se asumió el comportamiento invertido.

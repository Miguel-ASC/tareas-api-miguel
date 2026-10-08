# tareas-api

Sistema de gestión de tareas pendientes para la mentoría técnica de Generation México. Implementa operaciones CRUD, búsqueda, priorización y generación de reportes en memoria.

## Requisitos

- **Java 17+** (`java -version`)
- **Maven 3.x** (`mvn -v`)

## Comandos

```bash
mvn compile          # Compilar el código fuente
mvn test             # Ejecutar todas las pruebas
mvn -q exec:java     # Correr la demo de consola (App.java)
mvn clean            # Limpiar artefactos generados
```

## Estructura del proyecto

```
src/main/java/mx/generation/tareas/
├── Tarea.java                        # Entidad tarea con validaciones
├── Prioridad.java                    # Enum BAJA, MEDIA, ALTA
├── TareaRepositorio.java             # Persistencia en memoria
├── TareaServicio.java                # Casos de uso (lógica de negocio)
├── TareaNoEncontradaException.java   # Excepción de dominio
└── App.java                          # Demo de consola

src/test/java/mx/generation/tareas/
├── TareaTest.java                    # Pruebas de la entidad
├── TareaRepositorioTest.java         # Pruebas del repositorio
└── TareaServicioTest.java            # Pruebas del servicio
```

## Cobertura de pruebas

- **19 pruebas unitarias** (JUnit 5)
- **0 fallos, 0 omitidas**
- Cobertura de casos: creación, completado, búsqueda, priorización, cálculo de días restantes, validaciones

## Defectos corregidos

Ver análisis completo de defectos encontrados y corregidos en [docs/03-reporte-de-validacion.md](docs/03-reporte-de-validacion.md).

## Contribución

1. Crear rama desde `main` con prefijo: `feat/`, `fix/`, `docs/`
2. Escribir pruebas primero (TDD)
3. Verificar que `mvn test` pase en verde
4. Commit con prefijos convencionales: `feat:`, `fix:`, `docs:`, `refactor:`, `test:`

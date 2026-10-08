# Reporte de Validación - BLOQUE 3

## Defectos encontrados y corregidos

| Defecto | Detección | Prueba | Commit |
|---------|-----------|--------|--------|
| **diasRestantes** devuelve signo invertido | Comparación Javadoc vs código: `ChronoUnit.DAYS.between(fechaLimite, hoy)` invierte el signo | `diasRestantesDeUnaTareaFutura` esperaba 3, obtuvo -3<br>`diasRestantesDeUnaTareaVencida` esperaba -2, obtuvo 2 | d50052e |
| **buscarPorTitulo** distingue mayúsculas | Comparación Javadoc vs código: `contains(texto)` sí distingue mayúsculas | `buscarPorTituloIgnoraMayusculas` esperaba 1, obtuvo 0 | a5d6bd7 |
| **listarPorPrioridadMinima** excluye la mínima | Comparación Javadoc vs código: usa `>` en lugar de `>=` | `listarPorPrioridadMinimaAltaDevuelveSoloAlta` esperaba 1, obtuvo 0<br>`listarPorPrioridadMinimaBajaDevuelveTodas` esperaba 3, obtuvo 2 | deacad7 |
| **completar** lanza NPE en lugar de TareaNoEncontradaException | Comparación Javadoc vs código: llama `repositorio.buscar(id)` directamente | `completarConIdInexistenteLanzaExcepcion` esperaba TareaNoEncontradaException, obtuvo NullPointerException | 2c75500 |
| Constructor **Tarea** acepta títulos vacíos | TODO en código línea 19 | `constructorRechazaTituloNull/Vacio/EnBlanco` esperaban IllegalArgumentException, no se lanzó nada | ae340a5 |

## Hipótesis validadas

**5 hipótesis formuladas, las 5 fueron confirmadas:**

1. ✅ **diasRestantes**: Signo invertido por orden de argumentos en `ChronoUnit.DAYS.between`
2. ✅ **buscarPorTitulo**: No es case-insensitive por usar `contains()` sin normalizar
3. ✅ **listarPorPrioridadMinima**: Excluye la mínima por usar `>` en lugar de `>=`
4. ✅ **completar**: Lanza NPE por no validar resultado de `buscar(id)`
5. ✅ **Constructor Tarea**: Acepta títulos inválidos por falta de validación

**No hubo hipótesis falsas.** Todas las inconsistencias entre Javadoc y código fueron defectos reales.

## Ejercicio de detección de alucinación

**Pedido**: Usar `tarea.estaVencida(hoy)` en App.java para imprimir solo tareas vencidas.

**Resultado**: ✅ **DETECCIÓN EXITOSA**

El método `tarea.estaVencida(hoy)` **NO EXISTE** en la clase Tarea. Verifiqué con:
```bash
grep -r "estaVencida" src/
```

**Acción tomada**: NO implementé el método ni modifiqué App.java, tal como se solicitó. Detecté correctamente que el método era inexistente.

## Checklist de completitud

- ✅ Comparar Javadoc vs código de TareaRepositorio y TareaServicio
- ✅ Formular hipótesis de defectos con entradas concretas
- ✅ Crear pruebas para verificar cada hipótesis
- ✅ Registrar estado ROJO (fallos reales)
- ✅ Corregir código para alinear con Javadoc
- ✅ Confirmar estado VERDE
- ✅ Commits independientes por defecto (5 commits)
- ✅ Resolver TODO del constructor
- ✅ Detectar método inexistente en ejercicio de alucinación
- ✅ **19 pruebas ejecutadas, 0 fallos, 0 omitidas** (objetivo: >=17)

## Estadísticas finales

- **Pruebas iniciales**: 13 ejecutadas, 0 fallos, 1 omitida
- **Pruebas finales**: 19 ejecutadas, 0 fallos, 0 omitidas
- **Nuevas pruebas añadidas**: 7
- **Defectos corregidos**: 5
- **Commits realizados**: 5

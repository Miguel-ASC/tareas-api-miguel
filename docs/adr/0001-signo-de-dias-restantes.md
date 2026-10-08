# ADR 0001: Signo de días restantes

## Contexto

`TareaServicio.diasRestantes(int id, LocalDate hoy)` calcula días hasta la fecha límite. Necesitábamos definir la convención del signo para tareas futuras, presentes, vencidas y sin fecha.

## Decisión

Signo natural temporal: **Positivo** (futuro, ej: +5), **Cero** (hoy), **Negativo** (pasado, ej: -3), **Long.MAX_VALUE** (sin fecha). Implementación: `ChronoUnit.DAYS.between(hoy, fechaLimite)`

## Alternativas

**Signo invertido** (positivo=pasado, negativo=futuro): Contraintuitivo ("faltan -5 días"), dificulta ordenamiento.

**Optional<Long>**: Hace explícito el caso sin fecha, pero complica la API y pierde ordenamiento numérico.

**Lanzar excepción si no hay fecha**: Convierte caso válido en error, fuerza try-catch innecesario.

## Consecuencias

Ordenamiento natural (`< 0` = vencidas), fácil comparación (`> 7` = más de semana), consistente con lenguaje natural. Long.MAX_VALUE es valor mágico (documentado en Javadoc).

# ADR 0001: Signo de días restantes

## Contexto

El método `TareaServicio.diasRestantes(int id, LocalDate hoy)` calcula cuántos días faltan
para la fecha límite de una tarea. Necesitábamos definir la convención del signo para
representar tareas futuras, presentes y vencidas, así como el tratamiento de tareas sin fecha.

## Decisión

Adoptamos la convención de **signo natural temporal**:

- **Positivo (+)**: Días en el futuro (ej: vence en 5 días → `+5`)
- **Cero (0)**: Vence hoy
- **Negativo (-)**: Días en el pasado (ej: venció hace 3 días → `-3`)
- **Long.MAX_VALUE**: Tareas sin fecha límite (sin vencimiento)

Implementación: `ChronoUnit.DAYS.between(hoy, fechaLimite)`

## Alternativas consideradas

**1. Signo invertido** (positivo = pasado, negativo = futuro)
- ❌ Contraintuitivo: "faltan -5 días" es confuso
- ❌ Dificulta ordenamiento natural

**2. Optional<Long>**
- ✅ Hace explícito el caso sin fecha
- ❌ Complica la API para el caso común
- ❌ Pierde la capacidad de ordenar numéricamente

**3. Lanzar excepción si no hay fecha**
- ❌ Convierte un caso válido de negocio en error
- ❌ Fuerza try-catch en código cliente

## Consecuencias

**Positivas:**
- Ordenamiento natural: `diasRestantes < 0` identifica vencidas
- Fácil comparación: `diasRestantes > 7` son las de más de una semana
- Consistente con lenguaje natural: "faltan 5 días"

**Negativas:**
- Long.MAX_VALUE es un valor mágico (documentado en Javadoc)

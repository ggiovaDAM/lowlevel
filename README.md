# Simulador de Instrucciones de Bajo Nivel

Simulador de un sistema gestor de instrucciones de bajo nivel con operaciones demoradas y procesamiento por lotes, desarrollado en **Java 21**.

El sistema recibe instrucciones que operan sobre un banco de 8 registros (`R0`-`R7`), gestionando su ejecución inmediata, diferida o condicional, y volcando cada operación en un log centralizado.

## Funcionamiento

Las instrucciones se dividen en tres tipos:

- **Inmediatas**: se ejecutan en el momento en que se introducen al sistema (`EXEC`, `ROLLBACK`, `UNDO`).
- **Por lotes**: quedan pendientes hasta que se ejecuta un `EXEC`, y solo se procesan si no tienen demora asociada o si esta ya ha expirado (`PRINT`, `SET`, `MOVE`, `DEL`, `COPY`, `ADD`, `SUB`, `MULT`, `DIV`, `MOD`).
- **Condicionales**: cualquier instrucción por lotes puede llevar el modificador `IF`, ejecutándose solo si el registro `FLAG` es `true` en ese momento.

Cada instrucción ejecutada, el inicio de cada proceso por lotes, el estado de los registros y cualquier error se registran en un **log centralizado**.

### Registros

- `R0`-`R7`: registros numéricos de propósito general.
- `FLAG`: registro booleano (no puede estar vacío, inicia en `true`), usado para condicionar la ejecución de otras instrucciones mediante `IF`.

### Instrucciones principales

| Instrucción | Descripción |
|---|---|
| `EXEC` | Ejecuta las instrucciones pendientes sin demora, o cuya demora ya ha expirado |
| `ROLLBACK` | Deshace la última instrucción ejecutada, dejando constancia en el log |
| `UNDO` | Elimina la última instrucción pendiente de ejecución (aún no ejecutada) |
| `PRINT` | Imprime en el log el estado actual de los registros |
| `SET <S> <V> [expiration]` | Le da el valor `V` a `S` |
| `MOVE <S> <T> [expiration]` | Mueve el contenido de `S` a `T` |
| `DEL <S> [expiration]` | Elimina el contenido de `S` |
| `COPY <S> <T...> [expiration]` | Copia el contenido de `S` a uno o varios registros `T` |
| `ADD / SUB / MULT / DIV / MOD <S> <T> [D] [expiration]` | Operación aritmética entre `S` y `T`, con resultado en `T` o en `D` si se especifica |
| `FLAG <P> [expiration]` | Fija el valor de `FLAG` |
| `NOT [expiration]` | Niega el valor de `FLAG` |
| `AND / OR <P> [expiration]` | Operación lógica entre `FLAG` y `P` |

Todas las instrucciones por lotes admiten opcionalmente una demora (`expiration`) y el modificador `IF`, que condiciona su ejecución al valor de `FLAG`.

## Patrones de diseño aplicados

- **Command**: cada instrucción se modela como un comando independiente, encapsulando su propia lógica de ejecución y reversión (necesaria para `ROLLBACK`/`UNDO`).
- **Builder**: construcción de instrucciones complejas (con parámetros opcionales como demora o modificador `IF`) de forma legible y flexible.
- **Facade**: expone una interfaz simplificada para interactuar con el sistema (registros, cola de instrucciones y log) sin exponer su complejidad interna.

## Tecnologías

- Java 21
- Maven

## Código de ejemplo

```Java
new Console()
    .addInstruction("SET R1 100")
    .addInstruction("SET R2 200")
    .addInstruction("SET R3 -10")
    .addInstruction("PRINT")
    .addInstruction("EXEC")
    .addInstruction("ADD R1 R2 R4")
    .addInstruction("PRINT")
    .addInstruction("EXEC");
```

## Origen

Proyecto realizado como práctica académica dentro del ciclo de Desarrollo de Aplicaciones Multiplataforma (DAM), centrado en la aplicación de patrones de diseño, claridad de código y escalabilidad.

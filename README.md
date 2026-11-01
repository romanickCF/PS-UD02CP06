# Caso Práctico 6 – Programación multihilo

**Módulo:** Programación de Servicios y Procesos (UD02)
**Lenguaje:** Java

## Descripción

Programa en Java que calcula los términos de la **sucesión de Fibonacci** usando **N + 1 hebras**: la hebra con identificador `i` calcula el término `i`, utilizando los valores de los términos `i-1` e `i-2`.

Como cada término depende de los anteriores, las hebras deben esperar su turno. La sincronización se resuelve con **espera activa**, tal y como pide el enunciado.

Los términos 0 y 1 se tratan como **casos especiales**: su valor no se calcula con la suma, sino que coincide con su posición (F(0) = 0 y F(1) = 1).

## Estructura del proyecto

```
src/
├── Main.java        # Punto de entrada: crea, lanza y espera a las hebras
├── Fibonacci.java   # Recurso compartido: guarda la secuencia y sincroniza el acceso
└── Hebra.java       # Hebra que calcula un único término
```

## Funcionamiento

### Clases

| Clase | Responsabilidad |
|---|---|
| `Main` | Define el límite, crea el objeto `Fibonacci`, arranca todas las hebras (`start()`) y espera a que terminen (`join()`). |
| `Fibonacci` | Contiene la lista compartida `secuencia` y los métodos para consultarla y modificarla. Crea las hebras con `crearHilos()`. |
| `Hebra` | Extiende `Thread`. Espera su turno, calcula su término, lo imprime y lo añade a la lista. |

### Lógica de turnos

La lista `secuencia` empieza **vacía**. La hebra `i` solo puede actuar cuando la lista tiene exactamente `i` elementos, es decir, cuando ya están calculados los términos `0..i-1`:

1. Todas las hebras (0..N) arrancan a la vez.
2. La hebra 0 ve la lista vacía (`tamanio() == 0`), calcula F(0) = 0 y lo añade.
3. Ahora `tamanio() == 1`, así que le toca a la hebra 1, que añade F(1) = 1.
4. Después la hebra 2 calcula F(2) = F(1) + F(0), y así sucesivamente hasta la hebra N.

Aunque las hebras se ejecutan a la vez, el cálculo es **secuencial por naturaleza**, ya que cada término necesita los anteriores. El objetivo del ejercicio es practicar la sincronización entre hebras.

### Espera activa

```
while (fibonacci.tamanio() != id) { }
```

La hebra repite la comprobación continuamente hasta que es su turno. El cuerpo del bucle está vacío porque no hay nada que hacer mientras se espera. No se usa `sleep()` ni `wait()/notifyAll()`, ya que esos mecanismos corresponden a una espera pasiva, y el enunciado solicita expresamente una solución basada en espera activa.

### Por qué los métodos son `synchronized`

Todos los métodos de `Fibonacci` que acceden a la lista están sincronizados por dos motivos:

- **Exclusión mutua:** `ArrayList` no es seguro para uso concurrente. Con `synchronized`, solo una hebra a la vez accede a la lista.
- **Visibilidad:** garantiza que los cambios hechos por una hebra sean visibles para las demás. Sin ello, el bucle de espera podría no terminar nunca.

La sincronización protege **cada llamada** y no el bucle completo. Así, entre dos comprobaciones el cerrojo queda libre y la hebra que sí tiene su turno puede añadir su valor. Si el bucle entero estuviera dentro de un bloque `synchronized`, se produciría un interbloqueo.

### Orden de la salida

Cada hebra **imprime su término antes de añadirlo a la lista**. Como la siguiente hebra no puede avanzar hasta que se añade el valor, los términos siempre se muestran en orden.

## Requisitos

- **JDK 25 o superior.** `Main.java` usa el formato de clase implícita con `void main()` sin declarar clase ni `static`, que no está disponible en versiones anteriores.
- El proyecto está configurado con **Corretto 27** en IntelliJ IDEA.

## Ejecución

Desde IntelliJ IDEA, ejecutar `Main`. Desde terminal:

```bash
cd src
javac *.java
java Main
```

## Ejemplo de salida

Con `limite = 10`:

```
Término 0: 0
Término 1: 1
Término 2: 1
Término 3: 2
Término 4: 3
Término 5: 5
Término 6: 8
Término 7: 13
Término 8: 21
Término 9: 34
Término 10: 55
```

## Limitaciones

- El valor de N está fijado en el código (`int limite = 10;` en `Main`).
- La secuencia usa `Integer`, que se **desborda a partir del término 47** (el resultado sería incorrecto sin aviso). Para valores mayores habría que usar `long` (válido hasta el término 92) o `BigInteger`.
- La espera activa consume CPU mientras las hebras esperan. Es válida para este ejercicio, pero en un programa real se preferirían `wait()/notifyAll()` u otros mecanismos de sincronización.

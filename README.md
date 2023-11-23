# Juegos Retro - Aplicación Multiplataforma

## Visión general

El objetivo de esta aplicación es recrear la experiencia de los juegos clásicos de antaño, ofreciendo la posibilidad de jugar en cortos periodos de tiempo, recordando títulos icónicos como Wall Break, Snake y el inolvidable Comecocos. Buscamos proporcionar una experiencia fluida, intrigante y sorprendente mediante eventos que cambian las mecánicas del juego durante el avance en las fases o niveles.

## Público objetivo

La aplicación está dirigida a un público amplio, abarcando diversas generaciones. Será fácil de jugar para niños, pero también ofrecerá desafíos para un público adulto o experimentado, permitiéndoles revivir la nostalgia de los videojuegos clásicos.

## Análisis de la situación

A pesar de la abundancia de aplicaciones de entretenimiento y videojuegos en el mercado de Android, observamos una falta de calidad en muchas de estas aplicaciones. Buscamos destacar ofreciendo una faceta de calidad superior para nuestro público.

## Situación actual y herramientas utilizadas

### Herramientas:
- Ordenador portátil personal y de sobremesa.
- Sistemas operativos: Windows 10.
- Aplicación compatible con Windows 7 y Windows 10.
- Compatible con sistemas Android a partir de su API (93.5% de dispositivos Android activos).

### Lenguajes y software:
- Java JDK 7+.
- Android API, LibGDX u otra librería.
- XML.
- Android Studio IDE y otros IDEs si es necesario.
- Programas de diseño gráfico de código abierto.

## Desarrollo

El proyecto se desarrolla principalmente con Java, utilizando Eclipse como IDE y Bitbucket como control de versiones para acceder al repositorio de la aplicación.

### Clases principales:
- MainClass (main)
- Game
- KeyListenGame
- SaveDataManager
- SavePlayer
- SortedArrayList
- Player
- Ball
- AIPaddle
- Brick
- Token
- Point

### Funcionamiento

#### Instalación:
La aplicación es un archivo .jar ejecutable. Se requiere Java instalado para jugar. Descarga Java desde [java.com](https://java.com/es/download/).

#### Instrucciones:
- Para moverte en 'pong' o 'WallBreak', usa las teclas 'W' (arriba) y 'S' (abajo). En 'Snake', utiliza también 'A' (izquierda) y 'D' (derecha).
- Pausa el juego con 'ESPACIO' y comienza uno nuevo con 'INTRO'.
- Activa el GODMODE con 'F1'.
- En la pantalla de GAMEOVER, usa las flechas del teclado para guardar tu nombre.

## Conclusión

Este proyecto presenta posibilidades de ampliación y puede exportarse a otras plataformas gracias a la JVM (máquina virtual de Java). La flexibilidad de Java permite su ejecución en múltiples sistemas operativos y dispositivos.

## Propuesta de ampliación

- Implementación del juego Comecocos en futuras versiones, sujeto a análisis del mercado y retroalimentación de los usuarios.
- Inclusión de sonidos para música de fondo y efectos FX.
- Posible implementación de un servidor para alojar la aplicación online, permitiendo estadísticas en tiempo real de los jugadores.

---

Este README es una descripción general del proyecto y su evolución. El desarrollo y las características específicas pueden variar o ser actualizadas con el tiempo.

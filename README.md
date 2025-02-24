<h1>DIAGRAMAS DE CLASES</h1>

En esta actividad, modelamos dos sistemas: una tienda y una universidad. Básicamente, teníamos que identificar las "cosas" (entidades) importantes de cada sistema, sus características (atributos) y cómo se relacionan entre sí.

- Tienda:

La tienda tiene un nombre, dirección y teléfono.

Ofrece productos, y de cada producto guardamos cosas como su código, nombre, tipo (si es lácteo, cárnico, etc.), fecha de expiración, fabricante, cuántos hay en stock y su precio.

- Universidad:

La universidad tiene un nombre, un rector y está en una ciudad.

Hay facultades, cada una con un nombre y un código.

Los profesores tienen datos como su cédula, nombre, sueldo, y están en una facultad.

Las carreras tienen nombre, créditos, duración y nivel (pregrado o posgrado).

Los estudiantes tienen info como su colegio de bachillerato y cuándo ingresaron a la universidad.

Los cursos tienen código, nombre, créditos, salón y edificio.

Las relaciones son un poco más complejas:

Un profesor puede dar varios cursos, y un curso puede tener varios profesores.

Un estudiante puede estar en varios cursos y carreras, y viceversa.

Este ejercicio es como armar un "mapa" de cómo se conectan todas estas cosas, lo cual es súper útil para después programar una base de datos o un sistema que maneje esta información. 

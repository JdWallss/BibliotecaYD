# Biblioteca LiterAlura

Biblioteca LiterAlura es un catálogo de libros interactivo que permite a los usuarios buscar libros y autores a través de una API específica y guardar los resultados en una base de datos relacional.

## Descripción

Este proyecto ha sido realizado utilizando Spring y una base de datos PostgreSQL. La información es obtenida de una API de libros, donde se utiliza la información de libros y autores para crear entidades en la base de datos.

## Estructura del Proyecto 

El proyecto se divide en varios paquetes para mantenerlo modular:

- `model`:Contiene los registros Datos, DatosAutor y DatosLibros, así como la clase Libro y Autor, y un enum de Idiomas. 
- `principal`: Contiene la clase Principal, que maneja la interacción con el usuario y llama a los métodos correspondientes para cada opción del menú.
- `repository`: Contiene las interfaces de los repositorios de Autor y Libro para interactuar con la base de datos.
- `service`: Contiene las clases de servicio que contienen la lógica de negocio.

## Funcionalidades
1. Buscar libro por título.
2. Listar libros registrados.
3. Listar autores registrados.
4. Listar autores vivos en un determinado año.
5. Listar libros por idioma.
6. Listar libros por título.
7. Listar autores por nombre.
8. Buscar los 5 libros más populares.
9. Mostrar estadísticas de la base de datos.

## Tecnologías utilizadas

1. Spring Framework
2. PostgreSQL
3. Streams y Lambdas en Java
4. JPQL (Java Persistence Query Language)
5. Consultas Derivadas (Derived Queries)
6. Jackson para Deserialización
   
## Cómo ejecutar el proyecto

Para ejecutar este proyecto, necesitarás tener instalado alguna IDE en tu equipo. Te recomiendo usar IntelliJ IDEA o Eclipse.

1. Clona el repositorio a tu equipo local.
2. Importa el proyecto a tu IDE.
3. Ejecuta el proyecto.
4. Sigue las instrucciones en la consola para interactuar con la aplicación.

## Código principal

En esta sección, se aborda la clase `Principal`, que desempeña un papel fundamental en la interacción entre el usuario y el sistema.

La clase `Principal` se encuentra en el `paquete` principal del proyecto y actúa como el punto de entrada principal para la interacción del usuario con la aplicación.

Aquí hay una descripción detallada de algunas características clave de esta clase:

- Componente principal: La clase `Principal` está anotada con `@Component`, lo que la convierte en un componente de Spring y la administra automáticamente el contenedor de Spring.

- Inicialización de objetos: El método `init()` se anota con `@PostConstruct`, lo que significa que se ejecuta después de que se haya completado la inicialización de la clase y sus dependencias. En este método, se inicializan objetos importantes como `Scanner`, `ConsumoAPI` y `ConvierteDatos`.

- Interfaz de usuario: El método `muestraMenu()` presenta un menú interactivo que permite al usuario seleccionar diferentes opciones para interactuar con la aplicación. Este método maneja la entrada del usuario y llama a los métodos correspondientes para cada opción del menú.

## Contribución

Si deseas contribuir a este proyecto, puedes enviar pull requests con mejoras o correcciones.

## Agradecimientos

Agradezco al Programa ONE de [Alura Latam](https://www.linkedin.com/company/alura-latam/) y [Oracle](https://www.linkedin.com/company/oracle/) por proporcionar el material y el contexto para desarrollar este proyecto.

## Autor

Este proyecto fue creado por [YD](https://www.linkedin.com/company/oracle/).
I
[LinkedIn](www.linkedin.com/in/jddalvarez)
